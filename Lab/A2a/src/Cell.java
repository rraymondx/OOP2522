import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private LifeForm lifeForm;
    private Color bgColour;
    private int x;
    private int y;
    private boolean filled;

    /**
     * Constructor for Cell
     * 
     * @param x
     * @param y
     */
    public Cell(int x, int y){
        lifeForm = null;
        this.x = x;
        this.y = y;
        this.filled = false;
        this.bgColour = Color.WHITE;
    }

    /**
     * Clears the cell
     */
    public void clearCell() {
        this.lifeForm = null;
        this.filled = false;
        this.bgColour = Color.WHITE;
    }

    /**
     * Populates the lifeForms and gives them colour depending on its type 
     * 
     * @param lifeForm
     */
    public void setLifeForm(LifeForm lifeForm) {
        this.lifeForm = lifeForm;
        this.filled = true;
        if(lifeForm instanceof Herbivore) {
            bgColour = Color.YELLOW;
        } else if(lifeForm instanceof Plants) {
            bgColour = Color.GREEN;
        }
    }

    /**
     * Returns the type of LifeForm
     * 
     * @return LifeForm
     */
    public LifeForm getLife(){
        return lifeForm;
    }

    /**
     * Getter for x-coords
     * 
     * @return int x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y-coords
     * 
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for Colour
     * 
     * @return Color the colours
     */
    public Color getBgColour() {
        return bgColour;
    }

    public boolean isFilled() {
        return filled;
    }

    /**
     * 
     * Draws.
     * 
     * @param g graphics
     * @param x x-coords
     * @param y y-coords
     * @param width width
     * @param height height
     */
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(bgColour);
        g.fillRect(x, y, width, height);
    }

}
