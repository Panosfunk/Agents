/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Giannis
 */
public class Simulation {
    
    int winners;
    
    Maze maze;
    HashMap<Integer,Pair> agPositions;
    ArrayList<Agent> agentList;
    
    Simulation(int totalAgs, int rows, int cols)
    {
        maze = new Maze(rows, cols);
        agentList = Agents.agentList;
        agPositions = new HashMap<>();
        winners = 0;
        
        Random random = new Random();
        
        for(int i=0;i<totalAgs;i++)
        {
            Agent agent = new Agent(i, "AGENT_"+i);
            agentList.add(agent);
            
            int randX = 2 * random.nextInt(maze.getCols()/2);
            int randY = 2 * random.nextInt(maze.getRows()/2);
            
            agPositions.put(agent.id, new Pair(randX, randY));
        }
    }
    
    public void setAgValidMoves(int id, int offset) {
        Agent agent = agentList.get(id);
        Pair pos = agPositions.get(id);
        
        Move previous = agent.moves.peek();
        
        ArrayList<Move> validMoves = new ArrayList<>();

        int xUp = pos.x;
        int yUp = pos.y-offset;

        int xDown = pos.x;
        int yDown = pos.y+offset;

        int xRight = pos.x+offset;
        int yRight = pos.y;

        int xLeft = pos.x-offset;
        int yLeft = pos.y;

        if(!maze.hasObj(Maze.PASS,xUp,yUp) && previous!=Move.DOWN)
            validMoves.add(Move.UP);

        if(!maze.hasObj(Maze.PASS, xDown, yDown) && previous!=Move.UP)
            validMoves.add(Move.DOWN);

        if(!maze.hasObj(Maze.PASS, xRight, yRight) && previous!=Move.LEFT)
            validMoves.add(Move.RIGHT);

        if(!maze.hasObj(Maze.PASS, xLeft, yLeft) && previous!=Move.RIGHT)
            validMoves.add(Move.LEFT);

        Collections.shuffle(validMoves);
        
        agent.validMoves = validMoves;
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
        Agent agent = agentList.get(0);
        while(winners != agentList.size())
        {
            setAgValidMoves(agent.id, 1);
        }        
    }
}
