import java.util.*;

// Question 4: The FitZone Membership Desk
public class Question4_TheFitZoneMembershipDesk {

    static class Member {
        private String name;

        public Member(String name) {
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

    static abstract class MembershipPlan {
        private String planName;
        private int months;

        public MembershipPlan(String planName, int months) {
            this.planName = planName;
            this.months = months;
        }

        public String getPlanName() {
            return planName;
        }

        public int getMonths() {
            return months;
        }

        public abstract double calculateFee();
    }

    static class MonthlyPlan extends MembershipPlan {
        public MonthlyPlan() {
            super("Monthly", 1);
        }

        @Override
        public double calculateFee() {
            return 1000.00;
        }
    }

    static class QuarterlyPlan extends MembershipPlan {
        public QuarterlyPlan() {
            super("Quarterly", 3);
        }

        @Override
        public double calculateFee() {
            return 3 * 1000.00 * 0.90; // 10% off
        }
    }

    static class AnnualPlan extends MembershipPlan {
        public AnnualPlan() {
            super("Annual", 12);
        }

        @Override
        public double calculateFee() {
            return 12 * 1000.00 * 0.75; // 25% off
        }
    }

    static enum MembershipStatus {
        ACTIVE("Active"),
        FROZEN("Frozen"),
        EXPIRED("Expired");

        private final String label;

        MembershipStatus(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    static class Membership {
        private Member member;
        private MembershipPlan plan;
        private MembershipStatus status;
        private double fee;

        public Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            this.fee = plan.calculateFee();
            this.status = MembershipStatus.ACTIVE;
            System.out.println(plan.getPlanName() + " membership created for " + member.getName() + ". Fee: \u20B9" + String.format(Locale.US, "%,.2f", fee) + ". Status: " + this.status + ".");
        }

        public Member getMember() {
            return member;
        }

        public MembershipPlan getPlan() {
            return plan;
        }

        public MembershipStatus getStatus() {
            return status;
        }

        public double getFee() {
            return fee;
        }

        public boolean checkIn() {
            if (this.status == MembershipStatus.ACTIVE) {
                System.out.println(member.getName() + " checked in successfully.");
                return true;
            } else {
                System.out.println("Check-in denied: " + member.getName() + "'s membership is " + this.status + ".");
                return false;
            }
        }

        public boolean freeze() {
            if (this.status == MembershipStatus.EXPIRED) {
                System.out.println("Cannot freeze an Expired membership.");
                return false;
            }
            this.status = MembershipStatus.FROZEN;
            System.out.println(member.getName() + "'s membership frozen. Status: " + this.status + ".");
            return true;
        }

        public boolean unfreeze() {
            if (this.status == MembershipStatus.EXPIRED) {
                System.out.println("Cannot unfreeze an Expired membership.");
                return false;
            }
            this.status = MembershipStatus.ACTIVE;
            System.out.println(member.getName() + "'s membership unfrozen. Status: " + this.status + ".");
            return true;
        }

        public void expire() {
            this.status = MembershipStatus.EXPIRED;
            System.out.println(member.getName() + "'s membership expired. Status: " + this.status + ".");
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        // Asha buys a Quarterly membership
        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());

        // Ravi buys a Monthly membership
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        // Asha checks in
        ashaMembership.checkIn();

        // Asha freezes her membership
        ashaMembership.freeze();

        // Asha attempts to check in
        ashaMembership.checkIn();

        // Ravi's membership expires
        raviMembership.expire();

        // Ravi attempts to freeze his membership
        raviMembership.freeze();
    }
}
