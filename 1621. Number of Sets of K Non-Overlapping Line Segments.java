class Solution {
    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;
        
        // If it's impossible to choose 2k points out of N points
        if (R > N) return 0;
        
        long mod = 1_000_000_007;
        long numerator = 1;
        long denominator = 1;
        
        // Calculate N! / (N-R)! and R! under modulo
        for (int i = 0; i < R; i++) {
            numerator = (numerator * (N - i)) % mod;
            denominator = (denominator * (i + 1)) % mod;
        }
        
        // Compute (numerator / denominator) % mod using Fermat's Little Theorem
        return (int) ((numerator * modInverse(denominator, mod)) % mod);
    }
    
    // Function to calculate modular inverse using Binary Exponentiation
    private long modInverse(long n, long mod) {
        return power(n, mod - 2, mod);
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
