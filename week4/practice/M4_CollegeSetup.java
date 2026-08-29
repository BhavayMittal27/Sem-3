package week4.practice;

public class M4_CollegeSetup {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};
        for (String name : names) {
            new SrmStudent(name);
        }
    }
}

class SrmStudent {
    static String collegeName;
    static String academicYear;
    String studentName;
    
    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }
    
    public SrmStudent(String studentName) {
        this.studentName = studentName;
        System.out.println("Student record created: " + studentName);
    }
}
