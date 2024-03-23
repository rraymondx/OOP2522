import java.util.ArrayList;
import java.util.List;

public class Carnivore extends LifeForm implements OmniEdible{

    private World world;
    public int health;
    
    /**
     * Herbivore Constructor
     * 
     * @param cell Cell 
     * @param world World
     */
    public Carnivore(Cell cell, World world) {
        super(cell);
        health = 5;
        this.cell = cell;
        this.world = world;
    }

    /**
     * Does all actions that a Carnivore should do
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
     * Moves the Carnivore to an empty cell spot
     * 
     */
    public void move() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        List<Cell> validSpot = new ArrayList<>();
        for(Cell neighbor: neighbors) {
            if(!(neighbor.isFilled()) || neighbor.getLife() instanceof CarniEdible) {
                validSpot.add(neighbor);
            }
        }

        // if the neighboring cells are not empty, move to a random empty cell.
        if (!validSpot.isEmpty()) {
            int randomIndex = RandomGenerator.nextNumber(validSpot.size());
            Cell randomValidSpot = validSpot.get(randomIndex);
            if(randomValidSpot.isFilled() && randomValidSpot.getLife() instanceof CarniEdible) {
                eat(randomValidSpot);
            }
            randomValidSpot.setLifeForm(this);
            cell.clearCell();
            cell = randomValidSpot;
        } 
    }

    @Override
    public void eat(Cell eatCell) {
        health = 5;
        eatCell.clearCell();
    }

    @Override
    public void breed() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        // Variable for amount of carnivores surrounding current carnivores
        int carniCount = 0;

        // Variable for amount of food items surrounding current carnivores
        int foodCount = 0;

        // Count the number of empty neighboring cells
        List<Cell> emptyNeighbors = new ArrayList<>();
        for(Cell neighbor : neighbors) {
            // Finding empty neighbors
            if (!neighbor.isFilled()) {
                emptyNeighbors.add(neighbor);
            }
            // Finding amount of carnivores surrounding current one
            if(neighbor.isFilled() && neighbor.getLife() instanceof Carnivore) {
                carniCount++;
            }
            // Finding amount of food items surrounding current carnivore
            if(neighbor.isFilled() && neighbor.getLife() instanceof CarniEdible) {
                foodCount++;
            }
        }
        
        // Check if there are at least 1 carnivore, 3 empty neighbouring cells, and 2 foodcells "plants"
        if (carniCount >= 1 && emptyNeighbors.size() >= 3 && foodCount >= 2) {
            // Send seeds to a random empty neighboring cell
            int randomIndex = RandomGenerator.nextNumber(emptyNeighbors.size());
            Cell randomEmptyNeighbor = emptyNeighbors.get(randomIndex);
            // Create new Carnivore in empty neighboring cell
            Carnivore c = new Carnivore(randomEmptyNeighbor, world);
            c.hasAction = false;
            randomEmptyNeighbor.setLifeForm(c);
        }
    }
}
