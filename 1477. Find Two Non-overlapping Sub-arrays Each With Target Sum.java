import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        
        int l = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        
        for (int r = 0; r < n; r++) {
            sum += arr[r];
            
            while (sum > target) {
                sum -= arr[l];
                l++;
            }
            
            if (sum == target) {
                int len = r - l + 1;
                if (l > 0 && minLen[l - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, len + minLen[l - 1]);
                }
                minLen[r] = len;
            }
            
            if (r > 0) {
                minLen[r] = Math.min(minLen[r], minLen[r - 1]);
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
