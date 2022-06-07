package main;

import java.awt.*;
import java.util.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This manipulates the user interface that the player sees while playing the game.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>UI creation - Kevin Zhan</li>
 * <li>Font selection - Alexander Peng</li>
 * <li>Text centering - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class UI {
    /**
     * the level the UI is for
     */
    Level lvl;
    /**
     * the graphics object used to draw on the screen
     */
    Graphics2D g2D;
    /**
     * the fonts used to draw text
     */
    Font font1, font2;
    /**
     * whether or not a message is currently being displayed
     */
    public boolean msgOn;
    /**
     * the message to be displayed
     */
    public String msg;
    /**
     * how long the message has been displayed for
     */
    public int msgTime;

    public int msgTimeLimit = 10;

    public Question question = null;

    boolean inQuestion = false;

    boolean startScreen = true;

    public ArrayList<Question> questionList = new ArrayList<>();

    final int numOfQuestions = 2;

    int questionIndex = 0;

    /**
     * constructor for the UI class
     */
    public UI(Level lvl) {
        this.lvl = lvl;
        try {
            font1 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fonts/RangerWider Regular.ttf"));
            font2 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fonts/ARCADECLASSIC.ttf"));
        } catch (Exception e) {
        }
        for(int i = 0; i < numOfQuestions; i++) {
            questionList.add(new Question(this, i));
        }
        Collections.shuffle(questionList);
    }

    /**
     * displays a message on the screen for 10 frames
     *
     * @param msg the message to display
     */
    public void showMessage(String msg) {
        this.msg = msg;
        msgOn = true;
        msgTimeLimit = 10;
    }

    /**
     * displays a message on the screen for a certain amount of time
     *
     * @param msg  the message to display
     * @param time the time to display the message for
     */
    public void showMessage(String msg, int time) {
        this.msg = msg;
        this.msgTimeLimit = time;
        msgOn = true;
    }

    /**
     * draws the UI
     */
    public void draw(Graphics2D g2D) {
        this.g2D = g2D;

        if (msgOn && msgTime <= msgTimeLimit) {
            g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
            g2D.setColor(Color.GRAY);
            g2D.fillRect(centerText(msg) - 5, lvl.player.screenY - 20, (int) g2D.getFontMetrics().getStringBounds(msg, g2D).getWidth() + 10, 20);
            g2D.setColor(Color.WHITE);
            g2D.drawString(msg, centerText(msg), lvl.player.screenY - 5);
            msgTime++;
        } else if (msgOn) {
            msgOn = false;
            msgTime = 0;
        }

        if (lvl.gameState == lvl.PAUSE_STATE) {
            g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 5));
            g2D.setColor(Color.WHITE);
            screenPaused();
        }

        if (lvl.gameState == lvl.BARRIER_QUESTION_STATE || lvl.gameState == lvl.SPEED_QUESTION_STATE) {
            question();
        }

        if (lvl.levelNum == 2) {
            displayProgress();
        }

        if (lvl.gameState == lvl.PLAY_STATE) {
            if (lvl.player.barrierDebuffTimer > 0) {
                drawTimer("Barrier Debuff", lvl.player.barrierDebuffTimer, 80, 100);
                if (lvl.player.speedDebuffTimer > 0) {
                    drawTimer("Speed Debuff", lvl.player.speedDebuffTimer, 80, 120);
                    if (lvl.player.invincibleTimer > 0) {
                        drawTimer("Invincible", lvl.player.invincibleTimer, 80, 140);
                    }
                } else if (lvl.player.invincibleTimer > 0) {
                    drawTimer("Invincible", lvl.player.invincibleTimer, 80, 120);
                }
            } else if (lvl.player.speedDebuffTimer > 0) {
                drawTimer("Speed Debuff", lvl.player.speedDebuffTimer, 80, 100);
                if (lvl.player.invincibleTimer > 0) {
                    drawTimer("Invincible", lvl.player.invincibleTimer, 80, 120);
                }
            } else if (lvl.player.invincibleTimer > 0) {
                drawTimer("Invincible", lvl.player.invincibleTimer, 80, 100);
            }

            if (lvl.levelNum == 2) {
                drawTimer(lvl.time, centerText("00:00"), 72);
            }
        }

        if (startScreen) {
            startScreen();
        }

        if (lvl.completed) {
            endScreen(!lvl.failed);
        }
    }

    /**
     * displays dialogue with NPC or objects
     */
    public void dialogue() {
        int x = lvl.tileSize * 2;
        int y = lvl.tileSize / 2 + lvl.tileSize * 5;
        int width = lvl.screenWidth - (lvl.tileSize * 4);
        int height = lvl.tileSize * 5;
        drawWindow(x, y, width, height);

        g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 20));
    }

    /**
     * displays the screen at the end of a level, whether success or failure
     *
     * @param success whether the level was successful
     */
    public void endScreen(boolean success) {
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, lvl.screenWidth, lvl.screenHeight);
        if (success) {
            g2D.setColor(Color.GREEN);
            g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 10));
            g2D.drawString("Level Complete!", centerText("Level Complete!"), lvl.screenHeight / 2);
            g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 20));
            g2D.setColor(Color.WHITE);
            g2D.drawString("Press Enter to Continue", centerText("Press Enter to Continue"), lvl.screenHeight / 2 + lvl.tileSize);
            g2D.drawString("Press Esc to Return to Menu", centerText("Press Esc to Return to Menu"), lvl.screenHeight / 2 + lvl.tileSize * 2);
            if (lvl.keyIn.enter && !lvl.keyIn.escape) {
                lvl.thisGame.nextLevel();
            } else if (lvl.keyIn.escape && !lvl.keyIn.enter) {
                lvl.thisGame.endLevel(false);
            }
        } else {
            g2D.setColor(Color.RED);
            g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 10));
            g2D.drawString("Level Failed!", centerText("Level Failed!"), lvl.screenHeight / 2);
            if (lvl.levelNum == 2) {
                g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 20));
                g2D.setColor(Color.WHITE);
                g2D.drawString("Press R to Retry", centerText("Press R to Retry"), lvl.screenHeight / 2 + lvl.tileSize);
                g2D.drawString("Press Esc to Return to Menu", centerText("Press Esc to Return to Menu"), lvl.screenHeight / 2 + lvl.tileSize * 2);
                if (lvl.keyIn.retry && !lvl.keyIn.escape) {
                    lvl.thisGame.endLevel(true);
                } else if (lvl.keyIn.escape && !lvl.keyIn.retry) {
                    lvl.thisGame.endLevel(false);
                }
            } else if (lvl.levelNum == 3) {
                //you overdosed
                //random if dead
                //if dead no chance to retry
            }
        }
    }

    public void startScreen() {
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, lvl.screenWidth, lvl.screenHeight);
        g2D.setColor(Color.WHITE);
        g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 10));
        //introduce the level
        g2D.drawString("Press Enter to Continue", centerText("Press Enter to Continue"), lvl.screenHeight / 2);
        if (lvl.keyIn.enter) {
            startScreen = false;
        }
    }

    /**
     * draws a window
     */
    public void drawWindow(int x, int y, int width, int height) {
        try {
            g2D.setColor(new Color(0, 0, 0, 200));
            g2D.fillRoundRect(x, y, width, height, 35, 35);
            g2D.setColor(Color.WHITE);
            int borderSize = 5;
            g2D.setStroke(new BasicStroke(borderSize));
            g2D.drawRoundRect(x + borderSize, y + borderSize, width - 2 * borderSize, height - 2 * borderSize, 35, 35);
        } catch (Exception e) {
        }
    }

    /**
     * allows the player to answer questions
     */
    public void question() {
        if (question == null || question.complete) {
            question = questionList.get(questionIndex++);
            if(questionIndex == numOfQuestions) {
                questionIndex = 0;
            }
        }
        inQuestion = true;
        int x = lvl.tileSize * 2;
        int y = lvl.tileSize / 2 + lvl.tileSize * 5;
        int width = lvl.screenWidth - (lvl.tileSize * 4);
        int height = lvl.tileSize * 5;
        drawWindow(x, y, width, height);

        g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 30));

        g2D.setColor(Color.WHITE);
        x += lvl.tileSize;
        for (int i = 0; i < question.lines.size() - 1; i++) {
            y += lvl.tileSize / 2;
            g2D.drawString(question.lines.get(i), x, y);
        }
        for (int i = 0; i < question.options.size(); i++) {
            if (question.selected - 1 == i) {
                g2D.setColor(new Color(94, 94, 94));
            } else {
                g2D.setColor(Color.WHITE);
            }
            y += lvl.tileSize / 2;
            g2D.drawString(question.options.get(i), x, y);
        }
        if (lvl.keyIn.enter && question.selected != -1) {
            if (question.selected == question.answer) {
                question.complete = true;
                if (question.secondAttempt) {
                    showMessage("Latest debuff cured");
                    if (lvl.gameState == lvl.BARRIER_QUESTION_STATE) {
                        lvl.player.barrierDebuffTimer += 300;
                    } else if (lvl.gameState == lvl.SPEED_QUESTION_STATE) {
                        lvl.player.speedDebuffTimer += 300;
                    }
                } else {
                    lvl.player.barrierDebuffTimer = 0;
                    lvl.player.speedDebuffTimer = 0;
                    showMessage("All debuffs cured");
                    lvl.assetSetter.barrierClear();
                }
            } else {
                if (question.secondAttempt) {
                    question.selected = -1;
                    ;
                    question.complete = true;
                    showMessage("Failed to cure debuff");
                    if (lvl.gameState == lvl.BARRIER_QUESTION_STATE) {
                        lvl.player.barrierDebuffTimer += 600;
                    } else if (lvl.gameState == lvl.SPEED_QUESTION_STATE) {
                        lvl.player.speedDebuffTimer += 600;
                    }
                } else {
                    question.selected = -1;
                    question.secondAttempt = true;
                    showMessage("Incorrect answer this is your second attempt");
                }
            }
        }


        if (question.complete) {
            lvl.gameState = lvl.PLAY_STATE;
            inQuestion = false;
        }
    }

    public void displayProgress() {
        g2D.setStroke(new BasicStroke(1));
        int endPoint = (lvl.maxWorldCols - 8) * lvl.tileSize;
        int startPoint = (int) (lvl.tileSize * 1.5);
        int distance = endPoint - startPoint;
        int progress = lvl.player.worldX - startPoint;
        int progressPercent = (int) (((double) progress / (double) distance) * 100);
        if(progressPercent > 100) progressPercent = 100;
        g2D.setColor(new Color(140, 132, 132, 150));
        g2D.fillRect(200, lvl.tileSize / 4, lvl.screenWidth - 400, lvl.tileSize / 2);
        g2D.setColor(new Color(121, 145, 180, 200));
        g2D.fillRect(200, lvl.tileSize / 4, (progressPercent * (lvl.screenWidth - 400)) / 100, lvl.tileSize / 2);
        g2D.setColor(Color.BLACK);
        g2D.drawRect(200, lvl.tileSize / 4, lvl.screenWidth - 400, lvl.tileSize / 2);
    }

    /**
     * draws the screen when paused
     */
    public void screenPaused() {
        String text = "PAUSED";
        g2D.drawString(text, centerText(text), lvl.screenHeight / 2);
    }

    /**
     * @return the parameters required to center the text on the screen
     */
    public int centerText(String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        return (lvl.screenWidth / 2) - (length / 2);
    }

    /**
     * draws a timer
     *
     * @param text the text to be displayed in front of the timer
     * @param time the time to be displayed
     * @param x    the x position of the timer
     * @param y    the y position of the timer
     */
    public void drawTimer(String text, int time, int x, int y) {
        int seconds = time / 30;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        String strSec = seconds + "";
        String strMin = minutes + "";
        if (seconds < 10) {
            strSec = "0" + seconds;
        }
        if (minutes < 10) {
            strMin = "0" + minutes;
        }
        g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
        g2D.setColor(Color.WHITE);
        g2D.drawString(text + "   " + strMin + ":" + strSec, x, y);
    }

    /**
     * draws a timer
     *
     * @param time the time to be displayed
     * @param x    the x position of the timer
     * @param y    the y position of the timer
     */
    public void drawTimer(int time, int x, int y) {
        int seconds = time / 30;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        String strSec = seconds + "";
        String strMin = minutes + "";
        if (seconds < 10) {
            strSec = "0" + seconds;
        }
        if (minutes < 10) {
            strMin = "0" + minutes;
        }
        g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 20));
        g2D.setColor(Color.WHITE);
        g2D.drawString(strMin + ":" + strSec, x, y);
    }

    public void processQuestions(int num) {

    }
}
