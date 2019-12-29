/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Giannis
 */
public class Agents {

    static Maze maze;
    static Agent agent1, agent2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        maze = new Maze( 5, 5);
        maze.generate();
//        maze.print();

        agent1 = new Agent(1);
        agent2 = new Agent(2, 0, 2);

        GUI gui = new GUI();



    }
    
}
