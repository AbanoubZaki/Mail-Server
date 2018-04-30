package MainClasses;

import java.io.File;
import java.io.IOException;

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
		f1.mkdirs();
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
		f1.renameTo(f2);
	}

	/**
	 * when user chosses to delete some items they are actually moved to the
	 * trash folder.
	 */
	public void delTrash() {
		File f2 = f1.getParentFile();
		File f3 = f2.getParentFile();
		File f4 = new File(f3.getPath() + "/Trash/" + name);
		System.out.println(f4.getPath());
		f1.renameTo(f4);

	}

	/**
	 * used only in a thread to delete the objects permanently from the user's
	 * account.
	 */
	public void delPermanent() {
		f1.delete();
	}
}
