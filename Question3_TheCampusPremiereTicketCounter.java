import java.util.*;

// Question 3: The Campus Premiere Ticket Counter
public class Question3_TheCampusPremiereTicketCounter {

    static class Customer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static abstract class Seat {
        private String seatNumber;
        private double price;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return seatNumber;
        }
    }

    static class RegularSeat extends Seat {
        public RegularSeat(String seatNumber) {
            super(seatNumber, 150.00);
        }
    }

    static class PremiumSeat extends Seat {
        public PremiumSeat(String seatNumber) {
            super(seatNumber, 250.00);
        }
    }

    static class ReclinerSeat extends Seat {
        public ReclinerSeat(String seatNumber) {
            super(seatNumber, 400.00);
        }
    }

    static class Show {
        private String name;
        private String time;
        private Set<String> bookedSeats = new HashSet<>();

        public Show(String name, String time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public String getTime() {
            return time;
        }

        public boolean isSeatBooked(String seatNumber) {
            return bookedSeats.contains(seatNumber);
        }

        public void bookSeat(String seatNumber) {
            bookedSeats.add(seatNumber);
        }

        public void releaseSeat(String seatNumber) {
            bookedSeats.remove(seatNumber);
        }
    }

    static class Booking {
        private Customer customer;
        private Show show;
        private List<Seat> seats;
        private double totalAmount;
        private boolean cancelled;

        public Booking(Customer customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>(seats);
            this.cancelled = false;
            double sum = 0.0;
            for (Seat s : seats) {
                sum += s.getPrice();
            }
            this.totalAmount = sum;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Show getShow() {
            return show;
        }

        public List<Seat> getSeats() {
            return seats;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public void cancel() {
            this.cancelled = true;
        }
    }

    static class TicketCounter {
        public Booking bookTickets(Customer customer, Show show, List<Seat> requestedSeats, boolean showStarted) {
            if (showStarted) {
                System.out.println("Cannot book seats: show has already started.");
                return null;
            }
            if (requestedSeats.size() > 6) {
                System.out.println("Maximum 6 seats per booking allowed.");
                return null;
            }

            for (Seat seat : requestedSeats) {
                if (show.isSeatBooked(seat.getSeatNumber())) {
                    System.out.println("Seat " + seat.getSeatNumber() + " is already booked for this show.");
                    return null;
                }
            }

            for (Seat seat : requestedSeats) {
                show.bookSeat(seat.getSeatNumber());
            }

            Booking booking = new Booking(customer, show, requestedSeats);
            List<String> seatNums = new ArrayList<>();
            for (Seat s : requestedSeats) {
                seatNums.add(s.getSeatNumber());
            }

            System.out.println("Booking confirmed for " + customer.getName() + ": " + String.join(", ", seatNums) + ". Total: \u20B9" + String.format(Locale.US, "%.2f", booking.getTotalAmount()) + ".");
            return booking;
        }

        public boolean cancelBooking(Booking booking, boolean showStarted) {
            if (booking == null || booking.isCancelled()) {
                return false;
            }
            if (showStarted) {
                System.out.println("Cannot cancel booking after the show has started.");
                return false;
            }

            for (Seat s : booking.getSeats()) {
                booking.getShow().releaseSeat(s.getSeatNumber());
            }
            booking.cancel();

            List<String> seatNums = new ArrayList<>();
            for (Seat s : booking.getSeats()) {
                seatNums.add(s.getSeatNumber());
            }

            System.out.println(booking.getCustomer().getName() + "'s booking cancelled. Seats " + String.join(", ", seatNums) + " released.");
            return true;
        }
    }

    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();
        Show show7PM = new Show("Campus Premiere", "7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        // Asha books Regular seats A1 and A2 and Premium seat F5 for the 7 PM show
        List<Seat> ashaSeats = Arrays.asList(new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5"));
        Booking ashaBooking = counter.bookTickets(asha, show7PM, ashaSeats, false);

        // Ravi attempts to book seat A2 for the same show
        counter.bookTickets(ravi, show7PM, Collections.singletonList(new RegularSeat("A2")), false);

        // Ravi books Recliner seat R1 for the same show
        counter.bookTickets(ravi, show7PM, Collections.singletonList(new ReclinerSeat("R1")), false);

        // Asha cancels her booking before the show starts
        counter.cancelBooking(ashaBooking, false);

        // Neha books seat A2 for the same show
        counter.bookTickets(neha, show7PM, Collections.singletonList(new RegularSeat("A2")), false);
    }
}
