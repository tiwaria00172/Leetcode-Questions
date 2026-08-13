class Solution {
    class Node {
        char leftChar, rightChar;
        int leftLen, rightLen, maxLen, size;

        Node(char c, int len) {
            leftChar = c;
            rightChar = c;
            leftLen = len;
            rightLen = len;
            maxLen = len;
            size = len;
        }

        Node(char lc, int lLen, char rc, int rLen, int mLen, int sz) {
            leftChar = lc;
            leftLen = lLen;
            rightChar = rc;
            rightLen = rLen;
            maxLen = mLen;
            size = sz;
        }
    }

    private Node[] tree;
    private char[] sChars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        sChars = s.toCharArray();
        tree = new Node[4 * n];
        build(0, 0, n - 1);

        int k = queryIndices.length;
        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            sChars[idx] = c;
            update(0, 0, n - 1, idx, c);
            res[i] = tree[0].maxLen; // Read from root node
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(sChars[start], 1);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node + 1, start, mid);
        build(2 * node + 2, mid + 1, end);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(c, 1);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, c);
        } else {
            update(2 * node + 2, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private Node merge(Node left, Node right) {
        char lc = left.leftChar;
        int lLen = left.leftLen;
        // Extend left prefix length if the entire left child is a single uniform character
        if (left.leftLen == left.size && left.rightChar == right.leftChar) {
            lLen += right.leftLen;
        }

        char rc = right.rightChar;
        int rLen = right.rightLen;
        // Extend right suffix length if the entire right child is a single uniform character
        if (right.rightLen == right.size && right.rightChar == left.rightChar) {
            rLen += left.rightLen;
        }

        // Calculate max block by considering individual child maximums and the middle crossover
        int mLen = Math.max(left.maxLen, right.maxLen);
        if (left.rightChar == right.leftChar) {
            mLen = Math.max(mLen, left.rightLen + right.leftLen);
        }

        return new Node(lc, lLen, rc, rLen, mLen, left.size + right.size);
    }
}
