import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int sumOfSortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        // Find all unique divisors of n
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }
        
        int totalSum = 0;
        
        // Validate each divisor k
        for (int k : divisors) {
            if (isValid(nums, sortedNums, n, k)) {
                totalSum += k;
            }
        }
        
        return totalSum;
    }
    
    private boolean isValid(int[] nums, int[] sortedNums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i += k) {
            int currentMin = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;
            int descendingTransitions = 0;
            
            // Step 1: Count descending transitions and find local min/max
            for (int j = 0; j < k; j++) {
                int currIdx = i + j;
                int nextIdx = i + (j + 1) % k;
                
                if (nums[currIdx] > nums[nextIdx]) {
                    descendingTransitions++;
                }
                
                currentMin = Math.min(currentMin, nums[currIdx]);
                currentMax = Math.max(currentMax, nums[currIdx]);
            }
            
            // Rule 1: A circular array can be sorted iff it has <= 1 descending transition
            if (descendingTransitions > 1) {
                return false;
            }
            
            // Rule 2: Global structure validation (elements must be correctly ordered between blocks)
            if (currentMin < prevMax) {
                return false;
            }
            
            // Rule 3: Ensure the set of elements matches the globally sorted chunk
            // Because duplicate boundaries might leak, verify currentMin/Max match the sorted counterpart
            if (currentMin != sortedNums[i] || currentMax != sortedNums[i + k - 1]) {
                return false;
            }
            
            prevMax = currentMax;
        }
        
        return true;
    }
}
