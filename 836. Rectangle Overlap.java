class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if rec1 is to the left, right, above, or below rec2
        boolean isLeft = rec1[2] <= rec2[0];
        boolean isRight = rec1[0] >= rec2[2];
        boolean isBelow = rec1[3] <= rec2[1];
        boolean isAbove = rec1[1] >= rec2[3];
        
        // If any of these non-overlapping conditions are true, return false
        return !(isLeft || isRight || isBelow || isAbove);
    }
}
