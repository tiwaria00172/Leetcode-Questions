class Solution {
    public int totalEvenNumbers(int[] digits) {
        // Count frequencies of each digit in the input array
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }
        
        int uniqueEvenCount = 0;
        
        // Iterate through all 3-digit even numbers (100 to 998, stepping by 2)
        for (int num = 100; num < 1000; num += 2) {
            if (canForm(num, count)) {
                uniqueEvenCount++;
            }
        }
        
        return uniqueEvenCount;
    }
    
    private boolean canForm(int num, int[] availableCount) {
        int[] numCount = new int[10];
        int temp = num;
        
        // Extract digits of the current number
        while (temp > 0) {
            numCount[temp % 10]++;
            temp /= 10;
        }
        
        // Check if we have enough of each digit
        for (int i = 0; i < 10; i++) {
            if (numCount[i] > availableCount[i]) {
                return false;
            }
        }
        
        return true;
    }
}
