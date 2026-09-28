public class Problem1_TheTalkingToyBox {
    public static abstract class Toy {
        private static int counter = 1000;
        private final String toyId;

        public Toy() {
            counter++;
            this.toyId = "TOY-" + counter;
        }

        public String getToyId() {
            return this.toyId;
        }

        public abstract String makeSound();
    }

    public static class ToyCar extends Toy {
        private final String name;

        public ToyCar(String name) {
            super();
            this.name = name;
        }

        @Override
        public String makeSound() {
            return this.name + ": Vroom vroom!";
        }
    }

    public static class ToyRobot extends Toy {
        private final String name;

        public ToyRobot(String name) {
            super();
            this.name = name;
        }

        @Override
        public String makeSound() {
            return this.name + ": Beep boop!";
        }
    }

    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        System.out.println("\"" + c.makeSound() + "\"");

        ToyRobot r = new ToyRobot("Bolt");
        System.out.println("\"" + r.makeSound() + "\"");

        System.out.println("\"" + c.getToyId() + "\"");
        System.out.println("\"" + r.getToyId() + "\"");
    }
}
