package week4.practice;

public class M5_AccountPayments {
    public static int hostelCount = 0;
    public static int dayScholarCount = 0;
    
    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };
        
        double amount = 60000.0;
        
        for (FeeAccount acc : accounts) {
            processPayment(acc, amount);
        }
        
        System.out.println("Hostel accounts processed: " + hostelCount + " | Day-scholar accounts processed: " + dayScholarCount);
    }
    
    public static void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            HostelFeeAccount hostelAcc = (HostelFeeAccount) account;
            hostelAcc.payHostel(amount);
            hostelCount++;
        } else {
            account.pay(amount);
            dayScholarCount++;
        }
    }
}

class FeeAccount {
    public void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    public void payHostel(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}
