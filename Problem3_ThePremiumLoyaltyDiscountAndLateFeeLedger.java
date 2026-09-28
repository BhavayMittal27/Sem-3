import java.util.Arrays;

public class Problem3_ThePremiumLoyaltyDiscountAndLateFeeLedger {
    public static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        private int[] lateFeeHistory;
        private int feeCount;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.lateFeeHistory = new int[10];
            this.feeCount = 0;
        }

        protected void chargeLateFee(int amount) {
            if (feeCount < lateFeeHistory.length) {
                lateFeeHistory[feeCount++] = amount;
            }
        }

        public int[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, feeCount);
        }

        public int getTotalLateFees() {
            int sum = 0;
            for (int i = 0; i < feeCount; i++) {
                sum += lateFeeHistory[i];
            }
            return sum;
        }
    }

    public static class PremiumMember extends GymMember {
        protected String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(p.getLateFeeHistory()));
    }
}
