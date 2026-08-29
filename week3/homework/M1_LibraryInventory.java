package week3.homework;

public class M1_LibraryInventory {
    public static void main(String[] args) {
        BookInventory[] books = {
            new BookInventory("Clean Code", "Robert C. Martin", 3),
            new BookInventory("Effective Java", "Joshua Bloch", 5),
            new BookInventory("Refactoring", "Martin Fowler", 0),
            new BookInventory("Design Patterns", "GoF", 2)
        };
        
        for (BookInventory book : books) {
            book.printEntry();
        }
    }
}

class BookInventory {
    String title;
    String author;
    int copiesAvailable;
    
    public BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }
    
    public void printEntry() {
        System.out.printf("%s by %s - %d copies available\n", title, author, copiesAvailable);
    }
}
