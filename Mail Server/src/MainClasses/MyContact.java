package MainClasses;

import Interfaces.IContact;

public class MyContact implements IContact{
	String username = new String();
	String pass = new String();

	public MyContact(String username, String pass) {
		this.username = username.toLowerCase();
		this.pass = pass;
	}
}
