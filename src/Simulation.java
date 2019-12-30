/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Giannis
 */
public class Simulation {
    
    int time;
    
    Maze maze;
    HashMap<Integer,Pair> agPositions;
    ArrayList<Agent> agentList;
    
    Agent winner;
    
    Simulation(int totalAgs, int rows, int cols)
    {
        time = 0;
        maze = new Maze(rows, cols);
        agentList = Agents.agentList;
        agPositions = new HashMap<>();
        
        Random random = new Random();
        
        for(int i=0;i<totalAgs;i++)
        {
            Agent agent = new Agent(i, "AGENT_"+i);
            
            int randX = 2 * random.nextInt(maze.getCols()/2);
            int randY = 2 * random.nextInt(maze.getRows()/2);
            
            agPositions.put(agent.id, new Pair(randX, randY));
        }
    }

    void moveAg(int id, Move move)
    {
        Pair pos = agPositions.get(id);
        
        switch(move)
        {
            case UP:
                pos.y--;
                break;
            case DOWN:
                pos.y++;
                break;
            case LEFT:
                pos.x--;
                break;
            case RIGHT:
                pos.x++;
                break;
        }       
        
        agPositions.put(id, pos);
            
    }

    void start()
    {
        //code for simulation here
    }
}
