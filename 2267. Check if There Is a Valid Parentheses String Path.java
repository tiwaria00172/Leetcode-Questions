public class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length; // Fixed: grid[0].length ensures correct column count
        
        // A valid parentheses string must have an even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        // Maximum possible open brackets we could accumulate
        int maxBal = m + n;
        boolean[][][] memo = new boolean[m][n][maxBal];
        
        return dfs(0, 0, 0, grid, memo, m, n);
    }
    
    private boolean dfs(int r, int c, int bal, char[][] grid, boolean[][][] memo, int m, int n) {
        // Update balance based on current cell
        if (grid[r][c] == '(') {
            bal++;
        } else {
            bal--;
        }
        
        // If balance goes negative, it's an invalid prefix sequence
        if (bal < 0 || bal >= memo[0][0].length) {
            return false;
        }
        
        // Reached the bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }
        
        // If this state has already been visited/evaluated, return false
        if (memo[r][c][bal]) {
            return false;
        }
        
        // Mark the current state as visited
        memo[r][c][bal] = true;
        
        // Move Right (passing all 7 required arguments)
        if (c + 1 < n && dfs(r, c + 1, bal, grid, memo, m, n)) {
            return true;
        }
        
        // Move Down (passing all 7 required arguments)
        if (r + 1 < m && dfs(r + 1, c, bal, grid, memo, m, n)) {
            return true;
        }
        
        return false;
    }
}
