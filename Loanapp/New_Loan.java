package Loanapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;

public class New_Loan extends JFrame {

	private static final long serialVersionUID = 1L;
	public JTextField textUserName;
	private JTextField textMobile;
	private JButton btnSubmit;
	public JLabel lblLoanTenure;
	private JLabel lblLoanAmount;
	private JLabel lblMobile;
	private JLabel lblUser;
	private JLabel lblRadha;
    private JSpinner spinner_1;
    private JSpinner spinner;
    private JButton btnGenerateEMI;
    private JPanel contentPane;
    public static New_Loan frame;
    String xa;
	 int amount;
	 String xt;
	 int tenure;
	 private JTable table;
	 private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new New_Loan();
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
	public New_Loan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1375, 784);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblRadha = new JLabel("Radha Bank");
		lblRadha.setBounds(300, 25, 300, 60);
		lblRadha.setFont(new Font("Garamond", Font.BOLD, 40));
		contentPane.add(lblRadha);
		
		textUserName = new JTextField();
		textUserName.setEditable(false);
		
		textUserName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textUserName.setBounds(252, 183, 151, 40);
		contentPane.add(textUserName);
		textUserName.setColumns(10);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 10000, 100));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 22));
		spinner.setBounds(252, 290, 151, 40);
		contentPane.add(spinner);
		
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 54, 1));
		spinner_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		spinner_1.setBounds(275, 355, 128, 40);
		contentPane.add(spinner_1);
		
		lblUser = new JLabel("Username");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(78, 189, 104, 29);
		contentPane.add(lblUser);
		
		lblMobile = new JLabel("Mobile Number");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMobile.setBounds(78, 246, 142, 29);
		contentPane.add(lblMobile);
		
		textMobile = new JTextField();
		textMobile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textMobile.setColumns(10);
		textMobile.setBounds(252, 233, 151, 40);
		contentPane.add(textMobile);
		
		lblLoanAmount = new JLabel("Loan Amount($)");
		lblLoanAmount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoanAmount.setBounds(78, 303, 151, 29);
		contentPane.add(lblLoanAmount);
		
	    lblLoanTenure = new JLabel("Loan Tenure(in weeks)");
		lblLoanTenure.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoanTenure.setBounds(78, 362, 201, 29);
		contentPane.add(lblLoanTenure);
		
		btnSubmit = new JButton("Submit the Application");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con= null;
				PreparedStatement st= null;
				PreparedStatement st2= null;
				ResultSet rs = null;
//				EMI_Generator g = new EMI_Generator();
//				g.setVisible(true);
				
				 String username;
//				 String xa;
//				 int amount;
//				 String xt;
//				 int tenure;
				 String status;
				 LocalDate date = LocalDate.now();
				 
					username=textUserName.getText();
					xa = spinner.getValue().toString();
					amount = Integer.parseInt(xa);
					xt = spinner_1.getValue().toString();
					tenure = Integer.parseInt(xt);
					status = "Pending";
					
					
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					con= DriverManager.getConnection("jdbc:mysql://localhost:3306/loanapp","root","root");
					
					 st=con.prepareStatement("insert into loan_application (username,applicationno,amount,tenure,status,formdate) values (?,?,?,?,?,?);");
					 
					 st2=con.prepareStatement("select MAX(applicationno) AS max_app from loan_application ;");
					 
					 
					 rs = st2.executeQuery();
					 rs.next();
					
					 int app_no = rs.getInt("max_app");
					 
					 if(app_no>0)
					 {
						 app_no=app_no+1;
					 }
					 else
					 {
						 app_no=1;
					 }
					 st.setString(1, username);
					 st.setInt(2,app_no);
				     st.setInt(3, amount);
				     st.setInt(4, tenure);
				     st.setString(5, status);
				     java.sql.Date sqlDate = java.sql.Date.valueOf(date);
				     st.setDate(6, sqlDate);
				     System.out.println(status);
				     
				     st.execute();
				   
				     con.close();
			    	 st.close();
			    	 st2.close();
			    	 
			    	

				}
				catch(Exception e1)
				{
					 JOptionPane.showMessageDialog(null, "An error occurred: " + e1.getMessage());
				}
			
			JOptionPane.showMessageDialog(null,"Application send to Admin wait for sir reaction") ;
			 
			 frame.dispose();
			 RadhaBank r = new RadhaBank();
			 r.setVisible(true);
			 
			 
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSubmit.setBounds(78, 555, 289, 60);
		contentPane.add(btnSubmit);
		btnSubmit.setEnabled(false);
		
		btnGenerateEMI = new JButton("Generate EMI");
		btnGenerateEMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xa = spinner.getValue().toString();
				amount = Integer.parseInt(xa);
				xt = spinner_1.getValue().toString();
				tenure = Integer.parseInt(xt);
				if(amount==0|| tenure==0)
				{
					JOptionPane.showConfirmDialog(null, "first enter the loan amount and tenure then click on this button");
				}
				else
				{
					btnSubmit.setEnabled(true);
					int remin=amount;
					LocalDate date = LocalDate.now();
					int emi=(amount/tenure);
					
					for(int i=1;i<=tenure;i++)
					{
						 LocalDate date7 = date.plusWeeks(i);
					String data[]= {i+"",amount + "",remin+"",emi+"",date7+""};
					DefaultTableModel ta = (DefaultTableModel)table.getModel();
					ta.addRow(data);
					remin=remin-emi;
					}
				}
			
				
		    	 
				
			}
		});
		btnGenerateEMI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGenerateEMI.setBounds(78, 453, 289, 60);
		contentPane.add(btnGenerateEMI);
		
		JLabel lblNewLabel_1 = new JLabel("EMI Scheduled Repayments");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(740, 166, 355, 36);
		contentPane.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(567, 246, 614, 184);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EMI no.", "Total Loan Amount", "Remaning Loan Amount ", "EMI Amount", "EMI Date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(59);
		table.getColumnModel().getColumn(1).setPreferredWidth(114);
		table.getColumnModel().getColumn(2).setPreferredWidth(128);
		table.getColumnModel().getColumn(4).setMinWidth(18);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
	}
}
