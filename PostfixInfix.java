import java.util.*;
import java.lang.Math;

public class PostfixInfix {
    // Java program to find infix for
// a given postfix.

    static boolean isOperand(char x)
    {
        return Character.isDigit(x);
    }

    // Get Infix for a given postfix
// expression
    static void getInfix(String exp)
    {
        boolean hasLetter = false;
        Stack<String> s = new Stack<String>();

        for (int i = 0; i < exp.length(); i++)
        {
            char c = exp.charAt(i);
            char d = 'x';
            if (i < exp.length() - 1) {
                d = exp.charAt(i + 1);
            }
            if (' ' == exp.charAt(i)) {

            }
            else if (Character.isDigit(c) && Character.isDigit(d)) {
                s.push(String.valueOf((c - 48)*10 + d - 48));
                i++;
            }
            // Push operands
            else if (isOperand(exp.charAt(i)))
            {
                s.push(exp.charAt(i) + "");
            }

            else if (c == '!') {
                String op2 = s.peek();
                s.pop();
                s.push("(" + op2 + exp.charAt(i) + ")");

            }
            // We assume that input is
            // a valid postfix and expect
            // an operator.

            else if (Character.isLetter(c)) {
                hasLetter = true;
                break;
            }

            else
            {
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
                s.push("(" + op2 + " " + exp.charAt(i) + " " + op1 + ")");
            }
        }

        // There must be a single element
        // in stack now which is the required
        // infix.
        if (!hasLetter) {
            String f = s.pop();
            if (s.empty()) {
                System.out.print(f + " = ");
                evaluatePostfix(exp);
            }
            else {
                System.out.print("Invalid Postfix Equation");
            }
        }
        else {
            System.out.print("Invalid Syntax");
        }

    }

    static void evaluatePostfix(String exp)
    {
        // Create a stack
        boolean hasLetter = false;
        Stack<Integer> stack = new Stack<>();

        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            char d = 'x';
            if (i < exp.length() - 1) {
                d = exp.charAt(i + 1);
            }

            if (' ' == c) {

            }

            else if (Character.isLetter(c)) {
                hasLetter = true;
                break;
            }

            else if (Character.isDigit(c) && Character.isDigit(d)) {
                stack.push((c - 48)*10 + d - 48);
                i++;
            }
            // If the scanned character is an operand
            // (number here), push it to the stack.
            else if (Character.isDigit(c)) {
                stack.push(c - '0');
            }
            //  If the scanned character is an operator, pop
            //  two elements from stack apply the operator
            else if (c == '!') {
                int val1 = stack.pop();
                stack.push(factorial(val1));

            }

            else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '%':
                        stack.push(val2 % val1);
                        break;
                    case '^':
                        stack.push((int) Math.pow(val2, val1));
                        break;
                }
            }
        }
        if (!hasLetter) {
            System.out.print(stack.pop());
        }

    }

    static int factorial(int n)
    {

        // Handling base case
        // If value of n=1 or n=0, it returns 1
        if (n == 0 || n == 1)
            return 1;

        // Generic case
        // Otherwise we do n*(n-1)!
        return n * factorial(n - 1);

    }

    // Driver code
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        in.nextLine();
        String[] array = new String[x];
        for (int i = 0; i < x; i++) {
            array[i] = in.nextLine();
        }
        for (int i = 0; i < x; i++) {
            getInfix(array[i]);
            System.out.println();
        }
    }
}