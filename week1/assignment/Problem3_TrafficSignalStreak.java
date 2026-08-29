package week1.assignment;

public class Problem3_TrafficSignalStreak {
    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
    
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("Empty log");
            return;
        }
        
        char longestChar = signalLog.charAt(0);
        int longestStreak = 1;
        
        char currentChar = signalLog.charAt(0);
        int currentStreak = 1;
        
        for (int i = 1; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);
            if (c == currentChar) {
                currentStreak++;
            } else {
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                    longestChar = currentChar;
                }
                currentChar = c;
                currentStreak = 1;
            }
        }
        
        if (currentStreak > longestStreak) {
            longestStreak = currentStreak;
            longestChar = currentChar;
        }
        
        System.out.println("Longest Streak: '" + longestChar + "' repeated " + longestStreak + " times");
    }
}
