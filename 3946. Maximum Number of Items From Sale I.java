class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int[] valueOnFirstBuy = new int[n];

        for (int i = 0; i < n; i++) {
            int factorI = items[i][0];
            int priceI = items[i][1];
            minPrice = Math.min(minPrice, priceI);

            int freeCount = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factorI == 0) {
                    freeCount++;
                }
            }
            // 1 purchased copy + free copies triggered by the first purchase of item i
            valueOnFirstBuy[i] = 1 + freeCount;
        }

        // dp[c] = max items (first copies + their freebies) obtainable with cost c
        int[] dp = new int[budget + 1];
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int gain = valueOnFirstBuy[i];
            for (int c = budget; c >= price; c--) {
                dp[c] = Math.max(dp[c], dp[c - price] + gain);
            }
        }

        // Spend leftover budget (budget - c) on the cheapest item
        int ans = 0;
        for (int c = 0; c <= budget; c++) {
            int extraCopies = (budget - c) / minPrice;
            ans = Math.max(ans, dp[c] + extraCopies);
        }

        return ans;
    }
}
