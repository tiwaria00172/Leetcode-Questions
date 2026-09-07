class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] dp = new long[26];
        long total = 0;

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            long newSub = (total - dp[index] + 1 + MOD) % MOD;
            total = (total + newSub) % MOD;
            dp[index] = (dp[index] + newSub) % MOD;
        }

        return (int) total;
    }
}
