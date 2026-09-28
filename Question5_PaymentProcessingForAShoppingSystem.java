import java.util.*;

// Question 5: Payment Processing for a Shopping System
public class Question5_PaymentProcessingForAShoppingSystem {

    static class Product {
        private String id;
        private String name;
        private double price;

        public Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    static class OrderItem {
        private Product product;
        private int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getSubtotal() {
            return product.getPrice() * quantity;
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
    }

    static interface PaymentMethod {
        String getMethodName();
        boolean processPayment(double amount);
    }

    static class CreditCardPayment implements PaymentMethod {
        @Override
        public String getMethodName() {
            return "Credit Card";
        }

        @Override
        public boolean processPayment(double amount) {
            return true;
        }
    }

    static class PayPalPayment implements PaymentMethod {
        private boolean simulateSuccess;

        public PayPalPayment(boolean simulateSuccess) {
            this.simulateSuccess = simulateSuccess;
        }

        @Override
        public String getMethodName() {
            return "PayPal";
        }

        @Override
        public boolean processPayment(double amount) {
            return simulateSuccess;
        }
    }

    static class BankTransferPayment implements PaymentMethod {
        @Override
        public String getMethodName() {
            return "Bank Transfer";
        }

        @Override
        public boolean processPayment(double amount) {
            return true;
        }
    }

    static enum OrderStatus {
        PENDING("Pending"),
        PAID("Paid"),
        FAILED("Failed");

        private final String label;

        OrderStatus(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    static class Order {
        private String orderId;
        private Customer customer;
        private List<OrderItem> items = new ArrayList<>();
        private OrderStatus status;

        public Order(String orderId, Customer customer) {
            this.orderId = orderId;
            this.customer = customer;
            this.status = OrderStatus.PENDING;
        }

        public void addProduct(Product product, int quantity) {
            items.add(new OrderItem(product, quantity));
        }

        public double calculateTotal() {
            double total = 0.0;
            for (OrderItem item : items) {
                total += item.getSubtotal();
            }
            return total;
        }

        public boolean processPayment(PaymentMethod paymentMethod) {
            if (items.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return false;
            }

            System.out.println("Payment initiated via " + paymentMethod.getMethodName() + " for " + orderId + ".");
            double amount = calculateTotal();
            boolean success = paymentMethod.processPayment(amount);

            if (success) {
                this.status = OrderStatus.PAID;
                System.out.println("Payment for " + orderId + " successful. Order status: " + this.status + ".");
                return true;
            } else {
                this.status = OrderStatus.PENDING;
                System.out.println("Payment for " + orderId + " failed. Order status: " + this.status + ".");
                return false;
            }
        }

        public OrderStatus getStatus() {
            return status;
        }
    }

    public static void main(String[] args) {
        Customer customerX = new Customer("CX", "Customer X");
        Customer customerY = new Customer("CY", "Customer Y");
        Customer customerZ = new Customer("CZ", "Customer Z");

        Product productA = new Product("P1", "Product A", 50.0);
        Product productB = new Product("P2", "Product B", 30.0);
        Product productC = new Product("P3", "Product C", 75.0);

        // Customer X creates an order with Product A (qty 2) and Product B (qty 1)
        Order orderX = new Order("Order X", customerX);
        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);
        System.out.println("Order created for " + customerX.getName() + ".");

        // Customer X attempts to pay for the order using Credit Card (successful)
        orderX.processPayment(new CreditCardPayment());

        // Customer Y creates an empty order
        Order orderY = new Order("Order Y", customerY);

        // Customer Y attempts to pay for the empty order
        orderY.processPayment(new CreditCardPayment());

        // Customer Z creates an order with Product C (qty 1)
        Order orderZ = new Order("Order Z", customerZ);
        orderZ.addProduct(productC, 1);
        System.out.println("Order created for " + customerZ.getName() + ".");

        // Customer Z attempts to pay for the order using PayPal (fails)
        orderZ.processPayment(new PayPalPayment(false));
    }
}
