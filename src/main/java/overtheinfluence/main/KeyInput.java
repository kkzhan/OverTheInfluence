package main;

import java.awt.event.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p></p>
 *
 * <p>Work Allocation:<ul>
 *     <li>KeyInput- Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class KeyInput implements KeyListener {

    /**
     * whether or not the player is moving in a specific direction
     */
    public boolean up, down, left, right;

    /**
     * whether or not the player is interacting with an object
     */
    public boolean interact;

    /**
     * the level the game is on
     */
    Level lvl;

    /**
     * constructor for the KeyInput class
     */
    public KeyInput(Level lvl) {
        this.lvl = lvl;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //important key codes
        //backspace = 8
        //tab = 9
        //enter = 10
        //clear = 12
        //shift = 16
        //ctrl = 17
        //alt = 18
        //space = 32
        //A = 65
        //D = 68
        //S = 83
        //W = 87

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            up = true;
        }
        if (key == KeyEvent.VK_A) {
            left = true;
        }
        if (key == KeyEvent.VK_S) {
            down = true;
        }
        if (key == KeyEvent.VK_D) {
            right = true;
        }
        if (key == KeyEvent.VK_E) {
            interact = true;
        }
        if(key == KeyEvent.VK_P) {
            if(lvl.gameState == 1) {
                lvl.gameState = 2;
                lvl.stopMusic();
            } else if(lvl.gameState == 2) {
                lvl.gameState = 1;
                lvl.playMusic(-1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            up = false;
        }
        if (key == KeyEvent.VK_A) {
            left = false;
        }
        if (key == KeyEvent.VK_S) {
            down = false;
        }
        if (key == KeyEvent.VK_D) {
            right = false;
        }
        if (key == KeyEvent.VK_E) {
            interact = false;
        }
    }
}
