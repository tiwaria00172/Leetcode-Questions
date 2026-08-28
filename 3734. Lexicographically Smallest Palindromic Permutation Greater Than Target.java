import java.util.Arrays;

public class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int m = n / 2;
        int[] counts = new int[26];
        
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }
        
        if (oddCount > 1) {
            return "";
        }
        
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCounts[i] = counts[i] / 2;
        }
        
        String bestPalindrome = null;
        
        for (int i = 0; i < m; i++) {
            int[] currentCounts = halfCounts.clone();
            boolean possible = true;
            
            for (int j = 0; j < i; j++) {
                int cIdx = target.charAt(j) - 'a';
                if (currentCounts[cIdx] > 0) {
                    currentCounts[cIdx]--;
                } else {
                    possible = false;
                    break;
                }
            }
            
            if (!possible) continue;
            
            int targetCharIdx = target.charAt(i) - 'a';
            for (int cIdx = targetCharIdx + 1; cIdx < 26; cIdx++) {
                if (currentCounts[cIdx] > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, i);
                    sb.append((char) ('a' + cIdx));
                    
                    int[] nextCounts = currentCounts.clone();
                    nextCounts[cIdx]--;
                    
                    for (int r = 0; r < 26; r++) {
                        while (nextCounts[r] > 0) {
                            sb.append((char) ('a' + r));
                            nextCounts[r]--;
                        }
                    }
                    
                    String halfStr = sb.toString();
                    StringBuilder fullSb = new StringBuilder(halfStr);
                    if (n % 2 != 0) {
                        fullSb.append(midChar);
                    }
                    fullSb.append(new StringBuilder(halfStr).reverse());
                    
                    String candidate = fullSb.toString();
                    if (candidate.compareTo(target) > 0) {
                        if (bestPalindrome == null || candidate.compareTo(bestPalindrome) < 0) {
                            bestPalindrome = candidate;
                        }
                    }
                    break; 
                }
            }
        }
        
        int[] currentCounts = halfCounts.clone();
        boolean possible = true;
        for (int j = 0; j < m; j++) {
            int cIdx = target.charAt(j) - 'a';
            if (currentCounts[cIdx] > 0) {
                currentCounts[cIdx]--;
            } else {
                possible = false;
                break;
            }
        }
        
        if (possible) {
            String halfStr = target.substring(0, m);
            StringBuilder fullSb = new StringBuilder(halfStr);
            if (n % 2 != 0) {
                fullSb.append(midChar);
            }
            fullSb.append(new StringBuilder(halfStr).reverse());
            
            String candidate = fullSb.toString();
            if (candidate.compareTo(target) > 0) {
                if (bestPalindrome == null || candidate.compareTo(bestPalindrome) < 0) {
                    bestPalindrome = candidate;
                }
            }
        }
        
        return bestPalindrome == null ? "" : bestPalindrome;
    }
}
