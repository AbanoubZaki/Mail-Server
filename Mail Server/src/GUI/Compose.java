package GUI;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Compose {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compose window = new Compose("                       ");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	DefaultListModel<String> dls;
	private JTextField toField;
	private JTextField fromField;
	private JLabel lblFrom;
	private JTextField subjectField;
	private JLabel lblSubject;
	private JTextField textField;
	/**
	 * Create the application.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Compose(String path) {
		frame = new JFrame("Inbox");
		frame.setBounds(5, 30, 510, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String[] s = new File(path + "Inbox").list();
		dls = new DefaultListModel<String>();
		for (int j = 0; j < s.length; j++) {
			dls.addElement(s[j]);
		}
		
		toField = new JTextField();
		toField.setBounds(76, 6, 360, 28);
		frame.getContentPane().add(toField);
		toField.setColumns(10);
		
		JLabel lblTo = new JLabel("To :");
		lblTo.setBounds(5, 12, 61, 16);
		frame.getContentPane().add(lblTo);
		
		fromField = new JTextField();
		fromField.setBounds(76, 38, 360, 28);
		frame.getContentPane().add(fromField);
		fromField.setColumns(10);
		
		lblFrom = new JLabel("From :");
		lblFrom.setBounds(5, 44, 61, 16);
		frame.getContentPane().add(lblFrom);
		
		subjectField = new JTextField();
		subjectField.setBounds(76, 70, 360, 28);
		frame.getContentPane().add(subjectField);
		subjectField.setColumns(10);
		
		lblSubject = new JLabel("Subject :");
		lblSubject.setBounds(5, 76, 61, 16);
		frame.getContentPane().add(lblSubject);
		
		textField = new JTextField();
		textField.setBounds(5, 104, 499, 265);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
