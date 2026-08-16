class Solution {
    public boolean stoneGameIX(int[] stones) {
        // Count frequencies of remainders when divided by 3
        int[] counts = new int[3];
        for (int stone : stones) {
            counts[stone % 3]++;
        }
        
        // If the number of 0-remainder stones is even, they don't change the turn outcome
        if (counts[0] % 2 == 0) {
            // Alice needs at least one of each type to have flexibility, 
            // and they cannot be equal if she wants to force a win.
            return counts[1] > 0 && counts[2] > 0;
        }
        
        // If the number of 0-remainder stones is odd, it flips the turn parity
        // Alice wins if the absolute difference between type 1 and type 2 stones is greater than 2
        return Math.abs(counts[1] - counts[2]) > 2;
    }
}
