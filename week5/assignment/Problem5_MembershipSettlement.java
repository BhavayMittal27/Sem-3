package week5.assignment;

public class Problem5_MembershipSettlement {
    public static class GymMember {
        private static int counter = 2000;
        private static int membersEnrolled = 0;

        public final String membershipNumber;
        protected int monthlyFee;
        protected int feesPaid;

        public GymMember(int monthlyFee) {
            this.monthlyFee = monthlyFee;
            counter++;
            this.membershipNumber = "GYM-" + counter;
            membersEnrolled++;
            this.feesPaid = 0;
        }

        public void payFee(int amount) {
            this.feesPaid += amount;
        }

        public void payFee(int amount, String mode) {
            // Records mode conceptually, delegates to flat-amount version
            payFee(amount);
        }

        public int getFeesPaid() {
            return this.feesPaid;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }
    }

    public static class GroupClassMember extends GymMember {
        protected String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return this.className;
        }
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
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

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        if (members != null) {
            for (GymMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (m instanceof GroupClassMember) {
                        group++;
                    } else {
                        individual++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        GymMember m1 = new GymMember(1000);
        System.out.println(m1.membershipNumber);
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(isValidReferralCode("G45B"));
        System.out.println(isValidReferralCode("G4B"));
        System.out.println(isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid());

        GymMember[] checkInBatch = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };
        System.out.println("\"" + processWeeklyCheckIn(checkInBatch) + "\"");
    }
}
