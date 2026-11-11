class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Cast to long to prevent integer overflow during multiplication and addition
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;
        
        // 1. Cover the overlapping needs (both need1 and need2 simultaneously)
        long commonNeeds = Math.min(n1, n2);
        long costPerPair = Math.min(c1 + c2, cb);
        long totalCost = commonNeeds * costPerPair;
        
        // 2. Cover the remaining needs for whichever type has more required units
        if (n1 > n2) {
            long remaining = n1 - n2;
            totalCost += remaining * Math.min(c1, cb);
        } else if (n2 > n1) {
            long remaining = n2 - n1;
            totalCost += remaining * Math.min(c2, cb);
        }
        
        return totalCost;
    }
}
