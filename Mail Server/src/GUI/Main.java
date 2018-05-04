package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		frmProfile = new JFrame();
		frmProfile.setResizable(false);
		frmProfile.setTitle("Profile");
		frmProfile.setBounds(10, 10, 304, 219);
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
		btnDrafts.setBounds(16, 100, 117, 29);
		panel.add(btnDrafts);

		JLabel lblWelcome = new JLabel("Welcome To Our Mail Server");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(57, 5, 182, 16);
		panel.add(lblWelcome);

		JButton btnInbox = new JButton("Inbox");
		btnInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inbox(path);
			}
		});
		btnInbox.setBounds(16, 60, 117, 29);
		panel.add(btnInbox);

		JButton btnSentMails = new JButton("Sent Mails");
		btnSentMails.setBounds(162, 60, 117, 29);
		panel.add(btnSentMails);

		JButton btnStarred = new JButton("Starred");
		btnStarred.setBounds(16, 140, 117, 29);
		panel.add(btnStarred);

		JButton btnTrash = new JButton("Trash");
		btnTrash.setBounds(162, 140, 117, 29);
		panel.add(btnTrash);

		JLabel lblNewLabel = new JLabel(path.substring(21, path.length() - 1));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(16, 33, 263, 16);
		panel.add(lblNewLabel);
		
		JButton btnContacts = new JButton("Contacts");
		btnContacts.setBounds(162, 100, 117, 29);
		panel.add(btnContacts);
	}
}
