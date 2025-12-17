/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;



public class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private String status; // Available, Checked Out, Reserved

    public Book(int bookId, String title, String author,
                String genre, int publicationYear, String status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(String status) {
    this.status = status;
}

    public void displayBook() {
        System.out.println(
                "ID: " + bookId +
                " | Title: " + title +
                " | Author: " + author +
                " | Genre: " + genre +
                " | Year: " + publicationYear +
                " | Status: " + status
        );
    }
}
    

