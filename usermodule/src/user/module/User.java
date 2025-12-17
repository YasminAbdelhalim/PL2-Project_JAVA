package user.module;

public abstract class User {

    protected String username;
    protected String password;
    protected String email;
    protected String phone;
    protected String preference;

    public User(String username, String password,
                String email, String phone, String preference) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.preference = preference;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPreference() {
        return preference;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public abstract String getRole();
    
    public void setPassword(String password) {
    this.password = password;
}
}