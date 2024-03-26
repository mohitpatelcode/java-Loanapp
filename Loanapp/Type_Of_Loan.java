package Loanapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Type_Of_Loan extends JFrame {
	public String username;
	private JButton btnExistingLoan;
	private JButton btnNewLoan;
	JLabel lblRadhaBank;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Type_Of_Loan frame = new Type_Of_Loan();
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
	public Type_Of_Loan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblRadhaBank = new JLabel("Radha Bank");
		lblRadhaBank.setBounds(300, 25, 300, 60);
		lblRadhaBank.setFont(new Font("Garamond", Font.BOLD, 40));
		contentPane.add(lblRadhaBank);
		
		btnNewLoan = new JButton("New Loan");
		btnNewLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				New_Loan n = new New_Loan();
				n.textUserName.setText(username);
				
				n.setVisible(true);
			}
		});
		btnNewLoan.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewLoan.setBounds(130, 194, 215, 86);
		contentPane.add(btnNewLoan);
		
	    btnExistingLoan = new JButton("Existing Loan");
		btnExistingLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Existing_Loan n = new Existing_Loan();
				n.userName = username;
				n.tablecall();
				
				n.setVisible(true);
				n.scrollPane_1.setVisible(false);
				n.textPay.setVisible(false);
				n.lblEnterAmountTo.setVisible(false);
				n.btnNewButton.setVisible(false);
			}
		});
		btnExistingLoan.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnExistingLoan.setBounds(457, 194, 215, 86);
		contentPane.add(btnExistingLoan);
	}

}
