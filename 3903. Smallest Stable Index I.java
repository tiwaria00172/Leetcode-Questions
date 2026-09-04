class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];
        
        // Compute prefix maximum from index 0 to i
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }
        
        // Compute suffix minimum from index i to n - 1
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        
        // Find the smallest index where instability score <= k
        for (int i = 0; i < n; i++) {
            int score = prefixMax[i] - suffixMin[i];
            if (score <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
