
package com.mycompany.librarymanagementsystem;


public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin();

        Book b1 = new Book(1, "Java Basics", "James Gosling",
                "Programming", 2022, "Available");

        Book b2 = new Book(2, "OOP Concepts", "Ali Ahmed",
                "Programming", 2021, "Available");

        admin.addBook(b1);
        admin.addBook(b2);

        System.out.println("\nAll Books:");
        admin.displayAllBooks();

        System.out.println("\nUpdate Book Status:");
        admin.updateBookStatus(1, "Checked Out");

        System.out.println("\nSearch Book:");
        admin.searchBook("Java Basics");

        System.out.println("\nDelete Book:");
        admin.deleteBook(2);

        System.out.println("\nAll Books After Delete:");
        admin.displayAllBooks();
    }
}

