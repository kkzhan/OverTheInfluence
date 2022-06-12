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
 * <li>Window drawing - Alexander Peng</li>
 * <li>Dialogue implementation - Kevin Zhan</li>
 * <li>Question implementation - Kevin Zhan</li>
 * <li>Start and end screens - Alexander Peng</li>
 * <li>Inventory - Alexander Peng</li>
 * <li>Task progress and withdrawal bar - Kevin Zhan</li>
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

    /**
     * how long the message can be displayed for
     */
    public int msgTimeLimit = 10;

    /**
     * the question that the player will have to answer
     */
    public Question question = null;

    /**
     * whether the player is currently answering a question
     */
    boolean inQuestion = false;

    /**
     * whether the start screen is still active
     */
    boolean startScreen = true;

    /**
     * a list of questions to be answered
     */
    public ArrayList<Question> questionList = new ArrayList<>();

    /**
     * the total number of questions
     */
    final int numOfQuestions;

    /**
     * the index of the current question in the question list
     */
    int questionIndex = 0;

    /**
     * the index of the current dialogue in the available dialogue
     */
    int dialogueIndex = 0;

    /**
     * whether the player is ready for the next line of dialogue
     */
    public boolean dialogueReady = true;

    /**
     * the instruction to be given to the player
     */
    public String instruction;

    /**
     * the number of questions the player has answered right in therapy
     */
    public int rightCount = 0;

    /**
     * whether the player has completed therapy in level 3
     */
    public boolean doneTherapy = false;

    /**
     * the current selected option in the inventory
     */
    public int inventorySelect = -1;


    /**
     * constructor for the UI class
     */
    public UI(Level lvl) {
        this.lvl = lvl;
        if (lvl.levelNum == 2) {
            numOfQuestions = 9;
        } else {
            numOfQuestions = 3;
        }
        try {
            font1 = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/resources/fonts/RangerWider Regular.ttf")));
            font2 = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/resources/fonts/ARCADECLASSIC.ttf")));
        } catch (Exception e) {
        }
        for (int i = 0; i < numOfQuestions; i++) {
            questionList.add(new Question(this, i));
        }
        Collections.shuffle(questionList);
    }

    /**
     * resets questions so that they can be used again
     */
    public void resetQuestions() {
        for (int i = 0; i < numOfQuestions; i++) {
            questionList.get(i).complete = false;
            questionList.get(i).selected = -1;
            questionList.get(i).secondAttempt = false;
        }
        rightCount = 0;
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
            if (lvl.gameState == lvl.PLAY_STATE || lvl.gameState == lvl.BARRIER_QUESTION_STATE || lvl.gameState == lvl.SPEED_QUESTION_STATE) {
                g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
                g2D.setColor(Color.GRAY);
                g2D.fillRect(centerText(msg) - 5, lvl.player.screenY - 20, (int) g2D.getFontMetrics().getStringBounds(msg, g2D).getWidth() + 10, 20);
                g2D.setColor(Color.WHITE);
                g2D.drawString(msg, centerText(msg), lvl.player.screenY - 5);
                msgTime++;
            }
        } else if (msgOn) {
            msgOn = false;
            msgTime = 0;
        }

        if (lvl.gameState == lvl.PAUSE_STATE) {
            g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 5));
            g2D.setColor(Color.WHITE);
            screenPaused();
        }

        if (lvl.gameState == lvl.BARRIER_QUESTION_STATE || lvl.gameState == lvl.SPEED_QUESTION_STATE || lvl.gameState == lvl.THERAPY_QUESTION_STATE) {
            question();
        }

        if (lvl.gameState == lvl.DIALOGUE_STATE) {
            dialogue();
        }

        if (lvl.gameState == lvl.INVENTORY_STATE) {
            inventory();
        }

        displayProgress();

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

        if (lvl.complete) {
            endScreen(!lvl.failed);
        }
    }

    /**
     * displays dialogue with NPC or objects
     */
    public void dialogue() {
        if (lvl.gameState == lvl.DIALOGUE_STATE) {
            int x = lvl.tileSize * 2;
            int y = lvl.tileSize / 2 + lvl.tileSize * 5;
            int width = lvl.screenWidth - (lvl.tileSize * 4);
            int height = lvl.tileSize * 5;
            drawWindow(x, y, width, height);


            //do whatever font stuff
            //draw Enter + triangle pointing to right
            g2D.setColor(Color.WHITE);
            g2D.setFont(font1.deriveFont(Font.BOLD, lvl.screenHeight / 20));
            g2D.drawString("Enter >", lvl.screenWidth - (int) (lvl.tileSize * 4.5), lvl.screenHeight - (int) (2.5 * lvl.tileSize));

            y += lvl.tileSize;
            String[] dialogue = lvl.speaker.dialogue.get(dialogueIndex).split("#");
            for (int i = 0; i < dialogue.length; i++) {
                if (i == 0) {
                    g2D.setFont(font1.deriveFont(Font.BOLD, lvl.screenHeight / 28));
                } else {
                    g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
                }
                g2D.drawString(dialogue[i], x + lvl.tileSize, y + lvl.tileSize * i);
            }
            if (lvl.keyIn.enter && dialogueReady && lvl.gameState == lvl.DIALOGUE_STATE) {
                dialogueIndex++;
                if (dialogueIndex >= lvl.speaker.dialogue.size()) {
                    dialogueIndex = 0;
                    lvl.gameState = lvl.PLAY_STATE;
                }
                dialogueReady = false;
            }
        }
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
                if (lvl.levelNum == 3) {
                    lvl.thisGame.endLevel(false);
                } else {
                    lvl.thisGame.nextLevel();
                }
            } else if (lvl.keyIn.escape && !lvl.keyIn.enter) {
                lvl.thisGame.endLevel(false);
            }
        } else {
            g2D.setColor(Color.RED);
            g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 10));
            g2D.drawString("Level Failed!", centerText("Level Failed!"), lvl.screenHeight / 2);
            if (lvl.levelNum == 2 || lvl.levelNum == 3) {
                g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 20));
                g2D.setColor(Color.WHITE);
                g2D.drawString("Press R to Retry", centerText("Press R to Retry"), lvl.screenHeight / 2 + lvl.tileSize);
                g2D.drawString("Press Esc to Return to Menu", centerText("Press Esc to Return to Menu"), lvl.screenHeight / 2 + lvl.tileSize * 2);
                if (lvl.keyIn.retry && !lvl.keyIn.escape) {
                    lvl.thisGame.endLevel(true);
                } else if (lvl.keyIn.escape && !lvl.keyIn.retry) {
                    lvl.thisGame.endLevel(false);
                }
            }
        }
    }

    /**
     * displays the start screen before a player continues to the level
     */
    public void startScreen() {
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, lvl.screenWidth, lvl.screenHeight);
        g2D.setColor(Color.WHITE);
        g2D.setFont(font2.deriveFont(Font.PLAIN, lvl.screenHeight / 10));
        String levelDesc = "";
        if(lvl.levelNum == 1) {
            levelDesc = "Level 1   Exploration";
        } else if(lvl.levelNum == 2) {
            levelDesc = "Level 2   Inner Demons";
        } else if(lvl.levelNum == 3) {
            levelDesc = "Level 3   Recovery";
        }
        g2D.drawString(levelDesc, centerText(levelDesc), lvl.screenHeight / 2);
        g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 20));
        g2D.drawString("Press Enter to Continue", centerText("Press Enter to Continue"), lvl.screenHeight / 2 + lvl.tileSize);
        if (lvl.keyIn.enter) {
            startScreen = false;
            if (lvl.levelNum == 3) {
                lvl.systemSpeaker.clearDialogue();
                lvl.systemSpeaker.addDialogue("Withdrawal#The bar at the bottom of your screen is your withdrawal meter.");
                lvl.systemSpeaker.addDialogue("Withdrawal#It empties as you move. When it is empty you will relapse.");
                lvl.systemSpeaker.addDialogue("Withdrawal#Mitigate the effects by consuming water or food that you can get at#designated locations in regular intervals.");
                lvl.systemSpeaker.addDialogue("Withdrawal#Remember to press I to check your inventory for food or water.");
                lvl.systemSpeaker.speak();
            }
        }
        dialogueReady = false;
    }

    /**
     * displays the inventory
     */
    public void inventory() {
        int x = lvl.tileSize * 2;
        int y = lvl.tileSize / 2 + lvl.tileSize * 5;
        int width = lvl.screenWidth - (lvl.tileSize * 4);
        int height = lvl.tileSize * 5;
        drawWindow(x, y, width, height);

        g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 30));

        g2D.setColor(Color.WHITE);
        x += lvl.tileSize;
        y += lvl.tileSize;
        g2D.drawString("Inventory:", x, y);
        y += lvl.tileSize;
        if (inventorySelect == 1) {
            g2D.setColor(new Color(94, 94, 94));
        }
        g2D.drawString("Food: " + lvl.player.inventory[0], x, y);
        g2D.setColor(Color.WHITE);
        if (inventorySelect == 2) {
            g2D.setColor(new Color(94, 94, 94));
        }
        y += lvl.tileSize / 2;
        g2D.drawString("Water: " + lvl.player.inventory[1], x, y);
        g2D.setColor(Color.WHITE);
        y += lvl.tileSize / 2;
        g2D.drawString("Enter   the   respective   number   to   select", x, y);
        y += lvl.tileSize / 2;
        g2D.drawString("Then   hit   enter   to   consume  one.", x, y);
        y += lvl.tileSize / 2;
        g2D.drawString("Press   Esc   to   close   inventory.", x, y);
        if (lvl.keyIn.enter) {
            if (inventorySelect == 1) {
                if (lvl.player.inventory[0] > 0) {
                    lvl.player.inventory[0]--;
                    showMessage("You ate food x1");
                    if (lvl.player.inRehab) {
                        lvl.player.withdrawalLevel += 40;
                    } else {
                        lvl.player.withdrawalLevel += 400;
                    }
                }
            } else if (inventorySelect == 2) {
                if (lvl.player.inventory[1] > 0) {
                    lvl.player.inventory[1]--;
                    showMessage("You drank water x1");
                    if (lvl.player.inRehab) {
                        lvl.player.withdrawalLevel += 50;
                    } else {
                        lvl.player.withdrawalLevel += 500;
                    }
                }
            }
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
            if (questionIndex < questionList.size()) {
                question = questionList.get(questionIndex++);
            } else {
                if (lvl.levelNum == 3) doneTherapy = true;
            }
        }
        if (doneTherapy) {
            if (rightCount < numOfQuestions) {
                resetQuestions();
                questionIndex = 0;
                doneTherapy = false;
                lvl.gameState = lvl.PLAY_STATE;
                lvl.therapyStarted = false;
                lvl.systemSpeaker.dialogue.clear();
                lvl.systemSpeaker.dialogue.add("Dr. Dockter#You did not pass the test.#Go complete another yoga challenge.");
                lvl.systemSpeaker.dialogue.add("Dr. Dockter#Come back and see me after.");
                lvl.systemSpeaker.speak();
                lvl.player.therapyChallenge = false;
                lvl.player.yogaChallenge = false;
                lvl.therapyStarted = false;
                lvl.yogaStarted = false;
            } else {
                lvl.player.therapyChallenge = true;
                lvl.gameState = lvl.PLAY_STATE;
                lvl.systemSpeaker.dialogue.clear();
                lvl.systemSpeaker.dialogue.add("Dr. Dockter#Alright, I think you’re ready to go back out into the real world.");
                lvl.systemSpeaker.dialogue.add("Dr. Dockter#Just remember, drug addiction is a chronic illness,#meaning it will never completely go away.");
                lvl.systemSpeaker.dialogue.add("Dr. Dockter#You will always feel a slight urge to take drugs but#it is important that you control yourself.");
                lvl.systemSpeaker.dialogue.add("Dr. Dockter#Relapsing back into drug usage is common, so don’t be#discouraged if it happens to you.");
                lvl.systemSpeaker.dialogue.add("Dr. Dockter#Just keep trying, and eventually, you will learn to control your desires.");
            }
            return;
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

        if (lvl.keyIn.enter && dialogueReady && question.selected != -1) {
            if (question.selected == question.answer) {
                question.complete = true;
                rightCount++;
                if (question.secondAttempt) {
                    showMessage("Latest debuff time has been halved");
                    if (lvl.gameState == lvl.BARRIER_QUESTION_STATE) {
                        lvl.player.barrierDebuffTimer += 300;
                    } else if (lvl.gameState == lvl.SPEED_QUESTION_STATE) {
                        lvl.player.speedDebuffTimer += 300;
                    }
                } else if (lvl.gameState != lvl.THERAPY_QUESTION_STATE) {
                    lvl.player.barrierDebuffTimer = 0;
                    lvl.player.speedDebuffTimer = 0;
                    showMessage("All debuffs cured");
                    lvl.assetSetter.barrierClear();
                }
            } else {
                if (question.secondAttempt) {
                    question.selected = -1;
                    question.complete = true;
                    showMessage("Failed to cure debuff");
                    if (lvl.gameState == lvl.BARRIER_QUESTION_STATE) {
                        lvl.player.barrierDebuffTimer += 600;
                    } else if (lvl.gameState == lvl.SPEED_QUESTION_STATE) {
                        lvl.player.speedDebuffTimer += 600;
                    }
                } else {
                    if (lvl.gameState == lvl.THERAPY_QUESTION_STATE) {
                        question.complete = true;
                        showMessage("Incorrect answer");
                    } else {
                        question.selected = -1;
                        question.secondAttempt = true;
                        showMessage("Incorrect answer this is your second attempt");
                    }
                }
            }
            dialogueReady = false;
        }

        if (question.complete) {
            lvl.gameState = lvl.PLAY_STATE;
            inQuestion = false;
        }
    }

    //displays the player's current progress in tasks as well as the withdrawal bar
    public void displayProgress() {
        if (lvl.levelNum == 1) {
            g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
            g2D.setColor(Color.BLACK);
            g2D.fillRect(lvl.tileSize - 10, lvl.tileSize - 20, (int) g2D.getFontMetrics().getStringBounds("Return to start", g2D).getWidth() + 20, 25);
            if (lvl.lvl1Sequence.size() == 0) {
                g2D.setColor(Color.BLACK);
                g2D.fillRect(lvl.tileSize - 10, (int) (lvl.tileSize * 1.5) - 20, (int) g2D.getFontMetrics().getStringBounds("Return to start", g2D).getWidth() + 20, 25);
                g2D.setColor(Color.WHITE);
                g2D.drawString("Return to start", lvl.tileSize, (int) (lvl.tileSize * 1.5));
            }
            g2D.setColor(Color.WHITE);
            g2D.drawString("Found: " + (8 - lvl.lvl1Sequence.size()) + " / 8", lvl.tileSize, lvl.tileSize);
        } else if (lvl.levelNum == 2) {
            g2D.setStroke(new BasicStroke(1));
            int endPoint = (lvl.maxWorldCols - 8) * lvl.tileSize;
            int startPoint = (int) (lvl.tileSize * 1.5);
            int distance = endPoint - startPoint;
            int progress = lvl.player.worldX - startPoint;
            int progressPercent = (int) (((double) progress / (double) distance) * 100);
            if (progressPercent > 100) progressPercent = 100;
            g2D.setColor(new Color(140, 132, 132, 150));
            g2D.fillRect(200, lvl.tileSize / 4, lvl.screenWidth - 400, lvl.tileSize / 2);
            g2D.setColor(new Color(121, 145, 180, 200));
            g2D.fillRect(200, lvl.tileSize / 4, (progressPercent * (lvl.screenWidth - 400)) / 100, lvl.tileSize / 2);
            g2D.setColor(Color.BLACK);
            g2D.drawRect(200, lvl.tileSize / 4, lvl.screenWidth - 400, lvl.tileSize / 2);
        } else if (lvl.levelNum == 3) {
            g2D.setFont(font1.deriveFont(Font.PLAIN, lvl.screenHeight / 30));
            g2D.setColor(Color.BLACK);
            int width = (int) g2D.getFontMetrics().getStringBounds("Talk to your Psychiatrist", g2D).getWidth() + 20;
            g2D.fillRect(lvl.tileSize - 10, lvl.tileSize - 20, width, 25);
            g2D.setColor(Color.WHITE);
            if (lvl.player.inRehab) {
                if (!lvl.player.yogaChallenge) {
                    g2D.drawString("Complete Yoga Challenge", lvl.tileSize, lvl.tileSize);
                    g2D.setColor(Color.BLACK);
                    g2D.fillRect(lvl.tileSize - 10, (int) (1.5 * lvl.tileSize) - 20, width, 25);
                    g2D.setColor(Color.WHITE);
                    if (!lvl.yogaStarted) {
                        g2D.drawString("Press Shift to start", lvl.tileSize, (int) (lvl.tileSize * 1.5));
                    } else {
                        g2D.drawString(instruction, lvl.tileSize, (int) (lvl.tileSize * 1.5));
                    }
                    if (lvl.keyIn.shift) {
                        lvl.yogaStarted = true;
                    }
                } else if (!lvl.player.therapyChallenge) {
                    g2D.drawString("Talk to your Psychiatrist", lvl.tileSize, lvl.tileSize);
                }
            } else {
                if (lvl.lvl3Sequence.size() != 0) {
                    g2D.drawString("Talk to People: " + (7 - lvl.lvl3Sequence.size()) + " / 7", lvl.tileSize, lvl.tileSize);
                }
            }
            //draw a blue rectangle to indicate the progress
            int percentWithdrawal = lvl.player.withdrawalLevel / 1000;
            g2D.setStroke(new BasicStroke(1));
            g2D.setColor(new Color(121, 145, 180, 200));
            g2D.fillRect(200, lvl.screenHeight - lvl.tileSize, (percentWithdrawal * (lvl.screenWidth - 400)) / 100, lvl.tileSize / 2);
            g2D.setColor(Color.BLACK);
            g2D.drawRect(200, lvl.screenHeight - lvl.tileSize, lvl.screenWidth - 400, lvl.tileSize / 2);
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
}
