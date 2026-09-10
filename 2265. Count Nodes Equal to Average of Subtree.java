/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Global variable to keep track of the total matching nodes
    private int matchingNodesCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodesCount = 0;
        calculateSumAndCount(root);
        return matchingNodesCount;
    }

    // Helper method that returns an array: [subtree_sum, subtree_node_count]
    private int[] calculateSumAndCount(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // Post-order traversal: process left and right subtrees first
        int[] leftData = calculateSumAndCount(node.left);
        int[] rightData = calculateSumAndCount(node.right);

        // Aggregate results for the current subtree
        int totalSum = node.val + leftData[0] + rightData[0];
        int totalCount = 1 + leftData[1] + rightData[1];

        // Integer division automatically rounds down to the nearest integer
        int currentAverage = totalSum / totalCount;

        // If the current node's value equals its subtree average, increment count
        if (node.val == currentAverage) {
            matchingNodesCount++;
        }

        return new int[]{totalSum, totalCount};
    }
}
