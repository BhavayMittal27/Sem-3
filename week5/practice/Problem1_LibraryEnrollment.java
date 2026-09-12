package week5.practice;

public class Problem1_LibraryEnrollment {
    public static void main(String[] args) {
        // Example 1: Constructor rejection
        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Example 2: StudentMember borrowing books
        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed());

        // Example 3: Batch enrollment
        String[] batch = {"STU1", "LB1", "STU2", " ", "STU3"};
        System.out.println(enrollBatch(batch, 3));
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: must not be blank, whitespace-only, or shorter than 4 characters.");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        this.booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return this.booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return this.course;
    }
}
