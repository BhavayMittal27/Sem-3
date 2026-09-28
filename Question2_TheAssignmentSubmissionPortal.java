import java.util.*;

// Question 2: The Assignment Submission Portal
public class Question2_TheAssignmentSubmissionPortal {

    static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static abstract class Assignment {
        private String title;
        private int maxMarks;
        private int dueDay;
        private String dueDate;

        public Assignment(String title, int maxMarks, int dueDay, String dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDay = dueDay;
            this.dueDate = dueDate;
        }

        public String getTitle() {
            return title;
        }

        public int getMaxMarks() {
            return maxMarks;
        }

        public int getDueDay() {
            return dueDay;
        }

        public String getDueDate() {
            return dueDate;
        }

        public abstract int getPenaltyPercentage(int lateDays);
        public abstract double calculateFinalMarks(double rawMarks, int lateDays);
    }

    static class CodingAssignment extends Assignment {
        public CodingAssignment(String title, int maxMarks, int dueDay, String dueDate) {
            super(title, maxMarks, dueDay, dueDate);
        }

        @Override
        public int getPenaltyPercentage(int lateDays) {
            return lateDays <= 0 ? 0 : lateDays * 10;
        }

        @Override
        public double calculateFinalMarks(double rawMarks, int lateDays) {
            if (lateDays <= 0) return rawMarks;
            double penaltyFraction = (lateDays * 10) / 100.0;
            return rawMarks * (1.0 - penaltyFraction);
        }
    }

    static class WrittenAssignment extends Assignment {
        public WrittenAssignment(String title, int maxMarks, int dueDay, String dueDate) {
            super(title, maxMarks, dueDay, dueDate);
        }

        @Override
        public int getPenaltyPercentage(int lateDays) {
            return lateDays <= 0 ? 0 : lateDays * 20;
        }

        @Override
        public double calculateFinalMarks(double rawMarks, int lateDays) {
            if (lateDays <= 0) return rawMarks;
            double penaltyFraction = (lateDays * 20) / 100.0;
            return rawMarks * (1.0 - penaltyFraction);
        }
    }

    static enum SubmissionStatus {
        SUBMITTED("Submitted"),
        GRADED("Graded");

        private final String label;

        SubmissionStatus(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    static class Submission {
        private Student student;
        private Assignment assignment;
        private int submitDay;
        private String submitDate;
        private SubmissionStatus status;
        private double awardedMarks;
        private double finalMarks;
        private int lateDays;

        public Submission(Student student, Assignment assignment, int submitDay, String submitDate) {
            this.student = student;
            this.assignment = assignment;
            this.submitDay = submitDay;
            this.submitDate = submitDate;
            this.status = SubmissionStatus.SUBMITTED;
            this.lateDays = Math.max(0, submitDay - assignment.getDueDay());

            String timing = (lateDays == 0) ? "on time" : lateDays + " days late";
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + timing + "). Status: " + this.status + ".");
        }

        public Student getStudent() {
            return student;
        }

        public Assignment getAssignment() {
            return assignment;
        }

        public SubmissionStatus getStatus() {
            return status;
        }

        public boolean grade(double rawMarks) {
            if (this.status == SubmissionStatus.GRADED) {
                System.out.println("Submission already graded.");
                return false;
            }
            this.awardedMarks = rawMarks;
            this.finalMarks = assignment.calculateFinalMarks(rawMarks, lateDays);
            this.status = SubmissionStatus.GRADED;

            int penaltyPct = assignment.getPenaltyPercentage(lateDays);
            if (penaltyPct > 0) {
                System.out.println(student.getName() + " graded: " + (int)finalMarks + "/" + assignment.getMaxMarks() + " after " + penaltyPct + "% late penalty. Status: " + this.status + ".");
            } else {
                System.out.println(student.getName() + " graded: " + (int)finalMarks + "/" + assignment.getMaxMarks() + ". Status: " + this.status + ".");
            }
            return true;
        }

        public boolean resubmit(int newSubmitDay, String newSubmitDate) {
            if (this.status == SubmissionStatus.GRADED) {
                System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
                return false;
            }
            this.submitDay = newSubmitDay;
            this.submitDate = newSubmitDate;
            this.lateDays = Math.max(0, newSubmitDay - assignment.getDueDay());
            System.out.println(student.getName() + "'s resubmission for '" + assignment.getTitle() + "' received.");
            return true;
        }
    }

    public static void main(String[] args) {
        Assignment codingLab = new CodingAssignment("Linked List Lab", 50, 10, "Mar 10");
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, 12, "Mar 12");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        // Asha submits 'Linked List Lab' on Mar 10 (day 10)
        Submission ashaSub = new Submission(asha, codingLab, 10, "Mar 10");

        // Ravi submits 'Design Essay' on Mar 14 (day 14, 2 days late)
        Submission raviSub = new Submission(ravi, designEssay, 14, "Mar 14");

        // Faculty awards Asha 45 marks
        ashaSub.grade(45);

        // Faculty awards Ravi 40 marks
        raviSub.grade(40);

        // Asha attempts to resubmit 'Linked List Lab'
        ashaSub.resubmit(15, "Mar 15");
    }
}
