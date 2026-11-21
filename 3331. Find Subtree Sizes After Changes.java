import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        findNewParents(0, s, origTree, lastSeen, newParent);

        List<Integer>[] newTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        computeSubtreeSizes(0, newTree, ans);
        return ans;
    }

    private void findNewParents(int u, String s, List<Integer>[] origTree, int[] lastSeen, int[] newParent) {
        int c = s.charAt(u) - 'a';
        int prev = lastSeen[c];

        if (prev != -1) {
            newParent[u] = prev;
        }

        lastSeen[c] = u;
        for (int v : origTree[u]) {
            findNewParents(v, s, origTree, lastSeen, newParent);
        }
        lastSeen[c] = prev;
    }

    private int computeSubtreeSizes(int u, List<Integer>[] newTree, int[] ans) {
        int size = 1;
        for (int v : newTree[u]) {
            size += computeSubtreeSizes(v, newTree, ans);
        }
        ans[u] = size;
        return size;
    }
}
