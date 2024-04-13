package main.leaderboard;

public class TreeNode {
    String username; /** Data to be stored in tree */
    int score;
    TreeNode left; /** Pointers */
    TreeNode right;

    public TreeNode(String username, int score) {
        this.username = username;
        this.score = score;
        this.left = null;
        this.right = null;
    }
}
