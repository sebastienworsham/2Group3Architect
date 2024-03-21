package main;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.List;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

class StartScreenTest extends ApplicationTest {
    Pane levelRoot = new Pane();
    Pane playerRoot = new Pane();
    Pane uiRoot = new Pane();
    StartScreen startScreen;
    int GAMEWIDTH = 1280;
    int GAMEHEIGHT = 720;

    // Override the main class start function to setup the screen
    // it follows the TestFx guidelines to setup the stage
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene uiScene = new Scene(uiRoot, GAMEWIDTH, GAMEHEIGHT);
        startScreen = new StartScreen(uiRoot, levelRoot, playerRoot, primaryStage, uiScene);
        startScreen.renderStartScreen(startScreen);

        primaryStage.setScene(uiScene);
        primaryStage.show();
        primaryStage.setTitle("Render Start Screen Test");
    }

    // Test for render start screen?
    @Test
    void renderStartScreenTest() throws Exception {
        Parent rootNode = startScreen.getScene().getRoot();

        Node borderPane=  rootNode.getChildrenUnmodifiable().get(0);
        Node centerPanel = ((BorderPane) borderPane).getCenter();
        Node topPane = ((BorderPane) borderPane).getTop();

        // Assert top pane controls e.g. game name
        List<Node> topPaneBox = ((HBox) topPane).getChildren();
        Assertions.assertEquals("ROLLING  RIFT",  ((Label) topPaneBox.get(0)).getText());

        // Assert center pane controls
        List<Node> box = ((HBox) centerPanel).getChildren();
        Assertions.assertEquals("New Game",  ((Button) box.get(0)).getText());
        Assertions.assertEquals("Load Game",  ((Button) box.get(1)).getText());
        Assertions.assertEquals("Leaderboard",  ((Button) box.get(2)).getText());
    }

}