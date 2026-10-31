public class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][][] targetMemo;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // targetMemo[r][c][d] stores the final [targetRow, targetCol] cell coordinates.
        // d = 0 for moving right, d = 1 for moving down.
        // Flattened 3D array to 1D/2D representation or an encoded int to optimize speed:
        // encoded int: row * n + col, or -1 if out of bounds.
        targetMemo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                targetMemo[i][j][0] = -2;
                targetMemo[i][j][1] = -2;
            }
        }

        // dp[r][c] stores the number of unique paths from (r, c) to (m-1, n-1)
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;

        // Traverse backward from bottom-right to top-left
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                // We only calculate paths from open/empty cells
                if (grid[r][c] == 1 || (r == m - 1 && c == n - 1)) {
                    continue;
                }

                long totalWays = 0;

                // Option 1: Try to move right into (r, c + 1) with direction 0 (moving right)
                int destR = getDest(r, c + 1, 0, grid, m, n);
                if (destR != -1) {
                    int destRow = destR / n;
                    int destCol = destR % n;
                    totalWays = (totalWays + dp[destRow][destCol]) % MOD;
                }

                // Option 2: Try to move down into (r + 1, c) with direction 1 (moving down)
                int destD = getDest(r + 1, c, 1, grid, m, n);
                if (destD != -1) {
                    int destRow = destD / n;
                    int destCol = destD % n;
                    totalWays = (totalWays + dp[destRow][destCol]) % MOD;
                }

                dp[r][c] = (int) totalWays;
            }
        }

        return dp[0][0];
    }

    /**
     * Resolves the ultimate non-mirror cell destination when moving into (r, c) with direction d.
     * @param d 0 for entering moving right, 1 for entering moving down.
     * @return encoded coordinates (row * n + col) of the target empty cell, or -1 if out of bounds.
     */
    private int getDest(int r, int c, int d, int[][] grid, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return -1;
        }
        if (grid[r][c] == 0) {
            return r * n + c;
        }

        if (targetMemo[r][c][d] != -2) {
            return targetMemo[r][c][d];
        }

        int result;
        if (d == 0) {
            // Entered moving right -> reflected down into (r + 1, c) moving down (d = 1)
            result = getDest(r + 1, c, 1, grid, m, n);
        } else {
            // Entered moving down -> reflected right into (r, c + 1) moving right (d = 0)
            result = getDest(r, c + 1, 0, grid, m, n);
        }

        targetMemo[r][c][d] = result;
        return result;
    }
}
