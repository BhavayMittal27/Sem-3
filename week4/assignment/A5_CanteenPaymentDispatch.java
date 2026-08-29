package week4.assignment;

public class A5_CanteenPaymentDispatch {
    public static double totalCollected = 0.0;
    
    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };
        
        double[] amounts = {100.0, 50.0, 200.0, 75.0, 120.0};
        
        for (int i = 0; i < payments.length; i++) {
            processTransaction(payments[i], amounts[i]);
        }
        
        System.out.printf("Total Collected: Rs %.1f\n", totalCollected);
    }
    
    public static void processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment cp = (CardPayment) payment;
            cp.payWithProcessingFee(amount);
            totalCollected += amount * 1.02;
        } else {
            payment.pay(amount);
            totalCollected += amount;
        }
    }
}

class Payment {
    public void pay(double amount) {
        System.out.printf("Paid (cash): Rs %.1f\n", amount);
    }
}

class CardPayment extends Payment {
    public void payWithProcessingFee(double amount) {
        System.out.printf("Charged (card, incl. fee): Rs %.1f\n", amount * 1.02);
    }
}
