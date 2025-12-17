/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.user;

import java.io.IOException;
import java.util.List;
/**
 *
 * @author VICTUS
 */

public class TestMain {

    public static void main(String[] args) {

        try {
            // start service with users.txt
            UserService service = new UserService("users.txt");

            // ===== 1) clear old test data (optional but useful) =====
            // delete all existing users so we start from a clean file
            for (User u : service.getAllUsers()) {
                service.deleteUser(u.getId());
            }

            // ===== 2) add two users =====
            User u1 = new User("Sara Ahmed", "sara", "1234", "LIBRARIAN", "sara@mail.com");
            User u2 = new User("Omar Ali", "omar", "abcd", "PATRON", "omar@mail.com");

            service.addUser(u1);
            service.addUser(u2);

            // ===== 3) try to add user with duplicate username =====
            try {
                User u3 = new User("Another Sara", "sara", "9999", "PATRON", "sara2@mail.com");
                service.addUser(u3);
            } catch (IllegalArgumentException ex) {
                System.out.println("Duplicate username test: " + ex.getMessage());
            }

            // ===== 4) print all users =====
            System.out.println("\nAll users:");
            List<User> all = service.getAllUsers();
            for (User u : all) {
                System.out.println(u.toFileString());
            }

            // ===== 5) test search =====
            System.out.println("\nSearch for:");
            for (User u : service.search("omar")) {
                System.out.println(u.toFileString());
            }

            // ===== 6) test authenticate (login) =====
            System.out.println("\nLogin test:");
            User logged = service.authenticate("sara", "1234");
            System.out.println(logged != null ? "Login success: " + logged.getRole()
                                             : "Login failed");

            // ===== 7) test updateCredentials for logged-in user =====
            if (logged != null) {
                service.updateCredentials(logged.getId(), "sara_new", "newpass");
                System.out.println("\nAfter changing credentials:");
                for (User u : service.getAllUsers()) {
                    System.out.println(u.toFileString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
