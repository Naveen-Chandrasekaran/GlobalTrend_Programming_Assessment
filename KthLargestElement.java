import java.util.Arrays;

public class KthLargestElement {

    // Function to find the kth largest element in the array
    public static int findKthLargest(int[] nums, int k) {
        // Sort the array in ascending order
        Arrays.sort(nums);

        // Return the kth largest element (since arrays are 0-indexed, nums.length - k gives the correct index)
        return nums[nums.length - k];
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element in the array is: " + result);
    }
}
