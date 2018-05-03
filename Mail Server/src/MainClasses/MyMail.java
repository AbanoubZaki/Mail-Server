package MainClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	String message = new String();
	String date = new String();
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

	public MyMail(String sender, LinkedBasedQueue receiver, String sub,
			String msg, DoubleLinkedList attach) throws IOException {
		Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"d-M-y 'at' h-m-s a");
		date = dateFormatter.format(now).toString();
		dateFormatter = new SimpleDateFormat("d-M-y");
		time = dateFormatter.format(now).toString();
		from = sender;
		setReceivers(receiver);
		priority = 3;
		subject = sub;
		message = msg;
		attachments.equals(attach);
		/**
		 * find the directory of the email of the user the get all his usernames.
		 */
		File user = new File("../Mail Server/Users/" + from);
		File[] listOfFiles = user.listFiles();
		int i = 1;
		int k = 0;
		for (File file : listOfFiles) {
			while (k < receivers.size()) {
				String j = Integer.toString(i);
				String des = "../Mail Server/Users/" + from + "/"
						+ file.getName() + "/Sent Mails";
				SimpleDateFormat dateFormatter1 = new SimpleDateFormat(
						"d-M-y 'at' h-m-s a");
				date = dateFormatter1.format(now).toString();
				totalMsg.createFolder(des, j);
				totalMsg.createFile(des + "/" + j, "/Message.txt");
				FileWriter fw1 = null;
				fw1 = new FileWriter(des + "/" + j + "/Message.txt");
				PrintWriter pw1 = new PrintWriter(fw1);
				pw1.println(date);
				pw1.println(from);
				String to = (String) receivers.dequeue();
				pw1.println(to);
				receivers.enqueue(to);
				pw1.println(subject);
				pw1.println(message);
				pw1.close();
				i++;
				k++;
			}
			k = 0;
			i = 1;

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

	/**
	 * prints the name of recievers in txt file beside the folder containing the
	 * mail.
	 * 
	 * @param receiver
	 * @throws IOException
	 */
	public void setReceivers(LinkedBasedQueue receiver) throws IOException {
		while (!receiver.isEmpty()) {
			receivers.enqueue(receiver.dequeue());
		}
	}

	public void setMessage(String msg) {
		message = msg;
	}
}
