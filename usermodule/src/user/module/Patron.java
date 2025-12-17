package user.module;

public class Patron extends User {

    public Patron(String username, String password,
                  String email, String phone, String preference) {
        super(username, password, email, phone, preference);
    }

    @Override
    public String getRole() {
        return "PATRON";
    }
}