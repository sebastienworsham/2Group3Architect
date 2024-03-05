package main;

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
import level.LevelController;

import java.util.ArrayList;
import java.util.HashMap;

import static main.Main.*;
import static main.StartScreen.playerInstance;

public class Game {
    private Pane levelRoot;
    public LevelController levelController;
    Scene gameScene;
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private boolean isPressed(KeyCode key) { return keys.getOrDefault(key, false); }
    boolean nKeyPressed;
    boolean mKeyPressed;


    public Game(Pane levelRoot, Scene gameScene) {
        this.levelRoot = levelRoot;
        this.gameScene = gameScene;
    }

    public void startGame(Stage primaryStage,int startLevelNum) {
        levelController = new LevelController(levelRoot, startLevelNum);
        levelController.nextLevel();
 //       levelController.scrollLevel(playerInstance.getPlayer());

        gameScene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        gameScene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        primaryStage.setScene(gameScene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkKeyInput();
            }
        };
        timer.start();
    }
    private void checkKeyInput() {
        if (isPressed(KeyCode.W)) {
            playerInstance.jumpPlayer();
        }
        if (isPressed(KeyCode.A)) {
            playerInstance.movePlayerX(-5);
        }
        if (isPressed(KeyCode.D)) {
            playerInstance.movePlayerX(5);
        }
        if (Player.pVelocityY < 10) {
            Player.changePVelocityY(1);
        }
        playerInstance.movePlayerY(Player.pVelocityY);

        if (isPressed(KeyCode.N) && !nKeyPressed) { //use n key for next level
            levelController.nextLevel();
            nKeyPressed = true;
        } else if (!isPressed(KeyCode.N)) {
            nKeyPressed = false;
        }
        if (isPressed(KeyCode.M) && !mKeyPressed) { //use m key to save game
            levelController.saveCurrentLevel();
            mKeyPressed = true;
        } else if (!isPressed(KeyCode.M)) {
            mKeyPressed = false;
        }
    }
}
