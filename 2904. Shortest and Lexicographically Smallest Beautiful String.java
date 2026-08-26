class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int minLen = n + 1;
        String result = "";
        
        int count = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count++;
            }
            
            while (count == k) {
                int currentLen = right - left + 1;
                String sub = s.substring(left, right + 1);
                
                if (currentLen < minLen) {
                    minLen = currentLen;
                    result = sub;
                } else if (currentLen == minLen) {
                    if (sub.compareTo(result) < 0) {
                        result = sub;
                    }
                }
                
                if (s.charAt(left) == '1') {
                    count--;
                }
                left++;
            }
        }
        
        return result;
    }
}
