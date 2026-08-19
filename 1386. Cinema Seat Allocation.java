import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reservedMap = new HashMap<>();
        
        // Build the row bitmasks
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            reservedMap.put(row, reservedMap.getOrDefault(row, 0) | (1 << col));
        }
        
        int count = 0;
        
        // Process rows with at least one reservation
        for (int mask : reservedMap.values()) {
            boolean left = (mask & 0x3C) == 0;     // Seats 2, 3, 4, 5
            boolean middle = (mask & 0xF0) == 0;   // Seats 4, 5, 6, 7
            boolean right = (mask & 0x3C0) == 0;   // Seats 6, 7, 8, 9
            
            if (left && right) {
                count += 2;
            } else if (left || middle || right) {
                count += 1;
            }
        }
        
        // Add 2 groups for each completely empty row
        int emptyRows = n - reservedMap.size();
        count += emptyRows * 2;
        
        return count;
    }
}
