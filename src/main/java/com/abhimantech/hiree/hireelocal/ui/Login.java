package com.abhimantech.hiree.hireelocal.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.abhimantech.hiree.hireelocal.HireeRetrofit;
import com.abhimantech.hiree.hireelocal.LoginRequest;
import com.abhimantech.hiree.hireelocal.LoginResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Login extends JFrame {

	public static void main(String[] args) {
		Login frameTabel = new Login();
	}
	
	JButton blogin = new JButton("Login");
	JPanel panel = new JPanel();
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	
	Login(){
	super("Login Autentification");
	setSize(400,250);
	setLocation(400,280);
	panel.setLayout (null); 
	
	
	txuser.setBounds(70,30,250,30);
	pass.setBounds(70,70,250,30);
	blogin.setBounds(150,110,90,30);
	
	panel.add(blogin);
	panel.add(txuser);
	panel.add(pass);
	
	getContentPane().add(panel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	actionlogin();
	}
	
	public void actionlogin(){
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText().trim();
				String ppaswd = pass.getText();
//				if(puname.equals("test") && ppaswd.equals("12345")) {
				HireeRetrofit.getApi().login(new LoginRequest(puname, ppaswd, "SIM_DEVICE", "0000000000"), new Callback<LoginResponse>() {
					@Override
					public void success(LoginResponse arg0, Response arg1) {
						// TODO Auto-generated method stub
						if(arg0.getStatus().equals("Success")){
							FileExplorer fileExplorer =new FileExplorer(arg0);
							fileExplorer.createExplorer();
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null,"Wrong Password / Username");
							txuser.setText("");
							pass.setText("");
							txuser.requestFocus();
						}
					}
					public void failure(RetrofitError arg0) {
						// TODO Auto-generated method stub
						JOptionPane.showMessageDialog(null,"Wrong Password / Username");
						txuser.setText("");
						pass.setText("");
						txuser.requestFocus();
					}

					
				});
			}
		});
	}
}




//public class Login {
//	private JFrame frame = null;
//	private JPanel panel = null;
//	public static void main(String[] args) {
//			Login login = new Login();	
//			login.placeComponents();
//	}
//
//	private void placeComponents() {
//		frame = new JFrame("Hiree Resume Parser");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		// make the frame half the height and width
//	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//	    int height = screenSize.height;
//	    int width = screenSize.width;
//	    frame.setSize(width/2, height/2);
//
//	    // center the jframe on screen
//	    frame.setLocationRelativeTo(null);
//	    panel = new JPanel();
//		frame.add(panel);
//		panel.setLayout(null);
//
//		JLabel userLabel = new JLabel("User");
//		userLabel.setBounds(10, 10, 80, 25);
//		panel.add(userLabel);
//
//		JTextField userText = new JTextField(20);
//		userText.setBounds(100, 10, 160, 25);
//		panel.add(userText);
//
//		JLabel passwordLabel = new JLabel("Password");
//		passwordLabel.setBounds(10, 40, 80, 25);
//		panel.add(passwordLabel);
//
//		JPasswordField passwordText = new JPasswordField(20);
//		passwordText.setBounds(100, 40, 160, 25);
//		panel.add(passwordText);
//
//		JButton loginButton = new JButton("login");
//		loginButton.setBounds(110, 80, 120, 25);
//		panel.add(loginButton);
//		frame.setVisible(true);
////		JButton registerButton = new JButton("register");
////		registerButton.setBounds(180, 80, 80, 25);
////		panel.add(registerButton);
//	}
//	
//
//}