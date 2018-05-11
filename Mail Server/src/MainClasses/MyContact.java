package MainClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import DataStructures.DoubleLinkedList;
import Interfaces.IContact;

public class MyContact implements IContact {
	String username = new String();
	String pass = new String();
	String email = new String();
	MyFolder access = new MyFolder();
	MyApp server = new MyApp();

	/**
	 * a contact for the server.
	 * 
	 * @param username
	 * @param email
	 * @param pass
	 */
	public MyContact(String username, String email, String pass) {
		this.username = username.toLowerCase();
		this.pass = pass;
		this.email = email;
	}

	/**
	 * adding a new contact in list of contacts in each user.
	 * 
	 * @param username
	 *            of new contact
	 * @param emailOfContact
	 *            email of new contact
	 * @param email
	 *            email of the user who wants to create the new contact
	 * @throws IOException
	 */
	public void addContact(String username, String emailOfContact, String email)
			throws IOException {
		File contactsFolder = new File(
				"../Mail Server/Users/" + email + "/Contacts");
		access.set(contactsFolder);
		access.createFile(contactsFolder.getPath(), "index.txt");
		File indexFile = new File(contactsFolder.getPath() + "/Index.txt");
		DoubleLinkedList list = access.listInsideTxt(indexFile);
		if (list.contains(emailOfContact)) {
			return;
		}
		if (indexFile.exists()) {
			FileWriter fw1 = null;
			try {
				fw1 = new FileWriter(indexFile.getPath(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter pw1 = new PrintWriter(fw1);
			pw1.append(emailOfContact);
			pw1.println();
			pw1.append(username);
			pw1.println();
			pw1.close();
		} else {
			FileWriter fw1 = null;
			try {
				fw1 = new FileWriter(indexFile.getPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter pw1 = new PrintWriter(fw1);
			pw1.println(emailOfContact);
			pw1.println(username);
			pw1.close();
		}
	}

	/**
	 * renaming a contact.
	 * 
	 * @param oldName
	 * @param newName
	 * @param email
	 */
	public void renameContact(String oldName, String newName, String email) {
		File indexTxt = new File(
				"../Mail Server/Users/" + email + "/Contacts/Index.txt");
		DoubleLinkedList list = new DoubleLinkedList();
		list = access.listInsideTxt(indexTxt);
		int i = 0;
		while (i < list.size) {
			if (list.get(i).equals(oldName)) {
				list.remove(i);
				list.add(i, newName);
				break;
			}
			i++;
		}
		FileWriter fw1 = null;
		try {
			fw1 = new FileWriter(indexTxt.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw1 = new PrintWriter(fw1);
		while (!list.isEmpty()) {
			pw1.println(list.get(0));
			list.remove(0);
		}
		pw1.close();
	}

	/**
	 * rename the username of an account.
	 * 
	 * @param oldName
	 * @param newName
	 * @param email
	 */
	public void renameUsername(String oldName, String newName, String email) {
		File indexTxt = new File("../Mail Server/Users/" + email + "/Info.txt");
		DoubleLinkedList list = new DoubleLinkedList();
		list = access.listInsideTxt(indexTxt);
		int i = 0;
		while (i < list.size) {
			if (list.get(i).equals(oldName)) {
				list.remove(i);
				list.add(i, newName);
				break;
			}
			i++;
		}
		FileWriter fw1 = null;
		try {
			fw1 = new FileWriter(indexTxt.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw1 = new PrintWriter(fw1);
		while (!list.isEmpty()) {
			pw1.println(list.get(0));
			list.remove(0);
		}
		pw1.close();
	}

	public void addAccount(String oldEmail, String newEmail, String newPass) {
		/**
		 * read info txt file of the old email.
		 */
		File infoTxt = new File(
				"../Mail Server/Users/" + oldEmail + "/Info.txt");
		DoubleLinkedList list = new DoubleLinkedList();
		list = access.listInsideTxt(infoTxt);
		
		
		MyContact contact = new MyContact((String) (list.get(0)), newEmail,
				newPass);
		/**
		 * try to sign up with the new email.
		 */
		if (server.signup(contact)) {
			/**
			 * write the new email in the info of the old one.
			 */
			FileWriter fw1 = null;
			try {
				fw1 = new FileWriter(infoTxt.getPath(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter pw1 = new PrintWriter(fw1);
			pw1.append(newEmail);
			pw1.println();
			pw1.close();
			
			/**
			 * write the old email in the info of the new one.
			 */
			infoTxt = new File(
					"../Mail Server/Users/" + newEmail + "/Info.txt");
			try {
				fw1 = new FileWriter(infoTxt.getPath(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw1 = new PrintWriter(fw1);
			pw1.append(oldEmail);
			pw1.println();
			pw1.close();
		}
	}
	
	public void deleteAccount(String email) {
		/**
		 * read info txt file of the old email.
		 */
		File index = new File(
				"../Mail Server/Users/Index.txt");
		DoubleLinkedList list = new DoubleLinkedList();
		list = access.listInsideTxt(index);
		int i = 0;
		while (i < list.size()) {
			if (email.equals(list.get(i))) {
				list.remove(i);
				break;
			}
			i++;
		}
		FileWriter fw1 = null;
		try {
			fw1 = new FileWriter(index.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw1 = new PrintWriter(fw1);
		while (!list.isEmpty()) {
			pw1.println(list.get(0));
			list.remove(0);
		}
		pw1.close();
		
		MyFolder access = new MyFolder();
		File f = new File("../Mail Server/Users/" + email);
		access.delPermanent(f);
	}
	
}
