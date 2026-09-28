public class Problem4_TheMonthlyAttendanceAnnouncer {
    public static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public void attendSession() {
            this.sessionsAttended++;
        }

        public int getSessionsAttended() {
            return this.sessionsAttended;
        }

        public String displayInfo() {
            return "Standard | Sessions: " + sessionsAttended;
        }
    }

    public static class PremiumMember extends GymMember {
        protected String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return this.trainerName;
        }

        @Override
        public String displayInfo() {
            return "Premium | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
        }
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (GymMember member : members) {
            sb.append(member.displayInfo());
            if (member instanceof PremiumMember) {
                PremiumMember pm = (PremiumMember) member;
                sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GymMember[] list = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };
        System.out.println("\"" + batchPrint(list) + "\"");

        // Demonstration of invalid cast caught conceptually
        GymMember plain = new GymMember("MEM8", 1000);
        try {
            PremiumMember bad = (PremiumMember) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught as expected for invalid downcast");
        }
    }
}
