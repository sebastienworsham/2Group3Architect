package main;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import static main.Main.*;

public class LoadScreen {
    private final Stage primaryStage;
    private StartScreen startScreen;
    private Game game;
    private Pane loadScreenPane;

    public LoadScreen(Pane loadScreenPane, Stage primaryStage, Game game, StartScreen startScreen) {
        this.loadScreenPane = loadScreenPane;
        this.primaryStage = primaryStage;
        this.game = game;
        this.startScreen = startScreen;
    }

    public void renderLoadScreen() {
        loadScreenPane.setStyle("-fx-background-color: black;");
        loadScreenPane.setPrefSize(GAMEWIDTH, GAMEHEIGHT);

        int indexInUsers = 0;
        VBox vbox = new VBox();

        BorderPane layout = new BorderPane();
        layout.setPrefSize(GAMEWIDTH, GAMEHEIGHT);

        loadGameButtonsRecursive(users.keySet().iterator(), vbox);
        layout.setCenter(vbox);

        loadScreenPane.getChildren().add(layout);
    }

    public void loadGameButtonsRecursive(Iterator<String> userNames, VBox box) {
        // If there are no more keys to process, exit recursion
        if (!userNames.hasNext()) {
            return;
        }

        String playerName = userNames.next();
        Button loadGameButton = new Button(playerName);

        loadGameButton.setOnAction(event -> {
            // Users is a hashmap of player name and their level
            // users.get(playername) will return the value of the level stored for the player
            startScreen.setupGameScene(users.get(playerName), getSaveInfo(playerName));
        });

        box.getChildren().add(loadGameButton);
        loadGameButtonsRecursive(userNames, box); // Recursive call with next index
    }


    public String[] getSaveInfo(String key){
        String[] currentUser = new String[] {
                key,
                users.get(key).toString()
        };

        return currentUser;
    }
}