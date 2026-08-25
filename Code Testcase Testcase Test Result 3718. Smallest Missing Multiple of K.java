import java.util.Arrays;

public class Solution {
    public int findSmallestMissingMultiple(int[] nums, int k) {
        // Step 1: Sort the array in-place to allow binary search
        Arrays.sort(nums);
        
        // Step 2: Check multiples of k starting from k
        int multiple = k;
        while (Arrays.binarySearch(nums, multiple) >= 0) {
            multiple += k;
        }
        
        return multiple;
    }
}
