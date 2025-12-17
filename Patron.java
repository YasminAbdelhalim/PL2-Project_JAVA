public class Patron
{
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public Patron(){}
    public Patron(String username, String email, String password, String confirmPassword)
    {
        this.username=username;
        this.email=email;
       
        this.password=password;
        this.confirmPassword=confirmPassword;
    }

    //getters
    public String getUsername(){return username;}
  
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getConfirmation(){return confirmPassword;}

    //setters
    public void setUsername(String username){this.username=username;}
  
    public void setEmail(String email){this.email=email;}
    public void setPassword(String password){this.password=password;}
    public void setConfirmation(String confirmPassword){this.confirmPassword=confirmPassword;}

}
