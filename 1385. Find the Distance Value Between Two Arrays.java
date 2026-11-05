import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 to enable binary search
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int val : arr1) {
            // Check if any element in arr2 falls within [val - d, val + d]
            if (isValid(arr2, val - d, val + d)) {
                distanceValue++;
            }
        }

        return distanceValue;
    }

    private boolean isValid(int[] arr2, int lower, int upper) {
        int left = 0;
        int right = arr2.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If an element is found within the forbidden range, it's invalid
            if (arr2[mid] >= lower && arr2[mid] <= upper) {
                return false; 
            } else if (arr2[mid] < lower) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // No element found in the forbidden range
        return true; 
    }
}
