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
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class is the launcher class of the game and includes the menu of the game as well as processes the launch
 * and end of games. </p>
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
    private Game currentGame;

    /**
     * checks whether or not a previous game has been started
     */
    boolean gameStarted = false;

    /**
     * the window the game is displayed in
     */
    JFrame window = new JFrame("Over the Influence");

    /**
     * the background colour of the launcher
     */
    Color bgColor = new Color(89, 89, 89);

    /**
     * the main panel displayed in the launcher
     */
    JPanel mainPanel = new JPanel();

    DataHandler dataHandler = new DataHandler(this);

    /**
     * constructor for the Launcher class
     */
    public Launcher() {
        currentGame = new Game(this);
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.remove(mainPanel);
                    exitProgram();
                }
            }
        });
        dataHandler.processData();
        splashScreen();
    }

    /**
     * This method displays the splash screen of the game
     */
    private void splashScreen() {
        mainMenu();
    }

    /**
     * This method displays the main menu of the game which provides access to the game, instructions, and credits
     */
    public void mainMenu() {
        BufferedImage mainScreenImg = null;
        try {
            mainScreenImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/mainScreen.png")));
        } catch (IOException e) {
        }
        JLabel mainScreen = new JLabel(new ImageIcon(mainScreenImg.getScaledInstance(1200, 800, Image.SCALE_SMOOTH)));
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
                currentGame.playLevel(1); //fix to 1
            }
        });
        resume1.addActionListener(e -> {
            JFrame dialog = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            if (!gameStarted) {
                JOptionPane.showMessageDialog(dialog, "You have not started a game yet!", "Warning", JOptionPane.WARNING_MESSAGE);
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
        } catch (IOException e) {
        }
        JLabel creditTitle = new JLabel(new ImageIcon(playTitle.getScaledInstance(276, 204, Image.SCALE_SMOOTH)));
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
        BufferedImage insTitle = null;
        BufferedImage controlsTitle = null;
        try {
            insTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/instructionsTitle.png")));
            controlsTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/insControls.png")));
        } catch (IOException e) {
        }

        JLabel title = new JLabel(new ImageIcon(insTitle.getScaledInstance(456, 78, Image.SCALE_SMOOTH)));
        JLabel controls = new JLabel(new ImageIcon(controlsTitle.getScaledInstance(306, 78, Image.SCALE_SMOOTH)));
        title.setSize(new Dimension(456, 78));
        controls.setSize(new Dimension(306, 78));
        title.setLocation(194, 70);
        controls.setLocation(700, 70);
        panel.add(title);
        panel.add(controls);
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

        BufferedImage insTitle = null;
        BufferedImage lvl1Title = null;
        try {
            insTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/instructionsTitle.png")));
            lvl1Title = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/insLevel1.png")));
        } catch (IOException e) {
        }

        JLabel title = new JLabel(new ImageIcon(insTitle.getScaledInstance(456, 78, Image.SCALE_SMOOTH)));
        JLabel level1Title = new JLabel(new ImageIcon(lvl1Title.getScaledInstance(234, 78, Image.SCALE_SMOOTH)));
        title.setSize(new Dimension(456, 78));
        level1Title.setSize(new Dimension(234, 78));
        title.setLocation(194, 70);
        level1Title.setLocation(700, 70);
        panel.add(title);
        panel.add(level1Title);
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

        BufferedImage insTitle = null;
        BufferedImage lvl2Title = null;
        try {
            insTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/instructionsTitle.png")));
            lvl2Title = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/insLevel2.png")));
        } catch (IOException e) {
        }

        JLabel title = new JLabel(new ImageIcon(insTitle.getScaledInstance(456, 78, Image.SCALE_SMOOTH)));
        JLabel level2Title = new JLabel(new ImageIcon(lvl2Title.getScaledInstance(240, 78, Image.SCALE_SMOOTH)));
        title.setSize(new Dimension(456, 78));
        level2Title.setSize(new Dimension(240, 78));
        title.setLocation(194, 70);
        level2Title.setLocation(700, 70);
        panel.add(title);
        panel.add(level2Title);
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

        BufferedImage insTitle = null;
        BufferedImage lvl3Title = null;
        try {
            insTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/instructionsTitle.png")));
            lvl3Title = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/insLevel3.png")));
        } catch (IOException e) {
        }

        JLabel title = new JLabel(new ImageIcon(insTitle.getScaledInstance(456, 78, Image.SCALE_SMOOTH)));
        JLabel level3Title = new JLabel(new ImageIcon(lvl3Title.getScaledInstance(234, 78, Image.SCALE_SMOOTH)));
        title.setSize(new Dimension(456, 78));
        level3Title.setSize(new Dimension(234, 78));
        title.setLocation(194, 70);
        level3Title.setLocation(700, 70);
        panel.add(title);
        panel.add(level3Title);
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

        BufferedImage creditsTitle = null;
        try {
            creditsTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/creditsTitle.png")));
        } catch (IOException e) {
        }
        JLabel creditTitle = new JLabel(new ImageIcon(creditsTitle.getScaledInstance(344, 104, Image.SCALE_SMOOTH)));
        creditTitle.setSize(new Dimension(344, 104));
        creditTitle.setLocation(428, 70);
        panel.add(creditTitle);
    }

    /**
     * This method is used to confirm the user's choice to quit the game and exit the program
     */
    private void exitProgram() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(bgColor);
        window.add(panel);
        window.pack();
        panel.setLayout(null);

        dataHandler.saveData();

        window.removeWindowListener(window.getWindowListeners()[0]);

        BufferedImage endPageImg = null;
        try {
            endPageImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/launcherFiles/endScreen.png")));
        } catch (IOException e) {
        }
        JLabel endPage = new JLabel(new ImageIcon(endPageImg.getScaledInstance(1200, 800, Image.SCALE_SMOOTH)));
        endPage.setSize(new Dimension(1200, 800));
        endPage.setLocation(0, 0);
        panel.add(endPage);

        Timer timer = new Timer(1500, e -> System.exit(0));
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * This method displays the screen when the player wins
     */
    private void gameSuccess() {
    }

    /**
     * This method displays the screen when the player loses
     */
    private void gameOver() {
    }

    /**
     * This method will process the end of the game and display the appropriate screen
     */
    public void gameEnd() {
        //to be implemented
    }

    /**
     * This method will be used to access past game data
     */
    private void processFile() {
        //to be implemented
    }

    /**
     * This method will be used to store game data for future use
     */
    private void saveGame() {
        //to be implemented
    }

    /**
     * This method closes the launcher when the player starts a game
     */
    private void closeLauncher() {

    }

    /**
     * This method opens the launcher when the player finishes or leaves the game
     */
    public void openLauncher() {
        cleanUp();
        mainMenu();
    }

    /**
     * This method will be used to clean up the launcher screen in between pages
     */
    private void cleanUp() {
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
    }
}