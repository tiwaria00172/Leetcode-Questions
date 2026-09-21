class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];
        
        // Iterate through all possible starting positions of the remaining subarray
        for (int i = 0; i < n; i++) {
            long currentProduct = 1;
            
            // Expand the subarray to the right
            for (int j = i; j < n; j++) {
                // Maintain the product modulo k to prevent integer overflow
                currentProduct = (currentProduct * nums[j]) % k;
                
                int remainder = (int) currentProduct;
                result[remainder]++;
            }
        }
        
        return result;
    }
}
