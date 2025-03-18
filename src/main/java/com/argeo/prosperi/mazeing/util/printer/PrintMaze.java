package com.argeo.prosperi.mazeing.util.printer;

import com.argeo.prosperi.mazeing.models.Maze;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class PrintMaze {
    private Maze maze;
    private String type;

    public void getMaze() {
        int height = maze.getHeight();
        int width = maze.getWidth();
        int[][] map =maze.getMazeMap();
        final String WALL = " \u001B[31m█\u001B[0m ";
        final String PATH = " \u001B[32m▒\u001B[0m ";
        System.out.println(type.toUpperCase());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == 1) {
                    System.out.print(WALL);
                } else {
                    System.out.print(PATH);
                }
            }
            System.out.println();
        }
        System.out.println("end");
        System.out.println();
    }
}
