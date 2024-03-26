package Loanapp;

import java.awt.EventQueue;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Existing_Loan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	public static String userName;
	private JTextField textApplicationNo;
	private JButton btnWayToPay;
	private JTable table_1;
	public JButton btnNewButton;
	public JTextField textPay;
	public JLabel lblEnterAmountTo;
	private static int amount;
	private static int tenure;
	private static java.util.Date date;
	public JScrollPane scrollPane_1;
	public static int appli=0;
	public static int emi;
	

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
		st2=con.prepareStatement("select * from loan_application where username = ?;");
		st2.setString(1, userName); 
				 
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
				try {
					Existing_Loan frame = new Existing_Loan();
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
	public Existing_Loan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1157, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRadhaBank = new JLabel("Radha Bank");
		lblRadhaBank.setBounds(300, 25, 300, 60);
		lblRadhaBank.setFont(new Font("Garamond", Font.BOLD, 40));
		contentPane.add(lblRadhaBank);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 143, 829, 158);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"username", "Appilcationno", "Amount", "Tenure", "Status", "Submission Date"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textApplicationNo = new JTextField();
		textApplicationNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textApplicationNo.setBounds(48, 344, 142, 49);
		contentPane.add(textApplicationNo);
		textApplicationNo.setColumns(10);
		
		btnWayToPay = new JButton("Way to Pay");
		btnWayToPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				scrollPane_1.setVisible(true);
				textPay.setVisible(true);
				lblEnterAmountTo.setVisible(true);
				btnNewButton.setVisible(true);
				
				
				
				try {
					
					appli=Integer.parseInt(textApplicationNo.getText());
					Connection con= null;
					ResultSet rsp = null;
					PreparedStatement st2= null;
					PreparedStatement st3= null;
					
					if(appli==0)
					{
						throw new Exception("An error occurred");
					}
				Class.forName("com.mysql.cj.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loanapp","root","root");
				 st2=con.prepareStatement("select amount, tenure,formdate from loan_application where  applicationno = ?");
				 st2.setInt(1, appli); 
				
				 rsp=st2.executeQuery();
				 
				 while(rsp.next())
				 {
					 amount=rsp.getInt("amount");
					 tenure=rsp.getInt("tenure");
					 date=rsp.getDate("formdate");
					 
				 }
				 int remin=amount;
					LocalDate datenow = LocalDate.now();
					LocalDate formDate = ((Date) date).toLocalDate();
					long daysBetween = ChronoUnit.DAYS.between(formDate, datenow);
					int days = Math.toIntExact(daysBetween);
					int week=days/7;
					emi=(amount/(tenure-week));
					
					
					for(int i=1;i<=tenure-week;i++)
					{
						 LocalDate date7 = formDate.plusWeeks(i);
					String data[]= {i+"",amount + "",emi+"",date7+""};
					DefaultTableModel ta = (DefaultTableModel)table_1.getModel();
					ta.addRow(data);
					remin=remin-emi;
					}
					
				 
				 
		    	
		    	 st2.close();   
					rsp.close();
					con.close();
		    	 
		    	 
				}
				
				catch(Exception e1)
				{
					 JOptionPane.showMessageDialog(null, "An error occurred: " + e1.getMessage());
					 
				}
			}
		});
		btnWayToPay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnWayToPay.setBounds(48, 488, 121, 49);
		contentPane.add(btnWayToPay);
		
		JLabel lblNewLabel = new JLabel("Selected Application number");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(48, 413, 256, 37);
		contentPane.add(lblNewLabel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(407, 344, 605, 140);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EMI remaining", "Remaining Amount", "EMI Amount", "EMI Date"
			}
		));
		
		btnNewButton = new JButton("Pay");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				int amountPay=0;
				amountPay=Integer.parseInt(textPay.getText());
//				amount=0;
//				appli=0;
				//emi=0;
				if(amountPay>=emi && amountPay<=amount)
				{
					Connection con= null;
					ResultSet rsp = null;
					PreparedStatement st2= null;
					PreparedStatement st3= null;
					int a=amount-amountPay;
					Class.forName("com.mysql.cj.jdbc.Driver");
					con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loanapp","root","root");
					 st2=con.prepareStatement("update loan_application set amount = ? where applicationno = ?");
					 st2.setInt(1, a); 
					 st2.setInt(2, appli);
					 st2.executeUpdate();
					
					 
					 
			    	
			    	 st2.close();   
						
						con.close();
			    	 JOptionPane.showMessageDialog(null,"Amount Paid") ;
					
				}
				else
				{
					throw new Exception("ENter correct amount to pay");
				}
				}
				catch(Exception e1)
				{
					 JOptionPane.showMessageDialog(null, "An error occurred: " + e1.getMessage());
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(774, 510, 127, 49);
		contentPane.add(btnNewButton);
		
		textPay = new JTextField();
		textPay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPay.setColumns(10);
		textPay.setBounds(446, 509, 142, 49);
		contentPane.add(textPay);
		
		lblEnterAmountTo = new JLabel("Enter Amount to be Paid");
		lblEnterAmountTo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterAmountTo.setBounds(446, 568, 256, 37);
		contentPane.add(lblEnterAmountTo);
	}
}
