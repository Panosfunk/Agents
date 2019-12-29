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
        maze = new Maze( 15, 15);
        maze.generate();
//        maze.print();

        agentList = new ArrayList<>();
        nameStack = new Stack<>();
        initializeStack();

        agentList.add(new Agent(1, 0, 0, nameStack.pop()));
        System.out.println(agentList.get(0).name);
        agentList.add(new Agent(2, 2, 2, nameStack.pop()));
        System.out.println(agentList.get(1).name);

        GUI gui = new GUI();



    }

}
