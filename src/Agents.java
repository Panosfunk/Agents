/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Giannis
 */


public class Agents {

    static Maze maze;
    static ArrayList<Agent> agentList;
    static Stack<String> nameStack;


    private static void initializeStack(){
        nameStack.add("images/agentOnTile.jpg");
        nameStack.add("images/jamesBondOnTile.jpg");
        nameStack.add("images/manInBlackOnTile.jpg");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int totalAgs = 2;
        int rows = 5;
        int cols = 5;
        
        Simulation sim = new Simulation(totalAgs, rows, cols);
        
        nameStack = new Stack<>();
        initializeStack();

        GUI gui = new GUI();

        gui.sim = sim;

    }

}
