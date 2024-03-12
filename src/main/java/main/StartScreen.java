package main;

import entities.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;

import static main.Main.*;

public class StartScreen {
    Pane bgRoot;
    Pane loadScreenPane;
    Pane uiRoot;
    Pane levelRoot;
    Pane playerRoot;
    Stage primaryStage;
    protected Game game;
    public static Player playerInstance;
    public static String currentUserName;
    String[] currentUser;
    public static Boolean newUser = false;
    LeaderboardScreen lbScreen;
    Scene uiScene;

    public StartScreen(Pane uiRoot, Pane levelRoot, Pane playerRoot, Stage primaryStage, Scene uiScene) {
        this.uiRoot = uiRoot;
        this.levelRoot = levelRoot;
        this.playerRoot = playerRoot;
        this.primaryStage = primaryStage;
        this.uiScene = uiScene;
        lbScreen = new LeaderboardScreen(primaryStage, uiScene);
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
        Button leaderboardButton = new Button("Leaderboard");
        newGameButton.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));
        loadGameButton.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));
        leaderboardButton.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));

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
        leaderboardButton.setOnMouseMoved(event -> {
            leaderboardButton.setStyle("-fx-background-color: #78ced9");
        });
        leaderboardButton.setOnMouseExited(event -> {
            leaderboardButton.setStyle("-fx-background-color: #ffffff; ");
        });

        newGameButton.setOnAction(event -> {
            setupGameScene(-1, askUsername());
        });
        loadGameButton.setOnAction(event -> {
            setupLoadScene(startScreen);
        });
        leaderboardButton.setOnAction(event -> {
            lbScreen.setupLbScreen();
        });

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(newGameButton, loadGameButton, leaderboardButton);
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

    public String[] askUsername() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter new username");
        currentUserName = dialog.showAndWait().orElse(null);


        newUser = true;

        currentUser = new String[] {(currentUserName), ("0")};
        return currentUser;
    }

    public void setupGameScene(int savedLevelNum, String[] currentUser) {
        bgRoot = new Pane();
        bgRoot.setStyle("-fx-background-color: #87ce87;");
        bgRoot.setPrefSize(GAMEWIDTH, GAMEHEIGHT);
        Scene gameScene = new Scene(new Pane(bgRoot, playerRoot, levelRoot), GAMEWIDTH, GAMEHEIGHT);
        game = new Game(levelRoot, gameScene, currentUser);
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