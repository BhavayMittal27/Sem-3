public class Problem2_ThreeBranchesOfTheMembershipTree {
    public static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId");
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

        public String displayInfo() {
            return "General Member | Books Borrowed: " + booksBorrowed;
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
            return "Student Member | Course: " + course + " | Books Borrowed: " + booksBorrowed;
        }
    }

    public static class HonorsStudentMember extends StudentMember {
        protected int bonusLimit;

        public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
            super(memberId, borrowLimit, course);
            this.bonusLimit = bonusLimit;
        }

        public int getBonusLimit() {
            return this.bonusLimit;
        }

        @Override
        public String displayInfo() {
            return "Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + booksBorrowed;
        }
    }

    public static class FacultyMember extends LibraryMember {
        protected String department;

        public FacultyMember(String memberId, int borrowLimit, String department) {
            super(memberId, borrowLimit);
            this.department = department;
        }

        public String getDepartment() {
            return this.department;
        }

        @Override
        public String displayInfo() {
            return "Faculty Member | Department: " + department + " | Books Borrowed: " + booksBorrowed;
        }
    }

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Unknown";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            if (member != null) {
                total += member.getBooksBorrowed();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        LibraryMember lm = new LibraryMember("STU1", 3);
        StudentMember sm = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember hsm = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember fm = new FacultyMember("STU4", 5, "Physics");

        System.out.println(lm.displayInfo());
        System.out.println(sm.displayInfo());
        System.out.println(hsm.displayInfo());
        System.out.println(fm.displayInfo());

        System.out.println(classifyGeneration(hsm));
        System.out.println(classifyGeneration(fm));

        sm.borrowBook(); sm.borrowBook(); // 2
        hsm.borrowBook(); // 1
        fm.borrowBook(); fm.borrowBook(); fm.borrowBook(); // 3

        LibraryMember[] mix = { sm, hsm, fm };
        System.out.println(getTotalBooksBorrowed(mix));
    }
}
