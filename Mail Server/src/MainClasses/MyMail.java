package MainClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataStructures.DoubleLinkedList;
import DataStructures.LinkedBasedQueue;
import DataStructures.SingleLinkedList;
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
	SingleLinkedList attachments = new SingleLinkedList();
	MyFolder access = new MyFolder();

	/**
	 * emails of the receivers.
	 */
	LinkedBasedQueue receivers = new LinkedBasedQueue();
	/**
	 * a folder that concatinates all components of the message.
	 */
	MyFolder totalMsg = new MyFolder();

	public MyMail(String sender, LinkedBasedQueue receiver, String sub,
			String msg, SingleLinkedList attach) throws IOException {
		from = sender;
		setReceivers(receiver);
		priority = 4;
		subject = sub;
		message = msg;
		attachments = attach;
	}

	/**
	 * prints the name of recievers in txt file beside the folder containing the
	 * mail.
	 * 
	 * @param receiver
	 * @throws IOException
	 */
	public void setReceivers(LinkedBasedQueue receiver) throws IOException {
		while (receiver != null && !receiver.isEmpty()) {
			receivers.enqueue(receiver.dequeue());
		}
	}

	/**
	 * sends the written email to drafts only if the recievers exist.
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean sendDraft() throws IOException {
		/**
		 * set now time.
		 */
		Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat();
		dateFormatter = new SimpleDateFormat("d-M-y");
		date = dateFormatter.format(now).toString();

		/**
		 * put a single folder in the draft folder with a message.txt file.
		 */
		if (receivers.isEmpty()) {
			String Drafts = "../Mail Server/Users/" + from + "/Drafts";
			SimpleDateFormat dateFormatter1 = new SimpleDateFormat("h-m-s a");
			time = dateFormatter1.format(now).toString();

			totalMsg.createFolder(Drafts, "null " + time);
			totalMsg.createFile(Drafts + "/null " + time, "/Message.txt");
			totalMsg.createFile(Drafts + "/null " + time, "Index.txt");
			/**
			 * write the attachments in the index file of the mail in drafts.
			 */
			FileWriter fw3 = new FileWriter("../Mail Server/Users/" + from
					+ "/Drafts/null " + time + "/Index.txt");
			PrintWriter pw3 = new PrintWriter(fw3);

			while (attachments != null && !attachments.isEmpty()) {
				access.sendCopy((String) (attachments.get(0)),
						Drafts + "/null" + " " + time);
				pw3.println((String) (attachments.get(0)));
				attachments.remove(0);
			}
			pw3.close();

			/**
			 * write the message in a txt file.
			 */
			FileWriter fw1 = null;
			fw1 = new FileWriter(Drafts + "/null " + time + "/Message.txt");
			PrintWriter pw1 = new PrintWriter(fw1);
			pw1.println(date);
			pw1.println(time);
			pw1.println(from);
			pw1.println();
			pw1.println(subject);
			pw1.println(message);
			pw1.close();
		}

		/**
		 * read all users that already have accounts
		 */
		int k = 0;
		while (k < receivers.size()) {
			String Drafts = "../Mail Server/Users/" + from + "/Drafts";
			SimpleDateFormat dateFormatter1 = new SimpleDateFormat("h-m-s a");
			time = dateFormatter1.format(now).toString();
			String to = (String) receivers.dequeue();
			receivers.enqueue(to);

			totalMsg.createFolder(Drafts, to + " " + time);
			totalMsg.createFile(Drafts + "/" + to + " " + time, "/Message.txt");

			totalMsg.createFile(Drafts + "/" + to + " " + time, "Index.txt");
			/**
			 * write the attachments in the index file of the mail in drafts.
			 */
			FileWriter fw3 = new FileWriter(
					Drafts + "/" + to + " " + time + "/Index.txt");
			PrintWriter pw3 = new PrintWriter(fw3);

			/**
			 * add attachments to the mail in the server.
			 */
			while (attachments != null && !attachments.isEmpty()) {
				access.sendCopy((String) (attachments.get(0)),
						Drafts + "/" + to + " " + time);
				pw3.println((String) (attachments.get(0)));
				attachments.remove(0);
			}
			pw3.close();
			FileWriter fw1 = null;
			fw1 = new FileWriter(
					Drafts + "/" + to + " " + time + "/Message.txt");
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
		return true;

	}

	public boolean sendMsg() throws IOException {
		/**
		 * 
		 */
		if (from == null || subject == null || receivers.size() == 0
				|| receivers == null) {
			return false;
		}
		/**
		 * set now time.
		 */
		Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat();
		dateFormatter = new SimpleDateFormat("d-M-y");
		date = dateFormatter.format(now).toString();
		/**
		 * find the directory of the email of the user the get all his
		 * usernames.
		 */
		int k = 0;
		/**
		 * read all users that already have accounts
		 */
		File index1 = new File("../Mail Server/Users/Index.txt");
		DoubleLinkedList list = new DoubleLinkedList();
		list = access.listInsideTxt(index1);
		MyContact add = new MyContact("", "", "");
		while (k < receivers.size()) {
			String sentMails = "../Mail Server/Users/" + from + "/"
					+ "/Sent Mails";
			SimpleDateFormat dateFormatter1 = new SimpleDateFormat("h-m-s a");
			time = dateFormatter1.format(now).toString();
			String to = (String) receivers.dequeue();
			String inbox = "../Mail Server/Users/" + to + "/" + "/Inbox";
			receivers.enqueue(to);

			/**
			 * check if recievers have accounts or fake ones.
			 */
			if (!list.contains(to)) {
				return false;
			}
			access.createFolder(inbox, from + " " + time);

			/**
			 * add the recievers to contacts of the sender.
			 */
			list = access.listInsideTxt(
					new File("../Mail Server/Users/" + to + "/Info.txt"));
			add.addContact((String) (list.get(0)), to, from);

			totalMsg.createFolder(sentMails, to + " " + time);
			totalMsg.createFile(sentMails + "/" + to + " " + time,
					"/Message.txt");
			totalMsg.createFile(sentMails + "/" + to + " " + time, "Index.txt");

			/**
			 * write the attachments in the index file of the mail in drafts.
			 */
			FileWriter fw3 = new FileWriter(
					sentMails + "/" + to + " " + time + "/Index.txt");
			PrintWriter pw3 = new PrintWriter(fw3);

			/**
			 * add attachments to the mail in the server.
			 */
			while (attachments != null && !attachments.isEmpty()) {
				access.sendCopy((String) (attachments.get(0)),
						sentMails + "/" + to + " " + time);
				access.sendCopy((String) (attachments.get(0)),
						inbox + "/" + from + " " + time);
				pw3.println((String) (attachments.get(0)));
				attachments.remove(0);
			}
			pw3.close();
			FileWriter fw1 = null;
			fw1 = new FileWriter(
					sentMails + "/" + to + " " + time + "/Message.txt");
			PrintWriter pw1 = new PrintWriter(fw1);
			pw1.println(date);
			pw1.println(time);
			pw1.println(from);
			pw1.println(to);
			pw1.println(subject);
			pw1.println(message);
			pw1.close();
			access.sendCopy(sentMails + "/" + to + " " + time + "/Message.txt",
					inbox + "/" + from + " " + time);
			access.sendCopy(sentMails + "/" + to + " " + time + "/Index.txt",
					inbox + "/" + from + " " + time);
			k++;
		}
		return true;
	}
}
