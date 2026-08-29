package week2;

public class Problem1_ATMPinValidator {
    public static void main(String[] args) {
        checkPinLength("482");
        checkPinLength("4820");
    }
    
    public static void checkPinLength(String pin) {
        if (pin == null || pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }
}
