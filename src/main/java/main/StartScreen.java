package main;

import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;

import static main.Main.GAMEHEIGHT;
import static main.Main.GAMEWIDTH;

public class StartScreen {
    Pane bgRoot;
    Pane loadScreenPane;
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

    void renderStartScreen(StartScreen startScreen) {
        uiRoot.setStyle("-fx-background-color: #87ce87;");
        uiRoot.setPrefSize(GAMEWIDTH, GAMEHEIGHT);

        Label gameName = new Label("ROLLING  RIFT");
        gameName.setFont(Font.font("Game Over", FontWeight.BOLD, 200));
        gameName.setAlignment(Pos.TOP_CENTER);
        gameName.setStyle("-fx-text-fill: black;");


        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load Game");
        newGameButton.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));
        loadGameButton.setFont(Font.font("Gameplay", FontWeight.BOLD, 19));

        newGameButton.setOnMouseMoved(event -> {
            newGameButton.setStyle("-fx-background-color: #78ced9; ");
        });
        newGameButton.setOnMouseExited(event -> {
            newGameButton.setStyle("-fx-background-color: #ffffff; ");
        });
        loadGameButton.setOnMouseMoved(event -> {
            loadGameButton.setStyle("-fx-background-color: #78ced9");
        });
        loadGameButton.setOnMouseExited(event -> {
            loadGameButton.setStyle("-fx-background-color: #ffffff; ");
        });

        newGameButton.setOnAction(event -> {
            setupGameScene(-1);
        });
        loadGameButton.setOnAction(event -> {
            setupLoadScene(startScreen);
        });

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(newGameButton, loadGameButton);
        hBox.setAlignment(Pos.CENTER);


        HBox topBox = new HBox();
        topBox.setFillHeight(true);
        topBox.getChildren().add(gameName);
        topBox.setAlignment(Pos.CENTER);

        topBox.setMargin(gameName, new Insets(300, 0, 0, 0));
        BorderPane layout = new BorderPane();

        layout.setPrefSize(GAMEWIDTH, GAMEHEIGHT);
        layout.setTop(topBox);
        layout.setCenter(hBox);

        HBox bottomBox = new HBox();
        topBox.setMaxHeight(100);

        layout.setBottom(bottomBox);
        uiRoot.getChildren().add(layout);
    }

    public void setupGameScene(int savedLevelNum) {
        bgRoot = new Pane();
        bgRoot.setStyle("-fx-background-color: #87ce87;");
        bgRoot.setPrefSize(GAMEWIDTH, GAMEHEIGHT);
        Scene gameScene = new Scene(new Pane(bgRoot, playerRoot, levelRoot), GAMEWIDTH, GAMEHEIGHT);
        game = new Game(levelRoot, gameScene);
        playerInstance = new Player(playerRoot);

        game.startGame(primaryStage, savedLevelNum);
    }
    private void setupLoadScene(StartScreen startScreen) {
        loadScreenPane = new Pane();

        Scene loadScene = new Scene(loadScreenPane, GAMEWIDTH, GAMEHEIGHT);
        LoadScreen loadScreen = new LoadScreen(loadScreenPane, primaryStage, game, startScreen);
        loadScreen.renderLoadScreen();

        primaryStage.setScene(loadScene);
        primaryStage.show();
    }
}