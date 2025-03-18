package com.argeo.prosperi.mazeing.util;

import com.argeo.prosperi.mazeing.models.Maze;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
@Getter
public class MazeGeneratorKruskal {
    private Maze maze;
    private Random random;

    // Vettori DX e DY per gestire direzioni
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public MazeGeneratorKruskal(int width, int height, long seed) {
        this.maze = new Maze(width, height, "Kruskal", seed);
        this.random = new Random(seed);  // Imposta il seed
    }

    // Classe UnionFind per gestire i gruppi di celle collegate
    static class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        public int find(int p) {
            if (p != parent[p]) parent[p] = find(parent[p]);
            return parent[p];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) parent[rootP] = rootQ;
        }
    }

    // Genera il labirinto con l'algoritmo di Kruskal
    public int[][] generate() {
        int[][] map = maze.getMazeMap();
        List<int[]> walls = new ArrayList<>();
        for (int y = 1; y < maze.getHeight(); y += 2) {
            for (int x = 1; x < maze.getWidth(); x += 2) {
                map[y][x] = 0; // Crea un percorso
                if (x + 2 < maze.getWidth()) walls.add(new int[]{x, y, x + 2, y});
                if (y + 2 < maze.getHeight()) walls.add(new int[]{x, y, x, y + 2});
            }

        }

        Collections.shuffle(walls, random);

        UnionFind uf = new UnionFind(maze.getWidth() * maze.getHeight());

        for (int[] wall : walls) {
            int x1 = wall[0], y1 = wall[1], x2 = wall[2], y2 = wall[3];
            int cell1 = y1 * maze.getWidth() + x1;
            int cell2 = y2 * maze.getWidth() + x2;

            // Se le celle sono in set separati, rimuovi la parete
            if (uf.find(cell1) != uf.find(cell2)) {
                uf.union(cell1, cell2);
                map[(y1 + y2) / 2][(x1 + x2) / 2] = 0; // Rimuovi la parete tra le celle
            }
        }
        maze.setMazeMap(map);
        return maze.getMazeMap();
    }
}
