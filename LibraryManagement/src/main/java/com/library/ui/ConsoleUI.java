package com.library.ui;

import com.library.service.LibraryService;
import com.library.model.Book;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private LibraryService libraryService;
    private Scanner scanner;

    public ConsoleUI(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a new book");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Track borrowed books");
            System.out.println("5. View available books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 : addBook();
                case 2 : borrowBook();
                case 3 : returnBook();
                case 4 : displayBooks(libraryService.getBorrowedBooks());
                case 5 : displayBooks(libraryService.getAvailableBooks());
                case 6 : {
                    System.out.println("Exiting...");
                    return;
                }
                default : System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        if (libraryService.addBook(title, author)) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add book.");
        }
    }

    private void borrowBook() {
        System.out.print("Enter book ID to borrow: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (libraryService.borrowBook(id)) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book not available or invalid ID.");
        }
    }

    private void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (libraryService.returnBook(id)) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Invalid ID or book not borrowed.");
        }
    }
}
