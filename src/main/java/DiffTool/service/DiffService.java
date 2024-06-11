package DiffTool.service;

import DiffTool.event.handler.DiffResultsHandler;
import DiffTool.event.handler.UIPathsSetHandler;
import DiffTool.model.DiffResultsModel;
import DiffTool.model.IgnoreList;
import DiffTool.model.PathsModel;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DiffService {

    private static PathsModel paths1Model = new PathsModel();
    private static PathsModel paths2Model = new PathsModel();
    private static DiffResultsModel diffResult = new DiffResultsModel();

    public static void setPath1(String path) {
        paths1Model.setPath(path);
    }

    public static void setPath2(String path) {
        paths2Model.setPath(path);
    }

    public static Path getPath1 () {
        return paths1Model.getPath();
    }

    public static Path getPath2 () {
        return paths2Model.getPath();
    }

    public static String getDiffResult () {
        return diffResult.getDiffResult();
    }

    public static void addPathsChangedListeners(UIPathsSetHandler pathsSetHandler) {
        paths1Model.addDataChangeListener(pathsSetHandler);
        paths2Model.addDataChangeListener(pathsSetHandler);
    }

    public static void addDiffResultsListener(DiffResultsHandler diffResultsHandler) {
        diffResult.addDataChangeListener(diffResultsHandler);
    }

    private static boolean containsIgnoreSubstrings(String string) {
        String[] ignoreList = IgnoreList.getIgnoreList();
        for (String ignoreSubstring : ignoreList) {
            if (string.contains(ignoreSubstring)) {
                return true;
            }
        }

        return false;
    }

    private static Map<String, Integer> getPathMap(Path path) {
        Map<String, Integer> output = new HashMap<>();

        try (Stream<Path> streamRoot1 = Files.walk(path, FileVisitOption.FOLLOW_LINKS)) {

            streamRoot1.forEach(entry -> {

                String pathString = path.toString();
                String keyString = entry.toString();

                if (pathString != keyString &&
                        !containsIgnoreSubstrings(keyString) &&
                        !Files.isDirectory(Paths.get(keyString))) {
                    int endIndex = pathString.length();
                    String keySubstring = keyString.substring(endIndex, keyString.length());

                    output.put(keySubstring, 1);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static void performDiff(){
        Map<String, Integer> root1Map = getPathMap(paths1Model.getPath());
        Map<String, Integer> root2Map = getPathMap(paths2Model.getPath());

        List<String> foundFiles = new ArrayList<>();
        List<String> extraFiles = new ArrayList<>();
        List<String> missingFiles = new ArrayList<>();
        List<String> fileChanges = new ArrayList<>();
        List<String> sameFiles = new ArrayList<>();

        // Get extraFiles.
        for (Map.Entry<String, Integer> entry : root1Map.entrySet()) {
            if (root2Map.containsKey(entry.getKey()) && !foundFiles.contains(entry.getKey())) {
                foundFiles.add(entry.getKey());
            } else {
                extraFiles.add(entry.getKey());
            }
        }

        // Get missingFiles.
        for (Map.Entry<String, Integer> entry : root2Map.entrySet()) {
            if (root1Map.containsKey(entry.getKey()) && !foundFiles.contains(entry.getKey())) {
                foundFiles.add(entry.getKey());
            } else {
                missingFiles.add(entry.getKey());
            }
        }

        // Iterate through found files and see if they match.
        for (String file : foundFiles) {
            String path1File = paths1Model.getPath() + file;
            String path2File = paths2Model.getPath() + file;

            try {
                List<String> path1Lines = Files.readAllLines(Paths.get(path1File));
                List<String> path2Lines = Files.readAllLines(Paths.get(path2File));

                if (!path1Lines.equals(path2Lines)){
                    fileChanges.add(file);
                } else {
                    sameFiles.add(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // Print results.
        String result = "";
        result += "Extra Files: \n";
        result += Arrays.toString(extraFiles.toArray()) + "\n\n";

        result += "Missing Files: \n";
        result += Arrays.toString(missingFiles.toArray()) + "\n\n";

        result += "Changed files: \n";
        result += Arrays.toString(fileChanges.toArray()) + "\n\n";

        result += "Unchanged files: \n";
        result += Arrays.toString(sameFiles.toArray());

        diffResult.setDiffResult(result);

    }

}
