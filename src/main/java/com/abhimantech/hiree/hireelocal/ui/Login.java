package com.abhimantech.hiree.hireelocal.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	private JFrame frame = null;
	private JPanel panel = null;
	public static void main(String[] args) {
			Login login = new Login();	
			login.placeComponents();
	}

	private void placeComponents() {
		frame = new JFrame("Hiree Resume Parser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// make the frame half the height and width
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
	    frame.setSize(width/2, height/2);

	    // center the jframe on screen
	    frame.setLocationRelativeTo(null);
	    panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(110, 80, 120, 25);
		panel.add(loginButton);
		frame.setVisible(true);
//		JButton registerButton = new JButton("register");
//		registerButton.setBounds(180, 80, 80, 25);
//		panel.add(registerButton);
	}

}