/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Giannis
 */
public class Simulation {
    
    int time;
    
    Maze maze;
    ArrayList<Agent> agents;
    HashMap<Integer,Pair> agPositions;
    
    Agent winner;
    
    Simulation(int totalAgs, int rows, int cols)
    {
        time = 0;
        maze = new Maze(rows, cols);
        agents = new ArrayList<>();
        agPositions = new HashMap<>();
        
        for(int i=0;i<totalAgs;i++)
        {
            agents.add(new Agent(i));
        }
    }
    
    void start()
    {
        //code for simulation here
    }
}
