class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int depth = 0;
        
        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                depth++;
                res[i] = depth % 2;
            } else {
                res[i] = depth % 2;
                depth--;
            }
        }
        
        return res;
    }
}
```

### Complexity
* **Time Complexity:** $\mathcal{O}(n)$, where $n$ is the length of the string, because we loop through the string once.
* **Space Complexity:** $\mathcal{O}(n)$ to store the result array.

<FollowUp>
Would you like an explanation of **how the depth parity logic** minimizes the maximum depth, or assistance with a solution in another **programming language** like Python or C++?
</FollowUp>
