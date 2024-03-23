import java.util.ArrayList;
import java.util.List;

/**
 * Herbivore Class
 */
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
            if(!(hasBred)) {
                breed();
                hasBred = true;
            }
        }
        hasAction = false;
        health--;
    }

    /**
     * Moves the Herbivore to an empty cell spot
     * 
     */
    public void move() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        // Add either non-filled space or herbEdible to (validSpot)
        List<Cell> validSpot = new ArrayList<>();
        for(Cell neighbor: neighbors) {
            if(!(neighbor.isFilled() || neighbor.getLife() instanceof HerbEdible)) {
                validSpot.add(neighbor);
            }
        }

        // if the neighboring cells are not empty, move to a random empty cell.
        if (!validSpot.isEmpty()) {
            int randomIndex = RandomGenerator.nextNumber(validSpot.size());
            Cell randomValidSpot = validSpot.get(randomIndex);
            if(randomValidSpot.isFilled() && randomValidSpot.getLife() instanceof HerbEdible) {
                eat(randomValidSpot);
            }
            // Move the herbivore to the destination cell
            randomValidSpot.setLifeForm(this);
            cell.clearCell();
            cell = randomValidSpot; // Update the reference to the current cell
        } 
    }

    /**
     * Eats the plant.
     * 
     * @param Cell eatingPlantCells 
     */
    @Override
    public void eat(Cell eatCell) {
        health = 5;
        eatCell.clearCell();
    }

    /**
     * Breeds Herbivores. 
     */
    public void breed() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        // Variable for how many herbivores are surrounding current herbivore
        int herbCount = 0;

        // Variable for how many "food" items surrounding current herbivore
        int foodCount = 0;

        // Count the number of empty neighboring cells, meaning that the cell (is not filled)
        List<Cell> emptyNeighbors = new ArrayList<>();

        // Loop through the neighbors arr
        for(Cell neighbor : neighbors) {
            // Finding empty neighbors
            if (!(neighbor.isFilled())) {
                emptyNeighbors.add(neighbor);
            }
            // Finding herbivores that are surrounding current herbivore
            if(neighbor.isFilled() && neighbor.getLife() instanceof Herbivore) {
                herbCount++;
            }
            // Finding herbEdibles that are surrounding current herbivore
            if(neighbor.isFilled() && neighbor.getLife() instanceof HerbEdible) {
                foodCount++;
            }
        }
        
        // Check if there are at least 1 herbivore, 2 empty neighbouring cells, and 2 foodcells "plants"
        // to perform breed function
        if (herbCount >= 1 && emptyNeighbors.size() >= 2 && foodCount >= 2) {
            // randomly breed at an empty cell.
            int randomIndex = RandomGenerator.nextNumber(emptyNeighbors.size());
            Cell randomEmptyNeighbor = emptyNeighbors.get(randomIndex);
            // Create a new Herivore in the random empty neighboring cell
            // Sets hasAction to false so they dont immediately perform their behave().
            Herbivore n = new Herbivore(randomEmptyNeighbor, world);
            n.hasAction = false;
            randomEmptyNeighbor.setLifeForm(n);
        }
    }
}
