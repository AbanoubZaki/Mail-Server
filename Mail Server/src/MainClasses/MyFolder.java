package MainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
	}

	public void createFile(String des, String n) throws IOException {
		path = des;
		name = n;
		f1 = new File(des + "/" + n);
		f1.createNewFile();
	}

	/**
	 * moves a file into certain destination.
	 * 
	 * @param des
	 */
	public void move(String des) {
		path = des;
		String name = f1.getName();
		File f2 = new File(des + "/" + name);
//		f1.renameTo(f2); not effective .
		try {
			Files.move(f1.toPath(), f2.toPath(), StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * when user chooses to delete some items they are actually moved to the
	 * trash folder.
	 * 
	 * @throws IOException
	 */
	public void delTrash() throws IOException {
		File message = new File(f1.getPath() + "/Message.txt");// message
		File inbox = f1.getParentFile();// inbox
		File f3 = inbox.getParentFile();// user
		String Email = f3.getName();// user as a string

		BufferedReader in = null;
		in = new BufferedReader(new FileReader(message));
		DoubleLinkedList msg = new DoubleLinkedList();
		String temp = new String();
		while ((temp = in.readLine()) != null) {
			msg.add(temp);
		}
		in.close();
		Date now = new Date();
		String date = new String();
		SimpleDateFormat dateFormatter = new SimpleDateFormat();
		dateFormatter = new SimpleDateFormat("d-M-y");
		date = dateFormatter.format(now).toString();
		msg.add(0, date);
		FileWriter fw1 = null;
		try {
			fw1 = new FileWriter(f1.getPath() + "/Message.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw1 = new PrintWriter(fw1);
		while (!msg.isEmpty()) {
			pw1.append((String) msg.get(0));
			msg.remove(0);
		}

		File f4 = new File(f3.getPath() + "/Trash/" + name);
		System.out.println(f4.getPath());
		f1.renameTo(f4);
		pw1.close();
		fw1.close();
	}

	/**
	 * used only in a thread to delete the objects permanently from the user's
	 * account.
	 */
	public void delPermanent() {
		f1.delete();
	}
}
