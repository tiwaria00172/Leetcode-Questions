class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = 25L * k; // Max possible value bound
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (count(mid, coins) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long count(long m, int[] coins) {
        long total = 0;
        int n = coins.length;
        int nMask = 1 << n;
        
        for (int i = 1; i < nMask; i++) {
            long lcmVal = 1;
            int bitCount = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    bitCount++;
                    lcmVal = lcm(lcmVal, coins[j]);
                    if (lcmVal > m) {
                        break;
                    }
                }
            }
            if (lcmVal <= m) {
                if (bitCount % 2 == 1) {
                    total += m / lcmVal;
                } else {
                    total -= m / lcmVal;
                }
            }
        }
        return total;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
