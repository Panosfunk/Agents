/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

/**
 *
 * @author Giannis
 */
public class JavaApplication5 {

    static Maze maze;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        maze = new Maze( 2, 2);
        maze.generate();
        maze.print();

        GUI gui = new GUI();



    }
    
}
