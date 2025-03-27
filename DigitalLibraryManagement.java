import java.util.*;

// Book class to store and manage book details
class Book {
    private final String bookID; // Unique identifier for the book
    private String title; // Title of the book
    private String author; // Author of the book
    private String genre; // Genre/category of the book
    private String availabilityStatus; // Availability status (Available/Checked Out)

    // Constructor to initialize book details
    public Book(String bookID, String title, String author, String genre, String availabilityStatus) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    // Getter methods to access book properties
    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    // Setter methods to update book properties
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    // Method to return book details as a formatted string
    @Override
    public String toString() {
        return "Book ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Status: " + availabilityStatus;
    }
}

// Library class to manage book collection
class Library {
    private final Map<String, Book> books = new HashMap<>(); // Stores books using a HashMap for quick access

    // Method to add a new book to the collection
    public void addBook(Book book) {
        if (books.containsKey(book.getBookID())) {
            System.out.println("Error: Book ID already exists!");
            return;
        }
        books.put(book.getBookID(), book);
        System.out.println("Book added successfully.");
    }

    // Method to display all books in the library
    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.values().forEach(System.out::println);
    }

    // Method to search for a book by ID or title
    public Book searchBook(String key) {
        return books.values().stream()
                .filter(book -> book.getBookID().equalsIgnoreCase(key) || book.getTitle().equalsIgnoreCase(key))
                .findFirst()
                .orElse(null);
    }

    // Method to update book details
    public void updateBook(String bookID, String title, String author, String genre, String status) {
        Book book = books.get(bookID);
        if (book == null) {
            System.out.println("Error: Book not found!");
            return;
        }
        if (!title.isEmpty()) book.setTitle(title);
        if (!author.isEmpty()) book.setAuthor(author);
        if (!genre.isEmpty()) book.setGenre(genre);
        if (!status.isEmpty() && (status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Checked Out"))) {
            book.setAvailabilityStatus(status);
        } else {
            System.out.println("Invalid status! Keeping the previous status.");
        }
        System.out.println("Book updated successfully.");
    }

    // Method to delete a book from the collection
    public void deleteBook(String bookID) {
        if (books.remove(bookID) != null) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Error: Book not found!");
        }
    }
}

// Main class to run the digital library system
public class DigitalLibraryManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            try {
                // Display menu options
                System.out.println("\nDigital Library Book Management System");
                System.out.println("1. Add a Book");
                System.out.println("2. View All Books");
                System.out.println("3. Search a Book");
                System.out.println("4. Update Book Details");
                System.out.println("5. Delete a Book");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1 -> {
                        // Taking input for adding a new book
                        System.out.print("Enter Book ID: ");
                        String id = scanner.nextLine().trim();
                        if (id.isEmpty()) {
                            System.out.println("Error: Book ID cannot be empty!");
                            break;
                        }
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine().trim();
                        System.out.print("Enter Genre: ");
                        String genre = scanner.nextLine().trim();
                        System.out.print("Enter Availability Status (Available/Checked Out): ");
                        String status = scanner.nextLine().trim();
                        if (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Checked Out")) {
                            System.out.println("Error: Invalid status. Must be 'Available' or 'Checked Out'.");
                            break;
                        }
                        library.addBook(new Book(id, title, author, genre, status));
                    }
                    case 2 -> library.viewAllBooks();
                    case 3 -> {
                        System.out.print("Enter Book ID or Title to search: ");
                        String key = scanner.nextLine().trim();
                        Book book = library.searchBook(key);
                        System.out.println(book != null ? book : "Book not found!");
                    }
                    case 4 -> {
                        // Updating book details
                        System.out.print("Enter Book ID to update: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter new Title (leave blank to keep unchanged): ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter new Author (leave blank to keep unchanged): ");
                        String author = scanner.nextLine().trim();
                        System.out.print("Enter new Genre (leave blank to keep unchanged): ");
                        String genre = scanner.nextLine().trim();
                        System.out.print("Enter new Availability Status (Available/Checked Out): ");
                        String status = scanner.nextLine().trim();
                        library.updateBook(id, title, author, genre, status);
                    }
                    case 5 -> {
                        System.out.print("Enter Book ID to delete: ");
                        String id = scanner.nextLine().trim();
                        library.deleteBook(id);
                    }
                    case 6 -> {
                        System.out.println("Exiting system...");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
            }
        }
    }
}
