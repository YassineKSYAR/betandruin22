package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Users.User;
import dataAccess.RegisterDb;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Font;


public class Register extends JFrame {

	private JPanel signUp;
	private JTextField nameS;
	private JTextField lnameS;
	private JTextField emailS;
	private JPasswordField passwordS;
	private JPasswordField cpasswordS;
	private JLabel field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setTitle("Sign Up");
		setType(Type.UTILITY);
		setBackground(Color.YELLOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 391);
		signUp = new JPanel();
		signUp.setBackground(new Color(255, 255, 0));
		signUp.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(signUp);
		signUp.setLayout(null);
		
		
		
		nameS = new JTextField();
		nameS.setBackground(Color.WHITE);
		nameS.setBounds(143, 77, 149, 19);
		signUp.add(nameS);
		nameS.setColumns(10);
		
		lnameS = new JTextField();
		lnameS.setBackground(Color.WHITE);
		lnameS.setBounds(143, 110, 149, 19);
		signUp.add(lnameS);
		lnameS.setColumns(10);
		
		emailS = new JTextField();
		emailS.setBackground(Color.WHITE);
		emailS.setBounds(143, 139, 149, 19);
		signUp.add(emailS);
		emailS.setColumns(10);
		
		passwordS = new JPasswordField();
		passwordS.setBounds(143, 168, 149, 19);
		signUp.add(passwordS);
		
		cpasswordS = new JPasswordField();
		cpasswordS.setBounds(143, 197, 149, 19);
		signUp.add(cpasswordS);
		
		
		JLabel nameSu = new JLabel("Name");
		nameSu.setHorizontalAlignment(SwingConstants.RIGHT);
		nameSu.setBounds(10, 77, 96, 19);
		signUp.add(nameSu);
		
		JLabel lnameSu = new JLabel("Laste Name");
		lnameSu.setHorizontalAlignment(SwingConstants.RIGHT);
		lnameSu.setBounds(10, 110, 96, 19);
		signUp.add(lnameSu);
		
		JLabel emailSu = new JLabel("E-Mail");
		emailSu.setHorizontalAlignment(SwingConstants.RIGHT);
		emailSu.setBounds(61, 139, 45, 19);
		signUp.add(emailSu);
		
		JLabel passwordSu = new JLabel("Password");
		passwordSu.setHorizontalAlignment(SwingConstants.CENTER);
		passwordSu.setBounds(45, 171, 63, 13);
		signUp.add(passwordSu);
		
		JLabel cpasswordSu = new JLabel("Confirm password");
		cpasswordSu.setHorizontalAlignment(SwingConstants.CENTER);
		cpasswordSu.setBounds(0, 200, 106, 13);
		signUp.add(cpasswordSu);
		
		field = new JLabel("");
		field.setForeground(Color.BLACK);
		field.setFont(new Font("Tahoma", Font.BOLD, 14));
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setBounds(143, 54, 149, 13);
		signUp.add(field);
		
		JLabel psd = new JLabel("");
		psd.setFont(new Font("Tahoma", Font.BOLD, 11));
		psd.setForeground(Color.BLACK);
		psd.setHorizontalAlignment(SwingConstants.CENTER);
		psd.setBounds(107, 226, 222, 13);
		signUp.add(psd);
		
	
		
		JButton SignUpButton = new JButton("SignUp");
		SignUpButton.setBackground(Color.DARK_GRAY);
		SignUpButton.setForeground(SystemColor.inactiveCaptionBorder);
		SignUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameS.getText().isEmpty() || lnameS.getText().isEmpty() || emailS.getText().isEmpty()|| passwordS.getText().isEmpty()||cpasswordS.getText().isEmpty()) {
					field.setText("Field is empty");
					
				}
				else if(!(passwordS.getText().equals(cpasswordS.getText())) ){
					psd.setText("Password confirmation is invalid");
					
				}
				else {
				RegisterDb dataManager = new RegisterDb();
				dataManager.storeUser(nameS.getText(),lnameS.getText(),emailS.getText(),passwordS.getText());
				dataManager.closeDb();
				System.out.println("Ok");
				
				}
			}
		});
		SignUpButton.setBounds(174, 248, 85, 21);
		signUp.add(SignUpButton);
		
	
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setBounds(90, 80, 45, 13);
		signUp.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBackground(Color.RED);
		lblNewLabel_2.setBounds(88, 113, 45, 13);
		signUp.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("*");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBackground(Color.RED);
		lblNewLabel_3.setBounds(88, 142, 45, 13);
		signUp.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("*");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBackground(Color.RED);
		lblNewLabel_4.setBounds(90, 200, 45, 13);
		signUp.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("*");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBackground(Color.RED);
		lblNewLabel_5.setBounds(88, 171, 45, 13);
		signUp.add(lblNewLabel_5);
		
		
		
	
	}
}
