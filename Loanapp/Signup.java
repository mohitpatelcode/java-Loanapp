package Loanapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUserName;
	private JTextField passwordField;
	private JButton btnCreateAccount;
	private JLabel lblRadhaBank;
	private JLabel lblUserName;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Signup frame = new Signup();
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
	public Signup() throws ClassNotFoundException,SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblRadhaBank = new JLabel("Radha Bank");
		lblRadhaBank.setFont(new Font("Garamond", Font.BOLD, 40));
		lblRadhaBank.setBounds(259, 10, 300, 60);
		contentPane.add(lblRadhaBank);
		
		lblUserName = new JLabel("UserName");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserName.setBounds(109, 247, 217, 50);
		contentPane.add(lblUserName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(109, 338, 217, 50);
		contentPane.add(lblPassword);
		
		textUserName = new JTextField();
		textUserName.setColumns(10);
		textUserName.setBounds(329, 257, 354, 40);
		contentPane.add(textUserName);
		
		passwordField = new JTextField();
		passwordField.setBounds(329, 345, 354, 43);
		contentPane.add(passwordField);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent q) {
				 Connection con= null;
				 PreparedStatement st= null;
				String username;
				String password;
				username=textUserName.getText();
				password=passwordField.getText();
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loanapp","root","root");
				     st=con.prepareStatement("insert into userdata (username,password) values (?,?)");
				     if(username.isEmpty()|| password.isEmpty())
				     {
				    	 throw new Exception("");
				     }
					st.setString(1,username);
					st.setString(2,password);
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "created");
					st.close();
					con.close();
					
					RadhaBank r = new RadhaBank();
					r.setVisible(true);
					Signup s = new Signup();
					s.setVisible(false);
					}
				catch(Exception e ) {
					if(username.isEmpty()|| password.isEmpty())
				     {
						JOptionPane.showMessageDialog(null, "empty usernam or password");
				     }
					if (e.getMessage().contains("Duplicate entry"))
					{
					JOptionPane.showMessageDialog(null, "username already present try with another one ");
					System.out.println(e);
					}
					
				}
				
			}
		});
		btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCreateAccount.setBounds(381, 464, 217, 43);
		contentPane.add(btnCreateAccount);
	}
}
