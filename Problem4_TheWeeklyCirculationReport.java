public class Problem4_TheWeeklyCirculationReport {
    public static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
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

        public String displayInfo() {
            return "General | Books: " + booksBorrowed;
        }
    }

    public static class StudentMember extends LibraryMember {
        protected String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        public String getCourse() {
            return this.course;
        }

        @Override
        public String displayInfo() {
            return "Student | Course: " + course + " | Books: " + booksBorrowed;
        }
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (LibraryMember member : members) {
            sb.append(member.displayInfo());
            if (member instanceof StudentMember) {
                StudentMember sm = (StudentMember) member;
                sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] list = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };
        System.out.println("\"" + batchPrint(list) + "\"");

        // Demonstration of invalid cast caught conceptually
        LibraryMember plain = new LibraryMember("LB6", 3);
        try {
            StudentMember bad = (StudentMember) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught as expected for invalid downcast");
        }
    }
}
