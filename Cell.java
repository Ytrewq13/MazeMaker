
package mazemaker;

import javax.swing.*;
import java.util.ArrayList;

public class Cell {
    // For keeping track of self.
    private int[] coords; // Grid coords (0-->totalCells-1).
    // Bool values.
    public boolean[] walls = new boolean[4]; // Walls : [Top   , Right , Bottom, Left].
    public boolean solved; // Included in a solution?
    public boolean visited; // Visited in generation?
    // For solving.
    private Cell parent;
    // For calculating heuristics.
    private int hCost; // Manhattan.
    private int gCost; // Movement cost.
    private int fCost; // Total.
    
    public JPanel panel;
    // Keep track of how many cells there are. Might be useful.
    public static int numCells = 0;
    
    // Class constructor.
    public Cell(int[] coords, int pixels) {
        this.coords = coords;
        this.walls[0] = true;
        this.walls[1] = true;
        this.walls[2] = true;
        this.walls[3] = true;
        numCells++;
    } // End Constructor.
    public ArrayList<Cell> checkNeighbors() {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        Cell[][] grid = MazeMaker.grid;
        if (this.coords[0] > 0) {
            if (grid[this.coords[0]-1][this.coords[1]].visited == false) {
                neighbors.add(grid[this.coords[0]-1][this.coords[1]]);
            }
        }
        if (this.coords[0] < grid.length-1) {
            if (grid[this.coords[0]+1][this.coords[1]].visited == false) {
                neighbors.add(grid[this.coords[0]+1][this.coords[1]]);
            }
        }
        if (this.coords[1] > 0) {
            if (grid[this.coords[0]][this.coords[1]-1].visited == false) {
                neighbors.add(grid[this.coords[0]][this.coords[1]-1]);
            }
        }
        if (this.coords[1] < grid[0].length-1) {
            if (grid[this.coords[0]][this.coords[1]+1].visited == false) {
                neighbors.add(grid[this.coords[0]][this.coords[1]+1]);
            }
        }
        return neighbors;
    } // End Method.
    // Methods to set field values.
    // Needed for encapsulation - other classes must go through
    // these methods to modify data held by a Cell object.
    public void setHCost(Cell goal) {
        int xDiff = Math.abs(goal.coords[0] - this.coords[0]);
        int yDiff = Math.abs(goal.coords[1] - this.coords[1]);
        this.hCost = xDiff + yDiff;
    } // End Method.
    // Methods to return field values.
    // Needed for encapsulation (AKA *TRUE* OOP).
    public int[] getCoords() {
        return this.coords;
    } // End Method.
    public int getHCost() {
        return this.hCost;
    } // End Method.
} // End Class.
