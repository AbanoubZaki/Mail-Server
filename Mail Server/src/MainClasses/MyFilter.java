package MainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import DataStructures.DoubleLinkedList;
import Interfaces.IFilter;

public class MyFilter implements IFilter {
	/**
	 * Results of the search.
	 */
	DoubleLinkedList objects = new DoubleLinkedList();

	/**
	 * Mails in the durectory.
	 */
	DoubleLinkedList mails = new DoubleLinkedList();

	/**
	 * used to access the methods concerning dealing with files/folders.
	 */
	MyFolder access = new MyFolder();
	/**
	 * kind of the filter.
	 */
	String kindOfFilter = new String();
	/**
	 * directory to be filtered.
	 */
	String folderPath = new String();
	/**
	 * the word that the user searches with.
	 */
	String parameter = new String();
	/**
	 * takes a directory and a filter returns the mails filterd.
	 * 
	 * @param dir
	 * @param filter
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	public MyFilter(String path, String filter, String word)
			throws IOException, ParseException {
		folderPath = path;
		kindOfFilter = filter;
		parameter = word;
		switch (filter) {
		case "Subject":
			bySubject(word);
			break;
		case "Sender":
			bySender(word);
			break;
		case "Reciever":
			byReciever(word);
			break;
		case "Date":
			byDate(word);
			break;
		case "Body":
			byBody(word);
			break;
		default:
			break;
		}
	}

	public void setDirectory(String path) {
		folderPath = path;
	}
	/**
	 * filters by subhect.
	 * 
	 * @param email
	 * @param directory
	 * @param sub
	 * @return
	 * @throws IOException
	 */
	public DoubleLinkedList bySubject(String sub)
			throws IOException {
		File path = new File(folderPath);
		access.set(path);
		mails = access.listFolders();
		while (mails != null && !mails.isEmpty()) {
			File txt = new File(
					path.getPath() + "/" + mails.get(0) + "/Message.txt");
			BufferedReader in = new BufferedReader(new FileReader(txt));
			String temp;
			temp = in.readLine();
			temp = in.readLine();
			temp = in.readLine();
			temp = in.readLine();
			temp = in.readLine();
			if (temp.equals(sub)) {
				objects.add(txt.getParent());
			}
			in.close();
			mails.remove(0);
		}
		return objects;
	}

	/**
	 * filters by sender.
	 * 
	 * @param directory
	 * @param from
	 * @return
	 * @throws IOException
	 */
	public DoubleLinkedList bySender(String from)
			throws IOException {
		File path = new File(folderPath);
		access.set(path);
		mails = access.listFolders();
		while (mails != null && !mails.isEmpty()) {
			File txt = new File(
					path.getPath() + "/" + mails.get(0) + "/Message.txt");
			BufferedReader in = new BufferedReader(new FileReader(txt));
			String temp;
			temp = in.readLine();
			temp = in.readLine();
			temp = in.readLine();
			if (temp.equals(from)) {
				objects.add(txt.getParent());
			}
			in.close();
			mails.remove(0);
		}
		return objects;
	}

	/**
	 * filters by date.
	 * 
	 * @param directory
	 * @param date
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public DoubleLinkedList byDate(String date) throws IOException, ParseException {
		MySort sort = new MySort("Date");
		File index = new File(folderPath + "/Index.txt");
		access.listInsideTxt(index);
		mails = access.listFolders();
		DoubleLinkedList sortedByDates = new DoubleLinkedList();
		String[] array = new String[sortedByDates.size];
		DoubleLinkedList list = new DoubleLinkedList();
		sortedByDates = sort.sorted;
		HashMap<String, String> map = new HashMap<>();
		int i = 0;
		while (sortedByDates != null && !sortedByDates.isEmpty()) {
			File txt = new File(sortedByDates.get(0) + "/Message.txt");
			list = access.listInsideTxt(txt);
			/**
			 * put dates in an array.
			 */
			array[i] = (String) list.get(0);
			/**
			 * links dates with email paths.
			 */
			map.put((String) list.get(0), (String) sortedByDates.get(0));
			sortedByDates.remove(0);
			i++;
		}
		/**
		 * fadel binary search using stacks only.
		 */

		return objects;
		// return objects;
	}

	public DoubleLinkedList byReciever(String emailOfReciever)
			throws IOException {
		File path = new File(folderPath);
		access.set(path);
		mails = access.listFolders();
		while (mails != null && !mails.isEmpty()) {
			File txt = new File(
					path.getPath() + "/" + mails.get(0) + "/Message.txt");
			BufferedReader in = new BufferedReader(new FileReader(txt));
			DoubleLinkedList list = access.listInsideTxt(txt);
			String reciever = (String) list.get(3);
			if (reciever.equals(emailOfReciever)) {
				objects.add(txt.getParent());
			}
			in.close();
			mails.remove(0);
		}
		return objects;
	}

	public DoubleLinkedList byBody(String sentence)
			throws FileNotFoundException {
		File index = new File(folderPath + "/Index.txt");
		mails = access.listInsideTxt(index);
		while (mails != null && !mails.isEmpty()) {
			File txt = new File(
					folderPath + "/" + mails.get(0) + "/Message.txt");
			DoubleLinkedList list = access.listInsideTxt(txt);
			for (int i = 5; i < list.size; i++) {
				String line = (String) list.get(i);
				if (line.contains(sentence)) {
					objects.add(txt.getParent());
					break;
				}
			}
			mails.remove(0);
		}
		return objects;
	}

}
