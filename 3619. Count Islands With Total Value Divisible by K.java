class Solution {
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        // Traverse every cell in the matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Positive integers represent land
                if (grid[i][j] > 0) {
                    // Use a long variable to prevent integer overflow during summation
                    long[] islandSum = new long[1]; 
                    dfs(grid, i, j, m, n, islandSum);
                    
                    // Check if the total value of the island is divisible by k
                    if (islandSum[0] % k == 0) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    private void dfs(int[][] grid, int r, int c, int m, int n, long[] islandSum) {
        // Base case: boundary checks and ensuring the cell is land (> 0)
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] <= 0) {
            return;
        }
        
        // Add the current cell value to the island's total sum
        islandSum[0] += grid[r][c];
        
        // Mark the cell as visited by setting it to 0
        grid[r][c] = 0;
        
        // Explore all 4-directional neighbors
        dfs(grid, r + 1, c, m, n, islandSum); // Down
        dfs(grid, r - 1, c, m, n, islandSum); // Up
        dfs(grid, r, c + 1, m, n, islandSum); // Right
        dfs(grid, r, c - 1, m, n, islandSum); // Left
    }
}
