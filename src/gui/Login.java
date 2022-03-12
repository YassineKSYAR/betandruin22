package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import Users.User;
import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.RegisterDb;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField emailL;
	private JPasswordField passwordL;
	private Boolean admin=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setType(Type.UTILITY);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		emailL = new JTextField();
		emailL.setBounds(157, 94, 130, 19);
		contentPane.add(emailL);
		emailL.setColumns(10);
		
		JLabel Email = new JLabel("Email");
		Email.setForeground(Color.BLACK);
		Email.setHorizontalAlignment(SwingConstants.CENTER);
		Email.setFont(new Font("Tahoma", Font.BOLD, 14));
		Email.setBounds(72, 95, 90, 13);
		contentPane.add(Email);
		
		passwordL = new JPasswordField();
		passwordL.setBounds(157, 143, 130, 19);
		contentPane.add(passwordL);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Tahoma", Font.BOLD, 14));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setForeground(new Color(0, 0, 0));
		password.setBounds(70, 146, 77, 13);
		contentPane.add(password);
		
		JLabel pasemail = new JLabel("");
		pasemail.setFont(new Font("Tahoma", Font.BOLD, 14));
		pasemail.setForeground(Color.RED);
		pasemail.setHorizontalAlignment(SwingConstants.CENTER);
		pasemail.setBounds(0, 172, 436, 19);
		contentPane.add(pasemail);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterDb dataManager = new RegisterDb();
				List<User> UserList = dataManager.getUser();
				System.out.println("Database content:");
				for (User p : UserList) {
					if(emailL.getText().equals(p.getEmail()) && passwordL.getText().equals(p.getPassword()) && p.getIsAdmin()){
						MainGUI initWindow = new MainGUI();
						BlFacade businessLogic;
						ConfigXML config = ConfigXML.getInstance();
						dispose();

						try {

							UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
							// Other possibilities are:
							// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
							// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

							if (config.isBusinessLogicLocal())
								businessLogic = new BlFacadeImplementation();

							else {

								String serviceName= "http://" + config.getBusinessLogicNode() + ":" + 
										config.getBusinessLogicPort() + "/ws/" + config.getBusinessLogicName() +
										"?wsdl";
								URL url = new URL(serviceName);

								// 1st argument refers to above wsdl document
								// 2nd argument is service name, refer to wsdl document above
								QName qname = new QName("http://businessLogic/", "BlFacadeImplementationService");
								Service service = Service.create(url, qname);
								businessLogic = service.getPort(BlFacade.class);
							} 
							initWindow.setBussinessLogic(businessLogic);
							initWindow.setVisible(true);
						}
						catch (Exception e1) {
							initWindow.selectOptionLbl.setText("Error: " + e1.toString());
							initWindow.selectOptionLbl.setForeground(Color.RED);		
							System.out.println("Error in ApplicationLauncher: " + e1.toString());
						}
						
						}
				
					else if(emailL.getText().equals(p.getEmail()) && passwordL.getText().equals(p.getPassword())) {
						MainUser initWindow = new MainUser();
						BlFacade businessLogic;
						ConfigXML config = ConfigXML.getInstance();
						dispose();

						try {

							UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
							// Other possibilities are:
							// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
							// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

							if (config.isBusinessLogicLocal())
								businessLogic = new BlFacadeImplementation();

							else {

								String serviceName= "http://" + config.getBusinessLogicNode() + ":" + 
										config.getBusinessLogicPort() + "/ws/" + config.getBusinessLogicName() +
										"?wsdl";
								URL url = new URL(serviceName);

								// 1st argument refers to above wsdl document
								// 2nd argument is service name, refer to wsdl document above
								QName qname = new QName("http://businessLogic/", "BlFacadeImplementationService");
								Service service = Service.create(url, qname);
								businessLogic = service.getPort(BlFacade.class);
							} 
							initWindow.setBussinessLogic(businessLogic);
							initWindow.setVisible(true);
						}
						catch (Exception e1) {
							initWindow.selectOptionLbl.setText("Error: " + e1.toString());
							initWindow.selectOptionLbl.setForeground(Color.RED);		
							System.out.println("Error in ApplicationLauncher: " + e1.toString());
						}
						
					}
					else {
						pasemail.setText("invalid email or password");
						
					}
						
				}
				
			}
		});
		btnNewButton.setBounds(177, 207, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel emailpassword = new JLabel("");
		emailpassword.setForeground(Color.RED);
		emailpassword.setHorizontalAlignment(SwingConstants.CENTER);
		emailpassword.setBounds(0, 172, 436, 19);
		contentPane.add(emailpassword);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME TO BET AND RUIN");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 10, 436, 57);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("You don't have an account ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(113, 233, 198, 36);
		contentPane.add(lblNewLabel_1);
		
		JButton registerBtn = new JButton("create it now");
		registerBtn.setForeground(Color.WHITE);
		registerBtn.setBackground(Color.BLACK);
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register reg=new Register();
				reg.show();
				dispose();
			}
		});
		registerBtn.setBounds(157, 269, 130, 19);
		contentPane.add(registerBtn);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon("C:\\Users\\hejba\\Desktop\\betandruin22\\icone.jpeg"));
		icon.setBounds(0, 0, 436, 312);
		contentPane.add(icon);
		
		
	}
}
