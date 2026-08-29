package week3.practice;

public class M5_StudentStatic {
    public static void main(String[] args) {
        Student.collegeName = "SRM Institute of Science and Technology";
        
        new Student("Ravi", 85);
        new Student("Meera", 90);
        
        Student.printCollegeInfo();
    }
}

class Student {
    String name;
    int attendance;
    static String collegeName;
    static int studentCount = 0;
    
    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }
    
    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}
