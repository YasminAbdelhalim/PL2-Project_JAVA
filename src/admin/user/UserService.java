/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author VICTUS
 */
public class UserService {
    
private final UserFileHandler fileHandler;   // handles reading/writing the users.txt file
    private final List<User> users;              // stores all users in memory

    // constructor: load users from file when the service starts
    public UserService(String filePath) throws IOException {
        this.fileHandler = new UserFileHandler(filePath);
        this.users = new ArrayList<>();
        loadUsersFromFile();
    }

    // read all users from users.txt into the list
    private void loadUsersFromFile() throws IOException {
        users.clear();
        users.addAll(fileHandler.readAll());
    }

    // save the current list of users back to users.txt
    private void saveUsersToFile() throws IOException {
        fileHandler.writeAll(users);
    }

    // generate the next user id in the format U1, U2, U3, ...
    private String generateNextId() {
        int max = 0;
        for (User u : users) {
            if (u.getId() != null && u.getId().startsWith("U")) {
                try {
                    int n = Integer.parseInt(u.getId().substring(1));
                    if (n > max) max = n;
                } catch (NumberFormatException e) {
                    // ignore ids that don't follow the U<number> format
                }
            }
        }
        int next = max + 1;
        return "U" + next;
    }
    
    // check if a username already exists (used to keep usernames unique)
    public boolean usernameExists(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // add a new user (for librarian or patron accounts)
    public void addUser(User user) throws IOException {
         // do not allow duplicate usernames
        if (usernameExists(user.getUsername())) {
             throw new IllegalArgumentException("Username already exists");
        }

        // generate id if empty
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(generateNextId());
        }
        users.add(user);
        saveUsersToFile();
    }


    // update an existing user by id
    public boolean updateUser(String id, User newData) throws IOException {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getId().equals(id)) {
                // keep the same id for the user
                newData.setId(id);
                users.set(i, newData);
                saveUsersToFile();
                return true;
            }
        }
        return false; // user with this id not found
    }
    
    // update only username and password for a specific user (for example, the logged-in admin)
    public boolean updateCredentials(String userId, String newUsername, String newPassword) throws IOException {
        for (User u : users) {
            if (u.getId().equals(userId)) {

                // make sure the new username is unique (unless it's the same as before)
                if (!u.getUsername().equalsIgnoreCase(newUsername) && usernameExists(newUsername)) {
                 throw new IllegalArgumentException("Username already exists");
                }

                u.setUsername(newUsername);
                u.setPassword(newPassword);
                saveUsersToFile();
                return true;
           }
       }
       return false;
    }

    // delete a user by id
    public boolean deleteUser(String id) throws IOException {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                saveUsersToFile();
                return true;
            }
        }
        return false; // user with this id not found
    }

    // simple search by id, name, username or role
    public List<User> search(String keyword) {
        keyword = keyword.toLowerCase();
        List<User> result = new ArrayList<>();
        for (User u : users) {
            if (u.getName().toLowerCase().contains(keyword)
                    || u.getUsername().toLowerCase().contains(keyword)
                    || u.getRole().toLowerCase().contains(keyword)
                    || (u.getId() != null && u.getId().toLowerCase().contains(keyword))) {
                result.add(u);
            }
        }
        return result;
    }
    
    // find a user by username and password (
    public User authenticate(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)
                && u.getPassword().equals(password)) {
                return u;  // login success
            }
        }
        return null; // login failed
    }


    // return a copy of all users (used to show data in the table)
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}