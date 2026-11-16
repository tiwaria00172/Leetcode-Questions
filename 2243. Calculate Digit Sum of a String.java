class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Process the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                // Sum the digits in the current chunk
                for (int j = i; j < i + k && j < s.length(); j++) {
                    sum += s.charAt(j) - '0';
                }
                nextRound.append(sum);
            }
            
            // Update s for the next round
            s = nextRound.toString();
        }
        
        return s;
    }
}
