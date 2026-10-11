class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Loop through the array using 1-based indexing
        for (int i = 1; i <= n; i++) {
            // Check if i divides n perfectly
            if (n % i == 0) {
                // Since the array is 0-indexed in Java, 
                // the 1-indexed element 'i' is located at index 'i - 1'
                sum += nums[i - 1] * nums[i - 1];
            }
        }
        
        return sum;
    }
}
