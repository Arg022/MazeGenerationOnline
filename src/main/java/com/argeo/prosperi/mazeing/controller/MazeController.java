package com.argeo.prosperi.mazeing.controller;

import com.argeo.prosperi.mazeing.models.Maze;
import com.argeo.prosperi.mazeing.service.MazeService;
import com.argeo.prosperi.mazeing.util.printer.PrintMaze;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maze")
public class MazeController {

    private final PrintMaze printMaze;

    private final MazeService mazeService;

    @Autowired
    public MazeController ( MazeService mazeService , PrintMaze printMaze){
        this.mazeService = mazeService;
        this.printMaze  = printMaze;
    }


    @GetMapping("/test")
    public String testEndpoint() {
        return "Server is running!";
    }

    @PostMapping("/kruskal")
    public int[][] getMazeKruskal(@RequestBody MazeRequest mazeRequest) {
        System.out.println("Received params: width=" + mazeRequest.getWidth() + ", height=" + mazeRequest.getHeight() + ", seed=" + mazeRequest.getSeed() + "/n");

        Maze maze = mazeService.generateKruskalMaze(mazeRequest.getWidth(), mazeRequest.getHeight(), mazeRequest.getSeed());
        printMaze.printMazeConsole(maze);
        return maze.getMazeMap();
    }

    @PostMapping("/dfs")
    public int[][] getMazeDfs(@RequestBody MazeRequest mazeRequest) {
        System.out.println("Received params: width=" + mazeRequest.getWidth() + ", height=" + mazeRequest.getHeight() + ", seed=" + mazeRequest.getSeed() + "/n");

        Maze maze = mazeService.generateDFSMaze(mazeRequest.getWidth(), mazeRequest.getHeight(), mazeRequest.getSeed());
        printMaze.printMazeConsole(maze);
        return maze.getMazeMap();
    }

    @PostMapping("/prim")
    public int[][] getMazePrim(@RequestBody MazeRequest mazeRequest) {
        System.out.println("Received params: width=" + mazeRequest.getWidth() + ", height=" + mazeRequest.getHeight() + ", seed=" + mazeRequest.getSeed() + "/n");

        Maze maze = mazeService.generatePrimMaze(mazeRequest.getWidth(), mazeRequest.getHeight(), mazeRequest.getSeed());
        printMaze.printMazeConsole(maze);
        return maze.getMazeMap();
    }
}
