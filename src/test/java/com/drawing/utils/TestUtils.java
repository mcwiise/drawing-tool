package com.drawing.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
