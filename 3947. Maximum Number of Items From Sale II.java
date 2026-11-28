import java.util.*;

class Solution {
    public long maxNumberOfItems(int[][] items, long budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        long[] freq = new long[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        long[] multiplesCount = new long[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            long count = freq[f] - 1; // exclude self
            for (int m = 2 * f; m <= maxFactor; m += f) {
                count += freq[m];
            }
            multiplesCount[f] = count;
        }

        List<long[]> offers = new ArrayList<>();
        for (int[] item : items) {
            int f = item[0];
            long cost = item[1];
            long free = multiplesCount[f];
            if (free > 0 && cost <= 2 * minPrice) {
                offers.add(new long[]{cost, free});
            }
        }

        offers.sort(Comparator.comparingLong(a -> a[0]));

        long total = 0;
        for (long[] offer : offers) {
            long cost = offer[0];
            long free = offer[1];
            if (budget >= cost) {
                budget -= cost;
                total += 1 + free;
            }
        }

        total += budget / minPrice;
        return total;
    }
}
