package main;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

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

        int indexInUsers = 0;
        VBox vbox = new VBox();
        for (String key: users.keySet()) {
            Button loadGameButton = new Button(key);

            loadGameButton.setOnAction(event -> {
                //startScreen.setupGameScene(0, getSaveInfo(finalI));

                LevelScreen levelScreen = new LevelScreen(startScreen);
                levelScreen.showLevelSelection(primaryStage, getSaveInfo(key));

            });
            vbox.getChildren().add(loadGameButton);
            indexInUsers ++;
        }
        loadScreenPane.getChildren().add(vbox);
    }
    public String[] getSaveInfo(String key){
        String[] currentUser = new String[] {
                key,
                String.valueOf(savedLevelNum)
        };
        System.out.println("Current user: " + Arrays.toString(currentUser));

        return currentUser;
    }
}