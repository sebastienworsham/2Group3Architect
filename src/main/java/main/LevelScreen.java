package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import level.LevelController;

import static main.Main.GAMEHEIGHT;
import static main.Main.GAMEWIDTH;

public class LevelScreen {
    StartScreen startScreen;

    public LevelScreen(
            StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    public void showLevelSelection(Stage primaryStage) {
        Label selectLevelLabel = new Label("Select Level");
        selectLevelLabel.setFont(Font.font("Gameplay", FontWeight.BOLD, 24));
        
        Button level1Button = new Button("1");
        level1Button.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));

        Button level2Button = new Button("2");
        level2Button.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));

        Button level3Button = new Button("3");
        level3Button.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));

        Button level4Button = new Button("4");
        level4Button.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));

        Button level5Button = new Button("5");
        level5Button.setFont(Font.font("Gameplay", FontWeight.BOLD, 20));

        level1Button.setOnMouseClicked(event -> {
            startScreen.setupGameScene(0);
        });
        level2Button.setOnMouseClicked(event -> {
            startScreen.setupGameScene(1);
        });
        level3Button.setOnMouseClicked(event -> {
            startScreen.setupGameScene(2);
        });
        level4Button.setOnMouseClicked(event -> {
            startScreen.setupGameScene(3);
        });
        level5Button.setOnMouseClicked(event -> {
            startScreen.setupGameScene(4);
        });

        level1Button.setOnMouseMoved(event -> {
            level1Button.setStyle("-fx-background-color: #78ced9; ");
        });
        level1Button.setOnMouseExited(event -> {
            level1Button.setStyle("-fx-background-color: #ffffff; ");
        });
        level2Button.setOnMouseMoved(event -> {
            level2Button.setStyle("-fx-background-color: #78ced9");
        });
        level2Button.setOnMouseExited(event -> {
            level2Button.setStyle("-fx-background-color: #ffffff; ");
        });
        level3Button.setOnMouseMoved(event -> {
            level3Button.setStyle("-fx-background-color: #78ced9; ");
        });
        level3Button.setOnMouseExited(event -> {
            level3Button.setStyle("-fx-background-color: #ffffff; ");
        });
        level4Button.setOnMouseMoved(event -> {
            level4Button.setStyle("-fx-background-color: #78ced9");
        });
        level4Button.setOnMouseExited(event -> {
                    level4Button.setStyle("-fx-background-color: #ffffff; ");
        });
        level5Button.setOnMouseMoved(event -> {
            level5Button.setStyle("-fx-background-color: #78ced9");
        });
        level5Button.setOnMouseExited(event -> {
            level5Button.setStyle("-fx-background-color: #ffffff");
        });
        HBox levelButtons = new HBox(20);
        levelButtons.setAlignment(Pos.CENTER);
        levelButtons.getChildren().addAll(level1Button, level2Button, level3Button, level4Button, level5Button);

        VBox levelSelectionLayout = new VBox(20);
        levelSelectionLayout.setStyle("-fx-background-color: #87ce87;");
        levelSelectionLayout.setAlignment(Pos.CENTER);
        levelSelectionLayout.getChildren().addAll(levelButtons);
        Scene scene = new Scene(levelSelectionLayout, GAMEWIDTH, GAMEHEIGHT);


        primaryStage.setScene(scene);

        primaryStage.show();
    }
}