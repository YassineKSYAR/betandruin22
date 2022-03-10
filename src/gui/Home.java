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
		
		JLabel lblNewLabel = new JLabel("Welcome To Bet and Ruin");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(38, 40, 283, 39);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JButton Login = new JButton("LOG-IN");
		Login.setForeground(Color.BLACK);
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Login.setBackground(Color.WHITE);
		Login.setBounds(87, 128, 170, 26);
		contentPane.add(Login);
		
	
		JButton Register = new JButton("REGISTER");
		Register.setBackground(Color.WHITE);
		Register.setBounds(87, 164, 170, 26);
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register newRegister=new Register();
				newRegister.show();
				dispose();
			}
		});
		contentPane.add(Register);
		
		JButton QueryQuestions = new JButton("QUERY QUESTIONS");
		QueryQuestions.setBackground(Color.WHITE);
		QueryQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		QueryQuestions.setBounds(87, 200, 170, 26);
		contentPane.add(QueryQuestions);
		
		JButton Close = new JButton("Close");
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Close.setBounds(127, 249, 85, 21);
		contentPane.add(Close);
	}
}
