/**
package main.leaderboard;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class LBProcessor {
    private TreeNode root;
    VBox vbox;

    public LBProcessor(VBox vbox) {
        this.vbox = vbox;
    }

    public void process(String line) {
        String[] parts = line.split(",");

        if (parts.length == 2) {
            try {
                String username = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());


                this.root = insert(this.root, username, score);
            } catch (NumberFormatException e) {
                System.err.println("Invalid score format: " + parts[0]);
            }
        } else {
            System.err.println("Invalid CSV format: " + line);
        }
    }

    public TreeNode insert(TreeNode node, String username, int score) {
        if (node == null) {
            return new TreeNode(username, score);
        }

        /** Insert Node
        if (score < node.score) {
            node.left = insert(node.left, username, score);
        } else if (score > node.score) {
            node.right = insert(node.right, username, score);
        }

        /** Fix any violations of red-black tree properties
        if (isRed(node.right) && !isRed(node.left)) { //children different colors
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) { //parent and child red
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) { //children both red
            flipColors(node);
        }

        return node;
    }

    private TreeNode rotateLeft(TreeNode h) {
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = Color.RED;
        return x;
    }
    private TreeNode rotateRight(TreeNode h) {
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = Color.RED;
        return x;
    }
    private void flipColors(TreeNode h) {
        h.color = Color.RED;
        h.left.color = Color.BLACK;
        h.right.color = Color.BLACK;
    }

    public void printTopScorers(int count) {
        print(this.root, count);
    }
    private int print(TreeNode node, int count) {
        if (node == null || count <=0) {
            return count;
        }
        count = print(node.right, count); //Traverse to the bottom right of the subtree
        if (count > 0) {
            vbox.getChildren().add(new Button(node.username + ": " + node.score));
            count--;
        }

        if (count > 0) {
            count = print(node.left, count);
        }

        return count;
    }

    private boolean isRed(TreeNode node) {
        if (node == null) {
            return false; //null nodes considered black
        }
        return node.color == Color.RED;
    }

    public String[] getTopScorers(int count) {
        List<String> topScorers = new ArrayList<>();
        getTopScorers(this.root, topScorers, count);
        return topScorers.toArray(new String[0]);
    }

    private int getTopScorers(TreeNode node, List<String> topScorers, int count) {
        if (node == null || count <= 0) {
            return count;
        }

        count = getTopScorers(node.right, topScorers, count);
        if (count > 0) {
            topScorers.add(node.username + ": " + node.score);
            count--;
        }
        if (count > 0) {
            count = getTopScorers(node.left, topScorers, count);
        }

        return count;
    }
}
*/
package main.leaderboard;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class LBProcessor {
    private TreeNode root;
    private VBox vbox;

    public LBProcessor(VBox vbox) {
        this.vbox = vbox;
    }

    public void process(String line) {
        String[] parts = line.split(",");

        if (parts.length == 2) {
            try {
                String username = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());

                this.root = insert(this.root, username, score);
            } catch (NumberFormatException e) {
                System.err.println("Invalid score format: " + parts[0]);
            }
        } else {
            System.err.println("Invalid CSV format: " + line);
        }
    }

    public TreeNode insert(TreeNode node, String username, int score) {
        if (node == null) {
            return new TreeNode(username, score);
        }

        /** Insert Node */
        if (score < node.score) {
            node.left = insert(node.left, username, score);
        } else if (score > node.score) {
            node.right = insert(node.right, username, score);
        }

        /** Fix any violations of red-black tree properties */
        if (node.right != null && node.right.color && !node.left.color) { //children different colors
            node = rotateLeft(node);
        }
        if (node.left != null && node.left.color && node.left.left != null && node.left.left.color) { //parent and child red
            node = rotateRight(node);
        }
        if (node.left != null && node.left.color && node.right != null && node.right.color) { //children both red
            flipColors(node);
        }

        return node;
    }

    private TreeNode rotateLeft(TreeNode h) {
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = true; // Red
        return x;
    }

    private TreeNode rotateRight(TreeNode h) {
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = true; // Red
        return x;
    }

    private void flipColors(TreeNode h) {
        h.color = true; // Red
        h.left.color = false; // Black
        h.right.color = false; // Black
    }

    public void printTopScorers(int count) {
        print(this.root, count);
    }

    private int print(TreeNode node, int count) {
        if (node == null || count <= 0) {
            return count;
        }
        count = print(node.right, count); //Traverse to the bottom right of the subtree
        if (count > 0) {
            vbox.getChildren().add(new Button(node.username + ": " + node.score));
            count--;
        }

        if (count > 0) {
            count = print(node.left, count);
        }

        return count;
    }

    public String[] getTopScorers(int count) {
        List<String> topScorers = new ArrayList<>();
        getTopScorers(this.root, topScorers, count);
        return topScorers.toArray(new String[0]);
    }

    private int getTopScorers(TreeNode node, List<String> topScorers, int count) {
        if (node == null || count <= 0) {
            return count;
        }

        count = getTopScorers(node.right, topScorers, count);
        if (count > 0) {
            topScorers.add(node.username + ": " + node.score);
            count--;
        }
        if (count > 0) {
            count = getTopScorers(node.left, topScorers, count);
        }

        return count;
    }
}