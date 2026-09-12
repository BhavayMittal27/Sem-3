package week6.practice;

public class Problem3_SeatBookingGuard {
    public static class CineScreen {
        private int seatsTotal;
        private int seatsAvailable;

        public CineScreen(int seatsTotal) {
            if (seatsTotal <= 0) {
                throw new IllegalArgumentException("seatsTotal must be a positive integer.");
            }
            this.seatsTotal = seatsTotal;
            this.seatsAvailable = seatsTotal;
        }

        public void bookSeat() {
            if (seatsAvailable > 0) {
                seatsAvailable--;
            }
            // Silently rejected if 0
        }

        public void cancelBooking() {
            if (seatsAvailable < seatsTotal) {
                seatsAvailable++;
            }
            // Silently rejected if already at full capacity
        }

        public int getSeatsAvailable() {
            return this.seatsAvailable;
        }
    }

    public static void main(String[] args) {
        // Example 1: Constructor rejection
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Example 2: Boundary test on booking
        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat();
        System.out.println(c.getSeatsAvailable());

        // Example 3: Boundary test on canceling
        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking();
        System.out.println(c.getSeatsAvailable());
    }
}
