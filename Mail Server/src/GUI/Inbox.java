package GUI;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import MainClasses.MyFolder;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

public class Inbox {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inbox window = new Inbox("                       ");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("rawtypes")
	JList list;
	DefaultListModel<String> dls;

	/**
	 * Create the application.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Inbox(String path) {
		frame = new JFrame("Inbox");
		frame.setBounds(5, 30, 510, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String[] s = new File(path + "Inbox").list();
		dls = new DefaultListModel<String>();
		for (int j = 0; j < s.length; j++) {
			dls.addElement(s[j]);
		}
		list = new JList(dls);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setValueIsAdjusting(true);
		list.setSelectedIndex(0);
		list.setBounds(5, 30, 500, 300);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(5, 36, 500, 300);
		frame.getContentPane().add(scrollPane);
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshList(path);
			}
		});
		btnNewButton.setBounds(0, 6, 117, 29);
		frame.getContentPane().add(btnNewButton);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toBeDeleted = (String) list.getSelectedValue();
				if (toBeDeleted != null) {
					MyFolder mf = new MyFolder();
					mf.set(new File(path + "Inbox/" + toBeDeleted));
					try {
						mf.delTrash();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					refreshList(path);
				}
			}
		});
		btnDelete.setBounds(110, 6, 117, 29);
		frame.getContentPane().add(btnDelete);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Drafts", "Starred"}));
		comboBox.setBounds(414, 7, 90, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblMoveTo = new JLabel("Move To :");
		lblMoveTo.setBounds(355, 11, 61, 16);
		frame.getContentPane().add(lblMoveTo);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@SuppressWarnings({ "unchecked" })
	void refreshList(String path) {
		String[] s = new File(path + "/Inbox").list();
		dls = new DefaultListModel<String>();
		for (int j = 0; j < s.length; j++) {
			dls.addElement(s[j]);
		}
		list.setModel(dls);
	}
}
