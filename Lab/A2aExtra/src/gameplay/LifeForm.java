package gameplay;

public abstract class LifeForm {
    protected Cell cell;
    protected boolean alive;

    public LifeForm(Cell cell) {
        this.cell = cell;
        this.alive = true;
    }

    public abstract void action();

    public void die() {
        alive = false;
        cell.clearCell();
    }

    public boolean isAlive() {
        return alive;
    }

	public abstract void setWorldInstance(World world);
	
}
