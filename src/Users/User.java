package Users;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String fname;
	private String lname;
	private String email;
	private String password;
	
	/*public User(String email,String password) {
		this.email=email;
		this.password=password;
	}*/
	
	public User(String fname,String lname,String email,String password) {
		this.fname=fname;
		this.lname=lname;
		this.email=email;
		this.password=password;
	}
	

public String toString() {
	return fname+" "+lname+" "+email+" "+password;
}

}
