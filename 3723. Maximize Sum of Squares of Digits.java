public class Solution {
    public String maximizeSumOfSquares(int num, int sum) {
        // If the sum is impossible to achieve with the given number of digits
        if (sum > 9 * num) {
            return "";
        }

        // Calculate the count of each digit
        int count9 = sum / 9;
        int remainder = sum % 9;
        int count0 = num - count9 - (remainder > 0 ? 1 : 0);

        // Build the largest possible number using StringBuilder
        StringBuilder sb = new StringBuilder();
        
        // Append all 9s
        for (int i = 0; i < count9; i++) {
            sb.append('9');
        }
        
        // Append the remainder if it exists
        if (remainder > 0) {
            sb.append(remainder);
        }
        
        // Append all 0s
        for (int i = 0; i < count0; i++) {
            sb.append('0');
        }

        return sb.toString();
    }
}
