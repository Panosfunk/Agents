/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Giannis
 */
public class Agent {
    
    int id;
    
    ArrayList<Message> messages;
    Stack<Move> moves;
    ArrayList<Move> validMoves;
    State state;
    boolean won;
    Pair position;                  //his position is a pair of coordinates
    String name;
    int[][] maze;
    
    public Agent(int id, String name) {
        this.id = id;
        this.name = name;
        won = false;
        position = new Pair(0, 0);
    }

    public Agent(int id, int x, int y, String name) {
        this.id = id;
        this.name = name;
        won = false;
        position = new Pair(x, y);
    }

    public boolean inBounds(int x, int y) {
        return (x >= 0 && y >= 0 && x <= maze.length && y <= maze[0].length); //mporei na einai anapoda
    }

    //returns true if the cell has been visited or is out of bounds
    public boolean hasObj(int obj, int x, int y) {
        return !inBounds(x, y) || maze[x][y] == obj;
    }

    public ArrayList<Move> getValidMoves(int x, int y, int offset, Move previous) {
        ArrayList<Move> validMoves = new ArrayList<>();

        int xUp = x;
        int yUp = y-offset;

        int xDown = x;
        int yDown = y+offset;

        int xRight = x+offset;
        int yRight = y;

        int xLeft = x-offset;
        int yLeft = y;

        if(!hasObj(Maze.PASS,xUp,yUp) && previous!=Move.DOWN)
            validMoves.add(Move.UP);

        if(!hasObj(Maze.PASS, xDown, yDown) && previous!=Move.UP)
            validMoves.add(Move.DOWN);

        if(!hasObj(Maze.PASS, xRight, yRight) && previous!=Move.LEFT)
            validMoves.add(Move.RIGHT);

        if(!hasObj(Maze.PASS, xLeft, yLeft) && previous!=Move.RIGHT)
            validMoves.add(Move.LEFT);

        return validMoves;
    }

    public Move randomMove(){
        Move previousMove = Move.NULL;
                
        if(!moves.isEmpty())
        {
            previousMove = moves.peek();
        }
        
        validMoves = getValidMoves(position.x, position.y, 1, previousMove);

        Random r = new Random();

        if(!validMoves.isEmpty())
        {
            int idx = r.nextInt(validMoves.size());
            moves.push(validMoves.get(idx));
            return validMoves.get(idx);
        }
        
        return null;
    }

    void setWin(boolean won)
    {
        this.won = won;
    }
    
    int getId() 
    {
        return id;
    }
    
    void addMessage(Message message)
    {
        messages.add(message);
    }
    
    void send(int id, Message message)
    {
        MessageManager.sendToAg(id, message);
    }
    
    void send(Agent agent, Message message)
    {
        MessageManager.sendToAg(agent.getId(), message);
    }
    
    void sendToAll(Message message)
    {
        MessageManager.sendToAll(message);
    }
    
}
