import java.util.Arrays;

public class Solution {
    
    // Helper method to compute the sum of digits of a number
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    // Helper class to store the number, its original index, and its digit sum
    private static class Element {
        int value;
        int originalIndex;
        int digitSum;

        Element(int value, int originalIndex, int digitSum) {
            this.value = value;
            this.originalIndex = originalIndex;
            this.digitSum = digitSum;
        }
    }

    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        Element[] elements = new Element[n];

        // 1. Initialize the elements array with values, original indices, and digit sums
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(nums[i], i, getDigitSum(nums[i]));
        }

        // 2. Sort the array based on the custom rules
        Arrays.sort(elements, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }
            return Integer.compare(a.value, b.value);
        });

        // 3. Find the minimum swaps using cycle decomposition
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;

        for (int i = 0; i < n; i++) {
            // If the element is already visited or is in its correct sorted position
            if (visited[i] || elements[i].originalIndex == i) {
                continue;
            }

            // Calculate the size of the cycle
            int cycleSize = 0;
            int currIndex = i;

            while (!visited[currIndex]) {
                visited[currIndex] = true;
                // Move to the next index in the cycle
                currIndex = elements[currIndex].originalIndex;
                cycleSize++;
            }

            // If there is a cycle of size k, it takes k - 1 swaps to fix it
            if (cycleSize > 0) {
                totalSwaps += (cycleSize - 1);
            }
        }

        return totalSwaps;
    }
}
