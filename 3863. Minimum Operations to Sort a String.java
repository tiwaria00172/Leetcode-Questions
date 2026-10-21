class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                sorted = false;
                break;
            }
        }
        if (sorted) {
            return 0;
        }
        
        // If length is 2 and not sorted, it is impossible
        if (n == 2) {
            return -1;
        }
        
        // Find the global minimum and maximum characters
        char minChar = 'z', maxChar = 'a';
        for (char c : arr) {
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
        // Check if 1 operation is enough:
        // Either minChar is at the very first position or maxChar is at the very last position
        if (arr[0] == minChar || arr[n - 1] == maxChar) {
            return 1;
        }
        
        // Check if 2 operations are enough:
        // If minChar or maxChar can be brought to the correct side in two steps
        boolean minInMiddle = false;
        boolean maxInMiddle = false;
        for (int i = 1; i < n; i++) {
            if (arr[i] == minChar) minInMiddle = true;
        }
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == maxChar) maxInMiddle = true;
        }
        
        if (minInMiddle || maxInMiddle) {
            return 2;
        }
        
        // Otherwise, at most 3 operations are required
        return 3;
    }
}
