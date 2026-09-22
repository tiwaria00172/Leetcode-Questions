import java.util.*;

class Solution {
    // Segment Tree Node definition
    static class Node {
        int prod;
        int[] remain;

        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    private int n;
    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];
        
        // Build the initial Segment Tree
        build(nums, 0, 0, n - 1);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int xi = queries[i][3];

            // 1. Persistent update: Modify nums[idx] to val
            update(0, 0, n - 1, idx, val);

            // 2. Query the suffix starting from 'start' to 'n - 1'
            Node queryResult = query(0, 0, n - 1, start, n - 1);

            // 3. Extract the answer for the remainder xi
            result[i] = queryResult.remain[xi];
        }

        return result;
    }

    private void build(int[] nums, int cur, int left, int right) {
        tree[cur] = new Node(k);
        if (left == right) {
            int valMod = nums[left] % k;
            tree[cur].prod = valMod;
            tree[cur].remain[valMod] = 1;
            return;
        }
        int mid = left + (right - left) / 2;
        build(nums, 2 * cur + 1, left, mid);
        build(nums, 2 * cur + 2, mid + 1, right);
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private void update(int cur, int lo, int hi, int idx, int val) {
        if (lo == hi) {
            int valMod = val % k;
            Arrays.fill(tree[cur].remain, 0);
            tree[cur].prod = valMod;
            tree[cur].remain[valMod] = 1;
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (idx <= mid) {
            update(2 * cur + 1, lo, mid, idx, val);
        } else {
            update(2 * cur + 2, mid + 1, hi, idx, val);
        }
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private Node query(int cur, int lo, int hi, int ql, int qr) {
        if (ql <= lo && hi <= qr) {
            return tree[cur];
        }
        int mid = lo + (hi - lo) / 2;
        if (qr <= mid) {
            return query(2 * cur + 1, lo, mid, ql, qr);
        } else if (ql > mid) {
            return query(2 * cur + 2, mid + 1, hi, ql, qr);
        } else {
            Node leftNode = query(2 * cur + 1, lo, mid, ql, qr);
            Node rightNode = query(2 * cur + 2, mid + 1, hi, ql, qr);
            return merge(leftNode, rightNode);
        }
    }

    private Node merge(Node left, Node right) {
        Node parent = new Node(k);
        parent.prod = (left.prod * right.prod) % k;
        
        // Copy left sub-segments counts directly
        for (int j = 0; j < k; j++) {
            parent.remain[j] += left.remain[j];
        }
        
        // Accumulate right sub-segments counts shifted by left total product
        for (int j = 0; j < k; j++) {
            if (right.remain[j] > 0) {
                int nextMod = (j * left.prod) % k;
                parent.remain[nextMod] += right.remain[j];
            }
        }
        
        return parent;
    }
}
