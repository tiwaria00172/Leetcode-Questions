class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // Find the index of 0
        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }

        // Check if the permutation is cyclically increasing
        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(i + 1) % n] != (nums[i] + 1) % n) {
                isIncreasing = false;
                break;
            }
        }

        if (isIncreasing) {
            return Math.min(zeroIdx, 2 + n - zeroIdx);
        }

        // Check if the permutation is cyclically decreasing
        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(i + 1) % n] != (nums[i] - 1 + n) % n) {
                isDecreasing = false;
                break;
            }
        }

        if (isDecreasing) {
            return Math.min(n - zeroIdx, zeroIdx + 2);
        }

        // If it fits neither cyclic property, it cannot be sorted
        return -1;
    }
}
