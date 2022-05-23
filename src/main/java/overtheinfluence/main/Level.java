package overtheinfluence.main;

import overtheinfluence.entity.*;
import overtheinfluence.tiles.*;
import javax.swing.*;
import java.awt.*;

public class Level extends JPanel implements Runnable {

    final int baseTileSize = 16; //16 x 16 pixels
    final int scale = 3; //scale tile by 3

    public final int tileSize = baseTileSize * scale; //48 x 48 pixels displayed on game screen
    public final int maxScreenCols = 16;
    public final int maxScreenRows = 12;
    public final int screenWidth = maxScreenCols * tileSize; //768
    public final int screenHeight = maxScreenRows * tileSize; //576

    public final int maxWorldCols; //change later so that it is assigned through constructor
    public final int maxWorldRows; //change later so that it is assigned through constructor
    public final int worldWidth;
    public final int worldHeight;

    int FPS = 60;

    TileManager tm;
    KeyInput keyIn = new KeyInput();
    Thread gameThread;
    public Player player = new Player(this, keyIn);

    public Level(int maxWorldCols, int maxWorldRows, String mapName) {
        this.maxWorldCols = maxWorldCols;
        this.maxWorldRows = maxWorldRows;
        worldWidth = maxWorldCols * tileSize;
        worldHeight = maxWorldRows * tileSize;
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyIn);
        this.setFocusable(true);
        tm = new TileManager(this, mapName);
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update(); //update information
                repaint(); //draw screen with updated information | invokes paintComponent
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        tm.draw(g2D); //draw tiles before player so that player can walk on top of tiles
        player.draw(g2D);

        g2D.dispose();
    }
}
