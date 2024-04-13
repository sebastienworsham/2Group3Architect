package main.leaderboard;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

        if (score < node.score) {
            node.left = insert(node.left, username, score);
        } else if (score > node.score) {
            node.right = insert(node.right, username, score);
        }

        return node;
    }

    public void printTopScorers(int count) {
        print(this.root, count);
    }
    private int print(TreeNode node, int count) {
        if (node == null || count <=0) {
            return count;
        }
        count = print(node.right, count);

        if (count > 0) {
            vbox.getChildren().add(new Button(node.username + ": " + node.score));
            count--;
        }

        if (count > 0) {
            count = print(node.left, count);
        }

        return count;
    }
}
