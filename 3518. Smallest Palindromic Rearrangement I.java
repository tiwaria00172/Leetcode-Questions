import java.util.Arrays;

public class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        // Validate if a palindrome can be formed
        int oddCount = 0;
        int oddIndex = -1;
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                oddIndex = i;
            }
        }

        // If more than one character has an odd count, no palindrome can exist
        if (oddCount > 1) {
            return "";
        }

        // Work only with the first half of the character frequencies
        int[] halfCounts = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            halfCounts[i] = counts[i] / 2;
            halfLen += halfCounts[i];
        }

        // Precompute factorials up to halfLen to count permutations efficiently
        long[] fact = new long[halfLen + 1];
        fact[0] = 1;
        for (int i = 1; i <= halfLen; i++) {
            fact[i] = fact[i - 1] * i;
        }

        // Check if k is greater than the total number of unique palindromic permutations
        long totalPermutations = countPermutations(halfCounts, halfLen, fact);
        if (k > totalPermutations) {
            return "";
        }

        // Construct the first half of the palindrome lexicographically
        StringBuilder firstHalf = new StringBuilder();
        long currentK = k;

        for (int i = 0; i < halfLen; i++) {
            for (int j = 0; j < 26; j++) {
                if (halfCounts[j] > 0) {
                    // Try placing character 'a' + j at the current position
                    halfCounts[j]--;
                    long options = countPermutations(halfCounts, halfLen - 1 - i, fact);

                    if (currentK <= options) {
                        firstHalf.append((char) ('a' + j));
                        break; // Character accepted, move to the next position
                    } else {
                        currentK -= options; // Skip all permutations starting with this character
                        halfCounts[j]++;     // Backtrack
                    }
                }
            }
        }

        // Construct the full palindrome using the first half and the odd character (if any)
        String firstHalfStr = firstHalf.toString();
        String middle = (oddIndex != -1) ? String.valueOf((char) ('a' + oddIndex)) : "";
        String secondHalfStr = firstHalf.reverse().toString();

        return firstHalfStr + middle + secondHalfStr;
    }

    // Helper method to count unique permutations using multinomial coefficients
    private long countPermutations(int[] counts, int totalLen, long[] fact) {
        long denominator = 1;
        for (int count : counts) {
            if (count > 1) {
                denominator *= fact[count];
            }
        }
        return fact[totalLen] / denominator;
    }
}
