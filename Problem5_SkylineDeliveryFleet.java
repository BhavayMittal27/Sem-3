public class Problem5_SkylineDeliveryFleet {
    public static abstract class Drone {
        public Drone() {}
        public abstract String fly();
    }

    public interface Trackable {
        String getLocation();
    }

    public static class DeliveryDrone extends Drone implements Trackable {
        private final String id;

        public DeliveryDrone(String id) {
            super();
            this.id = id;
        }

        @Override
        public String fly() {
            return id + " flying";
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    public static class ScoutDrone extends Drone {
        private final String id;

        public ScoutDrone(String id) {
            super();
            this.id = id;
        }

        @Override
        public String fly() {
            return id + " scouting";
        }
    }

    public static class GroundRobot implements Trackable {
        private final String id;

        public GroundRobot(String id) {
            this.id = id;
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable t = (Trackable) o;
            return t.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println("\"" + getLocationIfTrackable(d) + "\"");

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println("\"" + getLocationIfTrackable(s) + "\"");

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println("\"" + getLocationIfTrackable(g) + "\"");
    }
}
