import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Store knowledge pairs in a hash map for O(1) average time lookup
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;

        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                // Find the closing bracket
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                // Extract the key inside the brackets
                String key = s.substring(i + 1, j);
                // Append the corresponding value or '?' if the key is missing
                sb.append(map.getOrDefault(key, "?"));
                // Move index past the closing bracket
                i = j + 1;
            } else {
                // Normal character outside brackets
                sb.append(c);
                i++;
            }
        }

        return sb.toString();
    }
}
