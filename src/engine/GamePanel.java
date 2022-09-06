package src.engine;

// external
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

import src.entity.Player;
import src.object.SuperObject;
import src.tile.TileManager;



public class GamePanel extends JPanel implements Runnable {
    
    // Screen settings

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;      //48*48
    public final int maxScreenCol = 16; //40 is full
    public final int maxScreenRow = 12; //22 is about full
    public final int screenWidth = tileSize * maxScreenCol;    // 1920px at full
    public final int screenHeight = tileSize * maxScreenRow;   // 1056px at full

    // World Settings
    public final int maxWorldCol = 58;
    public final int maxWorldRow = 22;

    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    //fps
    int FPS = 60;

    // Inits
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);

    // 10 is obj limit
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); 
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame() {
        aSetter.setObject();
    }

    @Override
    public void run() {
        //Game loop, this is the core
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            // Switched from sleep to delta time due to my hatred of try catch. long live the deltatime king.
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer+= (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        // Tiles
        tileM.draw(g2);

        // Objects
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //Entities
        player.draw(g2);
        g2.dispose();

    }

}
