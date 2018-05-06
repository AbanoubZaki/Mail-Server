package MainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import DataStructures.DoubleLinkedList;
import DataStructures.MyPriorityQueue;
import Interfaces.ISort;

public class MySort implements ISort {
	/**
	 * sorts by priority.
	 * 
	 * @param directory
	 * @throws IOException
	 */
	public MyPriorityQueue sortPriority(File directory) throws IOException {
		MyFolder myfolder = new MyFolder();
		myfolder.set(directory);
		DoubleLinkedList mails = new DoubleLinkedList();
		mails = myfolder.listFilesForFolder();
		MyPriorityQueue q = new MyPriorityQueue();
		while (!mails.isEmpty()) {
			int priority = new Integer(4);
			File mail = new File(directory.getPath() + "/" + mails.get(0));
			File txt = new File(directory.getPath() + "/" + mails.get(0) + "/"
					+ "Message.txt");
			mails.remove(0);
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
		return q;
	}

	/**
	 * sorts by date.
	 * 
	 * @param directory
	 * @return
	 * @throws IOException
	 */
	public DoubleLinkedList sortDate(File directory) throws IOException {
		MyFolder myfolder = new MyFolder();
		myfolder.set(directory);
		DoubleLinkedList mails = new DoubleLinkedList();
		mails = myfolder.listFilesForFolder();
		DoubleLinkedList list = new DoubleLinkedList();
		int i = 0;
		while (i < mails.size - 1) {
			File txt = new File(directory.getPath() + "/" + mails.get(i) + "/"
					+ "Message.txt");
			/**
			 * read the txt file.
			 */
			
			BufferedReader in = null;
			in = new BufferedReader(new FileReader(txt));
			String date1 = new String("");
			date1 += in.readLine();
			date1 += in.readLine();
			in.close();

		}
		return list;

	}
}