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

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    static final int GAMEWIDTH = 1280;
    static final int GAMEHEIGHT = 720;
    protected Pane gameRoot = new Pane();
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private boolean isPressed(KeyCode key) { return keys.getOrDefault(key, false); }
    protected Game game;

    Player playerInstance;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(gameRoot, GAMEWIDTH, GAMEHEIGHT);
        game = new Game(gameRoot);
        game.startGame();

        playerInstance = new Player(gameRoot);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        primaryStage.setScene(scene);
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
    }
}
