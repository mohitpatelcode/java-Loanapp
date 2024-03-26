package Loanapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionEvent;

public class Admin_Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField passwordField;
	private JButton btnLogin;
	private JLabel lblRadhaBank;
	private JButton btnUser;
	private JButton btnAdmin;
	private JLabel lblUserName;
	private JLabel lblPassword;
	public static Admin_Home frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Admin_Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin_Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblRadhaBank = new JLabel("Radha Bank");
		lblRadhaBank.setFont(new Font("Garamond", Font.BOLD, 40));
		lblRadhaBank.setBounds(300, 25, 300, 60);
		contentPane.add(lblRadhaBank);
		
		btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RadhaBank r = new RadhaBank();
				r.setVisible(true);
			}
		});
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUser.setBounds(217, 137, 171, 43);
		contentPane.add(btnUser);
		
		btnAdmin = new JButton("Admin");
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdmin.setBounds(447, 137, 171, 43);
		contentPane.add(btnAdmin);
		
		lblUserName = new JLabel("UserName");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserName.setBounds(88, 234, 217, 50);
		contentPane.add(lblUserName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(88, 320, 217, 50);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(315, 244, 354, 40);
		contentPane.add(textField);
		
		passwordField = new JTextField();
		passwordField.setBounds(315, 327, 354, 43);
		contentPane.add(passwordField);
		
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

					 st = con.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
					 st.setString(1, username);
				     st.setString(2, password);
				     rs = st.executeQuery();
				     if (rs.next()) {
				            // User authentication successful
				    	
				           
				            Admin_1 t = new Admin_1();
				            t.tablecall();
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
		btnLogin.setBounds(315, 426, 134, 43);
		contentPane.add(btnLogin);
	}
}
