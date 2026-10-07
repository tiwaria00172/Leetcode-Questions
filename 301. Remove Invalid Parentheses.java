import java.util.*;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        // Queue for BFS and a Set to avoid processing duplicate strings
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(s);
        visited.add(s);

        boolean foundValidAtThisLevel = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process the current level entirely
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // If it's valid, add it to our result list
                if (isValid(current)) {
                    result.add(current);
                    foundValidAtThisLevel = true;
                }

                // If a valid string was already found at this level, 
                // do not generate further states (which would mean more removals)
                if (foundValidAtThisLevel) continue;

                // Generate next states by removing one parenthesis at a time
                for (int j = 0; j < current.length(); j++) {
                    char c = current.charAt(j);
                    if (c != '(' && c != ')') continue; // Skip letters

                    // Construct the substring without the character at index j
                    String nextState = current.substring(0, j) + current.substring(j + 1);

                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.add(nextState);
                    }
                }
            }

            // Stop going to the next level if we found valid answers at the current level
            if (foundValidAtThisLevel) {
                break;
            }
        }

        return result;
    }

    // Helper method to check if a string has valid parentheses
    private boolean isValid(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false; // More closing than opening at this point
            }
        }
        return count == 0;
    }
}
