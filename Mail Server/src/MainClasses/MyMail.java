package MainClasses;

import java.io.IOException;

import DataStructures.DoubleLinkedList;
import DataStructures.LinkedBasedQueue;
import Interfaces.IMail;

public class MyMail implements IMail {
	int priority;
	String from;
	String subject;
	MyFolder message;
	String time;
	DoubleLinkedList attachments;
	LinkedBasedQueue receivers;
	MyFolder totalMsg = new MyFolder();
	public MyMail() {
		String des = "../Mail Server/Users/" + from + "/Sent Mails";
		totalMsg.createFolder(des, time);
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
		String des = "../Mail Server/Users/" + from + "/Sent Mails";
		receivers.equals(receiver);
		MyFolder txt = new MyFolder();
		txt.createFile(des, totalMsg.name + ".txt");
	}

	public void setMessage(MyFolder msg) throws IOException {
		message.equals(msg);
		message.createFile(totalMsg.path, subject + ".txt");
 	}
}
