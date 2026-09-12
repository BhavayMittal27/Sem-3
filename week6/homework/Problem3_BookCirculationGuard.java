package week6.homework;

public class Problem3_BookCirculationGuard {
    public static class BookInventory {
        private int copiesTotal;
        private int copiesAvailable;

        public BookInventory(int copiesTotal) {
            if (copiesTotal <= 0) {
                throw new IllegalArgumentException("copiesTotal must be positive.");
            }
            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }

        public void checkOut() {
            if (copiesAvailable > 0) {
                copiesAvailable--;
            }
            // Silently rejected if no copies available
        }

        public void checkIn() {
            if (copiesAvailable < copiesTotal) {
                copiesAvailable++;
            }
            // Silently rejected if already at full capacity
        }

        public int getCopiesAvailable() {
            return this.copiesAvailable;
        }
    }

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // 4th attempt
        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn(); // 4th attempt
        System.out.println(b.getCopiesAvailable());
    }
}
