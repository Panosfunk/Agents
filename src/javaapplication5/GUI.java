package javaapplication5;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class GUI extends JFrame {

    private static final int BORDER_GAP_SIZE = 30;  // different gap between the edges and the cards
    private static final int GRID_GAP_SIZE = 0;     // gap size between each grid cell

    Maze maze;

    private JFrame frame;
    private JPanel panel;
    private JLabel backgroundImages[][];
    private ImageIcon backside;

    private int numberOfRows;
    private int numberOfCols;


    private void setMapPanel(){
        panel = new JPanel();
        backgroundImages = new JLabel[numberOfRows][numberOfCols];
        for ( int i = 0; i < numberOfCols; i++){
            for ( int j = 0; j < numberOfRows; j++){
                backgroundImages[i][j] = new JLabel();
                backgroundImages[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(maze.getCell(i, j) == Maze.WALL) {
                    String imgName = "images/wall.jpg";
                    URL imageURL = getClass().getResource(imgName);
                    if (imageURL != null) {
                        backside = new ImageIcon(imageURL);
                    }
                    backgroundImages[i][j].setIcon(backside);
                }else if ( maze.getCell(i, j) == Maze.PASS){
                    String imgName = "images/tile.jpg";
                    URL imageURL = getClass().getResource(imgName);
                    if (imageURL != null) {
                        backside = new ImageIcon(imageURL);
                    }
                    backgroundImages[i][j].setIcon(backside);
                }else if ( maze.getCell(i, j) == Maze.EXIT){
                    String imgName = "images/finish.jpg";
                    URL imageURL = getClass().getResource(imgName);
                    if (imageURL != null) {
                        backside = new ImageIcon(imageURL);
                    }
                    backgroundImages[i][j].setIcon(backside);

                }


                panel.add(backgroundImages[i][j]);
            }
        }

        panel.setLayout(new GridLayout(numberOfRows, numberOfCols, GRID_GAP_SIZE, GRID_GAP_SIZE));
        panel.setBorder(new EmptyBorder( BORDER_GAP_SIZE, BORDER_GAP_SIZE, BORDER_GAP_SIZE, BORDER_GAP_SIZE ) );
        panel.setBackground( new Color ( 164, 151, 142 ) );
    }


    public GUI(){
        frame = new JFrame();
        frame.setTitle("Labyrinth");

        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        maze = JavaApplication5.maze;
        numberOfRows = maze.getRows();
        numberOfCols = maze.getCols();


        setMapPanel();

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        //sets the default state of the window to fullscreen
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

    }
}
