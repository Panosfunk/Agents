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
    HashMap<Integer,Pair> agPositions;
    ArrayList<Agent> agentList;
    
    Agent winner;
    
    Simulation(int totalAgs, int rows, int cols)
    {
        time = 0;
        maze = new Maze(rows, cols);
        agentList = Agents.agentList;
        agPositions = new HashMap<>();
    }



    void start()
    {
        //code for simulation here
    }
}
