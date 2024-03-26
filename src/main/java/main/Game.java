package main;

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
import level.LevelController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static main.Main.*;
import static main.StartScreen.playerInstance;

public class Game {

    private Pane levelRoot;
    public static LevelController levelController;
    Scene gameScene;
    private static HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private static boolean isPressed(KeyCode key) { return keys.getOrDefault(key, false); }
    boolean nKeyPressed;
    boolean mKeyPressed;
    static Coin coin;
    int score;
    static String[] currentUser;



    public Game(Pane levelRoot, Scene gameScene, String[] currentUser) {
        this.levelRoot = levelRoot;
        this.gameScene = gameScene;
        this.currentUser = currentUser;
    }

    public void startGame(Stage primaryStage,int startLevelNum, String[] currentUser) {
        coin = new Coin(levelRoot, 100,100);
        setScore(Integer.parseInt(currentUser[1]));
        levelController = new LevelController(levelRoot, startLevelNum, coin, this, primaryStage);
        levelController.nextLevel(currentUser);

        gameScene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        gameScene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        primaryStage.setScene(gameScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            saveOnClose();
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop();
            }
        };
        timer.start();
    }
    private void gameLoop() {
        if (isPressed(KeyCode.SPACE) || isPressed(KeyCode.UP)) {
            playerInstance.jumpPlayer();
        }
        if (isPressed(KeyCode.A) || isPressed(KeyCode.LEFT)) {
            playerInstance.movePlayerX(-5);
        }
        if (isPressed(KeyCode.D) || isPressed(KeyCode.RIGHT)) {
            playerInstance.movePlayerX(5);
        }

        if (Player.pVelocityY < 10) {
            Player.changePVelocityY(1);
        }
        playerInstance.movePlayerY(Player.pVelocityY);

        if (coin.isTouchingPlayer(playerInstance.getPlayer())) {
            levelController.nextLevel(currentUser);
            levelController.saveScore(currentUser);
        }
    }
    public static void triggerJumpAction() {
        // Simulate pressing the jump key
        keys.put(KeyCode.SPACE, true);
    }
    public static void ifSpacePressed() {
        if (isPressed(KeyCode.SPACE) || isPressed(KeyCode.UP)) {
            playerInstance.jumpPlayer();
        }
    }
    private void saveOnClose() {
        if (StartScreen.newUser) {
            users.add(new String[] { StartScreen.currentUserName, String.valueOf(score)});
            try {
                FileOutputStream fos = new FileOutputStream(SAVEPATH);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(users);
                oos.close();
            } catch (IOException e) {
                System.err.print(e.getMessage());
            }
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(SAVEPATH);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(users);
                oos.close();
            } catch (IOException e) {
                System.err.print(e.getMessage());
            }
        }
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}

