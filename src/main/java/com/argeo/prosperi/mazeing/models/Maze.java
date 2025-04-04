package com.argeo.prosperi.mazeing.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Maze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int width, height;
    private int[][] mazeMap;
    private String type;
    private Long seed;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.mazeMap = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mazeMap[i][j] = 1; // 1 is a wall
            }
        }
    }

    public Maze(int width, int height, String type, long seed) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.seed = seed;
        this.mazeMap = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mazeMap[i][j] = 1;
            }
        }
    }
}
