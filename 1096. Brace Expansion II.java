import java.util.*;

class Solution {
    private Set<String> resultSet = new HashSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    private void dfs(String exp) {
        int j = exp.indexOf('}');
        // Base case: no more closing braces, add expression to set
        if (j == -1) {
            resultSet.add(exp);
            return;
        }
        
        // Find the last opening brace before the first closing brace `j`
        int i = exp.lastIndexOf('{', j);
        String prefix = exp.substring(0, i);
        String suffix = exp.substring(j + 1);
        
        // Split comma-delimited options inside the braces
        String inside = exp.substring(i + 1, j);
        String[] options = inside.split(",");
        
        // Recurse for each option
        for (String option : options) {
            dfs(prefix + option + suffix);
        }
    }
}
