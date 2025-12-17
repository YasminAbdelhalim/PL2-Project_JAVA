package user.module;

import java.io.*;
import java.util.ArrayList;

public class UserManager {

    private ArrayList<User> users = new ArrayList<>();
    private User currentUser;
    private final String FILE_NAME = "users.txt";

    public UserManager() {
        loadUsers();
    }


    private void loadUsers() {
        users.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                String username   = data[0];
                String password   = data[1];
                String role       = data[2];
                String email      = data[3];
                String phone      = data[4];
                String preference = data[5];

                switch (role) {
                    case "ADMIN":
                        users.add(new Admin(username, password, email, phone, preference));
                        break;

                    case "LIBRARIAN":
                        users.add(new Librarian(username, password, email, phone, preference));
                        break;

                    case "PATRON":
                        users.add(new Patron(username, password, email, phone, preference));
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading users file");
        }
    }

    private void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (User u : users) {
                pw.println(
                        u.getUsername() + "," +
                        u.getPassword() + "," +
                        u.getRole() + "," +
                        u.getEmail() + "," +
                        u.getPhone() + "," +
                        u.getPreference()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving users file");
        }
    }


    private boolean isValidEmail(String email) {
        return email != null &&
               email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }


    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) &&
                u.getPassword().equals(password)) {
                currentUser = u;
                return u;
            }
        }
        return null;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }


    public boolean updateProfile(User user, String email, String phone) {
        if (!isValidEmail(email)) {
            return false;
        }

        user.setEmail(email);
        user.setPhone(phone);
        saveUsers();
        return true;
    }

    public void updatePreference(User user, String preference) {
        user.setPreference(preference);
        saveUsers();
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        saveUsers();
    }

    public boolean addUser(User user) {

        
        if (!isValidEmail(user.getEmail())) {
            return false;
        }

         
        if (searchUser(user.getUsername()) != null) {
            return false;
        }

        users.add(user);
        saveUsers();
        return true;
    }

    public boolean deleteUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                users.remove(u);
                saveUsers();
                return true;
            }
        }
        return false;
    }

    public User searchUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> listUsers() {
        return new ArrayList<>(users);
    }

    public boolean isAdmin() {
        return currentUser != null &&
               currentUser.getRole().equals("ADMIN");
    }
}