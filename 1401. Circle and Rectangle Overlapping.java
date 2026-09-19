class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest x coordinate on the rectangle to the circle's center
        int closestX = clamp(xCenter, x1, x2);
        
        // Find the closest y coordinate on the rectangle to the circle's center
        int closestY = clamp(yCenter, y1, y2);
        
        // Calculate the distance from the circle's center to this closest point
        int distanceX = xCenter - closestX;
        int distanceY = yCenter - closestY;
        
        // Compare squared distance with squared radius to avoid square root
        return (distanceX * distanceX + distanceY * distanceY) <= (radius * radius);
    }
    
    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
