import java.util.Arrays;
import java.util.TreeMap;

public class Solution {
    private TreeMap<Integer, Integer> left = new TreeMap<>();
    private TreeMap<Integer, Integer> right = new TreeMap<>();
    private int leftSize = 0;
    private int rightSize = 0;
    private long leftSum = 0;
    private long rightSum = 0;
    private int windowSize;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        this.windowSize = x;
        long[] C = new long[n - x + 1];

        // 1. Sliding Window to calculate the cost for each subarray of size x
        for (int i = 0; i < n; i++) {
            add(nums[i]);
            
            if (i >= x) {
                remove(nums[i - x]);
            }
            
            if (i >= x - 1) {
                long median = left.lastKey();
                long cost = (leftSize * median - leftSum) + (rightSum - rightSize * median);
                C[i - x + 1] = cost;
            }
        }

        // 2. Dynamic Programming to find the minimum cost for k non-overlapping subarrays
        long[][] dp = new long[n + 1][k + 1];
        long INF = Long.MAX_VALUE / 2; // Prevent overflow during additions
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // Option 1: Do not include a subarray ending at index i - 1
                dp[i][j] = dp[i - 1][j];
                
                // Option 2: Include a subarray ending at index i - 1 (starts at i - x)
                if (i >= x) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - x][j - 1] + C[i - x]);
                }
            }
        }

        return dp[n][k];
    }

    private void add(int val) {
        if (left.isEmpty() || val <= left.lastKey()) {
            left.put(val, left.getOrDefault(val, 0) + 1);
            leftSum += val;
            leftSize++;
        } else {
            right.put(val, right.getOrDefault(val, 0) + 1);
            rightSum += val;
            rightSize++;
        }
        balance();
    }

    private void remove(int val) {
        if (left.containsKey(val)) {
            int count = left.get(val);
            if (count == 1) left.remove(val);
            else left.put(val, count - 1);
            leftSum -= val;
            leftSize--;
        } else {
            int count = right.get(val);
            if (count == 1) right.remove(val);
            else right.put(val, count - 1);
            rightSum -= val;
            rightSize--;
        }
        balance();
    }

    private void balance() {
        int targetLeft = (windowSize + 1) / 2;
        
        while (leftSize > targetLeft) {
            int maxLeft = left.lastKey();
            int count = left.get(maxLeft);
            if (count == 1) left.remove(maxLeft);
            else left.put(maxLeft, count - 1);
            leftSum -= maxLeft;
            leftSize--;

            right.put(maxLeft, right.getOrDefault(maxLeft, 0) + 1);
            rightSum += maxLeft;
            rightSize++;
        }
        
        while (leftSize < targetLeft && !right.isEmpty()) {
            int minRight = right.firstKey();
            int count = right.get(minRight);
            if (count == 1) right.remove(minRight);
            else right.put(minRight, count - 1);
            rightSum -= minRight;
            rightSize--;

            left.put(minRight, left.getOrDefault(minRight, 0) + 1);
            leftSum += minRight;
            leftSize++;
        }
    }
}
