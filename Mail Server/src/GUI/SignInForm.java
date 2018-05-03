package GUI;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Interfaces.IContact;
import MainClasses.MyApp;
import MainClasses.MyContact;

@SuppressWarnings("serial")
public class SignInForm extends Frame {
	public SignInForm() {
		JFrame f = new JFrame("Mail Server");
		JLabel l1 = new JLabel("User Name :");
		l1.setBounds(10, 10, 80, 10);
		JLabel l2 = new JLabel("Password :");
		l2.setBounds(10, 40, 80, 10);
		JButton signIn = new JButton("Sign In");
		signIn.setBounds(20, 60, 100, 30);// setting button position
		JButton signUp = new JButton("Sign Up");
		signUp.setBounds(130, 60, 100, 30);
		JTextField userName = new JTextField();
		userName.setBounds(100, 5, 150, 20);
		JPasswordField passWord = new JPasswordField();
		passWord.setBounds(100, 35, 150, 20);
		f.getContentPane().add(userName);
		f.getContentPane().add(passWord);
		f.getContentPane().add(l1);
		f.getContentPane().add(l2);
		f.getContentPane().add(signIn);
		f.getContentPane().add(signUp);
		f.setSize(260, 120);// frame size 300 width and 300 height
		f.setResizable(false);
		f.getContentPane().setLayout(null);// no layout manager
		f.setLocationRelativeTo(null);
		f.setVisible(true);// now frame will be visible, by default not visible
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String u = userName.getText();
				@SuppressWarnings("deprecation")
				String p = passWord.getText();
				if (new MyApp().signin(u, p)) {
					f.dispose();
					new Main("../Mail Server/Users/" + u + '/');
				} else {
					f.dispose();
					new SignInErrorMessage("../Mail Server/SignInError.jpeg");
				}

			}
		});
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String u = userName.getText();
				@SuppressWarnings("deprecation")
				String p = passWord.getText();
				f.dispose();
				 new SignUpForm();
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SignInForm();
	}

}
