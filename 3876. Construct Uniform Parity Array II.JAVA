class Solution {
    public boolean canConstruct(int[] nums1) {
        int mn = Integer.MAX_VALUE;
        for (int x : nums1) {
            if (x % 2 != 0) {
                mn = Math.min(mn, x);
            }
        }
        
        if (mn == Integer.MAX_VALUE) {
            return true; // All numbers are even
        }
        
        for (int x : nums1) {
            if (x % 2 == 0 && x < mn) {
                return false;
            }
        }
        
        return true;
    }
}
