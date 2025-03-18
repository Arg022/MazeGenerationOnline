package com.argeo.prosperi.mazeing.util;

import com.argeo.prosperi.mazeing.models.Maze;
import lombok.Getter;

import java.util.*;
@Getter
public class MazeGeneratorDFS {
    private Maze maze;
    private Random random;
    private int[][] map = maze.getMazeMap();

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public MazeGeneratorDFS(int width, int height, long seed) {
        this.maze = new Maze(width, height, "DFS", seed);
        this.random = new Random(seed);
    }

    private boolean isInBounds(int x, int y) {
        return x > 0 && y > 0 && x < maze.getWidth() && y < maze.getHeight();
    }

    public void generate(int startX, int startY) {
        map[startY][startX] = 0;
        List<Integer> directions = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(directions, random);

        for (int dir : directions) {
            int nx = startX + DX[dir] * 2;
            int ny = startY + DY[dir] * 2;

            if (isInBounds(nx, ny) && map[ny][nx] == 1) {
                map[startY + DY[dir]][startX + DX[dir]] = 0;
                generate(nx, ny);
            }

        }
        maze.setMazeMap(map);
    }
}
