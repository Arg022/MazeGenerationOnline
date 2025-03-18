package com.argeo.prosperi.mazeing.controller;

import com.argeo.prosperi.mazeing.models.Maze;
import com.argeo.prosperi.mazeing.service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maze")
public class MazeController {

    private final MazeService mazeService;

    @Autowired
    public MazeController ( MazeService mazeService ){
        this.mazeService = mazeService;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Server is running!";
    }

    @GetMapping("/Kruskal")
    public Maze getMazeKruskal(@RequestParam int width, @RequestParam int height, @RequestParam Long seed) {
        System.out.println("Received params: width=" + width + ", height=" + height + ", seed=" + seed + "/n" );

        return MazeService.generateKruskalMaze(width,height,seed);
    }

    @GetMapping("/dfs")
    public Maze getMazeDfs(@RequestParam int width, @RequestParam int height, @RequestParam Long seed) {
        System.out.println("Received params: width=" + width + ", height=" + height + ", seed=" + seed + "/n");

        return MazeService.generateDFSMaze(width,height,seed);
    }

    @GetMapping("/prim")
    public Maze getMazePrim(@RequestParam int width, @RequestParam int height, @RequestParam Long seed) {
        System.out.println("Received params: width=" + width + ", height=" + height + ", seed=" + seed + "/n");

        return MazeService.MazeGeneratorPrim(width,height,seed);
    }
}
