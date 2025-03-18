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

    @GetMapping("/kruskal")
    public Maze getMazeKruskal(@RequestParam int width, @RequestParam int height, @RequestParam Long seed) {
        System.out.println("Received params: width=" + width + ", height=" + height + ", seed=" + seed + "/n");

        Maze maze = mazeService.generateKruskalMaze(width,height,seed);

        printMaze.printMazeConsole(maze);
        return maze;
    }

    @GetMapping("/dfs")
    public Maze getMazeDfs(@RequestParam int width, @RequestParam int height, @RequestParam Long seed) {
        System.out.println("Received params: width=" + width + ", height=" + height + ", seed=" + seed + "/n");

        Maze maze = mazeService.generateDFSMaze(width,height,seed);
        printMaze.printMazeConsole(maze);
        return maze;
    }

    @GetMapping("/prim")
    public Maze getMazePrim(@RequestParam int width, @RequestParam int height, @RequestParam Long seed) {
        System.out.println("Received params: width=" + width + ", height=" + height + ", seed=" + seed + "/n");

        Maze maze = mazeService.MazeGeneratorPrim(width,height,seed);
        printMaze.printMazeConsole(maze);
        return maze;
    }
}
