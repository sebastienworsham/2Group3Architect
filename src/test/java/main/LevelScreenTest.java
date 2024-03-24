package main;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.StartScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

class LevelScreenTest extends ApplicationTest {
    Pane levelRoot = new Pane();
    Pane playerRoot = new Pane();
    Pane uiRoot = new Pane();
    StartScreen startScreen;
    LevelScreen levelScreen;
    int GAMEWIDTH = 1280;
    int GAMEHEIGHT = 720;

    public void start(Stage primaryStage) throws Exception {
        Scene uiScene = new Scene(this.uiRoot, (double)this.GAMEWIDTH, (double)this.GAMEHEIGHT);
        this.startScreen = new StartScreen(this.uiRoot, this.levelRoot, this.playerRoot, primaryStage, uiScene);
        this.startScreen.renderStartScreen(this.startScreen);
        // init levels screen
        levelScreen = new LevelScreen(this.startScreen);
        levelScreen.showLevelSelection(primaryStage, new String[]{"Unit Test User"});
        primaryStage.setScene(uiScene);
        primaryStage.show();
        primaryStage.setTitle("Render Start Screen Test");
    }

    @Test
    public void showLevelSelectionTest()
    {
        VBox vBox =(VBox) this.levelScreen.getScene().getRoot();
        Assertions.assertEquals("CENTER", vBox.getAlignment().getHpos().name());

        HBox hBox = (HBox)vBox.getChildren().get(0);
        Assertions.assertEquals("CENTER", hBox.getAlignment().getHpos().name());
        List<Node> children= hBox.getChildren();

        // assert level 1 button properties and events
        Button levelOneButton = (Button)children.get(0);
        Assertions.assertEquals("1", levelOneButton.getText());
        Assertions.assertEquals(20, levelOneButton.getFont().getSize());
        Assertions.assertEquals("Gameplay", levelOneButton.getFont().getFamily());
        Assertions.assertNotNull(levelOneButton.getOnMouseClicked());
        Assertions.assertNotNull(levelOneButton.getOnMouseMoved());
        Assertions.assertNotNull(levelOneButton.getOnMouseExited());
        Assertions.assertNull(levelOneButton.getOnMouseDragEntered());

        // assert level 2 button properties and events
        Button levelTwoButton = (Button)children.get(1);
        Assertions.assertEquals("2", levelTwoButton.getText());
        Assertions.assertEquals(20, levelTwoButton.getFont().getSize());
        Assertions.assertEquals("Gameplay", levelTwoButton.getFont().getFamily());
        Assertions.assertNotNull(levelTwoButton.getOnMouseClicked());
        Assertions.assertNotNull(levelTwoButton.getOnMouseMoved());
        Assertions.assertNotNull(levelTwoButton.getOnMouseExited());
        Assertions.assertNull(levelTwoButton.getOnMouseDragEntered());

        // assert level 3 button properties and events
        Button levelThreeButton = (Button)children.get(2);
        Assertions.assertEquals("3", levelThreeButton.getText());
        Assertions.assertEquals(20, levelThreeButton.getFont().getSize());
        Assertions.assertEquals("Gameplay", levelThreeButton.getFont().getFamily());
        Assertions.assertNotNull(levelThreeButton.getOnMouseClicked());
        Assertions.assertNotNull(levelThreeButton.getOnMouseMoved());
        Assertions.assertNotNull(levelThreeButton.getOnMouseExited());
        Assertions.assertNull(levelThreeButton.getOnMouseDragEntered());

        // assert level 4 button properties and events
        Button levelFourButton = (Button)children.get(3);
        Assertions.assertEquals("4", levelFourButton.getText());
        Assertions.assertEquals(20, levelFourButton.getFont().getSize());
        Assertions.assertEquals("Gameplay", levelFourButton.getFont().getFamily());
        Assertions.assertNotNull(levelFourButton.getOnMouseClicked());
        Assertions.assertNotNull(levelFourButton.getOnMouseMoved());
        Assertions.assertNotNull(levelFourButton.getOnMouseExited());
        Assertions.assertNull(levelFourButton.getOnMouseDragEntered());

        // assert level 5 button properties and events
        Button levelFiveButton = (Button)children.get(4);
        Assertions.assertEquals("5", levelFiveButton.getText());
        Assertions.assertEquals(20, levelFiveButton.getFont().getSize());
        Assertions.assertEquals("Gameplay", levelFiveButton.getFont().getFamily());
        Assertions.assertNotNull(levelFiveButton.getOnMouseClicked());
        Assertions.assertNotNull(levelFiveButton.getOnMouseMoved());
        Assertions.assertNotNull(levelFiveButton.getOnMouseExited());
        Assertions.assertNull(levelFiveButton.getOnMouseDragEntered());
    }
}