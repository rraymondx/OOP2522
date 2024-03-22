import java.util.ArrayList;
import java.util.List;

public class World {
    private Cell[][] grid;
    private int width;
    private int height;
    private int turn;

    /**
     * Constructor for World.
     * 
     * @param width the width
     * @param height the height
     */
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        this.turn = 0;
    }
    
    /**
     * Just increments the turn.
     */
    public void incrementTurn() {
        turn++;
    }

    /**
     * Utilizing the "Random Generator" to initialize the starting position of the lifeForms.
     */
    public void initializeCells() {
        // RandomGenerator.reset();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                grid[i][j] = new Cell(i, j); 
                int randomNumber = RandomGenerator.nextNumber(100);
                if(randomNumber >= 80) {
                    grid[i][j].setLifeForm(new Herbivore(grid[i][j], this));
                } else if(randomNumber >= 60) {
                    grid[i][j].setLifeForm(new Plants(grid[i][j], this));
                } else if(randomNumber >= 50) {
                    grid[i][j].setLifeForm(new Carnivore(grid[i][j], this));
                } else if(randomNumber >= 45) {
                    grid[i][j].setLifeForm(new Omnivore(grid[i][j], this));
                }
                // Otherwise, leave the cell empty
            }
        }
    }
    /**
     * Updates the world and increments the turn to simulate the game's actions
     * 
     */
    public void update() {
        incrementTurn();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (grid[i][j].isFilled() && grid[i][j].getLife() != null) {
                    LifeForm lifeForm = grid[i][j].getLife();
                    if (lifeForm.isAlive() && lifeForm.hasAction) {
                        lifeForm.behave();
                    }
                }
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(grid[i][j].isFilled() && grid[i][j].getLife() != null) {
                    grid[i][j].getLife().hasAction = true; 
                }
            }
        }

    }
    
    /**
     * Getter for the width
     *  
     * @return int width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter for the height.
     * 
     * @return int height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for the position of the cell.
     * 
     * @param x int x-coords
     * @param y int y-coords
     * @return Cell type at the coords
     */
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    /**
     * get cell's neighbouring cells
     * 
     * @param x int x-coords
     * @param y int y-coords
     * @return int array
     */
    public Cell[] getNeighbourCells(int x, int y) {
        List<Cell> neighbours = new ArrayList<>();

        int[][] directions = {
            {-1, -1},   {0, -1},   {1, -1},
            {-1, 0},                {1, 0},
            {-1, 1},     {0, 1},    {1, 1}
        };

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if(newX >= 0 && newX < width && newY >= 0 && newY < height) {
                neighbours.add(grid[newX][newY]);
            }
        }        
    return neighbours.toArray(new Cell[0]);
    }

}
