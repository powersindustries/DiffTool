package DiffTool.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IgnoreList {

    private static String[] ignoreList;

    public static void populateIgnoreList() {
        Path resourcesDir = Paths.get("src", "main", "resources");
        String ignoreListPath = resourcesDir.toAbsolutePath() + "/ignoreList.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader( ignoreListPath ))) {
            List<String> tempIgnoreList = new ArrayList<>();
            String currLine;
            while ((currLine = bufferedReader.readLine()) != null) {
                tempIgnoreList.add(currLine);
            }

            ignoreList = tempIgnoreList.toArray(new String[0]);

        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("Failed to find ignoreList.txt");
            System.exit(0);
        }
    }

    public static String[] getIgnoreList() {
        return ignoreList;
    }

}