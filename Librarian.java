package project;

import java.io.*;
import java.util.*;
        

public class Librarian {
    private final String BOOKS_FILE = "books.txt";

    // checkout Book
    public boolean checkoutBook(int bookId, int patronId) {
        List<String> allBooks = new ArrayList<>();
        boolean done = false;

        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // bookId,title,author,genre,year,status,patronId,reservedPatronId
                int id = Integer.parseInt(data[0]);
                String status = data[5];
                String reservedId = data[7];

                if (id == bookId) {
                    if (status.equals("Available") && reservedId.equals("-")) {
                        data[5] = "Checked Out";
                        data[6] = String.valueOf(patronId);
                        done = true;
                    } else if (status.equals("Reserved") && reservedId.equals(String.valueOf(patronId))) {
                        data[5] = "Checked Out";
                        data[6] = String.valueOf(patronId);
                        data[7] = "-"; 
                        done = true;
                    } else {
                        System.out.println("Book is not available for checkout.");
                        allBooks.add(String.join(",", data));
                        writeToFile(allBooks);
                        return false;
                    }
                }

                allBooks.add(String.join(",", data));
            }

        } catch (IOException e) {
            System.out.println("Error reading books file.");
        }

        writeToFile(allBooks);
        if (done) 
            System.out.println("Book checked out successfully.");
        return done;
    }

    // return Book
    public boolean returnBook(int bookId) {
        List<String> allBooks = new ArrayList<>();
        boolean done = false;

        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // bookId,title,author,genre,year,status,patronId,reservedPatronId
                int id = Integer.parseInt(data[0]);
                String status = data[5];
                String reservedId = data[7];

                if (id == bookId) {
                    if (!status.equals("Checked Out")) {
                        System.out.println("Book is not currently checked out.");
                        allBooks.add(String.join(",", data));
                        writeToFile(allBooks);
                        return false;
                    }
                    if (!reservedId.equals("-")) {
                        data[5] = "Reserved";  
                        data[6] = "-";
                        done = true;
                        System.out.println("Book returned. Reserved for Patron " + reservedId + ". Notify them.");
                    } else {
                        data[5] = "Available";
                        data[6] = "-";
                        done = true;
                        System.out.println("Book returned successfully. Now available.");
                    }
                }
                allBooks.add(String.join(",", data));
            }

        } catch (IOException e) {
            System.out.println("Error reading books file.");
        }
        writeToFile(allBooks);
        return done;
    }

    // reserve Book (Patron requests reservation)
    public boolean reserveBook(int bookId, int patronId) {
        List<String> allBooks = new ArrayList<>();
        boolean reserved = false;

        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // bookId,title,author,genre,year,status,patronId,reservedPatronId
                int id = Integer.parseInt(data[0]);

                if (id == bookId) {
                    if (data[5].equals("Available")) {
                        System.out.println("Book is available. No need to reserve.");
                    } else if (!data[7].equals("-")) {
                        System.out.println("Book already reserved by another patron.");
                    } else {
                        data[7] = String.valueOf(patronId);
                        reserved = true;
                        System.out.println("Book reserved successfully for Patron " + patronId);
                    }
                }
                allBooks.add(String.join(",", data));
            }
        }catch (IOException e) {
            System.out.println("Error reading books file.");
        }

        writeToFile(allBooks);
        return reserved;
    }

    // write to File
    private void writeToFile(List<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (String line : lines) {
                pw.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error writing books file.");
        }
    }
}
