package MainClasses;

import java.io.File;
import java.io.IOException;

import DataStructures.LinkedBasedQueue;
import DataStructures.SingleLinkedList;

public class Test {

	public MyContact contact = new MyContact("bebo", "bebo@aka.com", "123");

	public static void main(String[] args) throws IOException {
		MyApp server = new MyApp();
		Test test = new Test();
		/**
		 * test signing up ... moving mails... deleteing mails to trash.
		 */
		// server.signup(test.contact);
		SingleLinkedList mails = new SingleLinkedList();
		int i = 1;
		while (i < 8) {
			File f1 = new File("../Mail Server/Users/" + test.contact.email
					+ "/" + test.contact.username + "/Inbox/" + i);
			mails.add(f1);
			i++;
		}
		File f1 = new File("../Mail Server/Users/" + test.contact.email + "/"
				+ test.contact.username + "/Starred");
		// File f2 = new File("../Mail Server/Users/" + test.contact.username +
		// "/Starred");
		MyFolder f = new MyFolder();
		f.set(f1);
		server.deleteEmails(mails);

		/**
		 * test composing a mail.
		 */

		MyMail mail = new MyMail("bebo@aka.com");
		LinkedBasedQueue to = new LinkedBasedQueue();
		to.enqueue("amr@aka.com");
		mail.setReceivers(to);
		mail.setSubject("HI");
		server.compose(mail);

		/**
		 * test making a new folder.
		 */
		/*
		 * String path = "../Mail Server/Users/" + "bebo@aka.com" + "/" + "bebo"
		 * + "/Sent Mails"; MyFolder fold = new MyFolder();
		 * fold.createFolder(path, "done");
		 */
	}
}
