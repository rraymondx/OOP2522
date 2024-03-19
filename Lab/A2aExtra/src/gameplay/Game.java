package gameplay;

import javax.swing.JFrame;

public class Game extends JFrame {
    private World world;
    private GamePanel gamePanel;
    private int cellSize;

    public Game(int width, int height, int cellSize) {
        this.cellSize = cellSize;
        this.world = new World(width, height);
        world.initializeCells();
        this.gamePanel = new GamePanel(world, cellSize);
        add(gamePanel);
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void startSimulation() {
        while (true) {
            world.update();
            gamePanel.repaint();
            try {
                Thread.sleep(2000);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int width = 25;
        int height = 25;
        int cellSize = 25;
        Game game = new Game(width, height, cellSize);
        game.startSimulation();
    }
    

}
