package week3.homework;

public class M3_EmployeeOverloading {
    public static void main(String[] args) {
        Employee emp1 = new Employee("E-101", "Divya", 65000.0);
        Employee emp2 = new Employee("E-102", "Arjun");
        
        emp1.printProfile();
        emp2.printProfile();
    }

    public static class Employee {
        String empId;
        String empName;
        double salary;
        boolean isIntern;
        
        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
            this.isIntern = false;
        }
        
        public Employee(String empId, String empName) {
            this(empId, empName, 0.0);
            this.isIntern = true;
        }
        
        public void printProfile() {
            System.out.printf("%s | %s | Rs %.1f | Intern: %b\n", empId, empName, salary, isIntern);
        }
    }
}
