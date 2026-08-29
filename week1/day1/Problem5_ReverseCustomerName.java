package week1.day1;

public class Problem5_ReverseCustomerName {
    public static void main(String[] args) {
        String customerName = "Sunil";
        String reversed = reverseCustomerName(customerName);
        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversed);
    }
    
    public static String reverseCustomerName(String customerName) {
        if (customerName == null) {
            return null;
        }
        char[] chars = customerName.toCharArray();
        int n = chars.length;
        char[] reversed = new char[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = chars[n - 1 - i];
        }
        return new String(reversed);
    }
}
