package MainClasses;
 
import java.io.BufferedReader;
import java.io.File;
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
    }
 
    public void createFile(String des, String n) throws IOException {
        path = des;
        name = n;
        f1 = new File(des + "/" + n);
        f1.createNewFile();
    }
    
    public void sendCopy(String des) {
    	try {
			Files.copy(f1.toPath(), new File(des + "/" +f1.getName()).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
        BufferedReader in = null;
        in = new BufferedReader(new FileReader(msgtxt));
        DoubleLinkedList msg = new DoubleLinkedList();
        String temp = new String();
        while ((temp = in.readLine()) != null) {
            msg.add(temp);
        }
        in.close();
        /**
         * set the date of sending the email to trash.
         */
        Date now = new Date();
        String date = new String();
        SimpleDateFormat dateFormatter = new SimpleDateFormat();
        dateFormatter = new SimpleDateFormat("d-M-y");
        date = dateFormatter.format(now).toString();
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
        while (!msg.isEmpty()) {
            pw1.println((String) msg.get(0));
            msg.remove(0);
        }
        pw1.close();
        /**
         * finally move the email to trash.
         */
        File f4 = new File(email.getPath() + "/Trash/" + name);
        f1.renameTo(f4);
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
	 * gets all the mails in a folder.
	 * 
	 * @return
	 */
	public DoubleLinkedList listFilesForFolder() {
		DoubleLinkedList folderNames = new DoubleLinkedList();
		for (final File file : f1.listFiles()) {
			if (file.isDirectory()) {
				folderNames.add(file.getName());
			}
		}
		return folderNames;
	}
}