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
        this.world = world;
    }

    /**
     * Determines what the plants do.
     */
    @Override
    public void behave() {
        breed();
    }

    /**
     * Spreads seed to increase the plant growth
     */
    public void breed() {
        // Get neighboring cells
        Cell[] neighbors = world.getNeighbourCells(cell.getX(), cell.getY());

        // Count the number of empty neighboring cells
        List<Cell> emptyNeighbors = new ArrayList<>();
        for (Cell neighbor : neighbors) {
            if (!neighbor.isFilled()) {
                emptyNeighbors.add(neighbor);
            }
        }
        
        // Check if there are at least 3 empty neighboring cells, 2 plant neighbour. 
        if (countOtherPlants(neighbors) >= 2 && emptyNeighbors.size() >= 3 ) {
            // Send seeds to a random empty neighboring cell
            int randomIndex = RandomGenerator.nextNumber(emptyNeighbors.size());
            Cell randomEmptyNeighbor = emptyNeighbors.get(randomIndex);
            // Create a new plant in the random empty neighboring cell
            randomEmptyNeighbor.setLifeForm(new Plants(randomEmptyNeighbor, world));
        }
    }

    /**
     * Determines how many neighbouring plants there are.
     * 
     * @param neighbors Cell
     * @return int how many plant neighbours it has 
     */
    private int countOtherPlants(Cell[] neighbors) {
        int plantCount = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getLife() instanceof Plants) {
                plantCount++;
            }
        }
        return plantCount;
    }

    @Override
    public void eat(Cell eatCell) {
        // does nothing;
    }

}
