package week2;

public class Problem4_ISBNValidator {
    public static void main(String[] args) {
        String test1 = " pen2026004251 ";
        String norm1 = normalizeCode(test1);
        System.out.println(validateAndFormat(norm1));
        
        String test2 = "12N2026004251";
        String norm2 = normalizeCode(test2);
        System.out.println(validateAndFormat(norm2));
    }
    
    public static String normalizeCode(String raw) {
        if (raw == null) return "";
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }
    
    public static String validateAndFormat(String code) {
        if (code == null || code.length() != 13) {
            return "Invalid: wrong length";
        }
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }
        
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);
        
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ").append(year).append(" | CATALOG: ").append(catalog);
        
        return sb.toString();
    }
}
