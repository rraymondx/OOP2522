import java.util.ArrayList;
import java.util.List;

public class Plants extends LifeForm implements HerbEdible, OmniEdible {

    private World world;

    /**
     * Plants Constructor
     * 
     * @param cell Cell
     * @param world World
     */
    public Plants(Cell cell, World world) {
        super(cell);
        this.cell = cell;
        this.world = world;
    }

    /**
     * Determines what the plants do.
     */
    @Override
    public void behave() {
        if(!(hasBred)) {
            breed();
            hasBred = true;
        }
        hasAction = false;
    }

    /**
     * Spreads seed to increase the plant growth
     */
    public void breed() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        // Variables for how many plants are surrounding current plant
        int plantCount = 0;

        // Count the number of empty neighboring cells
        List<Cell> emptyNeighbors = new ArrayList<>();
        for (Cell neighbor : neighbors) {
            // Finding empty neighbors.
            if (!(neighbor.isFilled())) {
                emptyNeighbors.add(neighbor);
            }
            // Counts how many plants are near current plant.
            if(neighbor.isFilled() && neighbor.getLife() instanceof Plants) {
                plantCount++;
            }
        }
        
        // Check if there are at least 3 empty neighboring cells, 2 plant neighbour. 
        if (plantCount >= 2 && emptyNeighbors.size() >= 3 ) {
            // Send seeds to a random empty neighboring cell
            int randomIndex = RandomGenerator.nextNumber(emptyNeighbors.size());
            Cell randomEmptyNeighbor = emptyNeighbors.get(randomIndex);
            // Create a new plant in the random empty neighboring cell
            Plants p = new Plants(randomEmptyNeighbor, world);
            p.hasAction = false;
            randomEmptyNeighbor.setLifeForm(p);
        }
    }

    @Override
    public void eat(Cell eatCell) {
        // does nothing;
    }

}
