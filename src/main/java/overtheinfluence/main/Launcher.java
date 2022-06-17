package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

/**
 * <p>This class is the launcher class of the game and includes the menu of the game as well as processes the launch
 * and end of games. </p>
 *
 * <p>
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 * </p>
 *
 * <p>Work Allocation:<ul>
 * <li>Splash Screen - Alexander Peng</li>
 * <li>Main Menu - Kevin Zhan</li>
 * <li>Instructions - Alexander Peng</li>
 * <li>Play Menu - Kevin Zhan</li>
 * <li>Game Over - Alexander Peng</li>
 * <li>Game Won - Kevin Zhan</li>
 * <li>Exit Screen/Confirmation - Kevin Zhan</li>
 * <li>JavaFX to Swing conversion - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Launcher {
    /**
     * the currently running game
     */
    public Game currentGame;

    /**
     * checks whether a previous game has been started
     */
    boolean gameStarted = false;

    /**
     * the window the game is displayed in
     */
    final JFrame window = new JFrame("Over the Influence");

    /**
     * the background colour of the launcher
     */
    final Color bgColor = new Color(89, 89, 89);

    /**
     * the main panel displayed in the launcher
     */
    JPanel mainPanel = new JPanel();

    /**
     * if the splash screen is over
     */
    boolean splashOver = false;

    /**
     * constructor for the Launcher class
     */
    public Launcher() {
        currentGame = new Game(this);
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        splashScreen();
    }

    /**
     * This method displays the splash screen of the game
     */
    private void splashScreen() {
        Icon imgIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/resources/launcherFiles/splashScreen.gif")));
        JLabel label = new JLabel(imgIcon);
        label.setBounds(0, 0, 1200, 800);
        label.setPreferredSize(new Dimension(1200, 800));
        window.add(label);
        window.pack();
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //ignore
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    splashOver = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //ignore
            }
        };
        try {
            Thread.sleep(11320);
            window.addKeyListener(keyListener);
            window.setFocusable(true);
        } catch (InterruptedException ignored) {
        }
        for (int i = 0; i < 22530; i++) {
            if (splashOver) {
                break;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {
                }
            }
        }
        window.remove(label);
        mainMenu();
    }

    /**
     * This method displays the main menu of the game which provides access to the game, instructions, and credits
     */
    public void mainMenu() {
        while (window.getKeyListeners().length > 0) {
            window.removeKeyListener(window.getKeyListeners()[0]);
        }
        BufferedImage mainScreenImg = null;
        try {
            mainScreenImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/mainScreen.png")));
        } catch (IOException ignored) {
        }
        JLabel mainScreen = new JLabel(new ImageIcon(Objects.requireNonNull(mainScreenImg).getScaledInstance(1200, 800, Image.SCALE_SMOOTH)));
        JPanel panel = new JPanel();
        panel.add(mainScreen);
        mainPanel = panel;
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(bgColor);
        window.add(panel);
        window.pack();
        panel.setLayout(null);
        JButton play = new JButton("Play");
        JButton instructions = new JButton("Instructions");
        JButton credits = new JButton("Credits");
        JButton exit = new JButton("Exit");
        play.setLocation(100, 270);
        instructions.setLocation(100, 390);
        credits.setLocation(100, 510);
        exit.setLocation(100, 630);
        play.setSize(new Dimension(1000, 110));
        instructions.setSize(1000, 110);
        credits.setSize(1000, 110);
        exit.setSize(1000, 110);
        play.setBackground(new Color(64, 116, 28));
        instructions.setBackground(new Color(192, 148, 4));
        credits.setBackground(new Color(16, 84, 148));
        exit.setBackground(new Color(160, 4, 4));
        play.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        instructions.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        credits.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        play.setFont(new Font("Arial", Font.BOLD, 60));
        instructions.setFont(new Font("Arial", Font.BOLD, 60));
        credits.setFont(new Font("Arial", Font.BOLD, 60));
        exit.setFont(new Font("Arial", Font.BOLD, 60));
        play.setForeground(Color.WHITE);
        instructions.setForeground(Color.WHITE);
        credits.setForeground(Color.WHITE);
        exit.setForeground(Color.WHITE);
        panel.add(play);
        panel.add(instructions);
        panel.add(credits);
        panel.add(exit);
        play.addActionListener(e -> {
            window.remove(mainPanel);
            playGame();
        });
        instructions.addActionListener(e -> {
            window.remove(mainPanel);
            insControl();
        });
        credits.addActionListener(e -> {
            window.remove(mainPanel);
            credits();
        });
        exit.addActionListener(e -> {
            JFrame dialog = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            int result = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                window.remove(mainPanel);
                exitProgram();
            }
        });
        window.repaint();
    }

    /**
     * This method is used to create the game starting screen
     */
    private void playGame() {
        JPanel panel = new JPanel();
        mainPanel = panel;
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(bgColor);
        window.add(panel);
        window.pack();
        panel.setLayout(null);
        JButton play = new JButton("Play");
        JButton resume1 = new JButton("Resume From Level 1");
        JButton resume2 = new JButton("Resume From Level 2");
        JButton resume3 = new JButton("Resume From Level 3");
        play.setLocation(650, 420);
        resume1.setLocation(50, 285);
        resume2.setLocation(50, 420);
        resume3.setLocation(50, 555);
        play.setSize(new Dimension(500, 110));
        resume1.setSize(new Dimension(500, 110));
        resume2.setSize(new Dimension(500, 110));
        resume3.setSize(new Dimension(500, 110));
        play.setBackground(new Color(160, 156, 156));
        resume1.setBackground(new Color(160, 156, 156));
        resume2.setBackground(new Color(160, 156, 156));
        resume3.setBackground(new Color(160, 156, 156));
        play.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        resume1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        resume2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        resume3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        play.setFont(new Font("Arial", Font.BOLD, 40));
        resume1.setFont(new Font("Arial", Font.BOLD, 40));
        resume2.setFont(new Font("Arial", Font.BOLD, 40));
        resume3.setFont(new Font("Arial", Font.BOLD, 40));
        play.setForeground(Color.WHITE);
        resume1.setForeground(Color.WHITE);
        resume2.setForeground(Color.WHITE);
        resume3.setForeground(Color.WHITE);
        panel.add(play);
        panel.add(resume1);
        panel.add(resume2);
        panel.add(resume3);
        play.addActionListener(e -> {
            Frame dialog = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            int result = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to start a new game?", "Start Game", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                window.remove(mainPanel);
                window.setVisible(false);
                //start new game
                gameStarted = true;
                currentGame = new Game(this);
                currentGame.playLevel(3);
            }
        });
        resume1.addActionListener(e -> {
            JFrame dialog = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            if (!gameStarted) {
                JOptionPane.showMessageDialog(dialog, "You have not started a game yet!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (currentGame.levelComplete(1)) {
                JOptionPane.showMessageDialog(dialog, "You have already completed this level!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                int result = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to resume from Level 1?", "Resume Level 1", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    window.remove(mainPanel);
                    window.setVisible(false);
                    currentGame.playLevel(1);
                }
            }
        });
        resume2.addActionListener(e -> {
            JFrame dialog = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            if (!gameStarted) {
                JOptionPane.showMessageDialog(dialog, "You have not started a game yet!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!currentGame.levelComplete(1)) {
                JOptionPane.showMessageDialog(dialog, "You have not completed Level 1!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (currentGame.levelComplete(2)) {
                JOptionPane.showMessageDialog(dialog, "You have already completed this level!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                int result = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to resume from Level 2?", "Resume Level 2", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    window.remove(mainPanel);
                    window.setVisible(false);
                    currentGame.playLevel(2);
                }
            }
        });
        resume3.addActionListener(e -> {
            JFrame dialog = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            if (!gameStarted) {
                JOptionPane.showMessageDialog(dialog, "You have not started a game yet!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!currentGame.levelComplete(1)) {
                JOptionPane.showMessageDialog(dialog, "You have not completed Level 1!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!currentGame.levelComplete(2)) {
                JOptionPane.showMessageDialog(dialog, "You have not completed Level 2!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (currentGame.levelComplete(3)) {
                JOptionPane.showMessageDialog(dialog, "You have already completed this level!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                int result = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to resume from Level 3?", "Resume Level 3", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    window.remove(mainPanel);
                    window.setVisible(false);
                    currentGame.playLevel(3);
                }
            }
        });

        JButton back = new JButton("Back");
        back.setSize(new Dimension(200, 60));
        back.setLocation(50, 700);
        back.setBackground(new Color(183, 23, 23));
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.setFont(new Font("Arial", Font.BOLD, 30));
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(e -> {
            window.remove(mainPanel);
            mainMenu();
        });

        BufferedImage playTitle = null;
        try {
            playTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/playTitle.png")));
        } catch (IOException ignored) {
        }
        JLabel creditTitle = new JLabel(new ImageIcon(Objects.requireNonNull(playTitle).getScaledInstance(276, 204, Image.SCALE_SMOOTH)));
        creditTitle.setSize(new Dimension(276, 204));
        creditTitle.setLocation(462, 70);
        panel.add(creditTitle);
    }

    /**
     * This method is used to create and display the control instructions
     */
    private void insControl() {
        JPanel panel = new JPanel();
        mainPanel = panel;
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(bgColor);
        window.add(panel);
        window.pack();
        panel.setLayout(null);

        JButton back = new JButton("Back");
        back.setSize(new Dimension(200, 60));
        back.setLocation(50, 700);
        back.setBackground(new Color(183, 23, 23));
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.setFont(new Font("Arial", Font.BOLD, 30));
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(e -> {
            window.remove(mainPanel);
            mainMenu();
        });

        JButton continueBtn = new JButton("Continue");
        continueBtn.setSize(new Dimension(200, 60));
        continueBtn.setLocation(950, 700);
        continueBtn.setBackground(new Color(19, 150, 23));
        continueBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        continueBtn.setFont(new Font("Arial", Font.BOLD, 30));
        continueBtn.setForeground(Color.WHITE);
        panel.add(continueBtn);
        continueBtn.addActionListener(e -> {
            window.remove(mainPanel);
            insLvl1();
        });

        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/insControls.png")));
        } catch (IOException ignored) {
        }

        JLabel imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(image).getScaledInstance(1200, 650, Image.SCALE_SMOOTH)));
        imageLabel.setSize(new Dimension(1200, 650));
        imageLabel.setLocation(0, 0);
        panel.add(imageLabel);
    }

    /**
     * This method is used to create and display the Level 1 instructions
     */
    private void insLvl1() {
        JPanel panel = new JPanel();
        mainPanel = panel;
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(bgColor);
        window.add(panel);
        window.pack();
        panel.setLayout(null);

        JButton back = new JButton("Back");
        back.setSize(new Dimension(200, 60));
        back.setLocation(50, 700);
        back.setBackground(new Color(183, 23, 23));
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.setFont(new Font("Arial", Font.BOLD, 30));
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(e -> {
            window.remove(mainPanel);
            insControl();
        });

        JButton continueBtn = new JButton("Continue");
        continueBtn.setSize(new Dimension(200, 60));
        continueBtn.setLocation(950, 700);
        continueBtn.setBackground(new Color(19, 150, 23));
        continueBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        continueBtn.setFont(new Font("Arial", Font.BOLD, 30));
        continueBtn.setForeground(Color.WHITE);
        panel.add(continueBtn);
        continueBtn.addActionListener(e -> {
            window.remove(mainPanel);
            insLvl2();
        });

        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/insLevel1.png")));
        } catch (IOException ignored) {
        }

        JLabel imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(image).getScaledInstance(1200, 650, Image.SCALE_SMOOTH)));
        imageLabel.setSize(new Dimension(1200, 650));
        imageLabel.setLocation(0, 0);
        panel.add(imageLabel);
    }

    /**
     * This method is used to create and display the Level 2 instructions
     */
    private void insLvl2() {
        JPanel panel = new JPanel();
        mainPanel = panel;
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(bgColor);
        window.add(panel);
        window.pack();
        panel.setLayout(null);

        JButton back = new JButton("Back");
        back.setSize(new Dimension(200, 60));
        back.setLocation(50, 700);
        back.setBackground(new Color(183, 23, 23));
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.setFont(new Font("Arial", Font.BOLD, 30));
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(e -> {
            window.remove(panel);
            insLvl1();
        });

        JButton continueBtn = new JButton("Continue");
        continueBtn.setSize(new Dimension(200, 60));
        continueBtn.setLocation(950, 700);
        continueBtn.setBackground(new Color(19, 150, 23));
        continueBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        continueBtn.setFont(new Font("Arial", Font.BOLD, 30));
        continueBtn.setForeground(Color.WHITE);
        panel.add(continueBtn);
        continueBtn.addActionListener(e -> {
            window.remove(panel);
            insLvl3();
        });

        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/insLevel2.png")));
        } catch (IOException ignored) {
        }

        JLabel imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(image).getScaledInstance(1200, 650, Image.SCALE_SMOOTH)));
        imageLabel.setSize(new Dimension(1200, 650));
        imageLabel.setLocation(0, 0);
        panel.add(imageLabel);
    }

    /**
     * This method is used to create and display the Level 3 instructions
     */
    private void insLvl3() {
        JPanel panel = new JPanel();
        mainPanel = panel;
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(bgColor);
        window.add(panel);
        window.pack();
        panel.setLayout(null);

        JButton back = new JButton("Back");
        back.setSize(new Dimension(200, 60));
        back.setLocation(50, 700);
        back.setBackground(new Color(183, 23, 23));
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.setFont(new Font("Arial", Font.BOLD, 30));
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(e -> {
            window.remove(mainPanel);
            insLvl2();
        });

        JButton continueBtn = new JButton("Continue");
        continueBtn.setSize(new Dimension(200, 60));
        continueBtn.setLocation(950, 700);
        continueBtn.setBackground(new Color(19, 150, 23));
        continueBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        continueBtn.setFont(new Font("Arial", Font.BOLD, 30));
        continueBtn.setForeground(Color.WHITE);
        panel.add(continueBtn);
        continueBtn.addActionListener(e -> {
            window.remove(mainPanel);
            mainMenu();
        });

        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/insLevel3.png")));
        } catch (IOException ignored) {
        }

        JLabel imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(image).getScaledInstance(1200, 650, Image.SCALE_SMOOTH)));
        imageLabel.setSize(new Dimension(1200, 650));
        imageLabel.setLocation(0, 0);
        panel.add(imageLabel);
    }

    /**
     * This method is used to create the credits screen
     */
    private void credits() {
        JPanel panel = new JPanel();
        mainPanel = panel;
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(bgColor);
        window.add(panel);
        window.pack();
        panel.setLayout(null);

        JButton back = new JButton("Back");
        back.setSize(new Dimension(200, 60));
        back.setLocation(50, 700);
        back.setBackground(new Color(183, 23, 23));
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.setFont(new Font("Arial", Font.BOLD, 30));
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(e -> {
            window.remove(mainPanel);
            mainMenu();
        });

        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/credits.png")));
        } catch (IOException ignored) {
        }

        JLabel imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(image).getScaledInstance(1200, 650, Image.SCALE_SMOOTH)));
        imageLabel.setSize(new Dimension(1200, 650));
        imageLabel.setLocation(0, 0);
        panel.add(imageLabel);
    }

    /**
     * This method is used to confirm the user's choice to quit the game and exit the program
     */
    private void exitProgram() {
        JPanel panel = new JPanel(true);
        Icon imgIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/resources/launcherFiles/thankYouForPlaying.png")));
        JLabel endPage = new JLabel(imgIcon);
        endPage.setSize(new Dimension(1200, 800));
        endPage.setLocation(0, 0);
        panel.add(endPage);
        window.add(panel);
        window.pack();
        panel.setLayout(null);
        window.repaint();
        Timer timer = new Timer(1500, e -> System.exit(0));
        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
    }
}