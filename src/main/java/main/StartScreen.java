package main;

import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

import static main.Main.GAMEHEIGHT;
import static main.Main.GAMEWIDTH;

public class StartScreen {
    Pane gameRoot;
    Stage primaryStage;
    protected Game game;
    static Player playerInstance;
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private boolean isPressed(KeyCode key) { return keys.getOrDefault(key, false); }
    public StartScreen(Pane uiRoot, Pane gameRoot, Stage primaryStage) {
        this.gameRoot = gameRoot;
        this.primaryStage = primaryStage;

        renderStartScreen(uiRoot);
    }

    void renderStartScreen(Pane uiRoot) {
        uiRoot.setStyle("-fx-background-color: black;");
        uiRoot.setPrefSize(GAMEWIDTH, GAMEHEIGHT);

        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load Game");
        newGameButton.setOnAction(event -> {
            setupGameScene();
        });

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(newGameButton, loadGameButton);
        uiRoot.getChildren().add(vbox);
    }

    private void setupGameScene() {
        Scene gameScene = new Scene(gameRoot, GAMEWIDTH, GAMEHEIGHT);


        playerInstance = new Player(gameRoot);
        game = new Game(gameRoot);
        game.startGame();

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
    }
}
