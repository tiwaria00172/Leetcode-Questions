import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[31];
        long MOD = 1_000_000_007L;

        // Step 1: Count the total number of set bits at each position
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }

        long totalSumOfSquares = 0;

        // Step 2: Greedily construct the k largest numbers
        for (int step = 0; step < k; step++) {
            long currentNum = 0;
            for (int i = 0; i < 31; i++) {
                if (bitCounts[i] > 0) {
                    currentNum |= (1L << i);
                    bitCounts[i]--; // Use up one set bit for this position
                }
            }
            // Step 3: Add the square of the constructed number to the total sum
            long square = (currentNum * currentNum) % MOD;
            totalSumOfSquares = (totalSumOfSquares + square) % MOD;
        }

        return (int) totalSumOfSquares;
    }
}
