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
import main.Game;

import java.util.ArrayList;
import java.util.HashMap;

import static main.Main.GAMEWIDTH;

public class LevelController {
    private String currentLevel;
    private int levelWidth;
    private Pane gameRoot;
    public static ArrayList<Node> platforms = new ArrayList<Node>();

    public LevelController(Pane gameRoot) {
        this.gameRoot = gameRoot;
    }

    public void renderLevel(int currentLevelNumber) {
        currentLevel = "LEVEL" + Integer.toString(currentLevelNumber);
        System.out.print(currentLevel);
        levelWidth = LevelInfo.LEVEL1[0].length() * 60;

        for (int i=0; i<LevelInfo.LEVEL1.length; i++) {
            String currentLine = LevelInfo.LEVEL1[i];
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
                gameRoot.setLayoutX(-(offset - 640));
            }
        });
    }
    public Node drawRectangle (int x, int y, int w, int h, Color color) {
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        gameRoot.getChildren().add(entity);
        return entity;
    }
}
