import java.util.*;

public class Solution {
    private List<List<Integer>> adj;
    private int[] in, out, timer;
    private int[] depth, up;
    private int LOG;
    private int[] nodeMask;
    private int[] segTree, lazy;

    public boolean[] palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        LOG = 0;
        while ((1 << LOG) <= n) LOG++;
        up = new int[n * LOG];
        depth = new int[n];
        in = new int[n];
        out = new int[n];
        timer = new int[]{0};

        dfsLCA(0, -1, 0);

        char[] chars = s.toCharArray();
        nodeMask = new int[n];
        int[] rootMask = new int[n];
        dfsMask(0, -1, 0, chars, rootMask);

        // Segment tree array for Euler tour range
        segTree = new int[4 * n];
        lazy = new int[4 * n];
        build(1, 0, n - 1, rootMask);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int oldMask = maskOf(chars[u]);
                int newMask = maskOf(c);
                int diff = oldMask ^ newMask;
                chars[u] = c;
                updateRange(1, 0, n - 1, in[u], out[u], diff);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v);
                int maskU = queryPoint(1, 0, n - 1, in[u]);
                int maskV = queryPoint(1, 0, n - 1, in[v]);
                int maskLCA = maskOf(chars[lca]);
                int pathMask = maskU ^ maskV ^ maskLCA;
                ans.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        boolean[] res = new boolean[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }

    private int maskOf(char c) {
        return 1 << (c - 'a');
    }

    private void dfsLCA(int u, int p, int d) {
        depth[u] = d;
        up[u * LOG + 0] = p != -1 ? p : u;
        for (int i = 1; i < LOG; i++) {
            up[u * LOG + i] = up[up[u * LOG + i - 1] * LOG + i - 1];
        }
        for (int v : adj.get(u)) {
            if (v != p) {
                dfsLCA(v, u, d + 1);
            }
        }
    }

    private void dfsMask(int u, int p, int currentMask, char[] chars, int[] rootMask) {
        in[u] = timer[0]++;
        currentMask ^= maskOf(chars[u]);
        rootMask[in[u]] = currentMask;
        for (int v : adj.get(u)) {
            if (v != p) {
                dfsMask(v, u, currentMask, chars, rootMask);
            }
        }
        out[u] = timer[0] - 1;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth.length && depth[u] > depth[v]) {
            int t = u; u = v; v = t;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[v] - (1 << i) >= depth[u]) {
                v = up[v * LOG + i];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u * LOG + i] != up[v * LOG + i]) {
                u = up[u * LOG + i];
                v = up[v * LOG + i];
            }
        }
        return up[u * LOG + 0];
    }

    private void build(int node, int start, int end, int[] arr) {
        if (start == end) {
            segTree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid, arr);
        build(2 * node + 1, mid + 1, end, arr);
        segTree[node] = segTree[2 * node] ^ segTree[2 * node + 1];
    }

    private void push(int node) {
        if (lazy[node] != 0) {
            segTree[2 * node] ^= lazy[node];
            lazy[2 * node] ^= lazy[node];
            segTree[2 * node + 1] ^= lazy[node];
            lazy[2 * node + 1] ^= lazy[node];
            lazy[node] = 0;
        }
    }

    private void updateRange(int node, int start, int end, int l, int r, int val) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            segTree[node] ^= val;
            lazy[node] ^= val;
            return;
        }
        push(node);
        int mid = (start + end) / 2;
        updateRange(2 * node, start, mid, l, r, val);
        updateRange(2 * node + 1, mid + 1, end, l, r, val);
        segTree[node] = segTree[2 * node] ^ segTree[2 * node + 1];
    }

    private int queryPoint(int node, int start, int end, int idx) {
        if (start == end) return segTree[node];
        push(node);
        int mid = (start + end) / 2;
        if (idx <= mid) return queryPoint(2 * node, start, mid, idx);
        else return queryPoint(2 * node + 1, mid + 1, end, idx);
    }
}
