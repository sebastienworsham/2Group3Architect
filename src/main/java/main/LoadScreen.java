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
    String[] currentUser;

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
        for (String[] userInfo: users) {
            Button loadGameButton = new Button(Arrays.toString(userInfo));
            int finalI = indexInUsers;
            loadGameButton.setOnAction(event -> {
                startScreen.setupGameScene(-1, getSaveInfo(finalI));
            });
            vbox.getChildren().add(loadGameButton);
            indexInUsers ++;
        }
        loadScreenPane.getChildren().add(vbox);
    }
    public String[] getSaveInfo(int i){
        currentUser = users.get(i);
        System.out.println("Current user: " + Arrays.toString(currentUser));


        return currentUser;
    }
}