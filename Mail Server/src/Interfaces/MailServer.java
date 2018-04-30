package Interfaces;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import MainClasses.MyApp;
import MainClasses.MyContact;
 
@SuppressWarnings("serial")
public class MailServer extends Frame {
	MailServer() {
        JFrame f = new JFrame("Mail Server");
        Label l1 = new Label("User Name :");
        l1.setBounds(10, 10, 80, 10);
        Label l2 = new Label("Password :");
        l2.setBounds(10, 40, 80, 10);
        Button signIn = new Button("Sign In");
        signIn.setBounds(20, 60, 100, 30);// setting button position
        Button signUp = new Button("Sign Up");
        signUp.setBounds(130, 60, 100, 30);
        TextField userName = new TextField();
        userName.setBounds(100, 5, 150, 20);
        JPasswordField passWord = new JPasswordField();
        passWord.setBounds(100, 35, 150, 20);
        f.add(userName);
        f.add(passWord);
        f.add(l1);
        f.add(l2);
        f.add(signIn);
        f.add(signUp);
        f.setSize(260, 120);// frame size 300 width and 300 height
        f.setResizable(false);
        f.setLayout(null);// no layout manager
        f.setLocationRelativeTo(null);
        f.setVisible(true);// now frame will be visible, by default not visible
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String u = userName.getText();
                @SuppressWarnings("deprecation")
                String p = passWord.getText();
                if(new MyApp().signin(u, p)) {
                   // new Main();
                } else {
                    final ImageIcon icon = new ImageIcon("../Mail Server/download.jpeg");
                    JOptionPane.showMessageDialog(null, "",
                            "Invalid Username Or Password!!", JOptionPane.INFORMATION_MESSAGE, icon);
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
                new MyApp().signup((IContact) new MyContact(u, p));
            }
        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new MailServer();
    }
 
}