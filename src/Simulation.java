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
    //Agents Team;
    Simulation(int totalAgs, int rows, int cols)
    {
        agentList = new ArrayList<Agent>();
        maze = new Maze(rows, cols);
        maze.generate();
        //Team = new Agents();
        agPositions = new HashMap<>();
        winners = 0;
        
        Random random = new Random();
        
        for(int i=0;i<totalAgs;i++)
        {
            Agent agent = new Agent(i, "AGENT_"+i);
            this.agentList.add(agent);
            
            int randX = 2 * random.nextInt(maze.getCols()/2);
            int randY = 2 * random.nextInt(maze.getRows()/2);
            
            
            
            agPositions.put(agent.id, new Pair(randX, randY));
        }
    }
    
    public void setAgValidMoves(int id, int offset) {
        Agent agent = this.agentList.get(id);
        Pair pos = agPositions.get(id);
        
        Move previous = agent.moves.peek();
        
        ArrayList<Move> validMoves = new ArrayList<>();

        int xUp = pos.x-offset;
        int yUp = pos.y;

        int xDown = pos.x+offset;
        int yDown = pos.y;

        int xRight = pos.x;
        int yRight = pos.y+offset;

        int xLeft = pos.x;
        int yLeft = pos.y-offset;

        if(!maze.hasObj(Maze.WALL,xUp,yUp) && previous!=Move.DOWN)
            validMoves.add(Move.UP);

        if(!maze.hasObj(Maze.WALL, xDown, yDown) && previous!=Move.UP)
            validMoves.add(Move.DOWN);

        if(!maze.hasObj(Maze.WALL, xRight, yRight) && previous!=Move.LEFT)
            validMoves.add(Move.RIGHT);

        if(!maze.hasObj(Maze.WALL, xLeft, yLeft) && previous!=Move.RIGHT)
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
                pos.x--;
                System.out.println("UP ("+pos.x+","+pos.y+")");
                break;
            case DOWN:
                pos.x++;
                System.out.println("DOWN ("+pos.x+","+pos.y+")");
                break;
            case LEFT:
                pos.y--;
                System.out.println("LEFT ("+pos.x+","+pos.y+")");
                break;
            case RIGHT:
                pos.y++;
                System.out.println("RIGHT ("+pos.x+","+pos.y+")");
                break;
        }       
        
        agPositions.put(id, pos);
    }
    public void Intersection_Move(Agent agent)
    {
        Move back = agent.moves.pop();
        Pair pos = agPositions.get(agent.id);            
        switch(back)
        {
            case UP:
                moveAg(agent.id,Move.DOWN);
                break;
            case DOWN:
                moveAg(agent.id,Move.UP);
                break;
            case LEFT:
                moveAg(agent.id,Move.RIGHT);
                break;
            case RIGHT:
                moveAg(agent.id,Move.LEFT);
                break;
        }
    }
    void start()
    {
        //maze.print();
        int noOfAgsPlayed=0;      
        while(winners != this.agentList.size())
        {
            Agent agent = this.agentList.get(noOfAgsPlayed);
            System.out.println(agent.name);
            if(!agent.won)
            {
                if(!agent.pop)
                {
                   setAgValidMoves(agent.id, 1);
                   Move demove = agent.randomMove();
                   if(demove != null)
                   {
                      moveAg(agent.id, demove);
                   }
                    else
                    {
                      agent.pop = true;
                      Intersection_Move(agent);
                    }
                }
                else{
                    if(agent.moves.peek()!=Move.INTERSECTION)
                    {
                      Intersection_Move(agent);
                    }
                    else{
                         agent.pop = false;
                         agent.moves.pop();
                         moveAg(agent.id, agent.moves.peek());
                        }
                    }
                 Pair pos = agPositions.get(agent.id);
                 if(pos.x == maze.getCols()-1 && pos.y == maze.getRows()-1)
                 {
                   winners++;
                   agent.setWin(true);
                 }
            }
            if(noOfAgsPlayed + 1 == agentList.size())
            {
                noOfAgsPlayed = 0;
                for(int i = 0; i < agentList.size() - 1; i++)
                {
                    for(int j = i + 1; j < agentList.size(); j++)
                    {
                        if(agPositions.get(i).x == agPositions.get(j).x && agPositions.get(i).y == agPositions.get(j).y)
                        {
                            Agent agent1 = agentList.get(i);
                            Agent agent2 = agentList.get(j);
                            agent1.meetAgent(agent2.getId());
                            agent2.meetAgent(agent1.getId());
                        }
                    }
                }
            }
            else
                noOfAgsPlayed++;
        }
        for(int i=0;i<this.agentList.size();i++)
        {
            Agent agent1 = this.agentList.get(i);
            System.out.println("Agent" + i);
//            agent1.Sout();
        }
    }
}