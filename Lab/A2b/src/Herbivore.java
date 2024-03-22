import java.util.ArrayList;
import java.util.List;

public class Herbivore extends LifeForm implements CarniEdible, OmniEdible{

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
            breed(); // might be, most definitely busted
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
     * @param Cell eatingPlantCells 
     */
    @Override
    public void eat(Cell eatingPlantCells) {
        health = 5;
        eatingPlantCells.clearCell();
    }

    /**
     * Spreads seed to increase the plant growth
     */
    public void breed() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        // Count the number of empty neighboring cells
        List<Cell> emptyNeighbors = new ArrayList<>();
        for(Cell neighbor : neighbors) {
            if (!neighbor.isFilled()) {
                emptyNeighbors.add(neighbor);
            }
        }
        
        // Check if there are at least 1 herbivore, 2 empty neighbouring cells, and 2 foodcells "plants"
        if (countOtherHerbi(neighbors) >= 1 && emptyNeighbors.size() >= 2 && foodCount(neighbors) >= 2) {
            // Send seeds to a random empty neighboring cell
            int randomIndex = RandomGenerator.nextNumber(emptyNeighbors.size());
            Cell randomEmptyNeighbor = emptyNeighbors.get(randomIndex);
            // Create a new plant in the random empty neighboring cell
            randomEmptyNeighbor.setLifeForm(new Herbivore(randomEmptyNeighbor, world));
        }
    }

    /**
     * Determines how many neighbouring Herbivores there are.
     * 
     * @param neighbors Cell
     * @return int how many herbivore neighbours it has 
     */
    private int countOtherHerbi(Cell[] neighbors) {
        int herbCount = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getLife() instanceof Herbivore) {
                herbCount++;
            }
        }
        return herbCount;
    }

    /**
     * Determines how many neighbouring plants there are.
     * 
     * @param neighbors Cell
     * @return int how many plant neighbours it has 
     */
    private int foodCount(Cell[] neighbors) {
        int plantCount = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getLife() instanceof HerbEdible) {
                plantCount++;
            }
        }
        return plantCount;
    }

}
