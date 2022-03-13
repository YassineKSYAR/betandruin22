package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	private String fname;
	private String lname;
	private String email;
	private String password;
	private boolean  isAdmin=false;
	
	/*public User(String email,String password) {
		this.email=email;
		this.password=password;
	}*/
	
	public Users(String fname,String lname,String email,String password) {
		this.fname=fname;
		this.lname=lname;
		this.email=email;
		this.password=password;
		this.isAdmin=false;
	}
	
public String getEmail() {
	return email;
}
public String getFname() {
	return fname;
}
public String getLname() {
	return lname;
}
public String getPassword() {
	return password;
}

public boolean getIsAdmin(){
	return isAdmin;
}

public String toString() {
	return fname+" "+lname+" "+email+" "+password;
}

}
