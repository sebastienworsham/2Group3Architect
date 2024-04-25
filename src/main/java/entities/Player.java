package entities;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import level.LevelController;
import entities.Player;

import java.util.HashMap;

import main.Game;
import main.Game.*;
import static main.StartScreen.playerInstance;

public class Player {
    private Pane playerRoot;
    public Node player;
    private boolean canJump = true;
    public static int pVelocityY;
    private static HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private static boolean isPressed(KeyCode key) { return keys.getOrDefault(key, false); }

    public Player(Pane playerRoot) {
        this.playerRoot = playerRoot;
        player = drawPlayer(50, 50, Color.LIGHTGOLDENRODYELLOW);
        player.toFront();
    }
    public Pane getPlayerRoot() {
        return playerRoot;
    }
    public void jumpPlayer() {
        if (canJump) {
            pVelocityY -= 30;
            if (pVelocityY < -20) {
                pVelocityY = -20;
            }
            canJump = false;
        }
    }
    public String canPlayerJump() {
        return String.valueOf(canJump);
    }
    public int getTranslateX() {
        double translateX = player.getTranslateX();
        return (int) translateX;
    }
    public void movePlayerX(int moveX) {
        boolean movingRight = moveX > 0;

        for (int i=0; i<Math.abs(moveX); i++) { //Move player 1 pixel at a time
            for (Node platform: LevelController.platforms) {    //Check for Collisions
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        player.setTranslateX(player.getTranslateX() - 3);
                        return; //if collision detected return
                    } else {
                        player.setTranslateX(player.getTranslateX() + 3);
                        return; //if collision detected return
                    }
                }
            }
                player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    public void movePlayerY(int pVelocityY) {
        boolean movingDown = pVelocityY > 0; //if velocity is positive player is moving down, otherwise player is moving up
        for (int i=0; i<Math.abs(pVelocityY); i++) {
            for (Node platform: LevelController.platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        player.setTranslateY(player.getTranslateY() - 1);
                        canJump = true;
                        return;
                    } else {
                        player.setTranslateY(player.getTranslateY() + 2);
                        return;
                    }
                }
            }
            for (Node jumpThroughPlatform: LevelController.jumpThroughPlatforms) {
                if (player.getBoundsInParent().intersects(jumpThroughPlatform.getBoundsInParent())) {
                    if (pVelocityY == 3) {
                        player.setTranslateY(player.getTranslateY() + 5);
                        canJump = false;
                        return;
                    }
                    else {
                        player.setTranslateY(player.getTranslateY() - 1);
                        canJump = true;
                        return;
                    }
                }
            }
            for (Node resetBlock: LevelController.resetBlocks) {
                if (player.getBoundsInParent().intersects(resetBlock.getBoundsInParent())) {
                    playerInstance.resetPlayerPosition(); //puts player in top left of screen
                }
            }
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
        }
    }
    public void resetPlayerPosition() {
        player.setTranslateX(100);
        player.setTranslateY(100);
    }

    public Node drawPlayer (int x, int y, Color color) {
        Circle entity = new Circle(30, color);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        playerRoot.getChildren().add(entity);
        return entity;
    }

    public Node getPlayer() {
        return player;
    }

    public static void changePVelocityY(int value) {
        pVelocityY += value;
    }


}
