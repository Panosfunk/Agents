/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giannis
 */
public class Message {
    
    int agId;
    State[] message;
    
    Message(int numOfAgents)
    {
        message = new State[numOfAgents];
        for (int i=0;i<numOfAgents;i++)
        {
            message[i] = State.EXPLORING;
        }
    }
    
    void setMessage(int id, State st){
        message[id] = st;
    }
    
    public State getMessage(int id)
    {
        return message[id];
    }
}
