import java.util.*;

// Question 2: Employee Leave Request Workflow
public class Question2_EmployeeLeaveRequestWorkflow {

    static abstract class Employee {
        private String id;
        private String name;

        public Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public abstract String getRole();

        @Override
        public String toString() {
            return name;
        }
    }

    static class FullTimeEmployee extends Employee {
        public FullTimeEmployee(String id, String name) {
            super(id, name);
        }

        @Override
        public String getRole() {
            return "Full-Time Employee";
        }
    }

    static class PartTimeEmployee extends Employee {
        public PartTimeEmployee(String id, String name) {
            super(id, name);
        }

        @Override
        public String getRole() {
            return "Part-Time Employee";
        }
    }

    static class Contractor extends Employee {
        public Contractor(String id, String name) {
            super(id, name);
        }

        @Override
        public String getRole() {
            return "Contractor";
        }
    }

    static enum LeaveStatus {
        PENDING("Pending"),
        APPROVED("Approved"),
        REJECTED("Rejected");

        private final String label;

        LeaveStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    static class LeaveRequest {
        private Employee employee;
        private String dateRange;
        private int days;
        private LeaveStatus status;

        public LeaveRequest(Employee employee, String dateRange, int days) {
            this.employee = employee;
            this.dateRange = dateRange;
            this.days = days;
            this.status = LeaveStatus.PENDING;
        }

        public Employee getEmployee() {
            return employee;
        }

        public String getDateRange() {
            return dateRange;
        }

        public int getDays() {
            return days;
        }

        public LeaveStatus getStatus() {
            return status;
        }

        public boolean setStatus(LeaveStatus newStatus) {
            if (this.status != LeaveStatus.PENDING && newStatus == LeaveStatus.PENDING) {
                System.out.println("Cannot change leave request status from " + this.status.getLabel() + " to Pending.");
                return false;
            }
            this.status = newStatus;
            return true;
        }
    }

    static class Manager {
        private String name;

        public Manager(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void approveRequest(LeaveRequest request) {
            if (request.setStatus(LeaveStatus.APPROVED)) {
                System.out.println(request.getEmployee().getName() + "'s leave request (" + request.getDateRange() + ") approved. Status: " + request.getStatus() + ".");
            }
        }

        public void rejectRequest(LeaveRequest request) {
            if (request.setStatus(LeaveStatus.REJECTED)) {
                System.out.println(request.getEmployee().getName() + "'s leave request (" + request.getDateRange() + ") rejected. Status: " + request.getStatus() + ".");
            }
        }
    }

    static class LeaveService {
        public LeaveRequest submitLeaveRequest(Employee employee, String dateRange, int days) {
            LeaveRequest request = new LeaveRequest(employee, dateRange, days);
            System.out.println("Leave request submitted for " + employee.getName() + " (" + dateRange + "). Status: " + request.getStatus() + ".");
            return request;
        }
    }

    public static void main(String[] args) {
        LeaveService leaveService = new LeaveService();

        Employee john = new FullTimeEmployee("E1", "John");
        Employee jane = new PartTimeEmployee("E2", "Jane");

        Manager alice = new Manager("Alice");
        Manager bob = new Manager("Bob");

        // FullTimeEmployee John submits leave request for 5 days (Jan 1-5)
        LeaveRequest johnRequest = leaveService.submitLeaveRequest(john, "Jan 1-5", 5);

        // Manager Alice reviews John's request and approves it
        alice.approveRequest(johnRequest);

        // PartTimeEmployee Jane submits leave request for 2 days (Feb 10-11)
        LeaveRequest janeRequest = leaveService.submitLeaveRequest(jane, "Feb 10-11", 2);

        // Manager Bob reviews Jane's request and rejects it
        bob.rejectRequest(janeRequest);

        // John attempts to change his approved leave request to Pending
        johnRequest.setStatus(LeaveStatus.PENDING);
    }
}
