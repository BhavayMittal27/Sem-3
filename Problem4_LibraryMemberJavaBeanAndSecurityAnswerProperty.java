public class Problem4_LibraryMemberJavaBeanAndSecurityAnswerProperty {
    public static class LibraryMember {
        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String hashedSecurityAnswer;

        public LibraryMember() {
            this.membershipId = null;
            this.name = "";
            this.premiumMember = false;
            this.hashedSecurityAnswer = null;
        }

        public String getMembershipId() {
            return this.membershipId;
        }

        public void setMembershipId(String id) {
            // Write-once behavior
            if (this.membershipId == null) {
                this.membershipId = id;
            }
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPremiumMember() {
            return this.premiumMember;
        }

        public void setPremiumMember(boolean premium) {
            this.premiumMember = premium;
        }

        public void setSecurityAnswer(String answer) {
            if (answer != null) {
                this.hashedSecurityAnswer = "HASH-" + Integer.toHexString(answer.hashCode()) + ":" + answer.length();
            }
        }
        // Genuinely write-only: no getter exists anywhere on the class
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println("\"" + m.getMembershipId() + "\"");

        m.setMembershipId("FAKE-0000");
        System.out.println("\"" + m.getMembershipId() + "\"");

        System.out.println(m.isPremiumMember());

        m.setSecurityAnswer("BlueMountain");
        // No method anywhere on the class can retrieve this value again
    }
}
