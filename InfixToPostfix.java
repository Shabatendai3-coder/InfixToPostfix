import java.util.*;

/**
 * Simple Infix to Postfix Converter
 * Easy to understand for students
 */
public class InfixToPostfix {
    
    // Stack to store operators
    private Stack<Character> stack;
    
    // Constructor
    public InfixToPostfix() {
        stack = new Stack<>();
    }
    
    // Check if character is an operator
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }
    
    // Get precedence of operator
    private int getPrecedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        else if (ch == '*' || ch == '/')
            return 2;
        else if (ch == '^')
            return 3;
        else
            return 0;
    }
    
    // Check if character is a letter or digit
    private boolean isOperand(char ch) {
        return (ch >= 'A' && ch <= 'Z') || 
               (ch >= 'a' && ch <= 'z') || 
               (ch >= '0' && ch <= '9');
    }
    
    // Main conversion method
    public String convert(String infix) {
        String postfix = "";  // Result string
        stack.clear();         // Clear the stack
        
        // Scan each character from left to right
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            
            // Step 1: If operand, add to output
            if (isOperand(ch)) {
                postfix = postfix + ch;
            }
            
            // Step 2: If '(', push to stack
            else if (ch == '(') {
                stack.push(ch);
            }
            
            // Step 3: If ')', pop until '('
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix = postfix + stack.pop();
                }
                stack.pop(); // Remove '('
            }
            
            // Step 4: If operator
            else if (isOperator(ch)) {
                // Pop operators with higher or equal precedence
                while (!stack.isEmpty() && stack.peek() != '(' && 
                       getPrecedence(stack.peek()) >= getPrecedence(ch)) {
                    postfix = postfix + stack.pop();
                }
                stack.push(ch);
            }
        }
        
        // Step 5: Pop remaining operators
        while (!stack.isEmpty()) {
            postfix = postfix + stack.pop();
        }
        
        return postfix;
    }
    
    // Main method - Entry point
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InfixToPostfix converter = new InfixToPostfix();
        
        System.out.println("=== INFIX TO POSTFIX CONVERTER ===");
        System.out.println("Example: A+B*C becomes ABC*+");
        System.out.println();
        
        System.out.print("Enter infix expression: ");
        String infix = scanner.nextLine();
        
        // Remove spaces if any
        infix = infix.replaceAll("\\s+", "");
        
        // Convert to postfix
        String postfix = converter.convert(infix);
        
        System.out.println("\nResult:");
        System.out.println("Infix  : " + infix);
        System.out.println("Postfix: " + postfix);
        
        scanner.close();
    }
}