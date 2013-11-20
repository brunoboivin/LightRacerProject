package GUIPkg;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import GamePkg.TronGame;
import UserPkg.UserManagement;
import UserPkg.login_frame;
import UserPkg.register_frame;

public class ChooseMapsDisplay extends JFrame  {

	private JPanel contentPane;
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
	private JButton btnStartGame;
	int usersLoggedIn = 0;
	int loginResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}
	
	public ChooseMapsDisplay() {

		usersLoggedIn = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		

		btnStartGame = new JButton("Start Game!");
		btnStartGame.setEnabled(false);
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnStartGame.isEnabled()) {
					TronGame tron = new TronGame(UserManagement.user2, UserManagement.user1);
				}
			}
		});
		btnStartGame.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStartGame.setBounds(136, 290, 182, 72);
		contentPane.add(btnStartGame);

		
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

		lblLogin = new JLabel("SELECT A MAP");
		lblLogin.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lblLogin.setBounds(199, 18, 101, 45);
		contentPane.add(lblLogin);
	}

}