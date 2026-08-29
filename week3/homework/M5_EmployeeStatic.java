package week3.homework;

public class M5_EmployeeStatic {
    public static void main(String[] args) {
        Employee.companyName = "Bright Horizon Technologies";
        
        new Employee("Ravi", 40000.0);
        new Employee("Meera", 55000.0);
        new Employee("Karthik", 62000.0);
        
        Employee.printCompanyInfo();
    }

    public static class Employee {
        String empName;
        double salary;
        static String companyName;
        static int employeeCount = 0;
        
        public Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }
        
        public static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }
}
