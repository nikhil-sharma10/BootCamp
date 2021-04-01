package exerise1.question10;

public class Account {

    private int id;
    private String password;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean resetPassword() {
        System.out.println("password reset successfully");
        return true;
    }
}
