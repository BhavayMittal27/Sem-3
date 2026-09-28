public class Problem2_GalleryDescriptionCards {
    public static abstract class ArtPiece {
        private static int counter = 1000;
        private final String pieceId;

        public ArtPiece() {
            counter++;
            this.pieceId = "ART-" + counter;
        }

        public String getPieceId() {
            return this.pieceId;
        }

        public abstract String describe();
    }

    public static class Painting extends ArtPiece {
        private final String title;

        public Painting(String title) {
            super();
            this.title = title;
        }

        @Override
        public String describe() {
            return "Painting: " + title + ", framed on canvas";
        }
    }

    public static class Sculpture extends ArtPiece {
        private final String title;

        public Sculpture(String title) {
            super();
            this.title = title;
        }

        @Override
        public String describe() {
            return "Sculpture: " + title + ", carved from stone";
        }
    }

    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println("\"" + p.describe() + "\"");

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println("\"" + s.describe() + "\"");
    }
}
