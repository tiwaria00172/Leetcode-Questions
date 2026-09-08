class Solution {
    public int countCommas(int n) {
        int totalCommas = 0;
        
        
        long currentThreshold = 1000; 
        
        while (n >= currentThreshold) {
            
            totalCommas += (n - currentThreshold + 1);
            
            
            currentThreshold *= 1000;
        }
        
        return totalCommas;
    }
}
