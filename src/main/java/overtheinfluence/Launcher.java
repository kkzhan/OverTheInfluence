package overtheinfluence;

import javafx.animation.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.*;

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
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Launcher extends Application {
    //File to store game progress after program exits - to be added in the future
    //Game class variable - to be implemented in the future
    /**
     * checks whether or not a previous game has been started
     */
    boolean gameStarted = false;

    /**
     * this is where everything is displayed
     */
    private Stage stage;

    /**
     * this is the graphics context for the game needed to update the display
     */
    private GraphicsContext gc;

    @Override
    public void start(Stage mainStage) {
        stage = mainStage;
        processFile();
        stage.setTitle("Over the Influence");
        stage.setResizable(false);
        Canvas canvas = new Canvas(1200, 800);
        gc = canvas.getGraphicsContext2D();
        stage.setScene(new Scene(new Group(canvas)));
        stage.show();
        stage.setOnCloseRequest(e -> {
            exitProgram();
            e.consume();
        });
        splashScreen(); //the stage and gc are passed on so that everything is on the same screen
    }

    /**
     * This method displays the splash screen of the game
     */
    private void splashScreen() {
//        gc.setFill(Color.BLACK);
//        gc.fillRect(0, 0, 1200, 800);
//        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70));
//        Image logo = new Image("logo.png");
//        gc.drawImage(logo, 475, 200, 250, 250);
//        gc.setFill(Color.WHITE);
//        gc.fillText("Digital Athletics Inc.", 235, 500);
//        Rectangle rect = new Rectangle(0, 0, 1200, 800);
//        rect.setFill(Color.BLACK);
//
//        double op = 0;
//        while(rect.getOpacity() < 1) {
//            op += 0.01;
//            rect.setOpacity(op);
//        }
//
//        while(rect.getOpacity() > 0) {
//            op -= 0.01;
//            rect.setOpacity(op);
//        }
//
//        gc.setFill(Color.rgb(89, 89, 89));
//        gc.fillRect(0, 0, 1200, 800);
//        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70));
//        Image gameSplash = new Image("gameSplash.png");
//        gc.drawImage(gameSplash, 475, 350, 250, 250);
//        gc.setFill(Color.WHITE);
//        gc.fillText("Over the Influence.", 250, 275);
//        Rectangle rect2 = new Rectangle(0, 0, 1200, 800);
//        rect2.setFill(Color.BLACK);
//
//        op = 0;
//        while(rect2.getOpacity() < 1) {
//            op += 0.01;
//            rect2.setOpacity(op);
//        }
//
//        while(rect2.getOpacity() > 0) {
//            op -= 0.01;
//            rect2.setOpacity(op);
//        }

        mainMenu();
    }

    /**
     * This method displays the main menu of the game which provides access to the game, instructions, and credits
     */
    private void mainMenu() {
        gc.setFill(Color.rgb(89, 89, 89)); //background
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70)); //title
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Over the Influence", 600, 150);

        //creates 4 buttons
        Button buttonArr[] = new Button[4];
        for (int i = 0; i < 4; i++) {
            buttonArr[i] = new Button();
            //height, width, and text are same for all buttons
            buttonArr[i].setMinHeight(110);
            buttonArr[i].setMinWidth(1000);
            buttonArr[i].setFont(Font.font("Arial", FontWeight.THIN, 60));
            buttonArr[i].setTextFill(Color.WHITE);
        }

        //sets the text for each button
        buttonArr[0].setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        buttonArr[0].setText("Play");
        //create hover effect
        buttonArr[0].setOnMouseEntered(e -> {
            buttonArr[0].setStyle("-fx-background-color: #509123; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        buttonArr[0].setOnMouseExited(e -> {
            buttonArr[0].setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        buttonArr[0].setOnAction(e -> {
            playGame();
        });

        buttonArr[1].setStyle("-fx-background-color: #c09404; -fx-border-width: 3; -fx-border-color: #000000;");
        buttonArr[1].setText("Instructions");
        buttonArr[1].setOnMouseEntered(e -> {
            buttonArr[1].setStyle("-fx-background-color: #e3b009; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        buttonArr[1].setOnMouseExited(e -> {
            buttonArr[1].setStyle("-fx-background-color: #c09404; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        buttonArr[1].setOnAction(e -> {
            insControl();
        });

        buttonArr[2].setStyle("-fx-background-color: #105494; -fx-border-width: 3; -fx-border-color: #000000;");
        buttonArr[2].setText("Credits");
        buttonArr[2].setOnMouseEntered(e -> {
            buttonArr[2].setStyle("-fx-background-color: #167dde; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        buttonArr[2].setOnMouseExited(e -> {
            buttonArr[2].setStyle("-fx-background-color: #105494; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        buttonArr[2].setOnAction(e -> credits());

        buttonArr[3].setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        buttonArr[3].setText("Exit Game");
        buttonArr[3].setOnMouseEntered(e -> {
            buttonArr[3].setStyle("-fx-background-color: #c20202; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        buttonArr[3].setOnMouseExited(e -> {
            buttonArr[3].setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        //create the vbox that contains the buttons
        //this is done at this stage so that the button press can affect the vbox
        VBox vbox = new VBox(20, buttonArr[0], buttonArr[1], buttonArr[2], buttonArr[3]); //create vbox
        buttonArr[3].setOnAction(e -> {
            for (int i = 0; i < 4; i++) {
                buttonArr[i].setDisable(true); //disable all buttons
            }
            if (!exitProgram()) {
                for (int i = 0; i < 4; i++) {
                    buttonArr[i].setDisable(false); //enable all buttons
                }
            }
        });
        vbox.setAlignment(Pos.CENTER);
        vbox.setMargin(buttonArr[0], new Insets(230, 100, 0, 100));
        ((Group) stage.getScene().getRoot()).getChildren().add(vbox);
    }

    /**
     * This method is used to create the game starting screen
     */
    private void playGame() {
        //clearing screen and setting up the background
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1);
        }
        gc.setFill(Color.rgb(89, 89, 89));
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70)); //title
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Play", 600, 150);

        //creating the buttons
        Button startBtn = new Button();
        Button level1Btn = new Button();
        Button level2Btn = new Button();
        Button level3Btn = new Button();

        Button btnArr[] = {startBtn, level1Btn, level2Btn, level3Btn};
        for (int i = 0; i < 4; i++) {
            btnArr[i].setMinHeight(110);
            btnArr[i].setMinWidth(500);
            btnArr[i].setStyle("-fx-background-color: #a09c9c; -fx-border-width: 3; -fx-border-color: #000000;");
            int finalI = i;
            btnArr[i].setOnMouseEntered(e -> {
                btnArr[finalI].setStyle("-fx-background-color: #c2bebe; -fx-border-width: 3; -fx-border-color: #000000;");
            });
            btnArr[i].setOnMouseExited(e -> {
                btnArr[finalI].setStyle("-fx-background-color: #a09c9c; -fx-border-width: 3; -fx-border-color: #000000;");
            });
            if (i > 0) {
                btnArr[i].setDisable(true);
            }
            btnArr[i].setFont(Font.font("Arial", FontWeight.THIN, 40));
            btnArr[i].setTextFill(Color.WHITE);
        }

        startBtn.setText("Start New Game");
        startBtn.setLayoutX(650);
        startBtn.setLayoutY(370);
        startBtn.setOnAction(e -> {
            closeLauncher();
            //start a new game
        });
        level1Btn.setText("Resume Level 1");
        level1Btn.setOnAction(e -> {
            closeLauncher();
            //resume from level 1
        });
        level2Btn.setText("Resume Level 2");
        level2Btn.setOnAction(e -> {
            closeLauncher();
            //resume from level 2
        });
        level3Btn.setText("Resume Level 3");
        level3Btn.setOnAction(e -> {
            closeLauncher();
            //resume from level 3
        });

        VBox vbox = new VBox(20, level1Btn, level2Btn, level3Btn);
        vbox.setLayoutX(50);
        vbox.setLayoutY(240);
        ((Group) stage.getScene().getRoot()).getChildren().addAll(startBtn, vbox);

        //check if there is a saved game
        if (gameStarted) {
            level1Btn.setDisable(false);
//            if(level 1 is complete) {
//                level2Btn.setDisable(false);
//                if(level 2 is complete) {
//                    level3Btn.setDisable(false);
//                }
//            }
        }

        //back button
        Button backBtn = new Button("Back");
        backBtn.setLayoutX(50);
        backBtn.setLayoutY(700);
        backBtn.setMinWidth(200);
        backBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        backBtn.setOnMouseEntered(e -> {
            backBtn.setStyle("-fx-background-color: #c20202; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnMouseExited(e -> {
            backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnAction(e -> {
            cleanUp();
            mainMenu();
        });

        ((Group) stage.getScene().getRoot()).getChildren().add(backBtn);
    }

    /**
     * This method is used to create and display the control instructions
     */
    private void insControl() {
        //clearing screen and setting up the background
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1);
        }
        gc.setFill(Color.rgb(89, 89, 89));
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70)); //title
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Instructions: Controls", 600, 150);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setFont(Font.font("Arial", FontWeight.THIN, 40)); //instructions
        gc.fillText("Move Up:", 500, 250);
        gc.fillText("Move Left:", 500, 330);
        gc.fillText("Move Down:", 500, 410);
        gc.fillText("Move Right:", 500, 490);
        gc.fillText("Interact:", 500, 570);
        gc.fillText("Click Button:", 500, 650);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("W", 700, 250);
        gc.fillText("A", 700, 330);
        gc.fillText("S", 700, 410);
        gc.fillText("D", 700, 490);
        gc.fillText("Space Bar", 700, 570);
        gc.fillText("Left Mouse Button", 700, 650);

        //continue and back buttons
        Button continueBtn = new Button("Continue");
        continueBtn.setLayoutX(950);
        continueBtn.setLayoutY(700);
        continueBtn.setMinWidth(200);
        continueBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        continueBtn.setTextFill(Color.WHITE);
        continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        continueBtn.setOnMouseEntered(e -> {
            continueBtn.setStyle("-fx-background-color: #509123; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnMouseExited(e -> {
            continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnAction(e -> {
            cleanUp();
            insLvl1();
        });

        Button backBtn = new Button("Back");
        backBtn.setLayoutX(50);
        backBtn.setLayoutY(700);
        backBtn.setMinWidth(200);
        backBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        backBtn.setOnMouseEntered(e -> {
            backBtn.setStyle("-fx-background-color: #c20202; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnMouseExited(e -> {
            backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnAction(e -> {
            cleanUp();
            mainMenu();
        });

        ((Group) stage.getScene().getRoot()).getChildren().addAll(continueBtn, backBtn);
    }

    /**
     * This method is used to create and display the Level 1 instructions
     */
    private void insLvl1() {
        //clearing screen and setting up the background
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1);
        }
        gc.setFill(Color.rgb(89, 89, 89));
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70)); //title
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Instructions: Level 1", 600, 150);
        gc.setFont(Font.font("Arial", FontWeight.THIN, 30)); //instructions
        gc.fillText("During level 1, the player will learn about drug addiction, ", 600, 250);
        gc.fillText("and its effects. They are to interact with a certain number of ", 600, 310);
        gc.fillText("people and objects, who will teach them about addiction. ", 600, 370);
        gc.fillText("Downward facing arrows will appear above objects and people", 600, 430);
        gc.fillText("that can be interacted with. To pass level one, the player must ", 600, 490);
        gc.fillText("interact with all the entities and head to their room.", 600, 550);

        //continue and back buttons
        Button continueBtn = new Button("Continue");
        continueBtn.setLayoutX(950);
        continueBtn.setLayoutY(700);
        continueBtn.setMinWidth(200);
        continueBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        continueBtn.setTextFill(Color.WHITE);
        continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        continueBtn.setOnMouseEntered(e -> {
            continueBtn.setStyle("-fx-background-color: #509123; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnMouseExited(e -> {
            continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnAction(e -> {
            cleanUp();
            insLvl2();
        });

        Button backBtn = new Button("Back");
        backBtn.setLayoutX(50);
        backBtn.setLayoutY(700);
        backBtn.setMinWidth(200);
        backBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        backBtn.setOnMouseEntered(e -> {
            backBtn.setStyle("-fx-background-color: #c20202; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnMouseExited(e -> {
            backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnAction(e -> {
            cleanUp();
            insControl();
        });

        ((Group) stage.getScene().getRoot()).getChildren().addAll(continueBtn, backBtn);
    }

    /**
     * This method is used to create and display the Level 2 instructions
     */
    private void insLvl2() {
        //clearing screen and setting up the background
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1);
        }
        gc.setFill(Color.rgb(89, 89, 89));
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70)); //title
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Instructions: Level 2", 600, 150);
        gc.setFont(Font.font("Arial", FontWeight.THIN, 30)); //instructions
        gc.fillText("During level 2, the player will have to dodge various", 600, 250);
        gc.fillText("attacks while trying to reach the end within a given time limit.", 600, 310);
        gc.fillText("If hit by an attack, or for every set interval, the player will ", 600, 370);
        gc.fillText("receive a debuff. The player will have 2 tries to answer a", 600, 430);
        gc.fillText("question testing their knowledge. Depending on their success", 600, 490);
        gc.fillText("in answering the question, they may be able to cure the debuff", 600, 550);

        //continue and back buttons
        Button continueBtn = new Button("Continue");
        continueBtn.setLayoutX(950);
        continueBtn.setLayoutY(700);
        continueBtn.setMinWidth(200);
        continueBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        continueBtn.setTextFill(Color.WHITE);
        continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        continueBtn.setOnMouseEntered(e -> {
            continueBtn.setStyle("-fx-background-color: #509123; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnMouseExited(e -> {
            continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnAction(e -> {
            cleanUp();
            insLvl3();
        });

        Button backBtn = new Button("Back");
        backBtn.setLayoutX(50);
        backBtn.setLayoutY(700);
        backBtn.setMinWidth(200);
        backBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        backBtn.setOnMouseEntered(e -> backBtn.setStyle("-fx-background-color: #c20202; -fx-border-width: 3; -fx-border-color: #000000;"));
        backBtn.setOnMouseExited(e ->
                backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;"));
        backBtn.setOnAction(e -> {
            cleanUp();
            insLvl1();
        });

        ((Group) stage.getScene().getRoot()).getChildren().addAll(continueBtn, backBtn);
    }

    /**
     * This method is used to create and display the Level 3 instructions
     */
    private void insLvl3() {
        //clearing screen and setting up the background
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1);
        }
        gc.setFill(Color.rgb(89, 89, 89));
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70)); //title
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Instructions: Level 3", 600, 150);
        gc.setFont(Font.font("Arial", FontWeight.THIN, 30)); //instructions
        gc.fillText("During level 3, the player must try to treat their addiction", 600, 250);
        gc.fillText("through rehabilitation. They must try to pass through a series of", 600, 310);
        gc.fillText("challenges in the rehabilitation process. Then, they completing", 600, 370);
        gc.fillText("their rehabilitation, they must complete some tasks while trying to", 600, 430);
        gc.fillText("avoid relapsing. If they complete all of the tasks successfully, they", 600, 490);
        gc.fillText("will win the game. However, if they relapse, they must restart the", 600, 550);
        gc.fillText("level. Relapses may also cause overdoses which leads to hospitalization.", 600, 610);
        gc.fillText("The player must avoid hospitalization because it is GAME OVER.", 600, 670);

        //continue and back buttons
        Button continueBtn = new Button("Continue");
        continueBtn.setLayoutX(950);
        continueBtn.setLayoutY(700);
        continueBtn.setMinWidth(200);
        continueBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        continueBtn.setTextFill(Color.WHITE);
        continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        continueBtn.setOnMouseEntered(e -> {
            continueBtn.setStyle("-fx-background-color: #509123; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnMouseExited(e -> {
            continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnAction(e -> {
            cleanUp();
            mainMenu();
        });

        Button backBtn = new Button("Back");
        backBtn.setLayoutX(50);
        backBtn.setLayoutY(700);
        backBtn.setMinWidth(200);
        backBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        backBtn.setOnMouseEntered(e -> {
            backBtn.setStyle("-fx-background-color: #c20202; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnMouseExited(e -> {
            backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnAction(e -> {
            cleanUp();
            insLvl2();
        });

        ((Group) stage.getScene().getRoot()).getChildren().addAll(continueBtn, backBtn);
    }

    /**
     * This method is used to create the credits screen
     */
    private void credits() {
        //clearing screen and setting up the background
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1);
        }
        gc.setFill(Color.rgb(89, 89, 89));
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 70)); //title
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Credits", 600, 100);

        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setFont(Font.font("Arial", FontWeight.THIN, 30)); //instructions
        gc.fillText("Project Lead:", 550, 250);
        gc.fillText("Project Member:", 550, 300);
        gc.fillText("Game Designers:", 550, 350);
        gc.fillText("Story Director:", 550, 400);
        gc.fillText("Art Director:", 550, 450);
        gc.fillText("Production Director:", 550, 500);
        gc.fillText("Level Design Director:", 550, 550);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Kevin Zhan", 650, 250);
        gc.fillText("Alexander Peng", 650, 300);
        gc.fillText("Kevin Zhan & Alexander Peng", 650, 350);
        gc.fillText("Kevin Zhan", 650, 400);
        gc.fillText("Alexander Peng", 650, 450);
        gc.fillText("Kevin Zhan", 650, 500);
        gc.fillText("Alexander Peng", 650, 550);

        //back button
        Button backBtn = new Button("Back");
        backBtn.setLayoutX(50);
        backBtn.setLayoutY(700);
        backBtn.setMinWidth(200);
        backBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        backBtn.setOnMouseEntered(e -> {
            backBtn.setStyle("-fx-background-color: #c20202; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnMouseExited(e -> {
            backBtn.setStyle("-fx-background-color: #a00404; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        backBtn.setOnAction(e -> {
            cleanUp();
            mainMenu();
        });

        ((Group) stage.getScene().getRoot()).getChildren().add(backBtn);
    }

    /**
     * This method is used to confirm the user's choice to quit the game and exit the program
     */
    private boolean exitProgram() {
        //confirmation message
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Click OK to exit, or Cancel to stay.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            //erases the title and replaces with the exit message
            gc.setFill(Color.rgb(89, 89, 89));
            gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Arial Black", FontWeight.BOLD, 100));
            gc.fillText("Thanks for playing!", 600, 420);
            cleanUp();
            saveGame(); //save the game
            stage.setOnCloseRequest(e -> e.consume()); //prevents the user from closing the window
            //pause for a second before closing
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
            return true;
        }
        return false;
    }

    /**
     * This method displays the screen when the player wins
     */
    private void gameSuccess() {
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1);
        }
        gc.setFill(Color.rgb(89, 89, 89));
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 100));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Congratulations!", 600, 200);
        gc.setFont(Font.font("Arial", FontWeight.THIN, 50));
        gc.fillText("You successfully overcome your", 600, 400);
        gc.fillText("addiction and are now living a happy life!", 600, 500);

        //continue button
        Button continueBtn = new Button("Continue");
        continueBtn.setLayoutX(950);
        continueBtn.setLayoutY(700);
        continueBtn.setMinWidth(200);
        continueBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        continueBtn.setTextFill(Color.WHITE);
        continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        continueBtn.setOnMouseEntered(e -> {
            continueBtn.setStyle("-fx-background-color: #509123; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnMouseExited(e -> {
            continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnAction(e -> {
            cleanUp();
            mainMenu();
        });
    }

    /**
     * This method displays the screen when the player loses
     */
    private void gameOver() {
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1);
        }
        gc.setFill(Color.rgb(0, 0, 0));
        gc.fillRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 100));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Game Over", 600, 200);
        gc.setFont(Font.font("Arial", FontWeight.THIN, 50));
        gc.fillText("Oh no! You relapsed and overdosed.", 600, 400);
        gc.fillText("You have been hospitalized.", 600, 500);

        //continue button
        Button continueBtn = new Button("Continue");
        continueBtn.setLayoutX(950);
        continueBtn.setLayoutY(700);
        continueBtn.setMinWidth(200);
        continueBtn.setFont(Font.font("Arial", FontWeight.THIN, 30));
        continueBtn.setTextFill(Color.WHITE);
        continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        continueBtn.setOnMouseEntered(e -> {
            continueBtn.setStyle("-fx-background-color: #509123; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnMouseExited(e -> {
            continueBtn.setStyle("-fx-background-color: #40741c; -fx-border-width: 3; -fx-border-color: #000000;");
        });
        continueBtn.setOnAction(e -> {
            cleanUp();
            mainMenu();
        });

        ((Group) stage.getScene().getRoot()).getChildren().add(continueBtn);
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
        stage.hide();
    }

    /**
     * This method opens the launcher when the player finishes or leaves the game
     */
    public void openLauncher() {
        cleanUp();
        mainMenu();
        stage.show();
    }

    /**
     * This method will be used to clean up the launcher screen in between pages
     */
    private void cleanUp() {
        //when there are components other than the background
        while (((Group) stage.getScene().getRoot()).getChildren().size() > 1) {
            ((Group) stage.getScene().getRoot()).getChildren().remove(1); //removes components
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}