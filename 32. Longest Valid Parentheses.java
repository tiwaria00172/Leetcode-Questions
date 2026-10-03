import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        
        // Push -1 as a base index to handle edge cases and length calculations
        stack.push(-1);
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // Push the index of the opening parenthesis
                stack.push(i);
            } else {
                // Pop the top element for the closing parenthesis
                stack.pop();
                
                if (stack.isEmpty()) {
                    // Push current index as the new base for future valid substrings
                    stack.push(i);
                } else {
                    // Calculate the length of the valid parentheses substring
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        
        return maxLen;
    }
}
