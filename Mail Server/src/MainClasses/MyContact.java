package MainClasses;

import Interfaces.IContact;

public class MyContact implements IContact {
	String username = new String();
	String pass = new String();
	String email = new String();

	public MyContact(String username, String email, String pass) {
		this.username = username.toLowerCase();
		this.pass = pass;
		this.email = email;
	}
}
