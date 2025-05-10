import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Books are available initially
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        this.isAvailable = false;
    }

    public void returnBook() {
        this.isAvailable = true;
    }
}

public class LibraryManagementSystem {
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initializing books in the library
        books.add(new Book("The Alchemist", "Paulo Coelho"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book("1984", "George Orwell"));
        books.add(new Book("Moby Dick", "Herman Melville"));
        books.add(new Book("Pride and Prejudice", "Jane Austen"));

        // Main menu
        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. View available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Check book status");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    checkBookStatus();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // View available books
    private static void viewAvailableBooks() {
        System.out.println("\n--- Available Books ---");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }

    // Borrow a book
    private static void borrowBook() {
        System.out.print("\nEnter the title of the book to borrow: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);

        if (book != null && book.isAvailable()) {
            book.borrow();
            System.out.println("You have successfully borrowed the book: " + book.getTitle());
        } else if (book != null) {
            System.out.println("Sorry, the book '" + book.getTitle() + "' is already borrowed.");
        } else {
            System.out.println("The book '" + title + "' is not available in the library.");
        }
    }

    // Return a book
    private static void returnBook() {
        System.out.print("\nEnter the title of the book to return: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);

        if (book != null && !book.isAvailable()) {
            book.returnBook();
            System.out.println("You have successfully returned the book: " + book.getTitle());
        } else if (book != null) {
            System.out.println("The book '" + book.getTitle() + "' was not borrowed.");
        } else {
            System.out.println("The book '" + title + "' does not exist in the library.");
        }
    }

    // Check the status of a book (available or borrowed)
    private static void checkBookStatus() {
        System.out.print("\nEnter the title of the book to check its status: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);

        if (book != null) {
            if (book.isAvailable()) {
                System.out.println("The book '" + book.getTitle() + "' is available.");
            } else {
                System.out.println("The book '" + book.getTitle() + "' is currently borrowed.");
            }
        } else {
            System.out.println("The book '" + title + "' does not exist in the library.");
        }
    }

    // Find a book by its title
    private static Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
