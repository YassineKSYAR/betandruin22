package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;


public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setType(Type.UTILITY);
		setTitle("Bet and Ruin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 382, 355);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setForeground(new Color(255, 255, 0));
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO BET AND RUIN");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(0, 40, 368, 39);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(lblNewLabel);
		
		JButton Login = new JButton("LOG-IN");
		Login.setForeground(Color.WHITE);
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login newRegister=new Login();
				newRegister.show();
				dispose();
			}
		});
		Login.setBackground(Color.BLACK);
		Login.setBounds(87, 128, 170, 26);
		contentPane.add(Login);
		
	
		JButton Register = new JButton("REGISTER");
		Register.setForeground(Color.WHITE);
		Register.setBackground(Color.BLACK);
		Register.setBounds(87, 184, 170, 26);
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register newRegister=new Register();
				newRegister.show();
				dispose();
			}
		});
		contentPane.add(Register);
		
	
		
		JButton Close = new JButton("Close");
		Close.setBackground(Color.BLACK);
		Close.setForeground(Color.WHITE);
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Close.setBounds(127, 249, 85, 21);
		contentPane.add(Close);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\hejba\\Desktop\\betandruin22\\icone.jpeg"));
		lblNewLabel_1.setBounds(0, 0, 368, 318);
		contentPane.add(lblNewLabel_1);
	}
}
