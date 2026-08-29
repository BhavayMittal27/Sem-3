package week2;

import java.util.*;

public class Problem5_WordFrequency {
    public static void main(String[] args) {
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
    
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }
        
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "was", "and", "a", "is", "of", "in"));
        
        String normalized = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = normalized.split("\\s+");
        
        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words) {
            if (w.isEmpty() || stopWords.contains(w)) {
                continue;
            }
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freqMap.entrySet());
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
