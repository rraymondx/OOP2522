package gameplay;

public class Herbivore extends LifeForm {
    private int health;
    private World world; 

    public Herbivore(Cell cell) {
        super(cell);
        this.health = 5;
    }

    public void setWorldInstance(World worldInstance) {
        world = worldInstance;
    }

    @Override
    public void action() {
        health--;
        if (health <= 0) {
            die(); 
            return;
        }
        // checks adjacent cells for plants
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; 
                
                int newX = cell.getX() + i;
                int newY = cell.getY() + j;
                
                if (world.isValidCoordinate(newX, newY)) {
                    Cell adjacentCell = world.getCell(newX, newY);
                    if (adjacentCell.getLifeForm() instanceof Plants) {
                        eatPlant(adjacentCell);
                        return; 
                    }
                }
            }
        }
    }

    private void eatPlant(Cell plantCell) {
        health = 5; 
        plantCell.clearCell(); 
    }
}
