package main.leaderboard;

public class TreeNode {
    String username; /** Data to be stored in tree */
    int score;
    boolean color;
    TreeNode left; /** Pointers */
    TreeNode right;

    public TreeNode(String username, int score) {
        this.username = username;
        this.score = score;
        this.color = false;
        this.left = null;
        this.right = null;
    }
}
