import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length;
        int j = n - 1;

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            
            // Maintain a monotonic decreasing stack of indices
            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? j + 1 : dq.peekLast();
                cost += (long) (r - l) * (num - nums[l]);
            }
            dq.addLast(i);

            // Shrink the window from the right if the cost exceeds k
            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[j];
                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }
                j--;
            }

            // All valid subarrays starting at `i` and ending up to `j`
            ans += (j - i + 1);
        }

        return ans;
    }
}
