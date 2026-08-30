class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndex = 0;
        int maxIndex = 0;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        int i = Math.min(minIndex, maxIndex);
        int j = Math.max(minIndex, maxIndex);
        
        int opt1 = j + 1;             // Remove both from the front
        int opt2 = n - i;             // Remove both from the back
        int opt3 = (i + 1) + (n - j); // Remove one from front, one from back
        
        return Math.min(Math.min(opt1, opt2), opt3);
    }
}
