package gameplay;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private LifeForm lifeForm;
    private boolean occupied = false;
    private Color backgroundColor;
    private int x;
    private int y; 

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = false;
        this.backgroundColor = Color.WHITE;
    }

    public void setLifeForm(LifeForm lifeForm) {
        this.lifeForm = lifeForm;
        this.occupied = true;
        if (lifeForm instanceof Plants) {
            this.backgroundColor = Color.GREEN;
        } else if (lifeForm instanceof Herbivore) {
            this.backgroundColor = Color.YELLOW;
        }
    }
    
    public LifeForm getLifeForm() {
        return lifeForm;
    }

    public void clearCell() {
        this.lifeForm = null;
        this.occupied = false;
        this.backgroundColor = Color.WHITE;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(backgroundColor);
        g.fillRect(x, y, width, height);
    }
    	
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
