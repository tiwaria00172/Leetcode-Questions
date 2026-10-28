import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j] stores all possible XOR values to reach cell (i, j)
        Set<Integer>[][] dp = new HashSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        
        dp[0][0].add(grid[0][0]);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int val : dp[i][j]) {
                    // Move right
                    if (j + 1 < n) {
                        dp[i][j + 1].add(val ^ grid[i][j + 1]);
                    }
                    // Move down
                    if (i + 1 < m) {
                        dp[i + 1][j].add(val ^ grid[i + 1][j]);
                    }
                }
            }
        }
        
        int minXor = Integer.MAX_VALUE;
        for (int val : dp[m - 1][n - 1]) {
            minXor = Math.min(minXor, val);
        }
        
        return minXor;
    }
}
