package week1.assignment;

public class Problem2_TypingAccuracy {
    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
    
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null || original.length() != typed.length()) {
            System.out.println("Invalid input: Strings must be of equal length");
            return;
        }
        
        int length = original.length();
        int matches = 0;
        int firstMismatchPos = -1;
        
        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matches++;
            } else {
                if (firstMismatchPos == -1) {
                    firstMismatchPos = i + 1; // 1-indexed position
                }
            }
        }
        
        double accuracy = ((double) matches / length) * 100;
        
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matches, length, accuracy);
        if (firstMismatchPos == -1) {
            System.out.println("No Mismatches");
        } else {
            char origChar = original.charAt(firstMismatchPos - 1);
            char typedChar = typed.charAt(firstMismatchPos - 1);
            System.out.printf("First Mismatch at position %d ('%c' vs '%c')\n", firstMismatchPos, origChar, typedChar);
        }
    }
}
