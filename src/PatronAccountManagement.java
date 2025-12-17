public interface PatronAccountManagement
{
    public void createAccount(Patron patron);
    public boolean login(String username, String password);
    public void deleteAccount(String username);
}