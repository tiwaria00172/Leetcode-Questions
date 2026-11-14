class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        boolean found = false;

        // Iterate through each possible starting index
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            
            // Expand the subarray from index i
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;

                // Stop expanding if length exceeds r
                if (length > r) {
                    break;
                }

                // Check if length is within [l, r] and sum is strictly positive
                if (length >= l && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                    found = true;
                }
            }
        }

        return found ? minSum : -1;
    }
}
