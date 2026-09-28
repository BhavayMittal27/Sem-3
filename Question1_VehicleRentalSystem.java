import java.util.*;

// Question 1: Vehicle Rental System
public class Question1_VehicleRentalSystem {

    static abstract class Vehicle {
        private String id;
        private String model;
        private boolean available;

        public Vehicle(String id, String model) {
            this.id = id;
            this.model = model;
            this.available = true;
        }

        public String getId() {
            return id;
        }

        public String getModel() {
            return model;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public abstract double calculateRentalCharge(int days);

        @Override
        public String toString() {
            return model;
        }
    }

    static class Sedan extends Vehicle {
        private static final double DAILY_RATE = 50.0;

        public Sedan(String id, String model) {
            super(id, model);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return days * DAILY_RATE;
        }
    }

    static class SUV extends Vehicle {
        private static final double DAILY_RATE = 80.0;

        public SUV(String id, String model) {
            super(id, model);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return days * DAILY_RATE;
        }
    }

    static class Truck extends Vehicle {
        private static final double DAILY_RATE = 100.0;

        public Truck(String id, String model) {
            super(id, model);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return days * DAILY_RATE;
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

    static class Rental {
        private Customer customer;
        private Vehicle vehicle;
        private int days;
        private double charge;

        public Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.charge = vehicle.calculateRentalCharge(days);
        }

        public Customer getCustomer() {
            return customer;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public int getDays() {
            return days;
        }

        public double getCharge() {
            return charge;
        }
    }

    static class RentalSystem {
        private Map<String, Rental> activeRentals = new HashMap<>();

        public boolean rentVehicle(Customer customer, Vehicle vehicle, int days) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getModel() + " is currently unavailable.");
                return false;
            }

            Rental rental = new Rental(customer, vehicle, days);
            vehicle.setAvailable(false);
            activeRentals.put(vehicle.getId(), rental);
            System.out.println(vehicle.getModel() + " rented successfully by " + customer.getName() + ". Rental charge: $" + rental.getCharge() + ".");
            return true;
        }

        public boolean returnVehicle(Customer customer, Vehicle vehicle) {
            Rental rental = activeRentals.get(vehicle.getId());
            if (rental != null && rental.getCustomer().getId().equals(customer.getId())) {
                vehicle.setAvailable(true);
                activeRentals.remove(vehicle.getId());
                System.out.println(vehicle.getModel() + " returned by " + customer.getName() + ".");
                return true;
            } else {
                System.out.println("No active rental found for " + vehicle.getModel() + " by " + customer.getName() + ".");
                return false;
            }
        }
    }

    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();

        Customer customer1 = new Customer("C1", "Customer 1");
        Customer customer2 = new Customer("C2", "Customer 2");
        Customer customer3 = new Customer("C3", "Customer 3");

        Vehicle sedanA = new Sedan("V1", "Sedan A");
        Vehicle suvB = new SUV("V2", "SUV B");

        // Customer 1 rents Sedan A for 3 days
        system.rentVehicle(customer1, sedanA, 3);

        // Customer 2 attempts to rent Sedan A for 2 days (while it's rented by Customer 1)
        system.rentVehicle(customer2, sedanA, 2);

        // Customer 1 returns Sedan A
        system.returnVehicle(customer1, sedanA);

        // Customer 3 rents SUV B for 5 days
        system.rentVehicle(customer3, suvB, 5);
    }
}
