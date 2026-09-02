class Solution {
    public boolean uniformArrayII(int[] nums1) {
        int evenCount = 0;
        int oddCount = 0;
        
        for (int num : nums1) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        
    
        if (evenCount == 0 || oddCount == 0) {
            return true;
        }
        
        return true; 
    }
}
