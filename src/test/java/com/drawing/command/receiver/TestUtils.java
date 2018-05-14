package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Point;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
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

    public static Board getMockBoard(){
        Board board = new Board();
        board.initGrid(20,4);
        board.setGrid(mockGrid(22,6));
        return board;
    }

    private static String[][] mockGrid(int width, int height){
        String[][] grid = new String[height][width];
        Arrays.fill(grid[0], "-");
        Arrays.fill(grid[height-1], "-");

        for (int i = 1; i < height-1; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0 || j == width-1) {
                    grid[i][j] = "|";
                } else {
                    grid[i][j] = " ";
                }
            }
        }

        return grid;
    }

    public static List<List<Point<Integer,Integer>>> getMockPaths(){
        List<Point<Integer, Integer>> mockPath1 =
                Arrays.asList(new Point<>(16,1),
                        new Point<>(17,1),
                        new Point<>(18,1),
                        new Point<>(19,1),
                        new Point<>(20,1));

        List<Point<Integer, Integer>> mockPath2 =
                Arrays.asList(new Point<>(20,1),
                        new Point<>(20,2),
                        new Point<>(20,3));

        List<Point<Integer, Integer>> mockPath3 =
                Arrays.asList(new Point<>(16,3),
                        new Point<>(17,3),
                        new Point<>(18,3),
                        new Point<>(19,3),
                        new Point<>(20,3));

        List<Point<Integer, Integer>> mockPath4 =
                Arrays.asList(new Point<>(16,1),
                        new Point<>(16,2),
                        new Point<>(16,3));
        return Arrays.asList(mockPath1, mockPath2, mockPath3, mockPath4);
    }
}
