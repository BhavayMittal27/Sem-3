package week2;

public class Problem2_WordReversal {
    public static void main(String[] args) {
        String result = reverseEachWord("hello club");
        System.out.println(result);
    }
    
    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }
        
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder revWord = new StringBuilder();
            for (int j = word.length() - 1; j >= 0; j--) {
                revWord.append(word.charAt(j));
            }
            result.append(revWord);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
}
