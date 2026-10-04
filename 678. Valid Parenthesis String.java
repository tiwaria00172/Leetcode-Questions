class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0; // Minimum possible open parentheses
        int cmax = 0; // Maximum possible open parentheses
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmin--;
                cmax--;
            } else if (c == '*') {
                cmin--; // If we treat '*' as ')'
                cmax++; // If we treat '*' as '('
            }
            
            // If maximum possible open parentheses is negative, 
            // it means we have too many close parentheses ')'
            if (cmax < 0) {
                return false;
            }
            
            // cmin cannot be less than 0 because we can always choose 
            // to treat a '*' as an empty string or '(' instead of a ')'
            cmin = Math.max(0, cmin);
        }
        
        // If cmin is 0, it means we can successfully balance all parentheses
        return cmin == 0;
    }
}
