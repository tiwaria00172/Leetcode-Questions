class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            int current = nums[right];
            countMap.put(current, countMap.getOrDefault(current, 0) + 1);
            
            while (countMap.get(current) > k) {
                int leftVal = nums[left];
                countMap.put(leftVal, countMap.get(leftVal) - 1);
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
