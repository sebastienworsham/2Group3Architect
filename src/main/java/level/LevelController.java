package level;

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


import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import static main.Main.*;
import static main.StartScreen.playerInstance;

public class LevelController {
    //private int currentLevelNum;
    private static String[] currentLevelArray;
    private int levelWidth;
    private Pane levelRoot;
    public static ArrayList<Node> platforms = new ArrayList<Node>();
    int currentLevelNum;

    public LevelController(Pane levelRoot) {
        this.levelRoot = levelRoot;
        currentLevelNum = 0;
    }
    public LevelController(Pane levelRoot, int currentLevelNum) {
        this.levelRoot = levelRoot;
        this.currentLevelNum = currentLevelNum;
    }

    public void nextLevel() {
        playerInstance.resetPlayerPosition(); //puts player in top left of screen
        levelRoot.getChildren().clear(); //clears previous level
        currentLevelNum += 1;
       // levelRoot.setTranslateX(0);
       // levelRoot.setTranslateY(0);

        platforms.clear(); //clears previous level collision information

        switch (currentLevelNum) {
            case 1:
                currentLevelArray = LevelInfo.LEVEL1.clone();
                break;
            case 2:
                currentLevelArray = LevelInfo.LEVEL2.clone();
                break;
            case 3:
                currentLevelArray = LevelInfo.LEVEL3.clone();
                break;
            case 4:
                currentLevelArray = LevelInfo.LEVEL4.clone();
                break;
            case 5:
                currentLevelArray = LevelInfo.LEVEL5.clone();
                break;
            default:
                currentLevelArray = LevelInfo.LEVEL1.clone();
                currentLevelNum = 1;
                break;
        }
        renderLevel();
    }

    public void renderLevel() {
        levelWidth = currentLevelArray[0].length() * 60;

        for (int i=0; i<currentLevelArray.length; i++) {
            String currentLine = currentLevelArray[i];
            for (int j=0; j<currentLine.length(); j++) {
                if(currentLine.charAt(j) == '1') {
                    Node platform = drawRectangle(j*60, i*60, 60, 60, Color.BLACK);
                    platforms.add(platform);
                }
            }
        }
    }

    public void scrollLevel(Node player) {
        player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 640 && offset < levelWidth-640) {
                System.out.println("*"+offset);
                levelRoot.setTranslateX(-offset + 640);
            } else {
                System.out.println(offset);
            }
        });
    } //method scrollLevel currently not used because it isn't working
    public Node drawRectangle (int x, int y, int w, int h, Color color) {
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        levelRoot.getChildren().add(entity);
        return entity;
    }

    public void saveCurrentLevel() {
        System.out.println("saving current level");
        File file = new File(SAVEPATH);
        try {
            FileWriter fw = new FileWriter(file, false);

            if(file.createNewFile()) {
                System.out.println(SAVEPATH + "doesn't yet exist, created");
            } else {
                System.out.println(SAVEPATH + " already exists, appending");
            }
            fw.append(Integer.toString(currentLevelNum));
            fw.close();
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }
}
