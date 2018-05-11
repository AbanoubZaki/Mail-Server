package MainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import DataStructures.DoubleLinkedList;
import DataStructures.MyPriorityQueue;
import DataStructures.Stacks;
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
		mails = myfolder.listFolders();
		MyPriorityQueue q = new MyPriorityQueue();
		while (mails != null && !mails.isEmpty()) {
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
			insideMail = myfolder.listFiles();
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
	 * @throws ParseException
	 */
	public DoubleLinkedList sortDate(File directory)
			throws IOException, ParseException {
		MyFolder myfolder = new MyFolder();
		myfolder.set(directory);
		DoubleLinkedList mails = new DoubleLinkedList();
		DoubleLinkedList sortedMails = new DoubleLinkedList();
		mails = myfolder.listFolders();
		int i = 0;
		Date[] dates = new Date[mails.size];
		HashMap<Date, String> mailsDates = new HashMap<>();
		/**
		 * take all dates of all mails inside the directorty saving them in an
		 * array.
		 */
		while (i < mails.size) {
			File txt = new File(directory.getPath() + "/" + mails.get(i) + "/"
					+ "Message.txt");
			/**
			 * read the txt file.
			 */
			BufferedReader in = null;
			in = new BufferedReader(new FileReader(txt));
			String date = new String("");
			date += in.readLine();
			date += " " + in.readLine();
			SimpleDateFormat sdf = new SimpleDateFormat("d-M-y h-m-s a");
			Date totalDate = sdf.parse(date);
			dates[i] = totalDate;
			mailsDates.put(totalDate, txt.getParent());
			in.close();
			i++;
		}
		iterativeQsort(dates);
		i = 0;
		while (i < dates.length) {
			sortedMails.add(mailsDates.get(dates[i]));
			i++;
		}
		return sortedMails;
	}

	/**
	 * iterative implementation of quicksort sorting algorithm.
	 */
	public static void iterativeQsort(Date[] dates) {
		Stacks stack = new Stacks();
		stack.push(0);
		stack.push(dates.length);
		while (stack != null && !stack.isEmpty()) {
			int end = (int) stack.pop();
			int start = (int) stack.pop();
			if (end - start < 2) {
				continue;
			}
			int p = start + ((end - start) / 2);
			p = partition(dates, p, start, end);
			stack.push(p + 1);
			stack.push(end);
			stack.push(start);
			stack.push(p);
		}
	}

	/**
	 * Utility method to partition the array into smaller array, and comparing
	 * numbers to rearrange them as per quicksort algorithm.
	 * 
	 * @param input
	 * @param position
	 * @param start
	 * @param end
	 * @return
	 */
	private static int partition(Date[] input, int position, int start,
			int end) {
		int l = start;
		int h = end - 2;
		Date piv = input[position];
		swap(input, position, end - 1);
		while (l < h) {
			if (input[l].compareTo(piv) < 0) {
				l++;
			} else if (!(input[h].compareTo(piv) < 0)) {
				h--;
			} else {
				swap(input, l, h);
			}
		}
		int idx = h;
		if (input[h].compareTo(piv) < 0) {
			idx++;
		}
		swap(input, end - 1, idx);
		return idx;
	}

	/**
	 * Utility method to swap two numbers in given array.
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void swap(Date[] arr, int i, int j) {
		Date temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}