class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Eliminate all land connected to the top and bottom borders
        for (int c = 0; c < cols; c++) {
            if (grid[0][c] == 0) {
                dfs(grid, 0, c);
            }
            if (grid[rows - 1][c] == 0) {
                dfs(grid, rows - 1, c);
            }
        }
        
        // Step 2: Eliminate all land connected to the left and right borders
        for (int r = 0; r < rows; r++) {
            if (grid[r][0] == 0) {
                dfs(grid, r, 0);
            }
            if (grid[r][cols - 1] == 0) {
                dfs(grid, r, cols - 1);
            }
        }
        
        // Step 3: Count remaining closed islands
        int closedIslands = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    closedIslands++;
                    dfs(grid, r, c);
                }
            }
        }
        
        return closedIslands;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Boundary check and water check
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == 1) {
            return;
        }
        
        // Mark as visited by turning land (0) into water (1)
        grid[r][c] = 1;
        
        // Traverse all 4 directions
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
