import java.io.*;
import java.util.*;
import javax.swing.*;

public class PatronAccountManager implements PatronAccountManagement {

    private final String FILE_NAME = "patronsDatabase.txt";

    @Override
    public void createAccount(Patron patron) {
        List<Patron> patrons = skimPatrons();

    
        if(usernameExists(patron.getUsername())) {
            JOptionPane.showMessageDialog(null, "Username already taken!");
            return;
        }

        if(!patron.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "Invalid email format!");
            return;
        }

       
        if(!patron.getPassword().equals(patron.getConfirmation())) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!");
            return;
        }

        // Add patron and write to file
        patrons.add(patron);
        writePatrons(patrons);
        JOptionPane.showMessageDialog(null, "Account created successfully!");
    }

    @Override
    public boolean login(String username, String password) {
        List<Patron> patrons= skimPatrons();
        for(Patron p: patrons)
        {
            if(p.getUsername().equalsIgnoreCase(username)&&p.getPassword().equals(password)){
                //JOptionPane.showMessageDialog(null, "Login successful");--> moved to the ui 
                return true; 
            }
        }
        //JOptionPane.showMessageDialog(null, "Username or password Incorrect");-->moved to the ui
        return false;
    }
    @Override
    public void deleteAccount(String username) {
        List<Patron> patrons= skimPatrons();
        boolean removed= false;
        
        Iterator<Patron> iterate= patrons.iterator();
        while(iterate.hasNext())
        {
           Patron p= iterate.next();
           if(p.getUsername().equalsIgnoreCase(username)){
               iterate.remove();
               removed= true;
               break; 
            }
        }
        if(removed){
            writePatrons(patrons);
            JOptionPane.showMessageDialog(null,"Account deleted successfully");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Username not found");
        }
    }

    // File handling utility methods

    private List<Patron> skimPatrons() { //reads folder to search for user
        List<Patron> patrons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length >= 3) {
                    String username = data[0];
                    String email = data[1];
                    String password = data[2];
                    patrons.add(new Patron(username, email, password, password));
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return patrons;
    }

    private void writePatrons(List<Patron> patrons) { //puts a new user in the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for(Patron p : patrons) {
                bw.write(p.getUsername() + "," + p.getEmail() + "," + p.getPassword());
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private boolean usernameExists(String username) { //checks for username availability
        List<Patron> patrons = skimPatrons();
        for(Patron p : patrons) {
            if(p.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
}
