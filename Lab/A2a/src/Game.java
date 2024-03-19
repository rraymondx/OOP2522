import javax.swing.JFrame;

public class Game extends JFrame {
    private World world;
    private GamePanel gamePanel;
    private int cellSize;

    /**
     * Game Constructor
     * 
     * @param width int width of the world
     * @param height int height of the world
     * @param cellSize int cellSize, pixel of each cell block
     */
    public Game(int width, int height, int cellSize) {
        this.cellSize = cellSize;
        this.world = new World(width, height);
        this.world.initializeCells();
        this.gamePanel = new GamePanel(world, cellSize);
        add(gamePanel);
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Executes the program
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        int width = 25;
        int height = 25;
        int cellSize = 20;
        Game game = new Game(width, height, cellSize);
    }

}
