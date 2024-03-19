package gameplay;

public class World {
    private Cell[][] grid;
    private int width;
    private int height;
    private int turn;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        this.turn = 0;
    }
    
    public void incrementTurn() {
        turn++;
    }

    public void initializeCells() {
        RandomGenerator.reset();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Cell(i, j); 
                int randomNumber = RandomGenerator.nextNumber(100);
                if (randomNumber >= 85) {
                    grid[i][j].setLifeForm(new Herbivore(grid[i][j]));
                    grid[i][j].getLifeForm().setWorldInstance(this);
                }
                else if (randomNumber >= 65) {
                    grid[i][j].setLifeForm(new Plants(grid[i][j]));
                    grid[i][j].getLifeForm().setWorldInstance(this);
                }
                // Otherwise, leave the cell empty
            }
        }
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public void update() {
        incrementTurn();
        
        System.out.println(turn);
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].isOccupied() && grid[i][j].getLifeForm() != null) {
                    LifeForm lifeForm = grid[i][j].getLifeForm();
                    if (lifeForm.isAlive()) {
//                        lifeForm.action();
                        
                    }
                }
            }
        }
    }


    public boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
