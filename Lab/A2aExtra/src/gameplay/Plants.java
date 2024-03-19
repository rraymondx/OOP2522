package gameplay;

public class Plants extends LifeForm implements HerbEdible {
    private int growth;
    private World world;

    public Plants(Cell cell) {
        super(cell);
        this.growth = 0;
    }

    public void setWorld(World worldInstance) {
        world = worldInstance;
    }

    @Override
    public void action() {
        growth++;
        if (growth >= 3) {
            spreadSeed(); 
            growth = 0; 
        }
    }

    	private void spreadSeed() {
    	    for (int i = -1; i <= 1; i++) {
    	        for (int j = -1; j <= 1; j++) {
    	            if (i == 0 && j == 0) continue; 
    	            int newX = cell.getX() + i;
    	            int newY = cell.getY() + j;
    	            if (world.isValidCoordinate(newX, newY)) {
    	                Cell adjacentCell = world.getCell(newX, newY);
    	                if (!adjacentCell.isOccupied()) {
    	                    adjacentCell.setLifeForm(new Plants(adjacentCell));
    	                    adjacentCell.getLifeForm().setWorldInstance(world);
    	                    return; 
    	                }
    	            }
    	        }
    	    }
    	}


	@Override
	public void setWorldInstance(World world) {
		
	}
}
