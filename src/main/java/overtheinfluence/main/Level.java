package main;

import entity.*;
import objects.GameObject;
import tiles.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This is the superclass for levels that manages the overall structure of a singular level.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Game loop - Kevin Zhan</li>
 * <li>Camera movement - Kevin Zhan</li>
 * <li>Unlock camera at edge - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Level extends JPanel implements Runnable {

    /**
     * whether or not the game is complete
     */
    private boolean complete;

    /**
     * the usable tile size
     */
    public final int tileSize = 48; //48 x 48 pixels displayed on game screen
    /**
     * number of columns of tiles visible on screen
     */
    public final int maxScreenCols = 16;
    /**
     * number of rows of tiles visible on screen
     */
    public final int maxScreenRows = 12;
    /**
     * the width of the game screen
     */
    public final int screenWidth = maxScreenCols * tileSize; //768
    /**
     * the height of the game screen
     */
    public final int screenHeight = maxScreenRows * tileSize; //576

    /**
     * number of columns of tiles in the world map
     */
    public int maxWorldCols;
    /**
     * number of rows of tiles in the world map
     */
    public int maxWorldRows;
    /**
     * the width of the world map
     */
    public final int worldWidth;
    /**
     * the height of the world map
     */
    public final int worldHeight;

    /**
     * frame rate for game animation
     */
    int FPS = 60;

    /**
     * the tile manager that draws tiles for the world map
     */
    TileManager tm;
    /**
     * handles keyboard input for movement
     */
    KeyInput keyIn = new KeyInput(this);
    /**
     * the thread the game runs on
     */
    Thread gameThread;

    /**
     * the collision detector
     */
    public CollisionDetection collisionDetect = new CollisionDetection(this);

    /**
     * the user interface for the game
     */
    public UI ui = new UI(this);

    /**
     * sets assets for the level
     */
    public AssetSetter assetSetter = new AssetSetter(this);

    /**
     * the player entity
     */
    public Player player;

    /**
     * stores all objects in the game
     */
    public ArrayList<GameObject> objects = new ArrayList<GameObject>();

    /**
     * the game state (1 = playing, 2 = paused)
     */
    public int gameState;

    /**
     * manages sound effects
     */
    Sound sound = new Sound();

    /**
     * the level number
     */
    public int levelNum;


    /**
     * the constructor for the level class
     *
     * @param mapName the name of the map file to be loaded
     */
    public Level(String mapName) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(keyIn);
        this.setFocusable(true);
        tm = new TileManager(this, mapName);
        worldWidth = maxWorldCols * tileSize;
        worldHeight = maxWorldRows * tileSize;
        complete = false;
        int speed = 0;
        if(this instanceof Exploration || this instanceof Recovery || this instanceof RecoveryPart2) {
            speed = 5;
        } else if(this instanceof InnerDemons) {
            speed = 2;
        }
        player = new Player(this, keyIn, speed);
    }

    /**
     * sets up the level
     */
    public void setupLevel() {
        assetSetter.setObject();
        gameState = 1;
        if (levelNum == 1 || levelNum == 4) {
            playMusic(0);
        } else if (levelNum == 2 || levelNum == 3) {
            playMusic(1);
        }
    }

    /**
     * starts the thread for the game loop
     */
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

    /**
     * is the level complete?
     *
     * @return true if the level is complete
     */
    public boolean isComplete() {
        System.out.println("isComplete: " + complete);
        return complete;
    }

    /**
     * updates the game information from the player's movement
     */
    public void update() {
        if (gameState == 1) {
            player.update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        tm.draw(g2D); //draw tiles before player so that player can walk on top of tiles
        //object in between tile and player
        for (GameObject obj : objects) {
            obj.draw(g2D, this);
        }
        player.draw(g2D);
        ui.draw(g2D);

        g2D.dispose();
    }


    /**
     * plays background music
     *
     * @param i the index of the music to play
     */
    public void playMusic(int i) {
        if (i == -1) {
            if (levelNum == 1 || levelNum == 4) {
                playMusic(0);
            } else if (levelNum == 2 || levelNum == 3) {
                playMusic(1);
            }
        } else {
            sound.setFile(i);
            sound.play();
            sound.loop();
        }
    }

    /**
     * stops background music
     */
    public void stopMusic() {
        sound.stop();
    }

    /**
     * pauses background music
     */
    public void playSFX(int i) {
        sound.setFile(i);
        sound.play();
    }
}
