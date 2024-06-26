package entities;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

import static main.Main.GAMEHEIGHT;
import static main.Main.GAMEWIDTH;

public class Coin {
    Random random = new Random();
    Pane levelRoot;
    Circle circle;
    int xAxis;
    int yAxis;

    public Coin(Pane levelRoot, int xCords, int yCords) {
        this.levelRoot = levelRoot;
        xAxis = xCords;
        yAxis = yCords;
    }

    public void renderCoin(String[] currentLevelArray) {
        int rand1 = random.nextInt(1000);
        int rand2 = random.nextInt(1000);

        drawCircle(GAMEWIDTH - xAxis, GAMEHEIGHT - yAxis);
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void drawCircle(int x, int y) {
        circle = new Circle(30);
        circle.setTranslateX(x);
        circle.setTranslateY(y);
        circle.setFill(Color.YELLOW);

        levelRoot.getChildren().add(circle);
    }

    public boolean isTouchingPlayer(Node player) {
        if (circle.getBoundsInParent().intersects(player.getBoundsInParent())) {
            return true;
        } else {
            return false;
        }
    }
}
