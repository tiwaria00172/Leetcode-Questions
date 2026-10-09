class Solution {
    public int minInsertions(String s) {
        int res = 0; // Total insertions needed
        int open = 0; // Unmatched '(' count

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else { // c == ')'
                // Check if the next character is also ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // Skip the next ')' since it forms ')'
                } else {
                    res++; // We need one more ')' to complete ')'
                }

                if (open > 0) {
                    open--; // Match with an open '('
                } else {
                    res++; // We need an opening '(' for this ')' pair
                }
            }
        }

        // Each remaining open '(' needs two ')' characters
        res += open * 2;

        return res;
    }
}
