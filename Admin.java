
package com.mycompany.librarymanagementsystem;
import java.util.ArrayList;


public class Admin {
    private ArrayList<Book> books = new ArrayList<>();

    // Add Book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    // Update Book Status
    public void updateBookStatus(int bookId, String newStatus) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.setStatus(newStatus);
                System.out.println("Book status updated.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Delete Book
    public void deleteBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                System.out.println("Book deleted.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Search Book by Title
    public void searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.displayBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Display All Books
    public void displayAllBooks() {
        for (Book book : books) {
            book.displayBook();
        }
    }
}
