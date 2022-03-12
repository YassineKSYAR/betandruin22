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
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.ImageIcon;


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
		nameS.setBounds(153, 86, 149, 19);
		signUp.add(nameS);
		nameS.setColumns(10);
		
		lnameS = new JTextField();
		lnameS.setBackground(Color.WHITE);
		lnameS.setBounds(153, 115, 149, 19);
		signUp.add(lnameS);
		lnameS.setColumns(10);
		
		emailS = new JTextField();
		emailS.setBackground(Color.WHITE);
		emailS.setBounds(153, 144, 149, 19);
		signUp.add(emailS);
		emailS.setColumns(10);
		
		passwordS = new JPasswordField();
		passwordS.setBounds(153, 173, 149, 19);
		signUp.add(passwordS);
		
		cpasswordS = new JPasswordField();
		cpasswordS.setBounds(153, 202, 149, 19);
		signUp.add(cpasswordS);
		
		
		JLabel nameSu = new JLabel("First Name");
		nameSu.setFont(new Font("Tahoma", Font.BOLD, 10));
		nameSu.setHorizontalAlignment(SwingConstants.RIGHT);
		nameSu.setBounds(22, 86, 96, 19);
		signUp.add(nameSu);
		
		JLabel lnameSu = new JLabel("Last Name");
		lnameSu.setFont(new Font("Tahoma", Font.BOLD, 10));
		lnameSu.setHorizontalAlignment(SwingConstants.RIGHT);
		lnameSu.setBounds(22, 115, 96, 19);
		signUp.add(lnameSu);
		
		JLabel emailSu = new JLabel("E-Mail");
		emailSu.setFont(new Font("Tahoma", Font.BOLD, 10));
		emailSu.setHorizontalAlignment(SwingConstants.RIGHT);
		emailSu.setBounds(61, 144, 57, 19);
		signUp.add(emailSu);
		
		JLabel passwordSu = new JLabel("Password");
		passwordSu.setFont(new Font("Tahoma", Font.BOLD, 10));
		passwordSu.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordSu.setBounds(55, 176, 63, 13);
		signUp.add(passwordSu);
		
		JLabel cpasswordSu = new JLabel("Confirm password");
		cpasswordSu.setFont(new Font("Tahoma", Font.BOLD, 10));
		cpasswordSu.setHorizontalAlignment(SwingConstants.RIGHT);
		cpasswordSu.setBounds(12, 205, 106, 13);
		signUp.add(cpasswordSu);
		
		field = new JLabel("");
		field.setForeground(Color.BLACK);
		field.setFont(new Font("Tahoma", Font.BOLD, 14));
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setBounds(153, 105, 149, 13);
		signUp.add(field);
		
		JLabel psd = new JLabel("");
		psd.setFont(new Font("Tahoma", Font.BOLD, 11));
		psd.setForeground(Color.BLACK);
		psd.setHorizontalAlignment(SwingConstants.CENTER);
		psd.setBounds(108, 243, 222, 13);
		signUp.add(psd);
		
		JLabel haveAcc = new JLabel("");
		haveAcc.setHorizontalAlignment(SwingConstants.CENTER);
		haveAcc.setFont(new Font("Tahoma", Font.BOLD, 13));
		haveAcc.setBounds(153, 53, 149, 23);
		signUp.add(haveAcc);
		
		
		JButton SignUpButton = new JButton("SignUp");
		SignUpButton.setBackground(Color.BLACK);
		SignUpButton.setForeground(Color.WHITE);
		SignUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterDb dataManagers = new RegisterDb();
				List<User> UserList = dataManagers.getUser();
				System.out.println("Database content:");
				for (User p : UserList) {
					if(emailS.getText().equals(p.getEmail()) && passwordS.getText().equals(p.getPassword())&&nameS.getText().equals(p.getFname()) &&lnameS.getText().equals(p.getLname())){
						haveAcc.setText("User exist!!");
						}
						
					}
						
				
				
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
				Login log=new Login();
				log.show();
				dispose();
				}
			}
		});
		SignUpButton.setBounds(179, 266, 85, 21);
		signUp.add(SignUpButton);
		
	
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setBounds(106, 117, 45, 13);
		signUp.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBackground(Color.RED);
		lblNewLabel_2.setBounds(106, 88, 45, 13);
		signUp.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("*");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBackground(Color.RED);
		lblNewLabel_3.setBounds(116, 146, 24, 13);
		signUp.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("*");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBackground(Color.RED);
		lblNewLabel_4.setBounds(106, 204, 45, 13);
		signUp.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("*");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBackground(Color.RED);
		lblNewLabel_5.setBounds(106, 175, 45, 13);
		signUp.add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO BET AND RUIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 398, 33);
		signUp.add(lblNewLabel);
		
		JLabel lblNewLabel_6 = new JLabel("Requierd Field ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(41, 309, 96, 13);
		signUp.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("*");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setBounds(-11, 308, 45, 14);
		signUp.add(lblNewLabel_7);
		
		JLabel logLib = new JLabel("You have a count");
		logLib.setHorizontalAlignment(SwingConstants.RIGHT);
		logLib.setFont(new Font("Tahoma", Font.BOLD, 14));
		logLib.setBounds(116, 300, 160, 29);
		signUp.add(logLib);
		
		JButton logBtn = new JButton("LOG-IN");
		logBtn.setBackground(Color.BLACK);
		logBtn.setForeground(Color.WHITE);
		logBtn.setBounds(282, 306, 85, 21);
		logBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Login log =new Login();
				log.show();
				dispose();
			}
		});
		signUp.add(logBtn);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon("./icone.jpeg"));
		icon.setBounds(0, 0, 377, 365);
		signUp.add(icon);
		
		
		
		
	
	}
}
