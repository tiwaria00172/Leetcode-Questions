class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1; // Initialize with all bits set to 1
        
        for (int i = 0; i < nums.length; ++i) {
            // Find all misplaced elements
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        
        // If the array is already sorted, return 0 as required
        return ans == -1 ? 0 : ans;
    }
}
