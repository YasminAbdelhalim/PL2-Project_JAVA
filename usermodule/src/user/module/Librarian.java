package user.module;

public class Librarian extends User {

    public Librarian(String username, String password,
                     String email, String phone, String preference) {
        super(username, password, email, phone, preference);
    }

    @Override
    public String getRole() {
        return "LIBRARIAN";
    }
}