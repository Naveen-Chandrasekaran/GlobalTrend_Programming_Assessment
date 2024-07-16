import java.util.Stack;

public class ValidParentheses {

    public static boolean isValid(String s) {
        // Use a stack to track opening brackets
        Stack<Character> stack = new Stack<>();
        
        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                // If it's an opening bracket, push onto the stack
                stack.push(c);
            } else {
                // If it's a closing bracket
                if (stack.isEmpty()) {
                    // If stack is empty, no matching opening bracket
                    return false;
                }
                // Pop the top of the stack
                char top = stack.pop();
                // Check if it matches the corresponding opening bracket
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false; // Mismatched brackets
                }
            }
        }
        
        // Stack should be empty if all opening brackets have matching closing brackets
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        String input1 = "([{}])";
        String input2 = "([)]";
        
        // Test cases
        System.out.println("Input: " + input1 + " => Valid: " + isValid(input1));
        System.out.println("Input: " + input2 + " => Valid: " + isValid(input2));
    }
}
