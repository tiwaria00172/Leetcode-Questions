import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // If k equals the array length, the only subarray is the whole array
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
        
        // Count frequencies of each number
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        // If k == 1, find the maximum number that appears exactly once
        if (k == 1) {
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }
        
        // For 1 < k < n, only the first element nums[0] and last element nums[n-1] 
        // can appear in a single subarray of size k.
        int ans = -1;
        if (count.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (count.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
}
