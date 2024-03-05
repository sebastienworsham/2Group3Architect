package main;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import level.LevelController;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

import static main.Main.*;

public class LoadScreen {
    private final Stage primaryStage;
    private StartScreen startScreen;
    private Game game;
    private Pane loadScreenPane;
    int savedLevelNum;

    public LoadScreen(Pane loadScreenPane, Stage primaryStage, Game game, StartScreen startScreen) {
        this.loadScreenPane = loadScreenPane;
        this.primaryStage = primaryStage;
        this.game = game;
        this.startScreen = startScreen;
        savedLevelNum = 3;
    }

    public void renderLoadScreen() {
        loadScreenPane.setStyle("-fx-background-color: black;");
        loadScreenPane.setPrefSize(GAMEWIDTH, GAMEHEIGHT);

        Button loadGameButton = new Button("Load Last Game");
        loadGameButton.setOnAction(event -> {
           startScreen.setupGameScene(getSaveInfo());
        });

        loadScreenPane.getChildren().add(loadGameButton);
    }
    public int getSaveInfo(){
        File file = new File(SAVEPATH);
        int saveInfo = 0;
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNext()) {
                saveInfo = scanner.nextInt();
                System.out.print(saveInfo);
            }
        } catch (
                IOException e) {
            System.err.println(e.getMessage());
        }

        return saveInfo - 1;
    }
}