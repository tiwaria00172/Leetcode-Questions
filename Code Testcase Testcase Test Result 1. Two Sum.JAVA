public class TwoSumSorted { 
    public static int[] twoSum(int[] numbers, int target) { 
        int left = 0; 
        int right = numbers.length - 1; 
        
        while (left < right) { 
            int currentSum = numbers[left] + numbers[right]; 
            
            if (currentSum == target) { 
                return new int[]{left, right}; 
            } else if (currentSum < target) { 
                left++; 
            } else { 
                right--; 
            } 
        } 
        
        return new int[]{-1, -1}; // Fixed syntax error here
    } 
}
