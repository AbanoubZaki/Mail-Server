package MainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataStructures.DoubleLinkedList;
import Interfaces.IFolder;

public class MyFolder implements IFolder {

	/**
	 * temporary file to set MyFolder class to a certain one and apply
	 * operations on it.
	 */
	File f1;

	/**
	 * sets the path of MyFolder to the choosen one.
	 */
	String path;
	/**
	 * sets the name of MyFolder to the choosen one.
	 */
	String name;

	public void set(File f) {
		f1 = f;
		path = f.getPath();
		name = f.getName();
	}

	/**
	 * creates a new folder.
	 *
	 * @param des
	 * @param n
	 */
	public void createFolder(String des, String n) {
		path = des;
		name = n;
		f1 = new File(des + "/" + n);
		System.out.println(f1.mkdirs());
		/**
		 * add the new folder to index file.
		 */
		FileWriter fw1 = null;
		try {
			fw1 = new FileWriter(des + "/Index.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw1 = new PrintWriter(fw1);
		pw1.append(n);
		pw1.println();
		pw1.close();
	}

	public void createFile(String des, String n) throws IOException {
		path = des;
		name = n;
		f1 = new File(des + "/" + n);
		f1.createNewFile();
	}

	public void sendCopy(String file, String to) throws IOException {
		File toBeCopied = new File(file);
		Files.copy(toBeCopied.toPath(),
				new File(to + "/" + toBeCopied.getName()).toPath());
	}

	/**
	 * moves a file into certain destination.
	 *
	 * @param des
	 * @throws IOException
	 */
	public void move(String des) throws IOException {
		path = des;
		String name = f1.getName();
		File f2 = new File(des + "/" + name);
		f1.renameTo(f2);
		/**
		 * write in the destination folder index.
		 */
		FileWriter fw1 = null;
		fw1 = new FileWriter(des + "/Index.txt", true);
		PrintWriter pw1 = new PrintWriter(fw1);
		pw1.append(name);
		pw1.println();
		pw1.close();
		/**
		 * remove the name of the folder from the index of the old destination.
		 */
		DoubleLinkedList list = new DoubleLinkedList();
		list = listInsideTxt(new File(f1.getParent() + "/Index.txt"));
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(name)) {
				list.remove(i);
				break;
			}
		}
		fw1 = new FileWriter(f1.getParent() + "/Index.txt");
		pw1 = new PrintWriter(fw1);
		while (list != null && !list.isEmpty()) {
			pw1.println(list.get(0));
			list.remove(0);
		}
		pw1.close();
	}

	/**
	 * when user chooses to delete some items they are actually moved to the
	 * trash folder.
	 *
	 * @throws IOException
	 */
	public void delTrash() throws IOException {
		File msgtxt = new File(f1.getPath() + "/Message.txt");
		File folder = f1.getParentFile();
		File email = folder.getParentFile();
		/**
		 * read the txt message inside the email folder.
		 */
		DoubleLinkedList msg = new DoubleLinkedList();
		msg = listInsideTxt(msgtxt);
		/**
		 * set the date of sending the email to trash.
		 */
		Date now = new Date();
		String date = new String();
		SimpleDateFormat dateFormatter = new SimpleDateFormat();
		dateFormatter = new SimpleDateFormat("d-M-y");
		date = dateFormatter.format(now).toString();
		msg.add(0, folder.getPath());
		msg.add(0, folder.getName());
		msg.add(0, date);
		/**
		 * write the new date with all the previous contents of the message.
		 */
		FileWriter fw1 = null;
		try {
			fw1 = new FileWriter(msgtxt.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw1 = new PrintWriter(fw1);
		while (msg != null && !msg.isEmpty()) {
			pw1.println((String) msg.get(0));
			msg.remove(0);
		}
		pw1.close();
		/**
		 * finally move the email to trash.
		 */
		File f4 = new File(email.getPath() + "/Trash/" + name);
		f1.renameTo(f4);

		/**
		 * write in the trash folder index.
		 */
		FileWriter fw2 = null;
		fw2 = new FileWriter(email.getPath() + "/Trash" + "/Index.txt", true);
		PrintWriter pw2 = new PrintWriter(fw2);
		pw2.append(name);
		pw2.println();
		pw2.close();
		/**
		 * remove the name of the folder from the index of the old destination.
		 */
		DoubleLinkedList list = new DoubleLinkedList();
		list = listInsideTxt(new File(f1.getParent() + "/Index.txt"));
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(name)) {
				list.remove(i);
				break;
			}
		}
		fw1 = new FileWriter(f1.getParent() + "/Index.txt");
		pw1 = new PrintWriter(fw1);
		while (list != null && !list.isEmpty()) {
			pw1.println(list.get(0));
			list.remove(0);
		}
		pw1.close();
	}

	/**
	 * used to delete the mail permanently from the user's account.
	 */
	public void delPermanent(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				delPermanent(new File(dir, children[i]));
			}
		}
		dir.delete();
		return;
	}

	/**
	 * gets all the folders in a folder.
	 * 
	 * @return
	 */
	public DoubleLinkedList listFolders() {
		DoubleLinkedList folderNames = new DoubleLinkedList();
		for (final File file : f1.listFiles()) {
			if (file.isDirectory()) {
				folderNames.add(file.getName());
			}
		}
		return folderNames;
	}

	/**
	 * gets all the files in a folder.
	 * 
	 * @return
	 */
	public DoubleLinkedList listFiles() {
		DoubleLinkedList folderNames = new DoubleLinkedList();
		for (final File file : f1.listFiles()) {
			folderNames.add(file.getName());
		}
		return folderNames;
	}

	/**
	 * reads index files.
	 */
	public DoubleLinkedList listInsideTxt(File indexTxt) {
		String temp = new String();
		DoubleLinkedList emails = new DoubleLinkedList();
		try {
			BufferedReader in = new BufferedReader(new FileReader(indexTxt));
			while ((temp = in.readLine()) != null) {
				emails.add(temp);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emails;
	}
}