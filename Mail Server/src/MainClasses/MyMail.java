package MainClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataStructures.DoubleLinkedList;
import DataStructures.LinkedBasedQueue;
import Interfaces.IMail;

public class MyMail implements IMail {
	int priority;
	/**
	 * must be an email in the form of 'bebo@aka.com'.
	 */
	String from = new String();
	String subject = new String();
	MyFolder message = new MyFolder();
	String time = new String();
	DoubleLinkedList attachments = new DoubleLinkedList();
	/**
	 * emails of the receivers.
	 */
	LinkedBasedQueue receivers = new LinkedBasedQueue();
	/**
	 * a folder that concatinates all components of the messg.
	 */
	MyFolder totalMsg = new MyFolder();

	public MyMail(String sender) {
		Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("d-M-y 'at' h:m:s a");
		time = dateFormatter.format(now).toString(); 
		from = sender;
		/**
		 *  find the directory of the email of the user the get all his usernames
		 */
		File user = new File("../Mail Server/Users/" + from);
		File[] listOfFiles = user.listFiles();
		for (File file : listOfFiles) {
			String des = "../Mail Server/Users/" + from + "/" + file.getName()
					+ "/Sent Mails";
			totalMsg.createFolder(des, time);
			priority = 3;
		}
	}

	public void setPriority(int p) {
		priority = p;
	}

	public void setSender(String sender) {
		from = sender;
	}

	public void setSubject(String sub) {
		subject = sub;
	}

	public void setAttach(DoubleLinkedList attach) {
		attachments.equals(attach);
	}

	public void setReceivers(LinkedBasedQueue receiver) throws IOException {
		File user = new File("../Mail Server/Users/" + from);
		File[] listOfFiles = user.listFiles();
		for (File file : listOfFiles) {
			String des = "../Mail Server/Users/" + from + "/" + file.getName()
					+ "/Sent Mails";
			MyFolder txt = new MyFolder();
			txt.createFile(des, time + ".txt");
		}
		while (!receiver.isEmpty()) {
			receivers.enqueue(receiver.dequeue());
		}
	}

	public void setMessage(MyFolder msg) throws IOException {
		message.equals(msg);
		message.createFile(totalMsg.path, subject + ".txt");
	}
}
