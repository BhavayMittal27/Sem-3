package week1.assignment;

public class Problem5_WordLengthProfiler {
    public static void main(String[] args) {
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
    
    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }
        
        String cleanReview = review.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = cleanReview.split("\\s+");
        
        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;
        
        for (String word : words) {
            if (word.isEmpty()) continue;
            int length = word.length();
            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else if (length >= 9) {
                longCount++;
            }
        }
        
        System.out.printf("Short: %d | Medium: %d | Long: %d\n", shortCount, mediumCount, longCount);
    }
}
