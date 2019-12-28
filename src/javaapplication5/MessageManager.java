/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.util.ArrayList;

/**
 *
 * @author Giannis
 */
public class MessageManager {
    
    public static ArrayList<Agent> agents = new ArrayList<>();
    
    public static void sendToAll(Message message)
    {
        for(Agent agent : agents)
        {
            agent.addMessage(message);
        }
    }
    
    public static void sendToAg(int id, Message message)
    {
        for(Agent agent : agents)
        {
            if(id == agent.getId())
            {
                agent.addMessage(message);
            }
        }
    }
    
    public static void addAgent(Agent agent)
    {
        agents.add(agent);
    }
}
