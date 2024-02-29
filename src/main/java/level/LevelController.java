package level;

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

public class LevelController {
    private String currentLevel;
    private Pane gameRoot;
    public static ArrayList<Node> platforms = new ArrayList<Node>();

    public LevelController(Pane gameRoot) {
        this.gameRoot = gameRoot;
    }

    public void renderLevel(int currentLevelNumber) {
        currentLevel = "LEVEL" + Integer.toString(currentLevelNumber);
        System.out.print(currentLevel);

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
    public Node drawRectangle (int x, int y, int w, int h, Color color) {
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        gameRoot.getChildren().add(entity);
        return entity;
    }
}
