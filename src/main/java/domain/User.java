package domain;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class User   {

    @Id private @GeneratedValue long idUser;

    private String email;

    private boolean  isAdmin=false;
    private String fname;
    private String lname;
    private String userName;
    private String password;
    private float money;

    public User(String fname, String lname, String userName, String email, String password) {
        this.isAdmin=false;
        this.fname=fname;
        this.lname=lname;
        this.userName=userName;
        this.email=email;
        this.password=password;
        this.money=0;


    }

    public User(User user,float money) {
        this.userName=user.getUserName();
    }

    public User() {

    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void addMoney(float money) {
        this.money += money;

    }

    public void SubMoney(float money) {
        this.money -= money;

    }

    public long getId() {
        return idUser;
    }

    public float getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                '}';
    }
}
