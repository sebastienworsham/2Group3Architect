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
import level.LevelController;

import java.util.ArrayList;
import java.util.HashMap;

import static main.Main.*;
import static main.StartScreen.playerInstance;

public class Game {

    private Pane gameRoot;
    private int currentLevelNum;
    LevelController levelController;


    public Game(Pane gameRoot) {
        this.gameRoot = gameRoot;
        currentLevelNum = 1;
    }

    public void startGame() {
        Rectangle bg = new Rectangle(GAMEWIDTH, GAMEHEIGHT);
        bg.setFill(Color.RED);
        gameRoot.getChildren().add(bg);

        levelController = new LevelController(gameRoot);
        levelController.renderLevel(currentLevelNum);
        levelController.scrollLevel(playerInstance.getPlayer());

        //drawCurrentLevel(currentLevel);

        bg.toBack(); //Makes the background appear behind everything else
    }
}
