import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        // Track what we need to match the target up to each index
        int[] currentCounts = counts.clone();
        boolean canMatchPrefix = true;
        
        // Check how far we can perfectly match target's prefix
        for (int i = 0; i < n; i++) {
            int idx = target.charAt(i) - 'a';
            if (currentCounts[idx] > 0) {
                currentCounts[idx]--;
            } else {
                canMatchPrefix = false;
                break;
            }
        }

        // If the entire target can be formed by a permutation of s,
        // we can start our rollback process from the very end (index n - 1).
        // Otherwise, if we broke out at index `idx`, any divergence must happen at or before `idx`.
        int startIdx = canMatchPrefix ? n - 1 : n - 1; 
        
        // To be safe, always rollback character by character from target to see 
        // where we can replace target.charAt(i) with a strictly greater character.
        int[] rollbackCounts = counts.clone();
        for (int i = 0; i < n; i++) {
            rollbackCounts[target.charAt(i) - 'a']--;
        }

        for (int i = n - 1; i >= 0; i--) {
            // Put the target character at index i back into the available pool
            int targetCharIdx = target.charAt(i) - 'a';
            rollbackCounts[targetCharIdx]++;

            // Try to find a character strictly greater than target.charAt(i)
            for (int nextCharIdx = targetCharIdx + 1; nextCharIdx < 26; nextCharIdx++) {
                if (rollbackCounts[nextCharIdx] > 0) {
                    // Found the pivot! Build the result string
                    StringBuilder sb = new StringBuilder();
                    
                    // 1. Append identical prefix from target
                    sb.append(target, 0, i);
                    
                    // 2. Append the next larger character
                    sb.append((char) ('a' + nextCharIdx));
                    rollbackCounts[nextCharIdx]--;
                    
                    // 3. Append remaining characters in sorted order (smallest to largest)
                    for (int c = 0; c < 26; c++) {
                        while (rollbackCounts[c] > 0) {
                            sb.append((char) ('a' + c));
                            rollbackCounts[c]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}
