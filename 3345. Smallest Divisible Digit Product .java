class Solution {
    public int smallestNumber(int n, int t) {
        // Increment iteratively starting from n
        while (true) {
            if (getDigitProduct(n) % t == 0) {
                return n;
            }
            n++;
        }
    }

    // Helper method to compute the product of a number's digits
    private int getDigitProduct(int num) {
        int product = 1;
        while (num > 0) {
            product *= (num % 10);
            num /= 10;
        }
        return product;
    }
}
