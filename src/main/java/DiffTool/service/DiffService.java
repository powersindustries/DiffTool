package DiffTool.service;

import DiffTool.model.IgnoreList;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DiffService {
    private static Path path1;
    private static Path path2;

    public static void setPath1(String path) {
        path1 = Paths.get(path);
    }

    public static void setPath2(String path) {
        path2 = Paths.get(path);
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
        Map<String, Integer> root1Map = getPathMap(path1);
        Map<String, Integer> root2Map = getPathMap(path2);

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
            String path1File = path1 + file;
            String path2File = path2 + file;

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
        System.out.println("Extra Files.");
        System.out.println(Arrays.toString(extraFiles.toArray()));
        System.out.println();

        System.out.println("Missing Files.");
        System.out.println(Arrays.toString(missingFiles.toArray()));
        System.out.println();

        System.out.println("Files with different content.");
        System.out.println(Arrays.toString(fileChanges.toArray()));
        System.out.println();

        System.out.println("Fiels with same content.");
        System.out.println(Arrays.toString(sameFiles.toArray()));
    }

}
