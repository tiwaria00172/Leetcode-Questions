class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        int maxVal = 0;
        int overallLcm = 1;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            overallLcm = lcm(overallLcm, num);
        }
        long maxProd = (long) overallLcm * maxVal;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int g = 0;
            int l = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                if (prod > maxProd) {
                    break;
                }
                g = gcd(g, nums[j]);
                l = lcm(l, nums[j]);

                if (prod == (long) g * l) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a / gcd(a, b) * b;
    }
}
