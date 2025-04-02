package com.argeo.prosperi.mazeing.service;

import com.argeo.prosperi.mazeing.models.Maze;
import com.argeo.prosperi.mazeing.util.MazeGeneratorDFS;
import com.argeo.prosperi.mazeing.util.MazeGeneratorKruskal;
import com.argeo.prosperi.mazeing.util.MazeGeneratorPrim;
import org.springframework.stereotype.Service;

@Service
public class MazeService {
    public Maze generateKruskalMaze(int width, int height, long seed ){
        MazeGeneratorKruskal kruskalMaze = new MazeGeneratorKruskal(width,height,seed);
        kruskalMaze.generate();
        return kruskalMaze.getMaze();
    }
    public Maze generateDFSMaze(int width, int height, long seed ){
        MazeGeneratorDFS dfsMaze = new MazeGeneratorDFS(width,height,seed);
        dfsMaze.generate(1,1);
        return dfsMaze.getMaze();
    }
    public Maze generatePrimMaze(int width, int height, long seed ){
        MazeGeneratorPrim primMaze = new MazeGeneratorPrim(width,height,seed);
        primMaze.generate(1,1);
        return primMaze.getMaze();
    }

}
