public class PalindromeChecker {

    // Method to check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        // Remove spaces and convert to lowercase
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        // Check palindrome
        int left = 0;
        int right = cleanStr.length() - 1;
        
        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false; // Not a palindrome
            }
            left++;
            right--;
        }
        return true; // It is a palindrome
    }

    public static void main(String[] args) {
        // Test cases
        String str1 = "racecar";
        String str2 = "race a car";
        String str3 = "Was it a car or a cat I saw?";
        
        // Check if each string is a palindrome
        System.out.println("\"" + str1 + "\" is a palindrome: " + isPalindrome(str1));
        System.out.println("\"" + str2 + "\" is a palindrome: " + isPalindrome(str2));
        System.out.println("\"" + str3 + "\" is a palindrome: " + isPalindrome(str3));
    }
}
