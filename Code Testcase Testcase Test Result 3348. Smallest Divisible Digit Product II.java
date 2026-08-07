import java.util.Arrays;

public class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Count required prime factors for t
        long tempT = t;
        int t2 = 0, t3 = 0, t5 = 0, t7 = 0;
        while (tempT % 2 == 0) { t2++; tempT /= 2; }
        while (tempT % 3 == 0) { t3++; tempT /= 3; }
        while (tempT % 5 == 0) { t5++; tempT /= 5; }
        while (tempT % 7 == 0) { t7++; tempT /= 7; }
        
        // If t has prime factors other than 2, 3, 5, 7, it's impossible
        if (tempT > 1) {
            return "-1";
        }

        int n = num.length();
        
        // Prefixes arrays to count factors present in the current prefix of num
        int[] p2 = new int[n + 1];
        int[] p3 = new int[n + 1];
        int[] p5 = new int[n + 1];
        int[] p7 = new int[n + 1];
        
        // Track the first position of '0' if any
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            p2[i + 1] = p2[i];
            p3[i + 1] = p3[i];
            p5[i + 1] = p5[i];
            p7[i + 1] = p7[i];
            
            if (c == '0') {
                if (firstZero == n) firstZero = i;
            } else {
                int v = c - '0';
                while (v % 2 == 0) { p2[i + 1]++; v /= 2; }
                while (v % 3 == 0) { p3[i + 1]++; v /= 3; }
                while (v % 5 == 0) { p5[i + 1]++; v /= 5; }
                while (v % 7 == 0) { p7[i + 1]++; v /= 7; }
            }
        }

        // Case 1: Check if the number itself is already valid and zero-free
        if (firstZero == n && p2[n] >= t2 && p3[n] >= t3 && p5[n] >= t5 && p7[n] >= t7) {
            return num;
        }

        // Case 2: Try to change a digit at position i (from right to left)
        // We cannot keep any prefix that contains a '0'
        for (int i = Math.min(n - 1, firstZero); i >= 0; i--) {
            int startDigit = num.charAt(i) - '0' + 1;
            for (int d = startDigit; d <= 9; d++) {
                // Calculate missing factors if we place digit 'd' at position i
                int rem2 = Math.max(0, t2 - p2[i] - getFactor(d, 2));
                int rem3 = Math.max(0, t3 - p3[i] - getFactor(d, 3));
                int rem5 = Math.max(0, t5 - p5[i] - getFactor(d, 5));
                int rem7 = Math.max(0, t7 - p7[i] - getFactor(d, 7));
                
                int remLen = n - 1 - i;
                if (canForm(rem2, rem3, rem5, rem7, remLen)) {
                    // Valid prefix configuration found! Construct the answer string.
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(d);
                    sb.append(generateSmallestSuffix(rem2, rem3, rem5, rem7, remLen));
                    return sb.toString();
                }
            }
        }

        // Case 3: A longer number is required (cannot fit in 'n' digits)
        int remLen = n;
        while (!canForm(t2, t3, t5, t7, remLen)) {
            remLen++;
        }
        return generateSmallestSuffix(t2, t3, t5, t7, remLen);
    }

    // Helper to extract prime counts from a single digit
    private int getFactor(int val, int prime) {
        int count = 0;
        while (val % prime == 0) {
            count++;
            val /= prime;
        }
        return count;
    }

    // Dynamic state evaluation to see if missing factors fit within available digits
    private boolean canForm(int r2, int r3, int r5, int r7, int spaces) {
        int reqSpaces = r7 + r5 + (r3 + 1) / 2; // 7s, 5s, and 9s (which use two 3s)
        r2 = Math.max(0, r2 - (r3 % 2 == 1 ? 1 : 0)); // A remaining 3 combined with a 2 makes a '6'
        
        // Condense pairs and triplets of 2s into 8s and 4s
        reqSpaces += (r2 + 2) / 3; 
        return reqSpaces <= spaces;
    }

    // Construct the lexicographically smallest combination of digits
    private String generateSmallestSuffix(int r2, int r3, int r5, int r7, int spaces) {
        StringBuilder suffix = new StringBuilder();
        
        // Process prime factors 7 and 5 greedily
        while (r7 > 0) { suffix.append('7'); r7--; }
        while (r5 > 0) { suffix.append('5'); r5--; }
        
        // Handle combinations of 2s and 3s optimally to minimize digit values
        while (r3 > 1) { suffix.append('9'); r3 -= 2; }
        while (r2 > 2) { suffix.append('8'); r2 -= 3; }
        
        if (r3 == 1 && r2 == 2) {
            suffix.append('6'); suffix.append('4');
            r3 = 0; r2 = 0;
        } else if (r3 == 1 && r2 == 1) {
            suffix.append('6');
            r3 = 0; r2 = 0;
        } else if (r3 == 1 && r2 == 0) {
            suffix.append('3');
            r3 = 0;
        } else if (r3 == 0 && r2 == 2) {
            suffix.append('4');
            r2 = 0;
        } else if (r3 == 0 && r2 == 1) {
            suffix.append('2');
            r2 = 0;
        }
        
        // Fill remaining spaces with '1's at the front to maintain minimal value
        char[] chars = suffix.toString().toCharArray();
        Arrays.sort(chars);
        
        StringBuilder res = new StringBuilder();
        int ones = spaces - chars.length;
        while (ones > 0) {
            res.append('1');
            ones--;
        }
        res.append(new String(chars));
        return res.toString();
    }
}
