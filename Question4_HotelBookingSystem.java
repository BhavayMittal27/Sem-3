import java.util.*;

// Question 4: Hotel Booking System
public class Question4_HotelBookingSystem {

    static abstract class Room {
        private String roomNumber;
        private String roomType;
        private double dailyRate;

        public Room(String roomNumber, String roomType, double dailyRate) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.dailyRate = dailyRate;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public String getRoomType() {
            return roomType;
        }

        public double getDailyRate() {
            return dailyRate;
        }

        public abstract double calculatePrice(int days);

        @Override
        public String toString() {
            return roomType + " " + roomNumber;
        }
    }

    static class StandardRoom extends Room {
        public StandardRoom(String roomNumber) {
            super(roomNumber, "Standard Room", 100.0);
        }

        @Override
        public double calculatePrice(int days) {
            return days * getDailyRate();
        }
    }

    static class DeluxeRoom extends Room {
        public DeluxeRoom(String roomNumber) {
            super(roomNumber, "Deluxe Room", 150.0);
        }

        @Override
        public double calculatePrice(int days) {
            return days * getDailyRate();
        }
    }

    static class Suite extends Room {
        public Suite(String roomNumber) {
            super(roomNumber, "Suite", 250.0);
        }

        @Override
        public double calculatePrice(int days) {
            return days * getDailyRate();
        }
    }

    static class Customer {
        private String id;
        private String name;

        public Customer(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Reservation {
        private String reservationId;
        private Customer customer;
        private Room room;
        private int startDay;
        private int endDay;
        private String dateLabel;
        private int days;
        private double price;
        private boolean active;

        public Reservation(String reservationId, Customer customer, Room room, int startDay, int endDay, String dateLabel, int days) {
            this.reservationId = reservationId;
            this.customer = customer;
            this.room = room;
            this.startDay = startDay;
            this.endDay = endDay;
            this.dateLabel = dateLabel;
            this.days = days;
            this.price = room.calculatePrice(days);
            this.active = true;
        }

        public String getReservationId() {
            return reservationId;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Room getRoom() {
            return room;
        }

        public int getStartDay() {
            return startDay;
        }

        public int getEndDay() {
            return endDay;
        }

        public String getDateLabel() {
            return dateLabel;
        }

        public double getPrice() {
            return price;
        }

        public boolean isActive() {
            return active;
        }

        public void cancel() {
            this.active = false;
        }
    }

    static class Hotel {
        private List<Reservation> reservations = new ArrayList<>();
        private int resCounter = 1;

        public boolean isRoomAvailable(Room room, int startDay, int endDay) {
            for (Reservation res : reservations) {
                if (res.isActive() && res.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                    if (Math.max(startDay, res.getStartDay()) < Math.min(endDay, res.getEndDay())) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void checkAvailability(Room room, int startDay, int endDay, String dateDesc) {
            if (isRoomAvailable(room, startDay, endDay)) {
                System.out.println(room + " is available from " + dateDesc + ".");
            } else {
                System.out.println(room + " is not available from " + dateDesc + ".");
            }
        }

        public Reservation reserveRoom(Customer customer, Room room, int startDay, int endDay, String dateDesc, String shortDate, int days) {
            if (!isRoomAvailable(room, startDay, endDay)) {
                System.out.println(room + " is not available from " + dateDesc + ".");
                return null;
            }

            Reservation res = new Reservation("RES-" + (resCounter++), customer, room, startDay, endDay, shortDate, days);
            reservations.add(res);
            System.out.println("Reservation confirmed for " + customer.getName() + ", " + room + " (" + shortDate + "). Price: $" + res.getPrice() + ".");
            return res;
        }

        public boolean cancelReservation(Reservation reservation, boolean beforeDeadline) {
            if (reservation == null || !reservation.isActive()) {
                return false;
            }
            if (!beforeDeadline) {
                System.out.println("Cancellation failed: deadline has passed.");
                return false;
            }
            reservation.cancel();
            System.out.println("Reservation for " + reservation.getCustomer().getName() + ", " + reservation.getRoom() + " (" + reservation.getDateLabel() + ") cancelled successfully.");
            return true;
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        StandardRoom room101 = new StandardRoom("101");
        DeluxeRoom room201 = new DeluxeRoom("201");

        Customer customerA = new Customer("CA", "Customer A");
        Customer customerB = new Customer("CB", "Customer B");
        Customer customerC = new Customer("CC", "Customer C");

        // Customer A checks availability for Standard Room 101 from Jan 1 to Jan 5
        hotel.checkAvailability(room101, 1, 5, "Jan 1 to Jan 5");

        // Customer A reserves Standard Room 101 from Jan 1 to Jan 5 (4 days/nights)
        Reservation resA = hotel.reserveRoom(customerA, room101, 1, 5, "Jan 1 to Jan 5", "Jan 1-5", 4);

        // Customer B attempts to reserve Standard Room 101 from Jan 3 to Jan 7
        hotel.reserveRoom(customerB, room101, 3, 7, "Jan 3 to Jan 7", "Jan 3-7", 4);

        // Customer A cancels reservation for Standard Room 101 for Jan 1-5 (before deadline)
        hotel.cancelReservation(resA, true);

        // Customer C reserves Deluxe Room 201 from Feb 10 to Feb 12 (2 days/nights)
        hotel.reserveRoom(customerC, room201, 41, 43, "Feb 10 to Feb 12", "Feb 10-12", 2);
    }
}
