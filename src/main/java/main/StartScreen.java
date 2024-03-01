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
    Pane uiRoot;
    Pane levelRoot;
    Pane playerRoot;
    Stage primaryStage;
    protected Game game;
    public static Player playerInstance;

    public StartScreen(Pane uiRoot, Pane levelRoot, Pane playerRoot, Stage primaryStage) {
        this.uiRoot = uiRoot;
        this.levelRoot = levelRoot;
        this.playerRoot = playerRoot;
        this.primaryStage = primaryStage;
    }

    void renderStartScreen() {
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
        Scene gameScene = new Scene(new Pane(playerRoot, levelRoot), GAMEWIDTH, GAMEHEIGHT);
        game = new Game(levelRoot, gameScene);
        playerInstance = new Player(playerRoot);
        game.startGame(primaryStage);

    }
}
