import java.io.*;
import java.util.*;
import javax.swing.*;
public class PatronAccountManager implements PatronAccountManagement
{
    //main overridden methods
    @Override
    public void createAccount(Patron patron)
    {
        List<Patron> patrons= skimPatrons();
        // ask for email
        String email;
        while (true)
        {
            email=JOptionPane.showInputDialog(null, "Enter email: ");
            if(email==null)
            {
                return;
            }
            if(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            {
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid emial format");
            }
        }

        //ask for username
        String username;
        while (true)
        {
            username=JOptionPane.showInputDialog(null, "Enter username: ");
            if(username==null)
            {
                return;
            }
            if(usernameExists(username))
            {
                JOptionPane.showMessageDialog(null, "Username taken.");
            }
            else
            {
                break;
            }
        }

        //ask for password
        String password;
        String confirmPassword;

        while (true)
        {
            password= JOptionPane.showInputDialog(null, "Enter password: ");
            if(password==null)
            {
                return;
            }
            confirmPassword=JOptionPane.showInputDialog(null, "Confirm password: ");
            if(confirmPassword==null)
            {
                return;
            }

            if(password.equals(confirmPassword))
            {
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Unmatched passwords.");
            }

        }

        Patron newPatron= new Patron(username, email, password, confirmPassword);
        patrons.add(newPatron);
        writePatrons(patrons);
        JOptionPane.showMessageDialog(null, "Account created successfully!");
    }

    @Override
    public void login(String username, String password)
    {
       

    }
    
    @Override
    public void deleteAccount(String username, String password)
    {
       

    }


    //file handling utility methods:
    private final String FILE_NAME= "patronsDatabase.txt";
    
    private List<Patron> skimPatrons() //this reads all the patrons saved in the file
    {
        List <Patron> patrons= new ArrayList<>();
        try(BufferedReader br= new BufferedReader(new FileReader(FILE_NAME)))
        {
            String line;
            while((line= br.readLine()) != null)
            {
                String[] data= line.split(",");
                if(data.length >=4)
                {
                    String username= data[0];
                    String email= data[1];
                    String password= data[2];
                    patrons.add(new Patron(username, email, password, password));
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return patrons;
    }
    
    private void writePatrons(List<Patron> patrons) //this one should write into the file the new patrons
    {
        try(BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_NAME)))
        {
            for(Patron newP: patrons)
            {
                bw.write(newP.getUsername()+","+ newP.getEmail()+","+ newP.getPassword());
                bw.newLine();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private boolean usernameExists(String username) //this is a username checker in case the user already exists
    {
        List<Patron> patrons= skimPatrons();
        for(Patron p: patrons)
        {
            if(p.getUsername().equalsIgnoreCase(username))
            {
                return true;
            }
        }
        return false;
    }
}
