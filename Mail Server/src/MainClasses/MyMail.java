package MainClasses;

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
		SimpleDateFormat dateFormatter = new SimpleDateFormat();
		dateFormatter = new SimpleDateFormat("d-M-y");
		date = dateFormatter.format(now).toString();
		from = sender;
		setReceivers(receiver);
		priority = 3;
		subject = sub;
		message = msg;
		attachments.equals(attach);
		/**
		 * find the directory of the email of the user the get all his
		 * usernames.
		 */
		int k = 0;
		while (k < receivers.size()) {
			String des = "../Mail Server/Users/" + from + "/" + "/Sent Mails";
			SimpleDateFormat dateFormatter1 = new SimpleDateFormat(
					"h-m-s a");
			time = dateFormatter1.format(now).toString();
			String to = (String) receivers.dequeue();
			receivers.enqueue(to);
			totalMsg.createFolder(des, to + " " + time);
			totalMsg.createFile(des + "/" + to + " " + time, "/Message.txt");
			FileWriter fw1 = null;
			fw1 = new FileWriter(des + "/" + to + " " + time + "/Message.txt");
			PrintWriter pw1 = new PrintWriter(fw1);
			
			pw1.println(date);
			pw1.println(time);
			pw1.println(from);
			pw1.println(to);
			pw1.println(subject);
			pw1.println(message);
			pw1.close();
			k++;
		}
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
}
