class Solution {
    public int minChanges(String s) {
        int changes = 0;
        // Check every adjacent pair with a step of 2
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        return changes;
    }
}
