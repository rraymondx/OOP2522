package gameplay;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private World world;
    private int cellSize;

    public GamePanel(World world, int cellSize) {
        this.world = world;
        this.cellSize = cellSize;
        int width = world.getWidth() * cellSize;
        int height = world.getHeight() * cellSize;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	    super.mouseClicked(e);
        	    world.update();
        	    repaint();
        	}
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }
    
    private void drawGrid(Graphics g) {
        for (int i = 0; i < world.getWidth(); i++) {
            for (int j = 0; j < world.getHeight(); j++) {
                Cell cell = world.getCell(i, j);
                int x = i * cellSize;
                int y = j * cellSize;
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
                cell.draw(g, x, y, cellSize, cellSize);
            }
        }
    }

}
