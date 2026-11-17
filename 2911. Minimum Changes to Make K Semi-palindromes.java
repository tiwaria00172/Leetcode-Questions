class Solution {
    public int minimumChanges(String s, int k) {
        final int n = s.length();
        // factors[i] := proper divisors of length i
        List<Integer>[] factors = getFactors(n);
        
        // cost[i][j] := minimum changes to make substring s[i..j] a semi-palindrome
        int[][] cost = getCost(s, n, factors);
        
        // dp[i][j] := minimum changes to split prefix s[i..n-1] into j valid parts
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, n);
        }
        dp[n][0] = 0;
        
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 1; j <= k; ++j) {
                // Try all possible cut points 'l' for the first substring s[i..l]
                for (int l = i; l < n - (k - j); ++l) {
                    dp[i][j] = Math.min(dp[i][j], dp[l + 1][j - 1] + cost[i][l]);
                }
            }
        }
        
        return dp[0][k];
    }
    
    // Precompute proper divisors for each number up to n
    private List<Integer>[] getFactors(int n) {
        List<Integer>[] factors = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) {
            factors[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 2 * i; j <= n; j += i) {
                factors[j].add(i);
            }
        }
        return factors;
    }
    
    // Precompute the cost to convert any substring s[i..j] into a semi-palindrome
    private int[][] getCost(String s, int n, List<Integer>[] factors) {
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                cost[i][j] = Integer.MAX_VALUE;
                int len = j - i + 1;
                for (int d : factors[len]) {
                    cost[i][j] = Math.min(cost[i][j], minChangesForDivisor(s, i, j, d));
                }
            }
        }
        return cost;
    }
    
    // Count letter changes needed for a substring s[i..j] with a fixed divisor d
    private int minChangesForDivisor(String s, int i, int j, int d) {
        int changes = 0;
        for (int offset = 0; offset < d; ++offset) {
            int left = i + offset;
            int right = j - ((len(i, j) - 1 - offset) % d); // or standard group check
            // Simpler group check:
            // Group starts at i + offset and jumps by d
            for (int a = i + offset, b = j - (j - offset - i) % d; a < b; a += d, b -= d) {
                if (s.charAt(a) != s.charAt(b)) {
                    changes++;
                }
            }
        }
        // Let's refine the inner loop cleanly:
        return changes;
    }
    
    private int len(int i, int j) {
        return j - i + 1;
    }
}
