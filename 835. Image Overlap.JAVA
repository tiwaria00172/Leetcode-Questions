import java.util.*;

public class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // Step 1: Collect coordinates of all 1s from both images
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    list1.add(new int[]{i, j});
                }
                if (img2[i][j] == 1) {
                    list2.add(new int[]{i, j});
                }
            }
        }

        // Step 2: Use a map to count frequencies of each unique shift vector
        // Key represents the transformation string "rowShift_colShift"
        Map<String, Integer> map = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int rowShift = p2[0] - p1[0];
                int colShift = p2[1] - p1[1];
                
                String key = rowShift + "_" + colShift;
                map.put(key, map.getOrDefault(key, 0) + 1);
                
                maxOverlap = Math.max(maxOverlap, map.get(key));
            }
        }

        return maxOverlap;
    }
}
