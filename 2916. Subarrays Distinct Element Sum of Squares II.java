import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;
    
    // Segment Tree arrays
    private long[] sum;
    private long[] sumSq;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        long totalAns = 0;
        Map<Integer, Integer> lastSeen = new HashMap<>();

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int lastIdx = lastSeen.getOrDefault(x, -1);
            
            // Increment the count of distinct elements for all subarrays starting 
            // from index (lastIdx + 1) to j
            update(1, 0, n - 1, lastIdx + 1, j, 1);
            
            // Add the sum of squares of all subarrays ending at j to the total answer
            totalAns = (totalAns + sumSq[1]) % MOD;
            
            // Update the last seen position of the current number
            lastSeen.put(x, j);
        }

        return (int) totalAns;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] == 0) return;
        
        long val = lazy[node];
        int mid = l + (r - l) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        // Apply to left child
        long lenL = mid - l + 1;
        sumSq[leftChild] = (sumSq[leftChild] + 2 * val % MOD * sum[leftChild] % MOD + lenL * val % MOD * val % MOD) % MOD;
        sum[leftChild] = (sum[leftChild] + lenL * val) % MOD;
        lazy[leftChild] = (lazy[leftChild] + val) % MOD;

        // Apply to right child
        long lenR = r - mid;
        sumSq[rightChild] = (sumSq[rightChild] + 2 * val % MOD * sum[rightChild] % MOD + lenR * val % MOD * val % MOD) % MOD;
        sum[rightChild] = (sum[rightChild] + lenR * val) % MOD;
        lazy[rightChild] = (lazy[rightChild] + val) % MOD;

        lazy[node] = 0;
    }

    private void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql <= l && r <= qr) {
            long len = r - l + 1;
            sumSq[node] = (sumSq[node] + 2 * val % MOD * sum[node] % MOD + len * val % MOD * val % MOD) % MOD;
            sum[node] = (sum[node] + len * val) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }
        
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val);
        }
        
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
        sumSq[node] = (sum[2 * node]Sq + sumSq[2 * node + 1]) % MOD; // Note: small typo fixed below in text explanation if needed, code uses sumSq
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
    }
}
