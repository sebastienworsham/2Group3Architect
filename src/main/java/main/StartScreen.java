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
    /**
     * Makes a StartScreen object with the UI elements and primary stage.
     * @param uiRoot       The root pane for UI elements.
     * @param levelRoot    The root pane for level elements.
     * @param playerRoot   The root pane for player elements.
     * @param primaryStage The primary stage of the application.
     * @param uiScene      The scene for the UI.
     */
    public StartScreen(Pane uiRoot, Pane levelRoot, Pane playerRoot, Stage primaryStage, Scene uiScene) {
        this.uiRoot = uiRoot;
        this.levelRoot = levelRoot;
        this.playerRoot = playerRoot;
        this.primaryStage = primaryStage;
        this.uiScene = uiScene;
        lbScreen = new LeaderboardScreen(primaryStage, uiScene);
    }
    /**
     * Renders the start screen with buttons like new game, load game, and leaderboard.
     * @param startScreen To render start screen.
     */
    void renderStartScreen(StartScreen startScreen) {
        uiRoot.setStyle("-fx-background-color: #87ce87;");
        uiRoot.setPrefSize(GAMEWIDTH, GAMEHEIGHT);

        Label gameName = new Label("ROLLING  RIFT"); //Sets the title on the game
        gameName.setFont(Font.font("Game Over", FontWeight.BOLD, 200));
        gameName.setAlignment(Pos.TOP_CENTER);
        gameName.setStyle("-fx-text-fill: black;");


        Button newGameButton = new Button("New Game"); //Button for New game
        Button loadGameButton = new Button("Load Game"); // Button for Load game
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
            setupGameScene(0, askUsername());
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
    /**
     * Asks the user for a new username.
     * @return The new username and the game statistics.
     */
    public String[] askUsername() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter new username");
        currentUserName = dialog.showAndWait().orElse(null);

        newUser = true;

        currentUser = new String[] {(currentUserName), ("0"), ("0")};
        return currentUser;
    }
    /**
     * Sets up the game scene with the specified saved level number and user data.
     * @param savedLevelNum Level number to start the game from.
     * @param currentUser   Data of the current user.
     */
    public void setupGameScene(int savedLevelNum, String[] currentUser) {
        bgRoot = new Pane();
        bgRoot.setStyle("-fx-background-color: #87ce87;");
        bgRoot.setPrefSize(GAMEWIDTH, GAMEHEIGHT);
        Scene gameScene = new Scene(new Pane(bgRoot, playerRoot, levelRoot), GAMEWIDTH, GAMEHEIGHT);
        game = new Game(levelRoot, gameScene, currentUser);
        playerInstance = new Player(playerRoot);

        game.startGame(primaryStage, savedLevelNum, currentUser);
    }
    /**
     * Sets up the load scene for loading the saved game.
     * @param startScreen Handle load scene setup.
     */
    private void setupLoadScene(StartScreen startScreen) {
        loadScreenPane = new Pane();

        Scene loadScene = new Scene(loadScreenPane, GAMEWIDTH, GAMEHEIGHT);
        LoadScreen loadScreen = new LoadScreen(loadScreenPane, primaryStage, game, startScreen);
        loadScreen.renderLoadScreen();

        primaryStage.setScene(loadScene);
        primaryStage.show();
    }
}
