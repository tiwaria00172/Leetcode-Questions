import java.util.Arrays;

public class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // lastMatch[j] stores the largest index in word1 from which 
        // the suffix of word2 starting at index j can be completely matched.
        int[] lastMatch = new int[m];
        Arrays.fill(lastMatch, -1);
        
        // Fill lastMatch using a greedy approach from right to left
        int w1Idx = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (w1Idx >= 0 && word1.charAt(w1Idx) != word2.charAt(j)) {
                w1Idx--;
            }
            if (w1Idx >= 0) {
                lastMatch[j] = w1Idx;
                w1Idx--; // Move to next character for the next iteration
            } else {
                break; // Remaining suffix cannot be fully matched
            }
        }
        
        int[] result = new int[m];
        int j = 0; // Pointer for word2
        boolean altered = false; // Tracks if we used our one allowed alteration
        
        // Greedily find the smallest indices from left to right
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                result[j] = i;
                j++;
            } else if (!altered) {
                // We have a mismatch. We can alter this position if:
                // 1. It is the last character of word2 (j == m - 1)
                // 2. Or the suffix of word1 from i + 1 can match the remaining suffix of word2
                if (j == m - 1 || (lastMatch[j + 1] > i)) {
                    result[j] = i;
                    j++;
                    altered = true;
                }
            }
        }
        
        // If we couldn't match all characters of word2, return an empty array
        return j == m ? result : new int[0];
    }
}
