package main;

import java.awt.*;

public class UI {
    Level lvl;
    Graphics2D g2D;
    Font font1, font2;
    public boolean msgOn;
    public String msg;
    public int msgCnt;
    public boolean gameFin;

    public UI(Level lvl) {
        this.lvl = lvl;

        try {
            font1 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fonts/RangerWider Regular.ttf"));
        } catch (Exception e) {
        }
    }

    public void showMessage(String msg) {
        this.msg = msg;
        msgOn = true;
    }

    public void draw(Graphics2D g2D) {
        this.g2D = g2D;
        g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 5));
        g2D.setColor(Color.WHITE);
        if (lvl.gameState == 1) {
            //playstate stuff
        } else if (lvl.gameState == 2) {
            screenPaused();
        }
    }

    public void screenPaused() {
        String text = "PAUSED";
        g2D.drawString(text, centerText(text), lvl.screenHeight / 2);
    }

    public int centerText(String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        return (lvl.screenWidth / 2) - (length / 2);
    }
}
