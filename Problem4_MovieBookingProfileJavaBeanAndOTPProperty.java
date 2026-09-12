public class Problem4_MovieBookingProfileJavaBeanAndOTPProperty {
    public static class MovieBookingProfile {
        private String name;
        private boolean confirmed;
        private String otp;

        public MovieBookingProfile() {
            this.name = "";
            this.confirmed = false;
            this.otp = null;
        }

        public MovieBookingProfile(String name) {
            this();
            setName(name);
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isConfirmed() {
            return this.confirmed;
        }

        public void setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }
        // No getter exists for otp, making it write-only
    }

    public static void main(String[] args) {
        System.out.println("\"" + new MovieBookingProfile("Rahul Dev").getName() + "\"");

        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        p.setConfirmed(true);
        System.out.println(p.isConfirmed());

        p.setOtp("4471");
        // otp is write-only, cannot be retrieved
    }
}
