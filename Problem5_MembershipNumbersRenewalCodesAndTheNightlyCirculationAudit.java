public class Problem5_MembershipNumbersRenewalCodesAndTheNightlyCirculationAudit {
    public static class LibraryMember {
        private static int counter = 100;
        private static int membersEnrolled = 0;

        public final String memberNumber;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(int borrowLimit) {
            this.borrowLimit = borrowLimit;
            counter++;
            this.memberNumber = "LIB-" + counter;
            membersEnrolled++;
            this.booksBorrowed = 0;
        }

        public void borrowBook() {
            this.booksBorrowed++;
        }

        public void borrowBook(String genre) {
            // Records genre conceptually, delegates to no-argument version
            borrowBook();
        }

        public int getBooksBorrowed() {
            return this.booksBorrowed;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }
    }

    public static class FacultyMember extends LibraryMember {
        protected String department;

        public FacultyMember(int borrowLimit, String department) {
            super(borrowLimit);
            this.department = department;
        }

        public String getDepartment() {
            return this.department;
        }
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        if (members != null) {
            for (LibraryMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (m instanceof FacultyMember) {
                        faculty++;
                    } else {
                        regular++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(isValidRenewalCode("R12A"));
        System.out.println(isValidRenewalCode("R1A"));
        System.out.println(isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] auditBatch = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };
        System.out.println("\"" + processNightlyAudit(auditBatch) + "\"");
    }
}
