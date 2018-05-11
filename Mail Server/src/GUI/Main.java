package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import DataStructures.DoubleLinkedList;
import MainClasses.MyFolder;

import java.awt.Color;

public class Main {

	private JFrame frmProfile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main("                 ");
					window.frmProfile.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main(String path) {
		initialize(path);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String path) {
		checkTrash(path);
		frmProfile = new JFrame();
		frmProfile.setResizable(false);
		frmProfile.setTitle("Profile");
		frmProfile.setBounds(10, 10, 304, 240);
		frmProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frmProfile.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		frmProfile.setVisible(true);
		frmProfile.setLocationRelativeTo(null);
		JButton btnDrafts = new JButton("Drafts");
		btnDrafts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDrafts.setBounds(16, 153, 117, 29);
		panel.add(btnDrafts);

		JLabel lblWelcome = new JLabel("Welcome To Our Mail Server");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(57, 5, 182, 16);
		panel.add(lblWelcome);

		JButton btnInbox = new JButton("Inbox");
		btnInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProfile.dispose();
				new Inbox(path);
			}
		});
		btnInbox.setBounds(16, 123, 117, 29);
		panel.add(btnInbox);

		JButton btnSentMails = new JButton("Sent Mails");
		btnSentMails.setBounds(162, 123, 117, 29);
		panel.add(btnSentMails);

		JButton btnStarred = new JButton("Starred");
		btnStarred.setBounds(16, 183, 117, 29);
		panel.add(btnStarred);

		JButton btnTrash = new JButton("Trash");
		btnTrash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Trash(path);
			}
		});
		btnTrash.setBounds(162, 183, 117, 29);
		panel.add(btnTrash);

		JLabel lblNewLabel = new JLabel(path.substring(21, path.length() - 1));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(16, 33, 263, 16);
		panel.add(lblNewLabel);

		JButton btnContacts = new JButton("Contacts");
		btnContacts.setBounds(162, 153, 117, 29);
		panel.add(btnContacts);

		JButton btnNewButton = new JButton("Compose");
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.setForeground(new Color(128, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton.setBounds(16, 82, 263, 29);
		panel.add(btnNewButton);
	}

	void checkTrash(String path) {
		/**
		 * deleting the 30 passed trashed emails.
		 */
		File trash = new File(path + "Trash");
		MyFolder getFolders = new MyFolder();
		getFolders.set(trash);
		DoubleLinkedList names = new DoubleLinkedList();
		names = getFolders.listFolders();
		while (!names.isEmpty()) {
			File msg = new File(trash.getPath() + "/" + (String) names.get(0)
					+ "/" + "Message.txt");
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(msg));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String date1 = new String();
			String date2 = new String();
			try {
				date1 = in.readLine();
				names.remove(0);
				Date now = new Date();
				SimpleDateFormat dateFormatter = new SimpleDateFormat("d-M-y");
				date2 = dateFormatter.format(now).toString();
				SimpleDateFormat sdf = new SimpleDateFormat("d-M-y",
						Locale.ENGLISH);
				Date firstDate = sdf.parse(date2);
				Date secondDate = sdf.parse(date1);

				long diffInMillies = Math
						.abs(secondDate.getTime() - firstDate.getTime());
				long diff = TimeUnit.DAYS.convert(diffInMillies,
						TimeUnit.MILLISECONDS);
				System.out.println(diff);
				if (diff >= 30) {
					File mail = new File(msg.getParent());
					MyFolder del = new MyFolder();
					del.delPermanent(mail);
				}

			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
