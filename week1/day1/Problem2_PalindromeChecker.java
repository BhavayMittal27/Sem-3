package week1.day1;

public class Problem2_PalindromeChecker {
    public static void main(String[] args) {
        String[] testCases = {"madam", "hello"};
        
        for (String test : testCases) {
            boolean iter = isPalindromeIterative(test);
            boolean recur = isPalindromeRecursive(test);
            boolean rev = isPalindromeArrayReversal(test);
            
            System.out.println("Iterative: " + (iter ? "Palindrome" : "Not Palindrome") + 
                               " | Recursive: " + (recur ? "Palindrome" : "Not Palindrome") + 
                               " | Array Reversal: " + (rev ? "Palindrome" : "Not Palindrome"));
        }
    }
    
    public static boolean isPalindromeIterative(String text) {
        if (text == null) return false;
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public static boolean isPalindromeRecursive(String text) {
        if (text == null) return false;
        return isPalindromeRecursiveHelper(text, 0, text.length() - 1);
    }
    
    private static boolean isPalindromeRecursiveHelper(String text, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }
        return isPalindromeRecursiveHelper(text, left + 1, right - 1);
    }
    
    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) return false;
        char[] chars = text.toCharArray();
        int n = chars.length;
        char[] reversed = new char[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = chars[n - 1 - i];
        }
        return text.equals(new String(reversed));
    }
}
