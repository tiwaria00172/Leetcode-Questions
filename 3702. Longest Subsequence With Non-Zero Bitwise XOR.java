class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean hasNonZero = false;
        
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // If the total XOR is not zero, take the whole array
        if (totalXor != 0) {
            return n;
        }
        
        // If all elements are zero, no non-zero XOR subsequence exists
        if (!hasNonZero) {
            return 0;
        }
        
        // If total XOR is zero but elements are not all zero, drop one element
        return n - 1;
    }
}
