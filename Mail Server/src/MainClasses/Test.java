package MainClasses;

import java.io.File;

public class Test {

	public MyContact contact = new MyContact("bebo", "bebo@aka.com", "123");
	public static void main(String[] args) {
		File f = new File("/Users/AmrGeneidy/Desktop/download.jpeg");
		MyFolder mf = new MyFolder();
		mf.set(f);
		mf.sendCopy("/Users/AmrGeneidy/Desktop/Desk");
	}
}
