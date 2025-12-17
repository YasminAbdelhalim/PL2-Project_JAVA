package user.module;

public class Admin extends User {

    public Admin(String username, String password,
                 String email, String phone, String preference) {
        super(username, password, email, phone, preference);
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
}