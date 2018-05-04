package MainClasses;

import java.io.File;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		File f3 = new File("../Mail Server/Users/a/Sent Mails/1");
		MyFolder del = new MyFolder();
		del.set(f3);
		try {
			del.delTrash();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
