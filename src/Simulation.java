/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Giannis
 */
public class Simulation {
    
    int winners;
    private ImageIcon backside;
    
    Maze maze;
    HashMap<Integer,Pair> agPositions;
    ArrayList<Agent> agentList;
    Message messenger;

    Simulation(int totalAgs, int rows, int cols)
    {
        agentList = new ArrayList<>();
        maze = new Maze(rows, cols);
        maze.generate();
        //Team = new Agents();
        agPositions = new HashMap<>();
        winners = 0;
        
        Random random = new Random();
        
        for(int i = 0; i < totalAgs; i++)
        {
            Agent agent = new Agent(i, "AGENT_"+i, totalAgs);
            this.agentList.add(agent);
            
            int randX = 2 * random.nextInt(maze.getCols()/2);
            int randY = 2 * random.nextInt(maze.getRows()/2);
            
            agPositions.put(agent.id, new Pair(randX, randY));
        }
    }
    
    private void changeIcon(int i, int j, String imgName, JLabel[][] backgroundImages){
        URL imageURL = getClass().getResource(imgName);
        if (imageURL != null) {
            backside = new ImageIcon(imageURL);
        }
        backgroundImages[i][j].setIcon(backside);
        backgroundImages[i][j].setText("");
    }
    private void changeIcon(int i, int j, int id, String imgName, JLabel[][] backgroundImages){
        URL imageURL = getClass().getResource(imgName);
        if (imageURL != null) {
            backside = new ImageIcon(imageURL);
        }
        backgroundImages[i][j].setIcon(backside);
        backgroundImages[i][j].setText(String.valueOf(id));
        backgroundImages[i][j].setHorizontalTextPosition(JLabel.CENTER);
        backgroundImages[i][j].setVerticalAlignment(JLabel.CENTER);
    }
    
    public void setAgValidMoves(int id, int offset) {
        Agent agent = this.agentList.get(id);
        Pair pos = agPositions.get(id);
        boolean exit = false;
        
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

        if(!maze.hasObj(Maze.WALL,xUp,yUp) && previous!=Move.DOWN && maze.getMazeMessage(xUp, yUp) != -1){
            if(maze.getMazeMessage(xUp, yUp) != -2)
            {
                if(messenger.getMessage(maze.getMazeMessage(xUp, yUp)) == State.EXIT)
                {
                    exit = true;
                }
                validMoves.add(Move.UP);
            }
            else
            {
                validMoves.add(Move.UP);
            }
        }

        if(!maze.hasObj(Maze.WALL, xDown, yDown) && previous!=Move.UP && maze.getMazeMessage(xDown, yDown) != -1 && !exit){
            if(maze.getMazeMessage(xDown, yDown) != -2)
            {
                if(messenger.getMessage(maze.getMazeMessage(xDown, yDown)) == State.EXIT)
                {
                    validMoves.clear();
                    exit = true;
                }
                validMoves.add(Move.DOWN);
            }
            else
            {
                validMoves.add(Move.DOWN);
            }
        }

        if(!maze.hasObj(Maze.WALL, xRight, yRight) && previous!=Move.LEFT && maze.getMazeMessage(xRight, yRight) != -1 && !exit){
            if(maze.getMazeMessage(xRight, yRight) != -2)
            {
                if(messenger.getMessage(maze.getMazeMessage(xRight, yRight)) == State.EXIT)
                {
                    validMoves.clear();
                    exit = true;
                }
                validMoves.add(Move.RIGHT);
            }
            else
            {
                validMoves.add(Move.RIGHT);
            }
        }

        if(!maze.hasObj(Maze.WALL, xLeft, yLeft) && previous!=Move.RIGHT && maze.getMazeMessage(xLeft, yLeft) != -1 && !exit){
            if(maze.getMazeMessage(xLeft, yLeft) != -2)
            {
                if(messenger.getMessage(maze.getMazeMessage(xLeft, yLeft)) == State.EXIT)
                {
                    validMoves.clear();
                    exit = true;
                }
                validMoves.add(Move.LEFT);
            }
            else
            {
                validMoves.add(Move.LEFT);
            }
        }

        Collections.shuffle(validMoves);
        
        agent.validMoves = validMoves;
    }

    void moveAg(int id, Move move, JLabel[][] backgroundImages)
    {
        Pair pos = agPositions.get(id);
        changeIcon(pos.x,pos.y,"images/tile.jpg",backgroundImages);
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
        changeIcon(pos.x,pos.y, id,"images/bond.jpg",backgroundImages);
        
        agPositions.put(id, pos);
    }
    public void Intersection_Move(Agent agent, JLabel[][] backgroundImages)
    {
        Move back = agent.moves.pop();
        Pair pos = agPositions.get(agent.id);
        if(agent.moves.peek() == Move.INTERSECTION)
        {
            maze.setMaze(pos.x, pos.y, -1);
        }
        switch(back)
        {
            case UP:
                moveAg(agent.id,Move.DOWN, backgroundImages);
                break;
            case DOWN:
                moveAg(agent.id,Move.UP, backgroundImages);
                break;
            case LEFT:
                moveAg(agent.id,Move.RIGHT, backgroundImages);
                break;
            case RIGHT:
                moveAg(agent.id,Move.LEFT, backgroundImages);
                break;
        }
    }
    
    void messageUpdate()
    {
        for(int i=0;i<this.agentList.size();i++)
        {
            Agent agent = this.agentList.get(i);
            messenger.setMessage(i, agent.getState());
        }
    }
    
    
    void start(JLabel[][] backgroundImages)
    {
        //maze.print();
        messenger = new Message(this.agentList.size());
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
                      moveAg(agent.id, demove, backgroundImages);
                      if(agent.validMoves.size() > 1)
                      {
                          maze.setMaze(agPositions.get(agent.id).x, agPositions.get(agent.id).y, agent.getId());
                      }
                   }
                    else
                    {
                        agent.pop = true;
                        Intersection_Move(agent, backgroundImages);
                        agent.setState(State.DEAD_END);
                    }
                }
                else{
                    if(agent.moves.peek()!=Move.INTERSECTION)
                    {
                      Intersection_Move(agent, backgroundImages);
                    }
                    else{
                         agent.pop = false;
                         agent.moves.pop();
                         moveAg(agent.id, agent.moves.peek(), backgroundImages);
                         agent.setState(State.EXPLORING);
                        }
                    }
                 Pair pos = agPositions.get(agent.id);
                 if(pos.x == maze.getCols()-1 && pos.y == maze.getRows()-1)
                 {
                   winners++;
                   agent.setWin(true);
                   agent.setState(State.EXIT);
                 }
            }
            if(noOfAgsPlayed+1 == this.agentList.size())
            {
                noOfAgsPlayed = 0;
                for(int i=0;i<this.agentList.size()-1;i++)
                {
                    for(int j=i+1;j<this.agentList.size();j++)
                    {
                        if(agPositions.get(i).x == agPositions.get(j).x && agPositions.get(i).y == agPositions.get(j).y)
                        {
                            Agent agent1 = this.agentList.get(i);
                            Agent agent2 = this.agentList.get(j);
                            agent1.meetAgent(agent2.getId());
                            agent2.meetAgent(agent1.getId());
                        }
                    }
                }
                messageUpdate();
                try
                  { Thread.sleep(500); } 
                  catch(InterruptedException ex)
                  { Thread.currentThread().interrupt(); }
            }
            else
                noOfAgsPlayed++;
        }
        for(int i=0;i<this.agentList.size();i++)
        {
            Agent agent1 = this.agentList.get(i);
            System.out.println("Agent" + i);
            agent1.Sout();
        }
    }
}