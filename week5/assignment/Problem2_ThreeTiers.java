package week5.assignment;

public class Problem2_ThreeTiers {
    public static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId");
            }
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
            return "Standard Member | Sessions: " + sessionsAttended;
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
            return "Premium Member | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
        }
    }

    public static class EliteMember extends PremiumMember {
        protected String lockerNumber;

        public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
            super(memberId, monthlyFee, trainerName);
            this.lockerNumber = lockerNumber;
        }

        public String getLockerNumber() {
            return this.lockerNumber;
        }

        @Override
        public String displayInfo() {
            return "Elite Member | Trainer: " + trainerName + " | Locker: " + lockerNumber + " | Sessions: " + sessionsAttended;
        }
    }

    public static class GroupClassMember extends GymMember {
        protected String className;

        public GroupClassMember(String memberId, int monthlyFee, String className) {
            super(memberId, monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return this.className;
        }

        @Override
        public String displayInfo() {
            return "Group Class Member | Class: " + className + " | Sessions: " + sessionsAttended;
        }
    }

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Unknown";
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            if (member != null) {
                total += member.getSessionsAttended();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        GymMember gm = new GymMember("MEM1", 1000);
        PremiumMember pm = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember em = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember gcm = new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(gm.displayInfo());
        System.out.println(pm.displayInfo());
        System.out.println(em.displayInfo());
        System.out.println(gcm.displayInfo());

        System.out.println(classifyGeneration(em));
        System.out.println(classifyGeneration(gcm));

        pm.attendSession(); pm.attendSession(); pm.attendSession(); // 3
        em.attendSession(); em.attendSession(); // 2
        gcm.attendSession(); gcm.attendSession(); gcm.attendSession(); gcm.attendSession(); // 4

        GymMember[] mix = { pm, em, gcm };
        System.out.println(getTotalSessionsAttended(mix));
    }
}
