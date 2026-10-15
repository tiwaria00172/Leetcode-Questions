import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int totalSumOfSquares = 0;

        // Iterate through all possible starting positions of the subarray
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctElements = new HashSet<>();
            
            // Expand the subarray from index i to j
            for (int j = i; j < n; j++) {
                distinctElements.add(nums.get(j));
                
                // Get the count of distinct elements
                int distinctCount = distinctElements.size();
                
                // Add the square of the count to our total sum
                totalSumOfSquares += distinctCount * distinctCount;
            }
        }

        return totalSumOfSquares;
    }
}
