public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            // Calculate current area
            int currentArea = (right - left) * Math.min(height[left], height[right]);
            // Update max area if current area is greater
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer pointing to the smaller height towards the center
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // Example usage
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxWater = maxArea(height);
        System.out.println("Maximum area of water that can be contained: " + maxWater);
    }
}
