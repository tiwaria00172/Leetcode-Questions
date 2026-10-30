import java.util.*;

class Solution {
    public int shortestPathWithAtMostKConsecutiveIdenticalCharacters(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, w]
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // Priority Queue stores: {total_weight, u, consecutive_count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        
        // dist[u][c] = minimum weight to reach node u with `c` consecutive identical characters
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Start at node 0
        char startChar = labels.charAt(0);
        pq.offer(new long[]{0, 0, 1});
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (u == n - 1) {
                return (int) d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            for (int[] neighbor : adj[u]) {
                int v = neighbor.node = neighbor[0];
                int weight = neighbor[1];
                char nextChar = labels.charAt(v);
                char currChar = labels.charAt(u);

                int nextCount = (nextChar == currChar) ? count + 1 : 1;

                if (nextCount <= k) {
                    if (d + weight < dist[v][nextCount]) {
                        dist[v][nextCount] = d + weight;
                        pq.offer(new long[]{d + weight, v, nextCount});
                    }
                }
            }
        }

        // Find minimum distance to reach node n - 1 across all valid counts
        long minAns = Long.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            minAns = Math.min(minAns, dist[n - 1][c]);
        }

        return minAns == Long.MAX_VALUE ? -1 : (int) minAns;
    }
}
