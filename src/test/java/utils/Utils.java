package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static int countLinesInFile(String file) {
        int countLines = 0;
        try {
            countLines = (int)Files.lines(Path.of(file)).count();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return countLines;
    }

}
