public class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // Find the maximum possible difference to bound our bucket size
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }
        
        // If the max difference is already 0, the sum of squared differences is 0
        if (maxDiff == 0) {
            return 0;
        }
        
        // bucket[i] stores the count of pairs with an absolute difference of i
        long[] bucket = new long[maxDiff + 1];
        long totalDiffSum = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            bucket[diff]++;
            totalDiffSum += diff;
        }
        
        // If our total modifications 'k' can wipe out all differences completely
        if (k >= totalDiffSum) {
            return 0;
        }
        
        // Process differences greedily from largest to smallest
        for (int i = maxDiff; i > 0; i--) {
            if (bucket[i] == 0) {
                continue;
            }
            
            // Count how many operations are needed to reduce all elements of size 'i' to 'i - 1'
            long count = bucket[i];
            long opsNeeded = count; 
            
            if (k >= opsNeeded) {
                // We have enough k to reduce all 'count' elements from diff 'i' to 'i - 1'
                k -= opsNeeded;
                bucket[i - 1] += count;
                bucket[i] = 0;
            } else {
                // We can only partially reduce some elements of diff 'i' to 'i - 1'
                bucket[i - 1] += k;
                bucket[i] -= k;
                k = 0;
                break; // No more operations left
            }
        }
        
        // Calculate the final sum of squared differences
        long minSquaredSum = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (bucket[i] > 0) {
                minSquaredSum += bucket[i] * ((long) i * i);
            }
        }
        
        return minSquaredSum;
    }
}
