import java.util.List;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.StartScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

class StartScreenTest extends ApplicationTest {
    Pane levelRoot = new Pane();
    Pane playerRoot = new Pane();
    Pane uiRoot = new Pane();
    StartScreen startScreen;
    int GAMEWIDTH = 1280;
    int GAMEHEIGHT = 720;

    StartScreenTest() {
    }

    public void start(Stage primaryStage) throws Exception {
        Scene uiScene = new Scene(this.uiRoot, (double)this.GAMEWIDTH, (double)this.GAMEHEIGHT);
        this.startScreen = new StartScreen(this.uiRoot, this.levelRoot, this.playerRoot, primaryStage, uiScene);
        this.startScreen.renderStartScreen(this.startScreen);
        primaryStage.setScene(uiScene);
        primaryStage.show();
        primaryStage.setTitle("Render Start Screen Test");
    }
    @Test
    void renderStartScreenTest() throws Exception {
        Parent rootNode = this.startScreen.getScene().getRoot();
        Node borderPane = (Node)rootNode.getChildrenUnmodifiable().get(0);
        Node centerPanel = ((BorderPane)borderPane).getCenter();
        Node topPane = ((BorderPane)borderPane).getTop();
        List<Node> topPaneBox = ((HBox)topPane).getChildren();
        Assertions.assertEquals("ROLLING  RIFT", ((Label)topPaneBox.get(0)).getText());
        List<Node> box = ((HBox)centerPanel).getChildren();
        Assertions.assertEquals("New Game", ((Button)box.get(0)).getText());
        Assertions.assertEquals("Load Game", ((Button)box.get(1)).getText());
        Assertions.assertEquals("Leaderboard", ((Button)box.get(2)).getText());
    }
}