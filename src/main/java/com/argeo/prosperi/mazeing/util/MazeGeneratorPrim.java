package com.argeo.prosperi.mazeing.util;

import com.argeo.prosperi.mazeing.models.Maze;
import lombok.Getter;

import java.util.*;

@Getter
public class MazeGeneratorPrim {
    private Maze maze;
    private Random random;
    private int[][] map;

    public MazeGeneratorPrim(int width, int height, long seed) {
        this.maze = new Maze(width, height, "Prim", seed);
        this.random = new Random(seed);
        this.map = maze.getMazeMap(); // Inizializza `map` dopo aver inizializzato `maze`
    }

    public int[][] generate(int startX, int startY) {
        map[startY][startX] = 0;
        PriorityQueue<int[]> walls = new PriorityQueue<>(Comparator.comparingInt(a -> random.nextInt()));
        walls.add(new int[]{startX, startY});

        while (!walls.isEmpty()) {
            int[] wall = walls.poll();
            int x = wall[0];
            int y = wall[1];

            if (map[y][x] == 1) {
                map[y][x] = 0;

                for (int[] dir : new int[][]{{0, 2}, {2, 0}, {0, -2}, {-2, 0}}) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx > 0 && ny > 0 && nx < maze.getWidth() && ny < maze.getHeight() && map[ny][nx] == 1) {
                        walls.add(new int[]{nx, ny});
                        map[y + dir[1] / 2][x + dir[0] / 2] = 0;
                    }
                }
            }
        }
        maze.setMazeMap(map);
        return maze.getMazeMap();
    }
}