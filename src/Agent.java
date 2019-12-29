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
    
    public Agent(int id) {
        this.id = id;
        won = false;
        position = new Pair();
    }
    public Agent(int id, int x, int y) {
        this.id = id;
        won = false;
        position = new Pair(x, y);
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
