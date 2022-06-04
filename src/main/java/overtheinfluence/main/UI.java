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
    public boolean msgOn;
    public String msg;
    public int msgCnt;
    public boolean gameFin;

    /**
     * constructor for the UI class
     */
    public UI(Level lvl) {
        this.lvl = lvl;

        try {
            font1 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fonts/RangerWider Regular.ttf"));
        } catch (Exception e) {
        }
    }

    /**
     * displays a message on the screen
     *
     * @param msg the message to display
     */
    public void showMessage(String msg) {
        this.msg = msg;
        msgOn = true;
    }

    /**
     * draws the UI
     */
    public void draw(Graphics2D g2D) {
        this.g2D = g2D;

        if(msgOn) {
            g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
            g2D.setColor(Color.GRAY);
            g2D.fillRect(centerText(msg) - 5, lvl.player.screenY - 20, (int) g2D.getFontMetrics().getStringBounds(msg, g2D).getWidth() + 10, 20);
            g2D.setColor(Color.WHITE);
            g2D.drawString(msg, centerText(msg), lvl.player.screenY - 5);
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
