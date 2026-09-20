class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Calculate the 1-indexed position in the reversed alphabet ('a' = 26, 'z' = 1)
            int reversedAlphabetPos = 26 - (c - 'a');
            
            // Calculate the 1-indexed position in the string
            int stringPos = i + 1;
            
            // Add the product to the total sum
            totalDegree += reversedAlphabetPos * stringPos;
        }
        
        return totalDegree;
    }
}
