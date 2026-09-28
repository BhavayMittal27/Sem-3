import java.util.*;

// Question 1: The Hostel Laundry Queue
public class Question1_TheHostelLaundryQueue {

    static class Student {
        private String name;

        public Student(String name) {
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

    static abstract class WashType {
        private String name;
        private int durationMinutes;
        private double charge;

        public WashType(String name, int durationMinutes, double charge) {
            this.name = name;
            this.durationMinutes = durationMinutes;
            this.charge = charge;
        }

        public String getName() {
            return name;
        }

        public int getDurationMinutes() {
            return durationMinutes;
        }

        public double getCharge() {
            return charge;
        }
    }

    static class QuickWash extends WashType {
        public QuickWash() {
            super("Quick", 30, 20.00);
        }
    }

    static class NormalWash extends WashType {
        public NormalWash() {
            super("Normal", 45, 30.00);
        }
    }

    static class HeavyWash extends WashType {
        public HeavyWash() {
            super("Heavy", 60, 45.00);
        }
    }

    static class DelicateWash extends WashType {
        public DelicateWash() {
            super("Delicate", 25, 25.00);
        }
    }

    static class WashCycle {
        private Student student;
        private WashingMachine machine;
        private WashType washType;

        public WashCycle(Student student, WashingMachine machine, WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public Student getStudent() {
            return student;
        }

        public WashingMachine getMachine() {
            return machine;
        }

        public WashType getWashType() {
            return washType;
        }
    }

    static class WashingMachine {
        private String id;
        private boolean busy;
        private WashCycle currentCycle;

        public WashingMachine(String id) {
            this.id = id;
            this.busy = false;
        }

        public String getId() {
            return id;
        }

        public boolean isBusy() {
            return busy;
        }

        public boolean startWash(Student student, WashType washType) {
            if (this.busy) {
                System.out.println("Machine " + id + " is currently busy.");
                return false;
            }

            this.busy = true;
            this.currentCycle = new WashCycle(student, this, washType);
            System.out.println(washType.getName() + " wash started on " + id + " for " + student.getName() + " (" + washType.getDurationMinutes() + " min). Charge: \u20B9" + String.format(Locale.US, "%.2f", washType.getCharge()) + ".");
            return true;
        }

        public void completeCycle() {
            this.busy = false;
            this.currentCycle = null;
            System.out.println(id + " cycle completed. " + id + " is now free.");
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType normal = new NormalWash();
        WashType heavy = new HeavyWash();

        // Asha starts a Quick wash on Machine M1
        m1.startWash(asha, quick);

        // Ravi attempts to start a Heavy wash on Machine M1
        m1.startWash(ravi, heavy);

        // Ravi starts a Heavy wash on Machine M2
        m2.startWash(ravi, heavy);

        // Machine M1 completes its cycle
        m1.completeCycle();

        // Neha starts a Normal wash on Machine M1
        m1.startWash(neha, normal);
    }
}
