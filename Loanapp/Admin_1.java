package Loanapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin_1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane;
	public static Admin_1 frame;

	/**
	 * Launch the application.
	 */
	public static void tablecall()
	{
		try
		{
		Connection con= null;
		
		PreparedStatement st2= null;
		ResultSet rs = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loanapp","root","root");
		st2=con.prepareStatement("select * from loan_application ;");
				 
		 rs = st2.executeQuery();
		 while(rs.next())
		 {
			 String data[] = { rs.getString("username")+"",rs.getString("applicationno")+"",rs.getString("amount")+"",rs.getString("tenure")+"",rs.getString("status")+"",rs.getString("formdate")+""};
			 DefaultTableModel ta = (DefaultTableModel)table.getModel();
				ta.addRow(data);
		 }
		 con.close();
			
			st2.close();
			rs.close();
		}
		catch(Exception e1)
		{
			 JOptionPane.showMessageDialog(null, "An error occurred: " + e1.getMessage());
		}
		
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				System.out.println("1234");
				try {
					frame = new Admin_1();
					frame.setVisible(true);
					
						
				} 
				catch(Exception e1)
				{
					 JOptionPane.showMessageDialog(null, "An error occurred: " + e1.getMessage());
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin_1() {
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Radha Bank");
		lblNewLabel.setBounds(300, 25, 300, 60);
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 40));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Loan Application");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(335, 95, 146, 38);
		contentPane.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 216, 829, 158);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Application no.", "Loan Amount", "Tenure", "Status", "Submission date"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(133, 442, 152, 60);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Approved");
		btnNewButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent pl) {
				
//				  frame.getContentPane().removeAll();
//				 frame.repaint();
				Connection con= null;
				ResultSet rsp = null;
				PreparedStatement st2= null;
				PreparedStatement st3= null;
				
				try {
					int appli=0;
					appli=Integer.parseInt(textField.getText());
					if(appli==0)
					{
						throw new Exception("An error occurred");
					}
				
				appli=Integer.parseInt(textField.getText());
				Class.forName("com.mysql.cj.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loanapp","root","root");
				 st2=con.prepareStatement("update loan_application set status = ? where applicationno = ?");
				 st2.setString(1, "approved"); 
				 st2.setInt(2, appli);
				 st2.executeUpdate();
				
				 
				 
		    	
		    	 st2.close();   
					
					con.close();
		    	 JOptionPane.showMessageDialog(null,"Application approved") ;
		    	 
				}
				
				catch(Exception e1)
				{
					 JOptionPane.showMessageDialog(null, "An error occurred: " + e1.getMessage());
					 
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(396, 442, 152, 60);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Rejected");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
Connection con= null;
				
				PreparedStatement st2= null;
				
				try {
				int appli;
				appli=Integer.parseInt(textField.getText());
				Class.forName("com.mysql.cj.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loanapp","root","root");
				 st2=con.prepareStatement("update loan_application set status = ? where applicationno = ?");
				 st2.setString(1, "rejected"); 
				 st2.setInt(2, appli);
				 st2.executeUpdate();
				 
				 con.close();
		    	
		    	 st2.close();
		    	 JOptionPane.showMessageDialog(null,"Application rejected") ;
				}
				
				catch(Exception e1)
				{
					 JOptionPane.showMessageDialog(null, "An error occurred: " + e1.getMessage());
					 
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(671, 442, 152, 60);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Application no.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(133, 514, 152, 31);
		contentPane.add(lblNewLabel_2);
	}
}
