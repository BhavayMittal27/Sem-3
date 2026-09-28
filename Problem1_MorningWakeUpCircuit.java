public class Problem1_MorningWakeUpCircuit {
    public interface Ringable {
        String ring();
    }

    public static class AlarmClock implements Ringable {
        private final String time;

        public AlarmClock(String time) {
            this.time = time;
        }

        @Override
        public String ring() {
            return "Alarm ringing for " + time;
        }
    }

    public static class Doorbell implements Ringable {
        private final String location;

        public Doorbell(String location) {
            this.location = location;
        }

        @Override
        public String ring() {
            return "Doorbell ringing at " + location;
        }
    }

    public static void ringAll(Ringable[] devices) {
        if (devices != null) {
            for (Ringable device : devices) {
                if (device != null) {
                    System.out.println("\"" + device.ring() + "\"");
                }
            }
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        System.out.println("\"" + a.ring() + "\"");

        Doorbell d = new Doorbell("Front Door");
        System.out.println("\"" + d.ring() + "\"");

        ringAll(new Ringable[]{ a, d });
    }
}
