class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQuestion = 0, rightQuestion = 0;

        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                leftQuestion++;
            } else {
                leftSum += c - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                rightQuestion++;
            } else {
                rightSum += c - '0';
            }
        }

        int sumDiff = leftSum - rightSum;
        int qDiff = leftQuestion - rightQuestion;

        if ((leftQuestion + rightQuestion) % 2 != 0) {
            return true; 
        }

        return sumDiff != -9 * (qDiff / 2);
    }
}
