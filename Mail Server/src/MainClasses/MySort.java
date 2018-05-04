package MainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import DataStructures.DoubleLinkedList;
import Interfaces.ISort;

public class MySort implements ISort {
	/**
	 * sorts by priority.
	 * 
	 * @param directory
	 * @throws IOException
	 */
	public void sortPriority(File directory) throws IOException {
		MyFolder myfolder = new MyFolder();
		myfolder.set(directory);
		DoubleLinkedList mails = new DoubleLinkedList();
		mails = myfolder.listFilesForFolder();
		MyPriorityQueue q = new MyPriorityQueue();
		int priority = new Integer(3);
		while (!mails.isEmpty()) {
			File mail = new File(directory.getPath() + "/" + mails.get(0));
			File txt = new File(directory.getPath() + "/" + mails.get(0) + "/"
					+ "Message.txt");
			/**
			 * read the mail folder (txt + attachments).
			 */
			DoubleLinkedList insideMail = new DoubleLinkedList();
			myfolder.set(mail);
			insideMail = myfolder.listFilesForFolder();
			if (insideMail.size > 1) {
				priority--;
			}
			/**
			 * read the txt file.
			 */
			DoubleLinkedList insideTxt = new DoubleLinkedList();
			BufferedReader in = new BufferedReader(new FileReader(txt));
			String temp = new String();
			while ((temp = in.readLine()) != null) {
				insideTxt.add(temp);
			}
			in.close();
			if (insideTxt.size > 6) {
				priority--;
			}
			q.insert(mail, priority);
		}

	}
}