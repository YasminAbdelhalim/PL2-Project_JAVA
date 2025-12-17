public interface PatronAccountManagement
{
    public void createAccount(Patron patron);
    public void login(String username, String password);
    public void deleteAccount(String username, String password);
}