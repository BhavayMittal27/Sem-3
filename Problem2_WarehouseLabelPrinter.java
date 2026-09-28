public class Problem2_WarehouseLabelPrinter {
    public interface Printable {
        String printLabel();
    }

    public static class PackageBox implements Printable {
        private final String trackingId;

        public PackageBox(String trackingId) {
            this.trackingId = trackingId;
        }

        @Override
        public String printLabel() {
            return "Package label: " + trackingId;
        }
    }

    public static class Invoice implements Printable {
        private final String invoiceNumber;

        public Invoice(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        @Override
        public String printLabel() {
            return "Invoice label: " + invoiceNumber;
        }
    }

    public static void printAll(Printable[] items) {
        if (items != null) {
            for (Printable item : items) {
                if (item != null) {
                    System.out.println("\"" + item.printLabel() + "\"");
                }
            }
        }
    }

    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        System.out.println("\"" + p.printLabel() + "\"");

        Invoice i = new Invoice("INV-42");
        System.out.println("\"" + i.printLabel() + "\"");

        printAll(new Printable[]{ p, i });
    }
}
