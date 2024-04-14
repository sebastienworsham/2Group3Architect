package main.leaderboard;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.leaderboard.LBProcessor;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static main.Main.*;

public class LeaderboardScreen<K, V> {
    Stage primaryStage;
    LBProcessor lbp;
    private Pane lbPane;
    VBox vbox;
    Scene uiScene;
    Scene lbScene;
    TreeMap<Integer, String> leaderBoard;
    public LeaderboardScreen(Stage primaryStage, Scene uiScene) {
        this.primaryStage = primaryStage;
        this.uiScene = uiScene;

        lbPane = new Pane();
        lbPane.setStyle("-fx-background-color: #87ce87;");;
        lbPane.setPrefSize(GAMEWIDTH, GAMEHEIGHT);
        lbScene = new Scene(lbPane, GAMEWIDTH, GAMEHEIGHT);
        vbox = new VBox(40);

        lbp = new LBProcessor(vbox);
    }

    public void setupLbScreen() {
        Label lbTitle = new Label("LeaderBoard");
        lbTitle.setFont(Font.font("Game Over", FontWeight.BOLD, 100));
        lbTitle.setAlignment(Pos.CENTER);
        lbTitle.setStyle("-fx-text-fill: black;");
        vbox.getChildren().addAll(lbTitle);
        vbox.setTranslateX(500);

        renderBackButton();
        renderLeaderboard();

        lbPane.getChildren().addAll(vbox);

        primaryStage.setScene(lbScene);
        primaryStage.show();
    }

    private void renderBackButton() {
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));
        
        backButton.setOnMouseMoved(event -> {
            backButton.setStyle("-fx-background-color: #78ced9; ");
        });
        backButton.setOnMouseExited(event -> {
            backButton.setStyle("-fx-background-color: #ffffff; ");
        });
        
        backButton.setOnAction(event -> {
            closeLbScreen();
        });
        lbPane.getChildren().addAll(backButton);
    }

    private void renderLeaderboard() {
        URL url = null;
        leaderBoard = new TreeMap<>(Collections.reverseOrder());

        try {
            url = new URL("https://sebastienworsham.github.io/LeaderBoardInfo/leaderboardSaveInfo.csv");
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            Scanner scanner = new Scanner(in);

            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                lbp.process(line); /** Parses input and inserts into TreeNode */
            }
            scanner.close();
            lbp.printTopScorers(5); /** Prints the top users in leaderboard. Change count to change the amount printed */
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closeLbScreen() {
        lbPane.getChildren().clear();
        vbox.getChildren().clear();
        primaryStage.setScene(uiScene);
        primaryStage.show();
    }
}
