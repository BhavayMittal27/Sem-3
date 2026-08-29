package week1.assignment;

public class Problem1_SeatDuplication {
    public static void main(String[] args) {
        int[] case1 = {101, 102, 103, 102, 105};
        int[] case2 = {101, 102, 103, 104, 105};
        
        checkDuplicateSeats(case1);
        checkDuplicateSeats(case2);
    }
    
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundDuplicate = false;
        boolean[] printed = new boolean[seatNumbers.length];
        
        for (int i = 0; i < seatNumbers.length; i++) {
            if (printed[i]) continue;
            boolean duplicate = false;
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    duplicate = true;
                    printed[j] = true;
                }
            }
            if (duplicate) {
                System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                foundDuplicate = true;
            }
        }
        
        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }
}
