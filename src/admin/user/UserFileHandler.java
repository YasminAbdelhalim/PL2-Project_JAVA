/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.user;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VICTUS
 */

public class UserFileHandler {

    private final File file;

    public UserFileHandler(String filePath) {
        this.file = new File(filePath);
    }

    public List<User> readAll() throws IOException {
        List<User> users = new ArrayList<>();

        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                User u = User.fromFileString(line);
                if (u != null) {
                    users.add(u);
                }
            }
        }

        return users;
    }

    
    public void writeAll(List<User> users) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            bw.write("ID|NAME|USERNAME|PASSWORD|ROLE|EMAIL");
            bw.newLine();

           
            for (User u : users) {
                bw.write(u.toFileString());
                bw.newLine();
            }
        }
    }
}

