package MainClasses;

import java.io.File;
import java.io.IOException;

import DataStructures.SingleLinkedList;
public class Test {

	
	
	
	
	MyContact contact = new MyContact("bebo", "123");

	public static void main(String[] args) throws IOException {
		Test test = new Test();
		//MyApp server = new MyApp();
		SingleLinkedList mails = new SingleLinkedList();
		int i = 1;
		while (i < 8) {
			File f1 = new File("../Mail Server/Users/" + test.contact.username + "/Inbox/" + i);
			mails.add(f1);
			i++;
		}
		File f1 = new File("../Mail Server/Users/" + test.contact.username + "/Starred/" + 7);
		//File f2 = new File("../Mail Server/Users/" + test.contact.username + "/Starred");
		MyFolder f = new MyFolder();
		f.setFolder(f1);
		//test.move(mails, (IFolder) f);
		f.delTrash();
	}
}
