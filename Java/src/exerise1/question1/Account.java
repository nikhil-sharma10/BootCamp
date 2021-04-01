package exerise1.question1;

public abstract class Account {

    private String id;
    private String password;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public boolean resetPassword(){
        System.out.println("Password reset successfully");
        return true;
    }
}
