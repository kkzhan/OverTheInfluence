package main;

import entity.*;
import projectiles.*;
import tiles.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
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
    public final Game thisGame;

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
    public final int FPS = 30;

    /**
     * the tile manager that draws tiles for the world map
     */
    final TileManager tm;
    /**
     * handles keyboard input for movement
     */
    public final KeyInput keyIn = new KeyInput(this);
    /**
     * the thread the game runs on
     */
    Thread gameThread;

    /**
     * the collision detector
     */
    public final CollisionDetection collisionDetect = new CollisionDetection(this);

    /**
     * the user interface for the game
     */
    public final UI ui;

    /**
     * sets assets for the level
     */
    public final AssetSetter assetSetter = new AssetSetter(this);

    /**
     * the player entity
     */
    public final Player player;

    /**
     * stores all entities in the game
     */
    public final ArrayList<Entity> entities = new ArrayList<>();

    /**
     * stores all objects in the game
     */
    public final ArrayList<Entity> objects = new ArrayList<>();

    /**
     * stores all NPCs in the game
     */
    public final ArrayList<Entity> npcs = new ArrayList<>();

    /**
     * stores all blocks in the game
     */
    public final ArrayList<Entity> blocks = new ArrayList<>();

    /**
     * stores all projectiles in the game
     */
    public final ArrayList<Projectile> projectiles = new ArrayList<>();

    /**
     * the game state
     */
    public int gameState;

    /**
     * the different game state values
     */
    public final int PLAY_STATE = 1, PAUSE_STATE = 2, BARRIER_QUESTION_STATE = 3, SPEED_QUESTION_STATE = 4, THERAPY_QUESTION_STATE = 5, DIALOGUE_STATE = 6, INVENTORY_STATE = 7;

    /**
     * manages sound effects
     */
    final Sound sound = new Sound();

    /**
     * the level number
     */
    public final int levelNum;

    /**
     * the time and initial time for the level
     */
    public int time, startTime;

    /**
     * has this level been started
     */
    boolean started = false;

    /**
     * whether the level is complete
     */
    public boolean complete = false;

    /**
     * whether the level was failed
     */
    public boolean failed = false;

    /**
     * the inner demon for level 2
     */
    public InnerDemon innerDemon;

    /**
     * the sequence of entities to interact with in level 1
     */
    public final ArrayList<Entity> lvl1Sequence = new ArrayList<>();

    /**
     * the sequence of entities to interact with in level 3
     */
    public final ArrayList<Entity> lvl3Sequence = new ArrayList<>();

    /**
     * the entity speaking dialogue
     */
    public Entity speaker;

    /**
     * the colour of the yoga mat that the player needs to go to
     */
    public String matColor;

    /**
     * whether the challenges have been started
     */
    public boolean yogaStarted, therapyStarted;

    /**
     * whether the target yoga mat has been reached
     */
    public boolean matReached = true;

    /**
     * number of yoga mats that have been assigned
     */
    public int matCount = 0;

    public final SystemSpeaker systemSpeaker = new SystemSpeaker(this);

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
        player = new Player(this, keyIn, 8);
        if (levelNum == 2) {
            time = FPS * 180; //3 minutes
            startTime = time;
            innerDemon = new InnerDemon(this);
        }
    }

    /**
     * sets up the level
     */
    public void setupLevel() {
        if (!started) {
            assetSetter.setObject();
            started = true;
        }
        gameState = PLAY_STATE;
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
        return complete;
    }

    /**
     * updates the game information from the player's movement
     */
    public void update() {
        if (gameState == PLAY_STATE) {
            if (levelNum == 2) {
                innerDemon.update();
                int debuffInterval = FPS * 45; //45 seconds
                if (time > 0) {
                    time--;
                    if ((startTime - time) % debuffInterval == 0) {
                        projectiles.clear();
                        player.invincible = true;
                        player.invincibleTimer = FPS * 5;
                        gameState = SPEED_QUESTION_STATE;
                    } else if ((startTime - time) % debuffInterval == debuffInterval - FPS * 2) {
                        ui.showMessage("Incoming speed debuff", 45);
                    }
                } else {
                    complete = true;
                    failed = true;
                    sound.stop();
                }
            }
            if (levelNum == 3) {
                if(therapyStarted && player.yogaChallenge) {
                    gameState = THERAPY_QUESTION_STATE;
                }
                if(lvl3Sequence.size() == 0) {
                    complete = true;
                    failed = false;
                    ui.endScreen(true);
                }
            }

            if(player.inRehab) {
                player.withdrawalDeduct = 35;
            } else {
                player.withdrawalDeduct = 5;
            }

            if (yogaStarted) {
                if (matReached) {
                    assetSetter.shuffleYogaMats();
                    //random number between 0 and 3
                    int random = (int) (Math.random() * 4);
                    switch (random) {
                        case 0 -> matColor = "Blue";
                        case 1 -> matColor = "Green";
                        case 2 -> matColor = "Pink";
                        case 3 -> matColor = "Purple";
                    }
                    ui.instruction = "Go to the " + matColor + " mat";
                    matReached = false;
                }
                if (matCount == 30) {
                    yogaStarted = false;
                    player.yogaChallenge = true;
                    systemSpeaker.clearDialogue();
                    systemSpeaker.addDialogue("Congratulations!#You successfully completed a yoga session.#You feel much more relaxed and calm.#Your urge to take drugs decreased by a bit.");
                    systemSpeaker.speak();
                }
            }
            player.update();
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).update();
            }
        } else if (gameState == BARRIER_QUESTION_STATE || gameState == SPEED_QUESTION_STATE || gameState == THERAPY_QUESTION_STATE) {
            if (!ui.inQuestion) {
                ui.question();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        tm.draw(g2D); //draw tiles before player so that player can walk on top of tiles

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).draw(g2D);
        }

        //add entities to list
        entities.add(player);

        if (levelNum == 2) {
            entities.add(innerDemon);
        }

        entities.addAll(objects);

        entities.addAll(npcs);

        entities.addAll(projectiles);

        //sort entities by y position
        entities.sort(Comparator.comparingInt(e -> e.worldY));

        //draw entities
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(g2D);
        }
        //remove entities from list
        entities.clear();

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
            if (levelNum == 1) {
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
