package week1.day2;

public class Problem3_FileExtensionValidator {
    public static void main(String[] args) {
        String res1 = validateFileExtension("Assignment1.PDF");
        String res2 = validateFileExtension("notes.txt");
        System.out.println(res1);
        System.out.println(res2);
    }
    
    public static String validateFileExtension(String filename) {
        if (filename == null) {
            return "Rejected — invalid file type";
        }
        
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }
        
        String ext = filename.substring(lastDotIndex + 1);
        if (ext.equalsIgnoreCase("pdf") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("zip")) {
            return "Accepted";
        }
        
        return "Rejected — invalid file type";
    }
}
