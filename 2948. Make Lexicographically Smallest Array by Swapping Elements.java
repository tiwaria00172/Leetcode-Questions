class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] indexedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            indexedNums[i][0] = nums[i];
            indexedNums[i][1] = i;
        }

        // Sort by value
        java.util.Arrays.sort(indexedNums, (a, b) -> Integer.compare(a[0], b[0]));

        java.util.List<java.util.List<Integer>> groups = new java.util.ArrayList<>();
        java.util.List<Integer> currentGroupVal = new java.util.ArrayList<>();
        java.util.List<Integer> currentGroupIdx = new java.util.ArrayList<>();

        currentGroupVal.add(indexedNums[0][0]);
        currentGroupIdx.add(indexedNums[0][1]);
        groups.add(currentGroupVal);

        java.util.List<java.util.List<Integer>> indexGroups = new java.util.ArrayList<>();
        indexGroups.add(currentGroupIdx);

        for (int i = 1; i < n; i++) {
            if (indexedNums[i][0] - indexedNums[i - 1][0] <= limit) {
                currentGroupVal.add(indexedNums[i][0]);
                currentGroupIdx.add(indexedNums[i][1]);
            } else {
                currentGroupVal = new java.util.ArrayList<>();
                currentGroupIdx = new java.util.ArrayList<>();
                currentGroupVal.add(indexedNums[i][0]);
                currentGroupIdx.add(indexedNums[i][1]);
                groups.add(currentGroupVal);
                indexGroups.add(currentGroupIdx);
            }
        }

        int[] result = new int[n];
        for (int g = 0; g < groups.size(); g++) {
            java.util.List<Integer> vals = groups.get(g);
            java.util.List<Integer> idxs = indexGroups.get(g);
            java.util.Collections.sort(idxs);

            for (int i = 0; i < idxs.size(); i++) {
                result[idxs.get(i)] = vals.get(i);
            }
        }

        return result;
    }
}
