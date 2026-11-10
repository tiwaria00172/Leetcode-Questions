import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] will store the minimum total cost to reach step i
        int[] dp = new int[n + 1];
        
        // Initialize with a large value to represent infinity
        final int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dp, INF);
        
        // Base case: start at step 0 with cost = 0
        dp[0] = 0;
        
        // Fill the dp array for each step from 1 to n
        for (int i = 1; i <= n; i++) {
            // From step j, we can jump to i (where j can be i-1, i-2, or i-3)
            for (int j = Math.max(0, i - 3); j < i; j++) {
                int jumpCost = costs[i - 1] + (i - j) * (i - j);
                dp[i] = Math.min(dp[i], dp[j] + jumpCost);
            }
        }
        
        return dp[n];
    }
}
