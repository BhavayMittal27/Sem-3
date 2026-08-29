package week3.homework;

public class M2_PayrollAccount {
    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}

class PayrollAccount {
    private double basicSalary;
    private double bonus;
    
    public PayrollAccount(double openingBasicSalary) {
        if (openingBasicSalary < 0) {
            System.out.println("Warning: Basic salary cannot be negative. Setting to 0.0.");
            this.basicSalary = 0.0;
        } else {
            this.basicSalary = openingBasicSalary;
        }
        this.bonus = 0.0;
    }
    
    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Credit rejected: bonus amount must be positive.");
        } else {
            this.bonus += amount;
            System.out.printf("Bonus credited: Rs %.1f\n", amount);
        }
    }
    
    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Deduction rejected: tax percentage must be between 0 and 100.");
        } else {
            this.basicSalary -= (percent / 100.0) * this.basicSalary;
            if (percent == (int) percent) {
                System.out.printf("Tax deducted: %d%%\n", (int) percent);
            } else {
                System.out.printf("Tax deducted: %.1f%%\n", percent);
            }
        }
    }
    
    public double getNetSalary() {
        return this.basicSalary + this.bonus;
    }
}
