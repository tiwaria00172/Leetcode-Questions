import java.util.Arrays;

public class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count the frequency of each letter
        int[] frequencies = new int[26];
        for (char c : word.toCharArray()) {
            frequencies[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in ascending order
        Arrays.sort(frequencies);
        
        int totalPushes = 0;
        int count = 0;
        
        // Step 3: Iterate backwards from the most frequent letter
        for (int i = 25; i >= 0; i--) {
            if (frequencies[i] == 0) {
                break; // No more unique letters left in the word
            }
            
            // Calculate push cost based on the number of keys assigned so far
            int pushCost = (count / 8) + 1;
            totalPushes += frequencies[i] * pushCost;
            
            count++;
        }
        
        return totalPushes;
    }
}
