package com.argeo.prosperi.mazeing.util;

import com.argeo.prosperi.mazeing.models.Maze;
import lombok.Getter;

import java.util.*;
@Getter
public class MazeGeneratorPrim {
    private Maze maze;
    private Random random;
    private int[][] map = maze.getMazeMap();

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public MazeGeneratorPrim(int width, int height, long seed) {
        this.maze = new Maze(width, height, "Prim", seed);
        this.random = new Random(seed);  // Imposta il seed
    }

    private boolean canBePassage(int x, int y) {
        int visitedCells = 0;
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + DX[dir], ny = y + DY[dir];
            if (isInBounds(nx, ny) && map[ny][nx] == 0) {
                visitedCells++;
            }
        }
        return visitedCells == 1;
    }

    private void addWalls(int x, int y, List<int[]> walls) {
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + DX[dir], ny = y + DY[dir];
            if (isInBounds(nx, ny) && map[ny][nx] == 1) {
                walls.add(new int[]{nx, ny});
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return x > 0 && y > 0 && x < maze.getWidth() - 1 && y < maze.getHeight() - 1;
    }

    public int[][] generate(int startX, int startY) {
        int[][] map = maze.getMazeMap();
        List<int[]> walls = new ArrayList<>();
        map[startY][startX] = 0;
        addWalls(startX, startY, walls);

        while (!walls.isEmpty()) {
            int[] current = walls.remove(random.nextInt(walls.size()));
            int cx = current[0], cy = current[1];
            if (canBePassage(cx, cy)) {
                map[cy][cx] = 0;
                addWalls(cx, cy, walls);
            }
        }
        maze.setMazeMap(map);
        return maze.getMazeMap();
    }
    //TODO: CORRECT PRIM E DSF ROUTES

}
