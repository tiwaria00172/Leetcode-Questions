import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long BASE = 100_003L;
    private static final long MOD = 1_000_000_000_000_000_003L; // Large prime to minimize collisions

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarrayOfLength(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarrayOfLength(int[] nums, int len) {
        int n = nums.length;
        if (len > n) {
            return false;
        }

        long pow = 1;
        for (int i = 0; i < len - 1; i++) {
            pow = mulMod(pow, BASE);
        }

        long hash = 0;
        for (int i = 0; i < len; i++) {
            hash = (mulMod(hash, BASE) + nums[i]) % MOD;
        }

        Map<Long, Integer> freq = new HashMap<>();
        freq.put(hash, 1);

        for (int i = len; i < n; i++) {
            long outgoing = mulMod(nums[i - len], pow);
            hash = (hash - outgoing) % MOD;
            if (hash < 0) {
                hash += MOD;
            }
            hash = (mulMod(hash, BASE) + nums[i]) % MOD;
            freq.put(hash, freq.getOrDefault(hash, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }

    private long mulMod(long a, long b) {
        long res = 0;
        a %= MOD;
        while (b > 0) {
            if ((b & 1L) == 1L) {
                res = (res + a) % MOD;
            }
            a = (a + a) % MOD;
            b >>= 1L;
        }
        return res;
    }
}
