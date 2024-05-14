package main;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class ConcurrencyTest extends ApplicationTest {
    Pane levelRoot = new Pane();
    Pane playerRoot = new Pane();
    Pane uiRoot = new Pane();
    StartScreen startScreen;
    UserService userService;
    int GAMEWIDTH = 1280;
    int GAMEHEIGHT = 720;

    public void start(Stage primaryStage) throws Exception {

        Scene uiScene = new Scene(this.uiRoot, (double)this.GAMEWIDTH, (double)this.GAMEHEIGHT);
        this.startScreen = new StartScreen(this.uiRoot, this.levelRoot, this.playerRoot, primaryStage, uiScene, userService);
        this.startScreen.renderStartScreen(this.startScreen);

        String[] dummyUser = new String[]{("ConcurrencyTestUser"), ("0"), ("0")};
        startScreen.setupGameScene(0, dummyUser );

        primaryStage.setScene(uiScene);
        primaryStage.show();
        primaryStage.setTitle("Render Start Screen Test");
    }

    @Test
    void testElapsedTimerIsStartedAndCounted() throws InterruptedException {
        Thread.sleep(5000);

        String expectedTextForTimerLabel = "Time spent: 5 seconds";
        String actulaTextForTimerLabel = startScreen.timeElapsedLabel.getText();
        Assertions.assertEquals(expectedTextForTimerLabel, actulaTextForTimerLabel);
        System.out.println(actulaTextForTimerLabel);

        Thread.sleep(2000);
        actulaTextForTimerLabel = startScreen.timeElapsedLabel.getText();
        System.out.println(actulaTextForTimerLabel);
        Thread.sleep(3000);
        expectedTextForTimerLabel = "Time spent: 10 seconds";
        actulaTextForTimerLabel = startScreen.timeElapsedLabel.getText();
        System.out.println(actulaTextForTimerLabel);
        Assertions.assertEquals(expectedTextForTimerLabel, actulaTextForTimerLabel);
    }

}