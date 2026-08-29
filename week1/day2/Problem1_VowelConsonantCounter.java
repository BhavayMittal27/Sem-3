package week1.day2;

public class Problem1_VowelConsonantCounter {
    public static void main(String[] args) {
        countVowelsAndConsonants("Java Programming");
    }
    
    public static void countVowelsAndConsonants(String text) {
        if (text == null) {
            System.out.println("Vowels: 0 | Consonants: 0");
            return;
        }
        
        int vowels = 0;
        int consonants = 0;
        String vowelsList = "aeiouAEIOU";
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                if (vowelsList.indexOf(c) != -1) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        
        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }
}
