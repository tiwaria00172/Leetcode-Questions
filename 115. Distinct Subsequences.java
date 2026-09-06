class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // If s is shorter than t, it's impossible to form t as a subsequence
        if (m < n) {
            return 0;
        }
        
        // dp[i][j] stores the number of distinct subsequences of t[0...i-1] in s[0...j-1]
        int[][] dp = new int[n + 1][m + 1];
        
        // Base case: An empty string t can always be formed by an empty subsequence of s
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 1;
        }
        
        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // If characters match, we have two options:
                // 1. Match the current characters: dp[i-1][j-1]
                // 2. Ignore the character in s and look for a match earlier: dp[i][j-1]
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    // If characters don't match, we must skip the character in s
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        
        return dp[n][m];
    }
}
