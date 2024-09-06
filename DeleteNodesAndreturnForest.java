import java.util.ArrayList;
import java.util.List;

// TreeNode structure as defined by the problem statement.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // To keep track of nodes to delete using their values.
    private boolean[] toDelete = new boolean[1001];
    // To store the resulting forest after deletions.
    private List<TreeNode> forest = new ArrayList<>();

    // Main function to delete nodes and return the remaining forest as a list.
    public List<TreeNode> delNodes(TreeNode root, int[] delNodes) {
        // Populate the toDelete array to mark nodes that need to be deleted.
        for (int value : delNodes) {
            toDelete[value] = true;
        }
        // Perform a DFS and add the root to the forest if it's not deleted.
        if (deleteAndReturnValidRoot(root) != null) {
            forest.add(root);
        }
        return forest;
    }

    // Helper function to perform DFS and handle deletions.
    private TreeNode deleteAndReturnValidRoot(TreeNode node) {
        if (node == null) {
            return null;
        }
        // Recursively deal with the left and right subtrees.
        node.left = deleteAndReturnValidRoot(node.left);
        node.right = deleteAndReturnValidRoot(node.right);
        // If current node is not to be deleted, return it.
        if (!toDelete[node.val]) {
            return node;
        }
        // If this node is to be deleted, add its children to the forest.
        if (node.left != null) {
            forest.add(node.left);
        }
        if (node.right != null) {
            forest.add(node.right);
        }
        // Return null because this node is to be deleted.
        return null;
    }
}