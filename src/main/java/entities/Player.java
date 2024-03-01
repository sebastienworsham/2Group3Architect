package entities;

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
import main.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private Pane gameRoot;
    public Node player;



    private boolean canJump = true;
    public static int pVelocityY;

    public Player(Pane gameRoot) {
        this.gameRoot = gameRoot;

        player = drawRectangle(100, 100, 32, 32, Color.WHITE);
    }

    public void jumpPlayer() {
        if (canJump) {
            pVelocityY -= 30;
            canJump = false;
        }
    }
    public void movePlayerX(int moveX) {
        boolean movingRight = moveX > 0;

        for (int i=0; i<Math.abs(moveX); i++) { //Move player 1 pixel at a time
            for (Node platform: LevelController.platforms) {    //Check for Collisions
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (player.getTranslateX() + 40 == platform.getTranslateX()) {
                            return; //if collision detected return
                        }
                    }
                    else {
                        if (player.getTranslateX() == platform.getTranslateX() + 600) {
                            return; //if collision detected return
                        }
                    }
                }

            }
                player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    public void movePlayerY(int pVelocityY) {
        boolean movingDown = pVelocityY > 0; //if velocity positive player is moving down, otherwise moving up

        for (int i=0; i<Math.abs(pVelocityY); i++) {
            for (Node platform: LevelController.platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        player.setTranslateY(player.getTranslateY() - 1);
                        canJump = true;
                        return;
                    }
                }
            }
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
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

    public static void changePVelocityY(int value) {
        pVelocityY += value;
    }
    public Node getPlayer() {
        return player;
    }
}
