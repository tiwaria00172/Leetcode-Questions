class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long totalOddSubarrays = 0;
        
        // Initialize evenCount to 1 to account for prefix sum = 0 before starting
        int evenCount = 1; 
        int oddCount = 0;
        int prefixSum = 0;
        
        for (int num : arr) {
            prefixSum += num;
            
            if (prefixSum % 2 != 0) {
                // Current prefix sum is odd; pair it with all previous even prefix sums
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even; pair it with all previous odd prefix sums
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }
        
        return (int) totalOddSubarrays;
    }
}
