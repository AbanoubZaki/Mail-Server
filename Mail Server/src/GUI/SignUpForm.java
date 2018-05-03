package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import MainClasses.MyApp;
import MainClasses.MyContact;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpForm {

	private JFrame frame;
	private JTextField email;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpForm window = new SignUpForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUpForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Sign Up");
		frame.setBounds(100, 100, 278, 145);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUser = new JLabel("User Name :");
		lblUser.setBounds(6, 11, 92, 17);
		frame.getContentPane().add(lblUser);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(6, 40, 73, 14);
		frame.getContentPane().add(lblEmail);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(6, 66, 73, 14);
		frame.getContentPane().add(lblPassword);

		email = new JTextField();
		email.setBounds(85, 37, 188, 20);
		frame.getContentPane().add(email);
		email.setColumns(10);

		username = new JTextField();
		username.setColumns(10);
		username.setBounds(85, 9, 188, 20);
		frame.getContentPane().add(username);

		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(85, 63, 188, 20);
		frame.getContentPane().add(password);
		JButton btnSign = new JButton("Sign Up");
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = new MyApp().signup(new MyContact(username.getText(),
						email.getText(), password.getText()));
				if (b) {
					frame.dispose();
					new SignInForm();
				} else {
					new SignInErrorMessage("../Mail Server/SignUpError.jpeg");
				}
			}
		});
		btnSign.setBounds(57, 92, 138, 23);
		frame.getContentPane().add(btnSign);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
