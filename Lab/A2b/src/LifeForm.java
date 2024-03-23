public abstract class LifeForm {
    Cell cell;
    protected boolean alive;
    public boolean hasAction;

    /**
     * LifeForm Constructor.
     * 
     * @param cell
     */
    public LifeForm(Cell cell) {
        this.hasAction = true;
        this.cell = cell;
        this.alive = true;
    }
    /**
     * Method to determine how each instance of lifeForm moves.
     */
    public abstract void behave();

    /**
     * Method to determine how each LifeForm "eats"
     */
    public abstract void eat(Cell eatCell);

    /**
     * 
     */
    public abstract void breed();

    /**
     * Method to clear the cell once any instance of lifeForm "dies"
     */
    public void die() {
        alive = false;
        cell.clearCell();
    }

    /**
     * Determines if an LifeForm is alive.
     * 
     * @return boolean alive or not
     */
    public boolean isAlive() {
        return alive;
    }

}
