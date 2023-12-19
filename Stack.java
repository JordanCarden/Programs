import java.util.*;
public class Stack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        String str2 = in.nextLine();
        String str3 = in.nextLine();
        String str4 = in.nextLine();
        String str5 = in.nextLine();
        String str6 = in.nextLine();
        String str7 = in.nextLine();
        String str8 = in.nextLine();
        isPalindrome(str1);
        isPalindrome(str2);
        isPalindrome(str3);
        isPalindrome(str4);
        isBalanced(str5);
        isBalanced(str6);
        isBalanced(str7);
        isBalanced(str8);


    }

    static void isPalindrome(String str) {
        String[] arr = str.split("");
        String[] revArr = new String[arr.length];
        Stack<String> s = new Stack<>();
        for(String word : arr){
            s.push(word);
        }
        int i = 0;
        while(!s.isEmpty()){
            revArr[i] = s.pop();
            i++;
        }

        String b = "";
        for (int j = 0; j < revArr.length; j++) {
            b = b + revArr[j];
        }

        if (b.equals(str)) {
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }
    }

    static void isBalanced(String str) {

        String[] arr = str.split("");
        Stack<String> s = new Stack<>();
        boolean skip = true;
        for(String word : arr){
            if (word.equals("(")) {
                s.push(word);
            }
            else if (s.isEmpty()) {
                System.out.println("false");
                skip = false;
            }
            else {
                s.pop();
            }
        }
        if(skip) {
            if (s.isEmpty()) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }
}