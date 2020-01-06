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
    boolean pop;
    boolean[] meet;
    String name;
    
    public Agent(int id, String name, int totalAgs) {
        this.id = id;
        this.name = name;
        won = false;
        this.pop = false;
        moves = new Stack();
        moves.add(Move.NULL);
        meet = new boolean[totalAgs];
        for(int i=0; i < totalAgs; i++)
        {
            meet[i] = false;
        }
        state = State.EXPLORING;
    }

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
    
    void setState(State st)
    {
        this.state = st;
    }
    
    State getState()
    {
        return this.state;
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
    void meetAgent(int id)
    {
        this.meet[id] = true;
    }
    void Sout()
    {
        for(int i=0;i<4;i++)
            System.out.println(this.meet[i]);
    }
    
}
