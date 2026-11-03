class Solution {
    public int minMoves(int[] nums) {
        int maxVal = Integer.MIN_VALUE;
        int sum = 0;
        
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            sum += num;
        }
        
        return (maxVal * nums.length) - sum;
    }
}
