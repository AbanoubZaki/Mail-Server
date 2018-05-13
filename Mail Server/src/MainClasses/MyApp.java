package MainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import DataStructures.DoubleLinkedList;
import DataStructures.SingleLinkedList;
import Interfaces.IApp;
import Interfaces.IContact;
import Interfaces.IFilter;
import Interfaces.IFolder;
import Interfaces.ILinkedList;
import Interfaces.IMail;
import Interfaces.ISort;

/**
 * @author Abanoub Ashraf
 * @author Amr
 * @author kiro
 */
public class MyApp implements IApp {

	/**
	 * sorted emails.
	 */
	DoubleLinkedList sorted = new DoubleLinkedList();
	/**
	 * filtered emails.
	 */
	DoubleLinkedList filtered = new DoubleLinkedList();
	/**
	 * a linked list of 10 sized arrays.
	 */
	DoubleLinkedList arrays = new DoubleLinkedList();
	

	/**
	 * Sign in to the application.
	 * 
	 * @param email
	 * @param password
	 * @return false if the email name not exist
	 */
	@Override
	public boolean signin(final String eMail, final String password) {
		// TODO Auto-generated method stub
		if (eMail == null || password == null) {
			return false;
		}
		String email = eMail.toLowerCase();
		File f1 = new File("../Mail Server/Users/" + email);
		if (f1.exists()) {
			File f2 = new File("../Mail Server/Users/" + email + "/Info.txt");
			String Email = new String();
			String Pass = new String();
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(f2));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Email = in.readLine();
				Email = in.readLine();
				Pass = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Email.equals(email) && Pass.equals(password)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Create new account.
	 * 
	 * @param contact
	 * @return false if the email name already exist
	 */
	@Override
	public boolean signup(final IContact contact) {
		// TODO Auto-generated method stub
		File f0 = new File("../Mail Server");
		File f1 = new File("../Mail Server/Users");
		File f21 = new File("../Mail Server/Users/Index.txt");
		f0.mkdirs();
		f1.mkdirs();
		try {
			f21.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * checks if one of the contact fields are null. then. check if the user
		 * is already found on server or not.
		 */
		SingleLinkedList emails = new SingleLinkedList();
		String email = new String();
		BufferedReader in = null;
		try {
			in = new BufferedReader(
					new FileReader("../Mail Server/Users/Index.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((email = in.readLine()) != null) {
				emails.add(email);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyContact mycontact = (MyContact) (contact);
		if (emails.contains(mycontact.email) || mycontact.username.equals(null)
				|| mycontact.pass.equals(null)
				|| mycontact.email.equals(null)) {
			return false;
		}
		File f2 = new File("../Mail Server/Users/" + mycontact.email);
		File f3 = new File(
				"../Mail Server/Users/" + mycontact.email + "/Sent Mails");
		File sentIn = new File("../Mail Server/Users/" + mycontact.email
				+ "/Sent Mails/Index.txt");
		File f6 = new File(
				"../Mail Server/Users/" + mycontact.email + "/Drafts");
		File draftsIn = new File("../Mail Server/Users/" + mycontact.email
				+ "/Drafts/Index.txt");
		File f7 = new File(
				"../Mail Server/Users/" + mycontact.email + "/Trash");
		File trashIn = new File(
				"../Mail Server/Users/" + mycontact.email + "/Trash/Index.txt");
		File f9 = new File(
				"../Mail Server/Users/" + mycontact.email + "/Contacts");
		File contactsIn = new File("../Mail Server/Users/" + mycontact.email
				+ "/Contacts/Index.txt");
		File f4 = new File(
				"../Mail Server/Users/" + mycontact.email + "/Inbox");
		File inboxIn = new File(
				"../Mail Server/Users/" + mycontact.email + "/Inbox/Index.txt");
		File f5 = new File(
				"../Mail Server/Users/" + mycontact.email + "/Starred");
		File starredIn = new File("../Mail Server/Users/" + mycontact.email
				+ "/Starred/Index.txt");
		File f22 = new File(
				"../Mail Server/Users/" + mycontact.email + "/Info.txt");
		File f23 = new File(
				"../Mail Server/Users/" + mycontact.email + "/Index.txt");

		f2.mkdirs();
		f3.mkdirs();
		f4.mkdirs();
		f5.mkdirs();
		f6.mkdirs();
		f7.mkdirs();
		f9.mkdirs();

		try {
			f22.createNewFile();
			f23.createNewFile();
			sentIn.createNewFile();
			draftsIn.createNewFile();
			trashIn.createNewFile();
			contactsIn.createNewFile();
			inboxIn.createNewFile();
			starredIn.createNewFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * write user's email in index.txt file.
		 */
		if (f2.exists()) {
			FileWriter fw1 = null;
			try {
				fw1 = new FileWriter("../Mail Server/Users/Index.txt", true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter pw1 = new PrintWriter(fw1);
			pw1.append(mycontact.email);
			pw1.println();
			pw1.close();
		} else {
			FileWriter fw1 = null;
			try {
				fw1 = new FileWriter("../Mail Server/Users/Index.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter pw1 = new PrintWriter(fw1);
			pw1.println(mycontact.email);
			pw1.close();
		}

		/**
		 * write user's information in info.txt file.
		 */
		FileWriter fw2 = null;
		try {
			fw2 = new FileWriter(
					"../Mail Server/Users/" + mycontact.email + "/Info.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw2 = new PrintWriter(fw2);
		pw2.println(mycontact.username);
		pw2.println(mycontact.email);
		pw2.println(mycontact.pass);
		pw2.close();

		/**
		 * write user's information in info.txt file.
		 */
		FileWriter fw3 = null;
		try {
			fw3 = new FileWriter(
					"../Mail Server/Users/" + mycontact.email + "/Index.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw3 = new PrintWriter(fw3);
		pw3.println("Contacts");
		pw3.println("Drafts");
		pw3.println("Inbox");
		pw3.println("Trash");
		pw3.println("Sent Mails");
		pw3.println("Starred");
		pw3.close();

		return true;

	}

	/**
	 * This function should be called before reading from the index file and
	 * apply the sort and search parameters.
	 * 
	 * @param folder
	 *            currently shown, can be null.
	 * @param filter
	 *            to apply search, can be null.
	 * @param sort
	 *            to apply sort.
	 */
	@Override
	public void setViewingOptions(final IFolder folder, final IFilter filter,
			final ISort sort) {
		MyFolder fol = new MyFolder();
		fol = (MyFolder) folder;
		if (sort == null) {
			MyFilter filt = (MyFilter) filter;
			filt.setDirectory(fol.path);
			filtered = filt.objects;
		} else {
			MySort sorting = (MySort) sort;
			sorting.setDirectory(fol.path);
			sorted = sorting.sorted;
		}
	}

	/**
	 * You should use setViewingOptions function first.
	 * 
	 * @param page
	 *            to handle paging.
	 * @return list of emails.
	 */
	@Override
	public IMail[] listEmails(final int page) {
		int i = 0;
		int j = 0;
		String[] ten = new String[10];
		while (i<sorted.size) {
			ten[j] = (String) sorted.get(i);
			j++;
			i++;
			if(j == 10) {
				arrays.add(ten);
				j=0;
			}
		}
		return null;
	}

	/**
	 * You should use setViewingOptions function first.
	 * 
	 * @param mails
	 *            to be moved to trash.
	 */
	@Override
	public void deleteEmails(final ILinkedList mails) {
		// TODO Auto-generated method stub
		MyFolder access = new MyFolder();
		File moved = new File((String) mails.get(0));
		File folder = moved.getParentFile();
		folder = folder.getParentFile();
		String trashDes = folder.getPath() + "/Trash";
		while (mails != null && !mails.isEmpty()) {
			moved = new File((String) mails.get(0));
			try {
				access.set(moved);
				access.move(trashDes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mails.remove(0);
		}
	}

	/**
	 * You should use setViewingOptions function first.
	 * 
	 * @param mails
	 *            to be moved.
	 * @param des
	 *            the destination folder.
	 */
	@Override
	public void moveEmails(final ILinkedList mails, final IFolder des) {
		// TODO Auto-generated method stub
		MyFolder desfolder = (MyFolder) des;
		while (mails != null && !mails.isEmpty()) {
			MyFolder moved = new MyFolder();
			File destination = new File((String) (mails.get(0)));
			moved.set(destination);
			try {
				moved.move(desfolder.path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mails.remove(0);
		}
	}

	/**
	 * Send a new email.
	 * 
	 * @param email
	 *            should contain all the information needed sender, list of.
	 *            receivers, list of attachments, email body, ...
	 * @return false if something wrong happened like sending to non-existing
	 *         user.
	 */
	@Override
	public boolean compose(final IMail email) {
		MyMail newMail = (MyMail) email;
		MyFolder access = new MyFolder();
		File index1 = new File("../Mail Server/Users/Index.txt");
		DoubleLinkedList list = new DoubleLinkedList();
		list = access.listInsideTxt(index1);
		String to = new String();
		int i = 0;
		while (i < newMail.receivers.size()) {
			to = (String) newMail.receivers.dequeue();
			if (!list.contains(to)) {
				return false;
			}
		}
		/**
		 * all main fields are not null and all recievers are not fake emails.
		 */
		return true;
	}

	public static void main(String[] args) {
	}
}