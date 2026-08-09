class Solution {
    private int[] suffixSum;
    private int[][] memo;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        
        // Build suffix sums from right to left
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        memo = new int[n][n + 1];
        return dp(0, 1);
    }

    private int dp(int i, int m) {
        // If we can take all remaining piles, take them all
        if (i + 2 * m >= n) {
            return suffixSum[i];
        }
        
        // Return memoized result if available
        if (memo[i][m] != 0) {
            return memo[i][m];
        }
        
        int maxStones = 0;
        // Try taking X piles, where 1 <= X <= 2M
        for (int x = 1; x <= 2 * m; x++) {
            // Max total stones = total remaining minus what the opponent gets next turn
            maxStones = Math.max(maxStones, suffixSum[i] - dp(i + x, Math.max(m, x)));
        }
        
        return memo[i][m] = maxStones;
    }
}
