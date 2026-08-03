class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        
        // Base case: no stones left means a score margin of 0
        dp[n] = 0;
        
        // Build the DP table from right to left
        for (int i = n - 1; i >= 0; i--) {
            int take = 0;
            int maxMargin = Integer.MIN_VALUE;
            
            // Current player can take up to 3 stones, if available
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                take += stoneValue[i + k - 1];
                maxMargin = Math.max(maxMargin, take - dp[i + k]);
            }
            
            dp[i] = maxMargin;
        }
        
        // Determine the winner based on Alice's net score margin at index 0
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
