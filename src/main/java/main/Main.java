package main;

import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main extends Application {
    public static final int GAMEWIDTH = 1280;
    public static final int GAMEHEIGHT = 720;
    public static final String SAVEPATH = "src/main/java/main/save.csv";
    public static final String USERPATH = "src/main/java/main/usernames.csv";
    protected Pane levelRoot = new Pane();
    protected Pane playerRoot = new Pane();
    protected Pane uiRoot = new Pane();
    public static ArrayList<String[]> users = new ArrayList<String[]>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        readSaveInfo();
        for (String[] test: users) {
            System.out.println(Arrays.toString(test));
        }

        Scene uiScene = new Scene(uiRoot, GAMEWIDTH, GAMEHEIGHT);

        StartScreen startScreen;
        startScreen = new StartScreen(uiRoot, levelRoot, playerRoot, primaryStage);
        startScreen.renderStartScreen(startScreen);

        primaryStage.setScene(uiScene);
        primaryStage.show();
    }

    private void readSaveInfo() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(SAVEPATH);
            ObjectInputStream ois = new ObjectInputStream(fis);

            users = (ArrayList<String[]>) ois.readObject();
            fis.close();
        } catch (IOException e) {
            System.err.print(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
