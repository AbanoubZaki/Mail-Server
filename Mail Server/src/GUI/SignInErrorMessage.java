package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class SignInErrorMessage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInErrorMessage window = new SignInErrorMessage("  ");
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
	public SignInErrorMessage(String iconPath) {
		initialize(iconPath);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String iconPath) {
		JFrame frame = new JFrame("Error!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final ImageIcon icon = new ImageIcon(iconPath);
		frame.getContentPane().setLayout(null);
		JButton btn = new JButton(icon);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new SignInForm();
			}
		});
		JScrollPane jsp = new JScrollPane(btn);
		jsp.setBounds(0, 0, 650, 600);
		frame.getContentPane().add(jsp);
		frame.setSize(650, 620);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
