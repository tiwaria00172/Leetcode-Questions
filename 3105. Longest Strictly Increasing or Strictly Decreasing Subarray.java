class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int maxLen = 1;
        int incLen = 1;
        int decLen = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                incLen++;
                decLen = 1; // reset decreasing length
            } else if (nums[i] < nums[i - 1]) {
                decLen++;
                incLen = 1; // reset increasing length
            } else {
                incLen = 1;
                decLen = 1; // equal elements break both sequences
            }
            maxLen = Math.max(maxLen, Math.max(incLen, decLen));
        }
        
        return maxLen;
    }
}
