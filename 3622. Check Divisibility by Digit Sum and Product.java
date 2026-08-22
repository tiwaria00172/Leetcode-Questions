class Solution {
    public boolean isDivisibleBySumAndProduct(int n) {
        int temp = n;
        int digitSum = 0;
        int digitProduct = 1;
        
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProduct *= digit;
            temp /= 10;
        }
        
        int totalSum = digitSum + digitProduct;
        
        // Avoid division by zero if totalSum is 0 (though n >= 1 prevents this)
        if (totalSum == 0) {
            return false; 
        }
        
        return n % totalSum == 0;
    }
}
