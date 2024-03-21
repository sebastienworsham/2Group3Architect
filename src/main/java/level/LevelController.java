package level;

import entities.Coin;
import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.Game;


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
    int currentLevelNum;
    Coin coin;
    Game game;
    Stage stage;

    public LevelController(Pane levelRoot) {
        this.levelRoot = levelRoot;
        currentLevelNum = 1;
    }
    public LevelController(Pane levelRoot, int currentLevelNum, Coin coin, Game game, Stage stage) {
        this.levelRoot = levelRoot;
        this.currentLevelNum = currentLevelNum;
        this.coin = coin;
        this.game = game;
        this.stage = stage;
    }

    public void nextLevel() {
        playerInstance.resetPlayerPosition(); //puts player in top left of screen
        levelRoot.getChildren().clear(); //clears previous level
        currentLevelNum += 1;
        // levelRoot.setTranslateX(0);
        // levelRoot.setTranslateY(0);

        platforms.clear(); //clears previous level collision information

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

    public void renderLevel() {
        levelWidth = currentLevelArray[0].length() * 60;

        for (int i=0; i<currentLevelArray.length; i++) {
            String currentLine = currentLevelArray[i];
            for (int j=0; j<currentLine.length(); j++) {
                if(currentLine.charAt(j) == '1') {
                    Node platform = drawRectangle(j*60, i*60, 60, 60, Color.BLACK);
                    platforms.add(platform);
                }
            }
        }
    }

    public Node drawRectangle (int x, int y, int w, int h, Color color) {
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


}