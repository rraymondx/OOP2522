import java.util.ArrayList;
import java.util.List;

public class Omnivore extends LifeForm implements CarniEdible{

    private World world;
    public int health;
    
    /**
     * Herbivore Constructor
     * 
     * @param cell Cell 
     * @param world World
     */
    public Omnivore(Cell cell, World world) {
        super(cell);
        health = 5;
        this.cell = cell;
        this.world = world;
    }

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
     * Moves the Omnivores to an empty cell spot
     * 
     */
    public void move() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        List<Cell> emptyNeighbors = new ArrayList<>();
        for(Cell neighbor: neighbors) {
            if(!(neighbor.getLife() instanceof Omnivore)) {
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
     * Checks to see if it is edible by Omnivores, and if it is, it eats it, and it clears the cell.
     * 
     * @param destination
     */
    private void moveToCell(Cell destination) {
        if (destination.getLife() instanceof OmniEdible) {
            // If the destination cell contains a plant, eat it and reset healths
            eat(destination);
        }
        // Move the herbivore to the destination cell
        destination.setLifeForm(this);
        cell.clearCell();
        cell = destination; // Update the reference to the current cell
        
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

        // Count the number of empty neighboring cells
        List<Cell> emptyNeighbors = new ArrayList<>();
        for(Cell neighbor : neighbors) {
            if (!neighbor.isFilled()) {
                emptyNeighbors.add(neighbor);
            }
        }
        
        // Check if there are at least 1 carnivore, 3 empty neighbouring cells, and 2 foodcells "plants"
        if (countOtherCarni(neighbors) >= 1 && emptyNeighbors.size() >= 3 && foodCount(neighbors) >= 1) {
            // Send seeds to a random empty neighboring cell
            int randomIndex = RandomGenerator.nextNumber(emptyNeighbors.size());
            Cell randomEmptyNeighbor = emptyNeighbors.get(randomIndex);
            // Create a new plant in the random empty neighboring cell
            randomEmptyNeighbor.setLifeForm(new Carnivore(randomEmptyNeighbor, world));
        }
    }

    /**
     * Determines how many neighbouring Carnivores there are.
     * 
     * @param neighbors Cell
     * @return int how many Carnivore neighbours it has
     */
    private int countOtherCarni(Cell[] neighbors) {
        int carniCount = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getLife() instanceof Carnivore) {
                carniCount++;
            }
        }
        return carniCount;
    }

    /**
     * Determines how many neighbouring "food" there are.
     * 
     * @param neighbors Cell
     * @return int how many "food" neighbours it has 
     */
    private int foodCount(Cell[] neighbors) {
        int foodCount = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getLife() instanceof OmniEdible) {
                foodCount++;
            }
        }
        return foodCount;
    }

}
