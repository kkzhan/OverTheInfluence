package main;

import java.awt.*;
import java.io.*;
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
     * @param msg the message to display
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

        if(msgOn && msgTime <= msgTimeLimit) {
            g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
            g2D.setColor(Color.GRAY);
            g2D.fillRect(centerText(msg) - 5, lvl.player.screenY - 20, (int) g2D.getFontMetrics().getStringBounds(msg, g2D).getWidth() + 10, 20);
            g2D.setColor(Color.WHITE);
            g2D.drawString(msg, centerText(msg), lvl.player.screenY - 5);
            msgTime++;
        } else if(msgOn) {
            msgOn = false;
            msgTime = 0;
        }

        if (lvl.gameState == lvl.PAUSE_STATE) {
            g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 5));
            g2D.setColor(Color.WHITE);
            screenPaused();
        }

        if(lvl.gameState == lvl.BARRIER_QUESTION_STATE || lvl.gameState == lvl.SPEED_QUESTION_STATE) {
            question();
        }
    }

    /**
     * displays dialogue with NPC or objects
     */
    public void dialogue() {
        int x = lvl.tileSize * 2;
        int y = lvl.tileSize/2 + lvl.tileSize * 5;
        int width = lvl.screenWidth - (lvl.tileSize * 4);
        int height = lvl.tileSize * 5;
        drawWindow(x, y, width, height);

        g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 20));
    }

    /**
     * draws a window
     */
    public void drawWindow(int x, int y, int width, int height) {
        g2D.setColor(new Color(0,0,0,200));
        g2D.fillRoundRect(x, y, width, height, 35, 35);
        g2D.setColor(Color.WHITE);
        int borderSize = 5;
        g2D.setStroke(new BasicStroke(borderSize));
        g2D.drawRoundRect(x + borderSize, y + borderSize, width - 2 * borderSize, height - 2 * borderSize, 35, 35);
    }

    /**
     * allows the player to answer questions
     */
    public void question() {
        int x = lvl.tileSize * 2;
        int y = lvl.tileSize/2 + lvl.tileSize * 5;
        int width = lvl.screenWidth - (lvl.tileSize * 4);
        int height = lvl.tileSize * 5;
        drawWindow(x, y, width, height);

        g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
        int questionNum;
        int numberOfQuestions = 1;
        questionNum = (int) (Math.random() * numberOfQuestions) + 1;
        BufferedReader br = null;
        if(lvl.levelNum == 2) {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions/lvl2/question" + questionNum + ".txt")));
        } else if(lvl.levelNum == 3 || lvl.levelNum == 4) {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions/lvl2/question" + questionNum + ".txt")));
        }
        ArrayList<String> lines = new ArrayList<>();
        try {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
        }
        g2D.setColor(Color.WHITE);
        x += lvl.tileSize;
        for(int i = 0; i < lines.size() - 1; i++) {
            y += lvl.tileSize/2;
            g2D.drawString(lines.get(i), x, y);
        }

        boolean answerRight = false;
        if(answerRight) {
            lvl.gameState = lvl.PLAY_STATE;
            lvl.updateOn = true;
        }
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
}
