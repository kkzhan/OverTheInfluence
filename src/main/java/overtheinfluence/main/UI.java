package main;

import java.awt.*;

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

        if (lvl.gameState == 2) {
            g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 5));
            g2D.setColor(Color.WHITE);
            screenPaused();
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
