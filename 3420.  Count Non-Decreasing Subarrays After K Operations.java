import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long currentOps = 0;
        
        // Monotonic decreasing deque storing indices of running maximums from left to right
        Deque<Integer> dq = new ArrayDeque<>();
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            // Merge smaller peaks to the right into the new element nums[l]
            while (!dq.isEmpty() && nums[dq.peekFirst()] < nums[l]) {
                int idx = dq.pollFirst();
                int nextIdx = dq.isEmpty() ? r + 1 : dq.peekFirst();
                currentOps += (long) (nextIdx - idx) * (nums[l] - nums[idx]);
            }
            dq.offerFirst(l);

            // Shrink window from the right until operations <= k
            while (currentOps > k) {
                int maxIdx = dq.peekLast();
                currentOps -= (nums[maxIdx] - nums[r]);
                if (maxIdx == r) {
                    dq.pollLast();
                }
                r--;
            }

            // All subarrays starting at l and ending between l and r are valid
            ans += (r - l + 1);
        }

        return ans;
    }
}
