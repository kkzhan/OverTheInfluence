package main;

import entity.*;
import projectiles.AlcoholProjectile;
import projectiles.NeedleProjectile;
import projectiles.PillProjectile;
import projectiles.Projectile;
import tiles.*;

import javax.swing.*;
import javax.swing.Timer;
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
     * the game that this level is a part of
     */
    public Game thisGame;

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
    int FPS = 30;

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
    public UI ui;

    /**
     * sets assets for the level
     */
    public AssetSetter assetSetter = new AssetSetter(this);

    /**
     * the player entity
     */
    public Player player;

    /**
     * stores all entities in the game
     */
    public ArrayList<Entity> entities = new ArrayList<>();

    /**
     * stores all objects in the game
     */
    public ArrayList<Entity> objects = new ArrayList<>();

    /**
     * stores all NPCs in the game
     */
    public ArrayList<Entity> npcs = new ArrayList<>();

    /**
     * stores all projectiles in the game
     */
    public ArrayList<Projectile> projectiles = new ArrayList<>();

    /**
     * the game state
     */
    public int gameState;

    public final int PLAY_STATE = 1, PAUSE_STATE = 2, BARRIER_QUESTION_STATE = 3, SPEED_QUESTION_STATE = 4, QUESTION_STATE = 5, WIN_STATE = 6, LOSE_STATE = 7;

    /**
     * manages sound effects
     */
    Sound sound = new Sound();

    /**
     * the level number
     */
    public int levelNum;

    /**
     * determines whether or not to update certain information
     */
    public boolean updateOn = true;

    /**
     * the time and initial time for the level
     */
    public int time, startTime;

    /**
     * has this level been started
     */
    boolean started = false;

    /**
     * whether or not the level is complete
     */
    boolean completed = false;

    /**
     * whether or not the level was failed
     */
    boolean failed = false;


    /**
     * the constructor for the level class
     *
     * @param levelNum the level number
     */
    public Level(int levelNum, Game game) {
        thisGame = game;
        this.levelNum = levelNum;
        ui = new UI(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(keyIn);
        this.setFocusable(true);
        tm = new TileManager(this, "map" + levelNum);
        worldWidth = maxWorldCols * tileSize;
        worldHeight = maxWorldRows * tileSize;
        int speed = 0;
        if (levelNum == 1 || levelNum == 3 || levelNum == 4) {
            speed = 8;
        } else if (levelNum == 2) {
            speed = 5;
            time = FPS * 60; //60 seconds
            startTime = time;
        }
        player = new Player(this, keyIn, speed);
    }

    /**
     * sets up the level
     */
    public void setupLevel() {
        if(!started) {
            assetSetter.setObject();
            started = true;
        }
        gameState = PLAY_STATE;
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
        return completed;
    }

    public void sendProjectiles() {
        Projectile p = new Projectile(this);
        int rand = (int) (Math.random() * 3);
        if (rand == 0) {
            p = new PillProjectile(this);
        } else if (rand == 1) {
            p = new NeedleProjectile(this);
        } else if (rand == 2) {
            p = new AlcoholProjectile(this);
        }
        p.set((maxWorldCols - 3) * tileSize, (int)(Math.random() * (maxWorldRows - 2) * tileSize) + tileSize,"left",true,player);
    }

    /**
     * updates the game information from the player's movement
     */
    public void update() {
        if (gameState == PLAY_STATE) {
            if (levelNum == 2) {
                int debuffInterval = FPS * 30; //30 seconds
                if (startTime - time % 180 == 0) {
                    sendProjectiles();
                }
                if (time > 0) {
                    time--;
                    if((startTime - time) % debuffInterval == 0) {
                        gameState = SPEED_QUESTION_STATE;
                    } else if((startTime - time) % debuffInterval == debuffInterval - FPS * 2) {
                        ui.showMessage("Incoming speed debuff", 45);
                    }
                } else {
                    completed = true;
                    failed = true;
                    sound.stop();
                }
            }
            player.update();
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).update();
            }
        } else if (gameState == BARRIER_QUESTION_STATE || gameState == SPEED_QUESTION_STATE) {
            if (updateOn) {
                try {
                    ui.showMessage("Answer this question on the first try to cure all debuffs", 30);
                    Timer timer = new Timer(2000, e -> {
                        ui.showMessage("Answer this question on the second try to cure the latest debuff", 30);
                        Timer timer1 = new Timer(2000, e2 -> {
                            ui.showMessage("If you fail to answer the question you must endure the debuff", 30);
                            Timer timer2 = new Timer(2000, e3 -> {
                                ui.question.started = true;
                            });
                            timer2.setRepeats(false);
                            timer2.start();
                        });
                        timer1.setRepeats(false);
                        timer1.start();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    updateOn = false;
                } catch (Exception e) {
                }
            }
            ui.question();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        tm.draw(g2D); //draw tiles before player so that player can walk on top of tiles

        //add entities to list
        entities.add(player);

        for (int i = 0; i < objects.size(); i++) {
            entities.add(objects.get(i));
        }

        for (int i = 0; i < npcs.size(); i++) {
            entities.add(npcs.get(i));
        }

        for (int i = 0; i < projectiles.size(); i++) {
            entities.add(projectiles.get(i));
        }

        //sort entities by y position
        Collections.sort(entities, (e1, e2) -> {
            int result = Integer.compare(e1.worldY, e2.worldY);
            return result;
        });

        //draw entities
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(g2D);
        }
        //remove entities from list
        for (int i = 0; i < entities.size(); i++) {
            entities.remove(i);
        }

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
