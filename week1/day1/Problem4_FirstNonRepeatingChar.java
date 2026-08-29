package week1.day1;

import java.util.HashMap;

public class Problem4_FirstNonRepeatingChar {
    public static void main(String[] args) {
        String[] testCases = {"swiss", "aabbcc"};
        
        for (String test : testCases) {
            char result = findFirstNonRepeatingChar(test);
            if (result == '\0') {
                System.out.println("No Non-Repeating Character Found");
            } else {
                System.out.println("First Non-Repeating Character: '" + result + "'");
            }
        }
    }
    
    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return '\0';
        }
        
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (freqMap.get(c) == 1) {
                return c;
            }
        }
        
        return '\0';
    }
}
