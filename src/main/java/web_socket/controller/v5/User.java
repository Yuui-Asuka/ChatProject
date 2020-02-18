package web_socket.controller.v5;

public class User {

    private String username;
    private String pwd;

    public User(){}

    public User(String userName, String pwd) {
        super();
        this.username = userName;
        this.pwd = pwd;
    }

    public void setUserName(String userName) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }
}
