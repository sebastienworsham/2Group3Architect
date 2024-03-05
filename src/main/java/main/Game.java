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


    public Game(Pane levelRoot, Scene gameScene) {
        this.levelRoot = levelRoot;
        this.gameScene = gameScene;
    }

    public void startGame(Stage primaryStage) {
        levelController = new LevelController(levelRoot);
        levelController.nextLevel();
        levelController.scrollLevel(playerInstance.getPlayer());

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
        if (isPressed(KeyCode.SPACE)) {
            playerInstance.jumpPlayer();
        }
        if (isPressed(KeyCode.A)) {
            playerInstance.movePlayerX(-5);
        }
        if (isPressed(KeyCode.D)) {
            playerInstance.movePlayerX(5);
        }
        if (playerInstance.getPlayerX() > 800) {
            levelController.nextLevel();
        }
        if (Player.pVelocityY < 10) {
            Player.changePVelocityY(1);
        }
        playerInstance.movePlayerY(Player.pVelocityY);
    }
}
