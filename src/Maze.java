/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class Maze {
    
    public static final int WALL = 0;
    public static final int PASS = 1;
    public static final int EXIT = 2;

    int maze[][];
    private int rows, cols;
    
    public Maze() {
        maze = null;
        rows = 0;
        cols = 0;
    }
    
    public Maze(int rows, int cols) {
        this.rows = 2 * rows + 1;
        this.cols = 2 * cols + 1;
        maze = new int[this.rows][this.cols];
    }
    
    public boolean inBounds(int x, int y)
    {
        return (x >= 0 && y >= 0 && x <= rows && y <= cols);
    }
    
    //returns true if the cell has been visited or is out of bounds
    public boolean hasObj(int obj, int x, int y) {
        if(!inBounds(x,y) || maze[x][y] == obj)
            return true;
        
        return false;
    }
    
    public boolean add(int obj, int x, int y) {
        if(inBounds(x,y)){
            maze[x][y] = obj;
            return true;
        }
        
        return false;
    }
    
    public ArrayList<Move> getValidMoves(int x, int y, int offset, Move previous) {
        ArrayList<Move> validMoves = new ArrayList<>();
        
        int xUp = x;
        int yUp = y-offset;
        
        int xDown = x;
        int yDown = y+offset;
        
        int xRight = x+offset;
        int yRight = y;
        
        int xLeft = x-offset;
        int yLeft = y;
        
        if(!hasObj(PASS,xUp,yUp) && previous!=Move.DOWN)
            validMoves.add(Move.UP);
        
        if(!hasObj(PASS, xDown, yDown) && previous!=Move.UP)
            validMoves.add(Move.DOWN);
        
        if(!hasObj(PASS, xRight, yRight) && previous!=Move.LEFT)
            validMoves.add(Move.RIGHT);
        
        if(!hasObj(PASS, xLeft, yLeft) && previous!=Move.RIGHT)
            validMoves.add(Move.LEFT);
        
        return validMoves;
    }
    
    public ArrayList<Pair> getValidPos(int x, int y, int offset) {
        ArrayList<Pair> validMoves = new ArrayList<>();
        
        int xUp = x;
        int yUp = y-offset;
        
        int xDown = x;
        int yDown = y+offset;
        
        int xRight = x+offset;
        int yRight = y;
        
        int xLeft = x-offset;
        int yLeft = y;
        
        if(!hasObj(PASS,xUp,yUp))
            validMoves.add(new Pair(xUp, yUp));
        
        if(!hasObj(PASS, xDown, yDown))
            validMoves.add(new Pair(xDown, yDown));
        
        if(!hasObj(PASS, xRight, yRight))
            validMoves.add(new Pair(xRight, yRight));
        
        if(!hasObj(PASS, xLeft, yLeft))
            validMoves.add(new Pair(xLeft, yLeft));
        
        return validMoves;
    }
    
    //checking available neighboring cells and picking one randomly
    public Pair randomMove(int x, int y) {
        ArrayList<Pair> validPos = getValidPos(x, y, 2);
        
        Random r = new Random();
        
        if(!validPos.isEmpty())
        {
            int idx = r.nextInt(validPos.size());
            return validPos.get(idx);
        }
        
        return null;
    }
    
    public void generate() {
        Stack<Pair> path = new Stack<>();
        
        Pair pos = new Pair(0,0);
        Pair nextPos = null;
        
        add(PASS, pos.x, pos.y); //add passage to initial state
        path.push(pos); //push initial state to stack
        
        while(!path.isEmpty())
        {
            pos = path.peek(); //set the top position from the stack as current
            nextPos = randomMove(pos.x, pos.y); //get a random neighboring cell
            
            //if there is an unchecked neighboring cell
            if(nextPos != null)
            {
                add(PASS, (pos.x+nextPos.x)/2, (pos.y+nextPos.y)/2); //visit middle cell
                add(PASS, nextPos.x, nextPos.y); //visit neighbor
                
                path.push(nextPos); //push neighbor to stack
            }
            else
            {
                // if every neighbor has been checked pop the top element
                path.pop();
            }
            
        };
        
        maze[rows-1][cols-1] = EXIT;
    }
    
    int getCell(int x, int y) {
        if(inBounds(x,y))
            return maze[x][y];
        
        return -1;
    }
    
    int getRows()
    {
        return rows;
    }
    
    int getCols()
    {
        return cols;
    }
    
    void print() {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                System.out.print(maze[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
