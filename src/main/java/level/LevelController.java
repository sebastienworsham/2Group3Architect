package level;

import entities.Coin;
import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.Game;
import main.UserService;


import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import static main.Main.*;
import static main.StartScreen.playerInstance;

public class LevelController {
    private static String[] currentLevelArray;
    private int levelWidth;
    private Pane levelRoot;
    public static ArrayList<Node> platforms = new ArrayList<Node>();
    public static ArrayList<Node> jumpThroughPlatforms = new ArrayList<Node>();
    public static ArrayList<Node> resetBlocks = new ArrayList<Node>();
    public static ArrayList<Node> topScreenBorderBlocks = new ArrayList<Node>();
    public static ArrayList<Node> rightScreenBorderBlocks = new ArrayList<Node>();
    public static ArrayList<Node> leftScreenBorderBlocks = new ArrayList<Node>();
    public static ArrayList<Node> bottomScreenBorderBlocks = new ArrayList<Node>();
    int currentLevelNum;
    Coin coin;
    Game game;
    Stage stage;
    UserService userService;
    Label elapsedTimeLabel;
    Long startTime;

    public LevelController(Pane levelRoot) {
        this.levelRoot = levelRoot;
        currentLevelNum = 1;
    }

    public LevelController(Pane levelRoot, int currentLevelNum, Coin coin, Game game, Stage stage, UserService userService, Label elapsedTimeLabel) {
        this.levelRoot = levelRoot;
        this.currentLevelNum = currentLevelNum;
        this.coin = coin;
        this.game = game;
        this.stage = stage;
        this.userService = userService;
        this.elapsedTimeLabel = elapsedTimeLabel;
    }

    public void nextLevel() {
        playerInstance.resetPlayerPosition(); //puts player in top left of screen
        levelRoot.getChildren().clear(); //clears previous level
        currentLevelNum += 1;
        // levelRoot.setTranslateX(0);
        // levelRoot.setTranslateY(0);

        platforms.clear(); //clears previous level collision information
        jumpThroughPlatforms.clear(); //clears previous level collision information
        resetBlocks.clear(); //clears previous level collision information
        topScreenBorderBlocks.clear();
        rightScreenBorderBlocks.clear();
        leftScreenBorderBlocks.clear();
        bottomScreenBorderBlocks.clear();

        switch (currentLevelNum) {
            case 1:
                currentLevelArray = LevelInfo.LEVEL1.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            case 2:
                currentLevelArray = LevelInfo.LEVEL2.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            case 3:
                currentLevelArray = LevelInfo.LEVEL3.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            case 4:
                currentLevelArray = LevelInfo.LEVEL4.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            case 5:
                currentLevelArray = LevelInfo.LEVEL5.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            default:
                currentLevelArray = LevelInfo.LEVEL1.clone();
                currentLevelNum = 1;
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
        }
        renderLevel();
        coin.renderCoin(currentLevelArray);
    }

    public void nextLevel(String[] currentUser) {
        playerInstance.resetPlayerPosition(); //puts player in top left of screen
        levelRoot.getChildren().clear(); //clears previous level
        currentLevelNum += 1;
        // levelRoot.setTranslateX(0);
        // levelRoot.setTranslateY(0);

        platforms.clear(); //clears previous level collision information
        jumpThroughPlatforms.clear(); //clears previous level collision information
        resetBlocks.clear(); //clears previous level collision information
        topScreenBorderBlocks.clear();
        rightScreenBorderBlocks.clear();
        leftScreenBorderBlocks.clear();
        bottomScreenBorderBlocks.clear();
        setupTimeThread();

        switch (currentLevelNum) {
            case 1:
                currentLevelArray = LevelInfo.LEVEL1.clone();
                System.out.print(currentLevelNum);
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            case 2:
                currentLevelArray = LevelInfo.LEVEL2.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            case 3:
                currentLevelArray = LevelInfo.LEVEL3.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            case 4:
                currentLevelArray = LevelInfo.LEVEL4.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            case 5:
                currentLevelArray = LevelInfo.LEVEL5.clone();
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
            default:
                currentLevelArray = LevelInfo.LEVEL1.clone();
                currentLevelNum = 1;
                stage.show();
                stage.setTitle("Level " + Integer.toString(currentLevelNum));
                break;
        }
        renderLevel();
        coin.renderCoin(currentLevelArray);
    }


    /*
    Setup the thread to start counting seconds and update the label text
    */
    private void setupTimeThread() {
        // Start the timer thread
        Thread timerThread = new Thread(() -> {
            startTime = System.currentTimeMillis();
            while (true) {
                try {
                    long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                    String timeText = "Time spent: " + elapsedTime + " seconds";
                    // Update the label on the JavaFX Application Thread
                    Platform.runLater(() -> elapsedTimeLabel.setText(timeText));
                    // Sleep for a second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.setDaemon(true); // Set the thread as a daemon
        timerThread.start();
    }

    public void renderLevel() {
        levelWidth = currentLevelArray[0].length() * 60;

        for (int i = 0; i < currentLevelArray.length; i++) {
            String currentLine = currentLevelArray[i];
            for (int j = 0; j < currentLine.length(); j++) {
                if (currentLine.charAt(j) == '1') { //normal block
                    Node platform = drawRectangle(j * 60, i * 60, 60, 60, Color.BLACK);
                    platforms.add(platform);
                }
                if (currentLine.charAt(j) == '2') { //Jump through platform
                    Node jumpThroughPlatform = drawRectangle(j * 60, i * 60, 60, 20, Color.BLACK);
                    jumpThroughPlatforms.add(jumpThroughPlatform);
                }
                if (currentLine.charAt(j) == '3') { //kill block
                    Node resetBlock = drawRectangle(j * 60, i * 60 + 40, 60, 20, Color.RED);
                    resetBlocks.add(resetBlock);
                }
                if (currentLine.charAt(j) == '4') { //top screen border
                    Node topScreenBorderBlock = drawRectangle(j * 60, i * 60 - 60, 60, 60, Color.BLACK);
                    topScreenBorderBlocks.add(topScreenBorderBlock);
                }
                if (currentLine.charAt(j) == '5') { //right screen border
                    Node rightScreenBorderBlock = drawRectangle(j * 60 + 60, i * 60, 60, 60, Color.BLACK);
                    rightScreenBorderBlocks.add(rightScreenBorderBlock);
                }
                if (currentLine.charAt(j) == '6') { //left screen border
                    Node leftScreenBorderBlock = drawRectangle(j * 60 - 60, i * 60, 60, 60, Color.BLACK);
                    leftScreenBorderBlocks.add(leftScreenBorderBlock);
                }
                if (currentLine.charAt(j) == '7') { //bottom screen border
                    Node bottomScreenBorderBlock = drawRectangle(j * 60, i * 60 + 60, 60, 60, Color.BLACK);
                    bottomScreenBorderBlocks.add(bottomScreenBorderBlock);
                }
            }
        }
    }

    public Node drawRectangle(int x, int y, int w, int h, Color color) {
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        levelRoot.getChildren().add(entity);
        return entity;
    }

    public void saveScore(String[] currentUser) {
        int temp = game.getScore();
        temp += 1;
        game.setScore(temp);
        System.out.println("Score: " + game.getScore());

        currentUser[1] = String.valueOf(game.getScore());
        //users.set(1, currentUser);
    }

    public int currentLevel() {
        return currentLevelNum;
    }


}
