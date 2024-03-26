package Loanapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RadhaBank {

	private JFrame frame;
	private JTextField textField , passwordField;

	private JLabel lblRadhaBank;
	private JButton btnUser;
	private JButton btnAdmin;
	private JLabel lblPassword;
	private JLabel lblUName;
	private JButton btnLogin;
	private JButton btnSignup;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RadhaBank window = new RadhaBank();
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
	public RadhaBank() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 843, 626);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblRadhaBank = new JLabel("Radha Bank");
		lblRadhaBank.setFont(new Font("Garamond", Font.BOLD, 40));
		lblRadhaBank.setBounds(300, 25, 230, 60);
		frame.getContentPane().add(lblRadhaBank);
		
		btnUser = new JButton("User");
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUser.setBounds(218, 148, 171, 43);
		frame.getContentPane().add(btnUser);
		
		btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Admin_Home a = new Admin_Home();
				a.setVisible(true);
			}
		});
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdmin.setBounds(451, 148, 171, 43);
		frame.getContentPane().add(btnAdmin);
		
		lblUName = new JLabel("UserName or Mail Id");
		lblUName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUName.setBounds(76, 235, 217, 50);
		frame.getContentPane().add(lblUName);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(303, 235, 354, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(303, 302, 354, 43);
		frame.getContentPane().add(passwordField);
		
	    lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(76, 302, 217, 50);
		frame.getContentPane().add(lblPassword);
		
		btnLogin = new JButton("Login");                                         
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Connection con= null;
				 PreparedStatement st= null;
				 ResultSet rs = null;
				    String username;
					String password;
					username=textField.getText();
					password=passwordField.getText();
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loanapp","root","root");

					 st = con.prepareStatement("SELECT * FROM userdata WHERE username = ? AND password = ?");
					 st.setString(1, username);
				     st.setString(2, password);
				     rs = st.executeQuery();
				     if (rs.next()) {
				            // User authentication successful
				    	
				           
				            Type_Of_Loan t = new Type_Of_Loan();
				            t.username = username;
				            t.setVisible(true);
				        } 
				     else {
				            // User authentication failed
				            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
				        }
				     con.close();
			    	 st.close();
			    	 rs.close();

				}
				catch(Exception e1)
				{
					 JOptionPane.showMessageDialog(null, "An error occurred: " + e1.getMessage());
				}
				
			
				
			}

			
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(305, 431, 134, 43);
		frame.getContentPane().add(btnLogin);
		
		btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				
				Signup s;
				try {
					s = new Signup();
					s.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSignup.setBounds(523, 431, 134, 43);
		frame.getContentPane().add(btnSignup);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
		frame.setVisible(true);
		
	}


	
}
