package GUI;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import DataStructures.DoubleLinkedList;
import MainClasses.MyFolder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Trash {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trash window = new Trash("                       ");
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
	public Trash(String path) {
		frame = new JFrame("Trash");
		frame.setBounds(5, 30, 510, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String[] s = new File(path + "Trash").list();
		dls = new DefaultListModel<String>();
		for (int j = 0; j < s.length; j++) {
			dls.addElement(s[j]);
		}
		list = new JList(dls);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setValueIsAdjusting(true);
		list.setSelectedIndex(0);
		list.setBounds(5, 30, 500, 270);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(5, 30, 500, 270);
		frame.getContentPane().add(scrollPane);

		JButton btnNewButton = new JButton("Restore");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scanner sc = null;
				try {
					sc = new Scanner(new FileReader(new File(path + "Trash/"
							+ list.getSelectedValue() + "/message.txt")));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sc.nextLine();
				String s = null;
				s = sc.nextLine();
				DoubleLinkedList dl = new DoubleLinkedList();
				while (sc.hasNextLine()) {
					dl.add(sc.nextLine());
				}
				sc.close();
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new File(path + "Trash/"
							+ list.getSelectedValue() + "/message.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pw.print("");
				pw.close();
				FileWriter fw = null;
				try {
					fw = new FileWriter(new File(path + "Trash/"
							+ list.getSelectedValue() + "/message.txt"), true);
					pw = new PrintWriter(new BufferedWriter(fw));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while (!dl.isEmpty()) {
					pw.println(dl.get(0));
					dl.remove(0);
				}
				pw.close();
				try {
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MyFolder mf = new MyFolder();
				mf.set(new File(path + "Trash/" + list.getSelectedValue()));
				try {
					mf.move(path + s);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshList(path);
			}
		});
		btnNewButton.setBounds(5, 0, 499, 29);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@SuppressWarnings({ "unchecked" })
	void refreshList(String path) {
		String[] s = new File(path + "Trash").list();
		dls = new DefaultListModel<String>();
		for (int j = 0; j < s.length; j++) {
			dls.addElement(s[j]);
		}
		list.setModel(dls);
	}
}
