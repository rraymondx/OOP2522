import java.util.ArrayList;
import java.util.List;

public class Herbivore extends LifeForm{

    private World world;
    public int health;
    
    /**
     * Herbivore Constructor
     * 
     * @param cell Cell 
     * @param world World
     */
    public Herbivore(Cell cell, World world) {
        super(cell);
        health = 5;
        this.cell = cell;
        this.world = world;
    }

    /**
     * Does all actions that a Herbivore should do
     */
    @Override
    public void behave() { 
        if(health == 0) {
            die();
        } else {
            move();
            hasAction = false;
        }
        health--;
    }

    /**
     * Moves the Herbivore to an empty cell spot
     * 
     */
    public void move() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        List<Cell> emptyNeighbors = new ArrayList<>();
        for(Cell neighbor: neighbors) {
            if(!(neighbor.getLife() instanceof Herbivore)) {
                emptyNeighbors.add(neighbor);
            }
        }

        // If there are empty neighboring cells, randomly pick one
        if (!emptyNeighbors.isEmpty()) {
            int randomIndex = RandomGenerator.nextNumber(emptyNeighbors.size());
            Cell randomEmptyNeighbor = emptyNeighbors.get(randomIndex);
            moveToCell(randomEmptyNeighbor);
        } 
    }

    /**
     * Checks to see if it is edible by herbivores, and if it is, it eats it, and it clears the cell.
     * 
     * @param destination
     */
    private void moveToCell(Cell destination) {
        if (destination.getLife() instanceof HerbEdible) {
            // If the destination cell contains a plant, eat it and reset healths
            eat(destination);
        }
        // Move the herbivore to the destination cell
        destination.setLifeForm(this);
        cell.clearCell();
        cell = destination; // Update the reference to the current cell
        
    }

    /**
     * Eats the plant.
     * 
     * @param eatingPlantCells
     */
    private void eat(Cell eatingPlantCells) {
        health = 5;
        eatingPlantCells.clearCell();
    }

}
