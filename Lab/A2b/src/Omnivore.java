import java.util.ArrayList;
import java.util.List;

public class Omnivore extends LifeForm implements CarniEdible{

    private World world;
    public int health;
    static int count = 0;
    private int id = 0;
    
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
        id = count++;
    }

    /**
     * Performs actions for Omnivores
     */
    @Override
    public void behave() { 
        if(health == 0) {
            die();
            if (id==0) System.out.println("I am omnivore, I died!! ");
        } else {
            move();
            if(!(hasBred)) {
                breed();
                hasBred = true;
            }
        }
        if (id == 0) {
            System.out.println("i am at " + cell.getX() + " " + cell.getY());
        }
        hasAction = false;
        health--;
    }

    /**
     * Moves the Omnivores to an empty cell spot
     * 
     */
    public void move() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        
        List<Cell> validSpot = new ArrayList<>();
        for(Cell neighbor: neighbors) {
            if(!(neighbor.isFilled()) || neighbor.getLife() instanceof OmniEdible) {
                validSpot.add(neighbor);
            }
        }

        // if the neighboring cells are not empty, move to a random empty cell.
        if (!validSpot.isEmpty()) {
            int randomIndex = RandomGenerator.nextNumber(validSpot.size());
            Cell randomValidSpot = validSpot.get(randomIndex);
            if(randomValidSpot.isFilled() && randomValidSpot.getLife() instanceof OmniEdible) {
                eat(randomValidSpot);
            }
            randomValidSpot.setLifeForm(this);
            cell.clearCell();
            cell = randomValidSpot;
        } 
    }

    /**
     * eat function for Omnivores
     */
    @Override
    public void eat(Cell eatCell) {
        health = 5;
        eatCell.clearCell();
    }

    /**
     * Breed function for Omnivores
     */
    @Override
    public void breed() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        // Variable for amount of Omnivore surrounding current Omnivore
        int omniCount = 0;

        // Variable for amount of food items surrounding current Omnivore
        int foodCount = 0;

        // Count the number of empty neighboring cells
        List<Cell> emptyNeighbors = new ArrayList<>();
        for(Cell neighbor : neighbors) {
            // Finding empty cells
            if(!neighbor.isFilled()) {
                emptyNeighbors.add(neighbor);
            }
            // Finding amount of Omnivores surrounding current Omnivore
            if(neighbor.isFilled() && neighbor.getLife() instanceof Omnivore) {
                omniCount++;
            }
            // Finding amount of food items surrounding current Omnivore
            if(neighbor.isFilled() && neighbor.getLife() instanceof OmniEdible) {
                foodCount++;
            }
        }
        
        // Check if there are at least 1 omnivore, 3 empty neighbouring cells, and 1 food cells
        if (omniCount >= 1 && emptyNeighbors.size() >= 3 && foodCount >= 1) {
            // Send seeds to a random empty neighboring cell
            int randomIndex = RandomGenerator.nextNumber(emptyNeighbors.size());
            Cell randomEmptyNeighbor = emptyNeighbors.get(randomIndex);
            // Create new Omnivore in empty neighboring cell
            Omnivore o = new Omnivore(randomEmptyNeighbor, world);
            o.hasBred= false;
            randomEmptyNeighbor.setLifeForm(o);
        }
    }
}
