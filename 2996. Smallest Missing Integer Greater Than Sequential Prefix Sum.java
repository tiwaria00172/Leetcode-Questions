import java.util.HashSet;

class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Find the sum of the longest sequential prefix
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        
        // Step 2: Store all array elements in a HashSet for O(1) lookups
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Step 3: Increment the sum until a missing integer is found
        while (set.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}
