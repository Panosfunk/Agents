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
    //Pair position;                  //his position is a pair of coordinates
    String name;
    
    public Agent(int id, String name) {
        this.id = id;
        this.name = name;
        won = false;
        //position = new Pair(0, 0);
    }

//    public Agent(int id, int x, int y, String name) {
//        this.id = id;
//        this.name = name;
//        won = false;
//        position = new Pair(x, y);
//    }

    public Move randomMove(){
        Move previousMove = Move.NULL;
                
        if(!moves.isEmpty())
        {
            previousMove = moves.peek();
        }

        Random r = new Random();

        if(!validMoves.isEmpty())
        {
            if(validMoves.size() >= 2)
            {
                for(int i=0;i<validMoves.size()-1;i++)
                {
                    moves.push(validMoves.get(i));
                    moves.push(Move.INTERSECTION);
                }
                
            }
            moves.push(validMoves.get(validMoves.size()-1));
            
            return moves.peek();
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
