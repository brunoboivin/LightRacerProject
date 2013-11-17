package UserPkg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.apache.commons.lang3.StringUtils;

import GamePkg.TronGame;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login_frame extends JFrame {

	private JPanel contentPane;
	private JPanel contentPopUp;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField usernameField1;
	private JTextField usernameField2;
	private JLabel lblUsername_1;
	private JLabel lblPassword_1;
	private JLabel lblIfYouDont;
	private JButton btnRegister;
	private JLabel lblLogin;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_frame frame = new login_frame();
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
	public login_frame() {
		
		final boolean mouseClicked = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(99, 112, 84, 28);
		contentPane.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(346, 112, 84, 28);
		contentPane.add(passwordField2);
		
		final JButton btnLoginPlayer1 = new JButton("Login player 1");
		btnLoginPlayer1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (StringUtils.isBlank(passwordField1.getPassword().toString()) || (StringUtils.isBlank(usernameField1.getText()))) {
					contentPopUp = new JPanel();
					contentPopUp.setBorder(new EmptyBorder(5,5,5,5));
					setContentPane(contentPopUp);
					contentPopUp.setLayout(null); 
				} else {		
					UserManagement.login(usernameField1.getText(), (passwordField1.getPassword()).toString());
					btnLoginPlayer1.setText("Done!");
					System.out.println("user1: " + UserManagement.user1);
				}
			}
		});
		btnLoginPlayer1.setBounds(66, 151, 117, 29);
		contentPane.add(btnLoginPlayer1);
		
		final JButton btnLoginPlayer2 = new JButton("Login player 2");
		btnLoginPlayer2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!StringUtils.isBlank(passwordField2.getPassword().toString()) && !StringUtils.isBlank(usernameField2.getText())) {
					UserManagement.login(usernameField2.getText(), (passwordField2.getPassword()).toString());
					btnLoginPlayer2.setText("Done!");
					System.out.println("user2: " + UserManagement.user2);
				}
			}
		});
		btnLoginPlayer2.setBounds(272, 151, 117, 29);
		contentPane.add(btnLoginPlayer2);
		
		usernameField1 = new JTextField();
		usernameField1.setBounds(99, 72, 84, 28);
		contentPane.add(usernameField1);
		usernameField1.setColumns(10);
		
		usernameField2 = new JTextField();
		usernameField2.setBounds(346, 72, 84, 28);
		contentPane.add(usernameField2);
		usernameField2.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(16, 78, 85, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(16, 118, 81, 16);
		contentPane.add(lblPassword);
		
		lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(272, 78, 84, 16);
		contentPane.add(lblUsername_1);
		
		lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(272, 118, 84, 16);
		contentPane.add(lblPassword_1);
		
		lblIfYouDont = new JLabel("Click \"Register!\" if you don't have an account");
		lblIfYouDont.setBounds(94, 192, 375, 16);
		contentPane.add(lblIfYouDont);
		
		btnRegister = new JButton("Register!");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				register_frame frame = new register_frame();
				frame.setVisible(true);
			}
		});
		btnRegister.setBounds(168, 217, 117, 29);
		contentPane.add(btnRegister);
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lblLogin.setBounds(199, 18, 101, 45);
		contentPane.add(lblLogin);
		
		btnNewButton = new JButton("Start Game");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				mouseClicked = true;
//				TronGame tron = new TronGame();
//				tron.startGame(UserManagement.user1, UserManagement.user2);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(134, 305, 188, 99);
		contentPane.add(btnNewButton);
	}
}
