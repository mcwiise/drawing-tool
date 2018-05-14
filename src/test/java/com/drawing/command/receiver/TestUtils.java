package com.drawing.command.receiver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestUtils {

    public static String getExpected(String fileName){
        StringBuilder sb = new StringBuilder();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        return sb.append(new BufferedReader(new InputStreamReader(is))
                .lines()
                .collect(Collectors.joining("\n")))
                .toString();
    }

    public static String[][] mockGrid(){
        String[][] mockCanvas = new String[6][22];
        Arrays.fill(mockCanvas[0], "-");
        Arrays.fill(mockCanvas[5], "-");

        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 22; j++) {
                if (j == 0 || j == 21) {
                    mockCanvas[i][j] = "|";
                } else {
                    mockCanvas[i][j] = " ";
                }
            }
        }

        return mockCanvas;
    }
}
