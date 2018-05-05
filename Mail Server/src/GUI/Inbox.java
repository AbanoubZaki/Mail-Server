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
import javax.swing.JTextField;

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
	private JTextField searchField;
	private JTextField filterField;
	@SuppressWarnings("rawtypes")
	private JComboBox filterBox;
	@SuppressWarnings("rawtypes")
	private JComboBox sortBox;
	@SuppressWarnings("rawtypes")
	private JComboBox moveBox;
	/**
	 * Create the application.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Inbox(String path) {
		frame = new JFrame("Inbox");
		frame.setBounds(5, 30, 510, 354);
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
		list.setBounds(5, 60, 500, 270);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(5, 60, 500, 270);
		frame.getContentPane().add(scrollPane);
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshList(path);
			}
		});
		btnNewButton.setBounds(0, 6, 74, 29);
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
		btnDelete.setBounds(0, 31, 74, 29);
		frame.getContentPane().add(btnDelete);

		moveBox = new JComboBox();
		moveBox.setModel(
				new DefaultComboBoxModel(new String[] {"Select Option", "Drafts", "Starred"}));
		moveBox.setBounds(363, 7, 141, 27);
		frame.getContentPane().add(moveBox);

		JLabel lblMoveTo = new JLabel("Move To :");
		lblMoveTo.setBounds(290, 11, 61, 16);
		frame.getContentPane().add(lblMoveTo);
		
		JLabel lblSearch = new JLabel("Search :");
		lblSearch.setBounds(71, 11, 61, 16);
		frame.getContentPane().add(lblSearch);
		
		searchField = new JTextField();
		searchField.setBounds(124, 5, 154, 28);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		JLabel lblSortBy = new JLabel("Sort By :");
		lblSortBy.setBounds(71, 36, 61, 16);
		frame.getContentPane().add(lblSortBy);
		
		sortBox = new JComboBox();
		sortBox.setModel(new DefaultComboBoxModel(new String[] {"Select"}));
		sortBox.setBounds(124, 32, 90, 27);
		frame.getContentPane().add(sortBox);
		
		JLabel lblNewLabel = new JLabel("Filter By :");
		lblNewLabel.setBounds(215, 36, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		filterBox = new JComboBox();
		filterBox.setModel(new DefaultComboBoxModel(new String[] {"Select"}));
		filterBox.setBounds(274, 32, 90, 27);
		frame.getContentPane().add(filterBox);
		
		filterField = new JTextField();
		filterField.setBounds(363, 30, 141, 28);
		frame.getContentPane().add(filterField);
		filterField.setColumns(10);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@SuppressWarnings({ "unchecked" })
	void refreshList(String path) {
		searchField.setText("");
		filterField.setText("");
		moveBox.setSelectedIndex(0);
		sortBox.setSelectedIndex(0);
		filterBox.setSelectedIndex(0);
		String[] s = new File(path + "Inbox").list();
		dls = new DefaultListModel<String>();
		for (int j = 0; j < s.length; j++) {
			dls.addElement(s[j]);
		}
		list.setModel(dls);
	}
}
