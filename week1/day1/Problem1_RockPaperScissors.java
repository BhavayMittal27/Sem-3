package week1.day1;

import java.util.Random;

public class Problem1_RockPaperScissors {
    public static void main(String[] args) {
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Paper", "Rock"};
        int N = 5;
        
        String[] computerMoves = new String[N];
        String[] results = new String[N];
        
        String[] movesList = {"Rock", "Paper", "Scissors"};
        // Use a fixed seed for reproducible results that match general game behaviors
        Random random = new Random(15); 
        
        int wins = 0, losses = 0, draws = 0;
        
        for (int i = 0; i < N; i++) {
            computerMoves[i] = movesList[random.nextInt(3)];
            String playerMove = playerMoves[i];
            
            results[i] = playRound(playerMove, computerMoves[i]);
            
            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }
        
        // Print summary table
        System.out.println("Round | Player Move | Computer Move | Result");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < N; i++) {
            System.out.printf("%-5d | %-11s | %-13s | %s\n", (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }
        System.out.println("--------------------------------------------");
        double winPercentage = ((double) wins / N) * 100;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n", wins, losses, draws, winPercentage);
    }
    
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }
}
