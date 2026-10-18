import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        table = new long[n + 1][n + 1];
        
        // Precompute combinations C(n, k) using Pascal's Identity
        for (int i = 0; i <= n; ++i) {
            table[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return (int) ((getWays(list) - 1 + MOD) % MOD);
    }

    private long getWays(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        long root = nums.get(0);
        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                leftSubtree.add(nums.get(i));
            } else {
                rightSubtree.add(nums.get(i));
            }
        }

        long leftWays = getWays(leftSubtree) % MOD;
        long rightWays = getWays(rightSubtree) % MOD;

        int leftSize = leftSubtree.size();
        int totalSize = leftSize + rightSubtree.size();

        // Ways to interleave left and right elements is C(leftSize + rightSize, leftSize)
        long waysFromCombinations = table[totalSize][leftSize];

        return (((waysFromCombinations * leftWays) % MOD) * rightWays) % MOD;
    }
}
