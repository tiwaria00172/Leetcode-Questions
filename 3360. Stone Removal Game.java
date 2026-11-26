class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        boolean aliceTurn = true;

        while (n >= remove) {
            n -= remove;
            remove--;
            aliceTurn = !aliceTurn;
        }

        // If it's currently Alice's turn and she can't move, Alice loses (!aliceTurn is false).
        // If it's currently Bob's turn and he can't move, Alice wins (!aliceTurn is true).
        return !aliceTurn;
    }
}
