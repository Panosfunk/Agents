import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

public class GUI extends JFrame {

    private static final int BORDER_GAP_SIZE = 30;  // different gap between the edges and the cards
    private static final int GRID_GAP_SIZE = 0;     // gap size between each grid cell

    Maze maze;
    ArrayList<Agent> agentList;

    private JFrame frame;
    private JPanel panel;
    private JLabel backgroundImages[][];
    private ImageIcon backside;

    private int numberOfRows;
    private int numberOfCols;
    private Stack<String> nameStack;


    private void setMapPanel(){
        panel = new JPanel();
        backgroundImages = new JLabel[numberOfRows][numberOfCols];
        for ( int i = 0; i < numberOfRows; i++){
            for ( int j = 0; j < numberOfCols; j++){
                backgroundImages[i][j] = new JLabel();
                backgroundImages[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(maze.getCell(i, j) == Maze.WALL) {
                    changeIcon(i, j,"images/wall.jpg");
                }else if ( maze.getCell(i, j) == Maze.PASS){
                    changeIcon(i, j, "images/tile.jpg");
                    for (Agent agent : agentList) {
                        if (i == agent.position.x && j == agent.position.y) {
                            changeIcon(i, j, agent.name);
                        }
                    }
                }else if ( maze.getCell(i, j) == Maze.EXIT){
                    changeIcon(i, j,"images/finish.jpg");
                }
                panel.add(backgroundImages[i][j]);

            }
        }

        panel.setLayout(new GridLayout(numberOfRows, numberOfCols, GRID_GAP_SIZE, GRID_GAP_SIZE));
        panel.setBorder(new EmptyBorder( BORDER_GAP_SIZE, BORDER_GAP_SIZE, BORDER_GAP_SIZE, BORDER_GAP_SIZE ) );
        panel.setBackground( new Color ( 164, 151, 142 ) );
    }

    private void changeIcon(int i, int j, String imgName){
        URL imageURL = getClass().getResource(imgName);
        if (imageURL != null) {
            backside = new ImageIcon(imageURL);
        }
        backgroundImages[i][j].setIcon(backside);
    }

    public GUI(){
        frame = new JFrame();
        frame.setTitle("Labyrinth");

        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        maze = Agents.maze;
        numberOfRows = maze.getRows();
        numberOfCols = maze.getCols();

        agentList = Agents.agentList;
        nameStack = Agents.nameStack;

        setMapPanel();

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        //sets the default state of the window to fullscreen
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

    }
}
