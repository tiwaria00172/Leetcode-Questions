class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }
        
        // Step 1: Find all suspicious methods reachable from k
        boolean[] suspicious = new boolean[n];
        dfs(k, graph, suspicious);
        
        // Step 2: Check if any non-suspicious method invokes a suspicious method
        boolean canRemove = true;
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!suspicious[u] && suspicious[v]) {
                canRemove = false;
                break;
            }
        }
        
        // Step 3: Return remaining methods
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!canRemove || !suspicious[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    private void dfs(int u, List<List<Integer>> graph, boolean[] suspicious) {
        suspicious[u] = true;
        for (int v : graph.get(u)) {
            if (!suspicious[v]) {
                dfs(v, graph, suspicious);
            }
        }
    }
}
