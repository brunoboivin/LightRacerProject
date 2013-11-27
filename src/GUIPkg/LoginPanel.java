package GUIPkg;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.apache.commons.lang3.StringUtils;

import UserPkg.UserLoginStatus;
import UserPkg.UserManagement;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPanel extends JPanel {

	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField usernameField1;
	private JTextField usernameField2;
	private JLabel lblUsername_1;
	private JLabel lblPassword_1;
	private JLabel lblIfYouDont;
	private JButton btnRegister;
	private JLabel lblLogin;
	private JButton btnMainMenu;
	
	private static final Font SMALL_FONT = new Font("STARWARS", Font.PLAIN, 18);
	private static final Font LARGE_FONT = new Font("STARWARS", Font.BOLD, 25);
	
	
	int usersLoggedIn = 0;
	boolean btn1AsLogout = false;
	boolean btn2AsLogout = false;
	UserLoginStatus loginResult;
	Color diabledField, enabledField;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public LoginPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(null);

		diabledField = Color.LIGHT_GRAY;
		usersLoggedIn = 0;

		passwordField1 = new JPasswordField();
		passwordField1.setFocusTraversalKeysEnabled(false);
		passwordField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
						btnRegister.transferFocus();
					} else {
						if (usernameField2.isEnabled()) {
							usernameField1.transferFocus();
						} else {
							passwordField2.transferFocus();
						}
					}
				}
			}
		});
		passwordField1.setBounds(109, 112, 84, 28);
		enabledField = passwordField1.getBackground();
		panel.add(passwordField1);

		passwordField2 = new JPasswordField();
		passwordField2.setFocusTraversalKeysEnabled(false);
		passwordField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_TAB) {
					if ((key.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
						usernameField1.transferFocus();
					} else {
						passwordField2.transferFocus();
					}
				}
			}
		});
		passwordField2.setBounds(333, 112, 84, 28);
		panel.add(passwordField2);

		btnMainMenu = new JButton("Go to main menu!");
		btnMainMenu.setEnabled(false);
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnMainMenu.isEnabled()) {
					//add MainMenuPanel to MainFrame
					MainFrame.mainMenuPanel = new MainMenuPanel();
				    MainFrame.deck.add("mainMenuPanel", MainFrame.mainMenuPanel);
					((MainFrame) getTopLevelAncestor()).swapView("mainMenuPanel");
					
					//add PlayerStatsPanel to MainFrame
					MainFrame.playerStatsPanel = new PlayerStatsPanel(UserManagement.user1, UserManagement.user2);
				    (MainFrame.deck).add("playerStatsPanel", MainFrame.playerStatsPanel);
				}
			}
		});
		btnMainMenu.setFont(SMALL_FONT);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMainMenu.setBounds(117, 295, 220, 45);
		panel.add(btnMainMenu);

		final JButton btnLoginPlayer1 = new JButton("Login player 1");
		btnLoginPlayer1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btn1AsLogout == false) {
					if (StringUtils.isBlank(passwordField1.getText()) || (StringUtils.isBlank(usernameField1.getText()))) {
						JOptionPane.showMessageDialog(null, "Please enter the username and/or the password.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} else {		
						loginResult = UserManagement.login(1, usernameField1.getText(), (passwordField1.getText()));
						switch (loginResult) {
						case UserLoggedIn: JOptionPane.showMessageDialog(null, "User already logged in!", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
						case UsernameNotFound: JOptionPane.showMessageDialog(null, "Username not found.", "Warning",
								JOptionPane.WARNING_MESSAGE);
						break;
						case WrongPassword: JOptionPane.showMessageDialog(null, "Provided username and password combination is invalid!", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
						case Success: btnLoginPlayer1.setText("Logout " + usernameField1.getText());
						btn1AsLogout = true;
						usernameField1.setEnabled(false);
						passwordField1.setEnabled(false);
						usernameField1.setBackground(diabledField);
						passwordField1.setBackground(diabledField);
						usersLoggedIn++;
						usernameField1.setText("");
						passwordField1.setText("");
						break;
						}
						if (usersLoggedIn == 2) {
							btnMainMenu.setEnabled(true);
						}
					}
				} else {
					UserManagement.logout(UserManagement.user1);
					btnLoginPlayer1.setText("Login player 1");
					usernameField1.setBackground(enabledField);
					passwordField1.setBackground(enabledField);
					usersLoggedIn--;
					btnMainMenu.setEnabled(false);
					btn1AsLogout = false;
					usernameField1.setEnabled(true);
					passwordField1.setEnabled(true);
				}
			}
		});
		btnLoginPlayer1.setBounds(6, 152, 187, 29);
		panel.add(btnLoginPlayer1);

		final JButton btnLoginPlayer2 = new JButton("Login player 2");
		btnLoginPlayer2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btn2AsLogout == false) {
					if (StringUtils.isBlank(passwordField2.getText()) || (StringUtils.isBlank(usernameField2.getText()))) {
						JOptionPane.showMessageDialog(null, "Please enter the username and/or the password.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} else {		
						loginResult = UserManagement.login(2, usernameField2.getText(), (passwordField2.getText()));
						switch (loginResult) {
						case UserLoggedIn: JOptionPane.showMessageDialog(null, "User already logged in!", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
						case UsernameNotFound: JOptionPane.showMessageDialog(null, "Username not found.", "Warning",
								JOptionPane.WARNING_MESSAGE);
						break;
						case WrongPassword: JOptionPane.showMessageDialog(null, "Provided username and password combination is invalid.", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
						case Success: btnLoginPlayer2.setText("Logout " + usernameField2.getText());
						btn2AsLogout = true;
						usernameField2.setEnabled(false);
						passwordField2.setEnabled(false);
						usernameField2.setBackground(diabledField);
						passwordField2.setBackground(diabledField);
						usernameField2.setText("");
						passwordField2.setText("");
						usersLoggedIn++;
						break;
						}
						if (usersLoggedIn == 2) {
							btnMainMenu.setEnabled(true);
						}
					}
				} else {
					UserManagement.logout(UserManagement.user2);
					usernameField2.setBackground(enabledField);
					passwordField2.setBackground(enabledField);
					usersLoggedIn--;
					btnMainMenu.setEnabled(false);
					btn2AsLogout = false;
					btnLoginPlayer2.setText("Login player 2");
					usernameField2.setEnabled(true);
					passwordField2.setEnabled(true);
				}
			} 
		});
		btnLoginPlayer2.setBounds(241, 152, 187, 29);
		panel.add(btnLoginPlayer2);

		usernameField1 = new JTextField();
		usernameField1.setFocusTraversalKeysEnabled(false);
		usernameField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {

				if (key.getKeyCode() == KeyEvent.VK_TAB) {
					if ((key.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
						btnLoginPlayer2.transferFocus();
					} else {
						usernameField2.transferFocus();
					}
				}

			}
		});
		usernameField1.setBounds(109, 72, 84, 28);
		panel.add(usernameField1);
		usernameField1.setColumns(10);

		usernameField2 = new JTextField();
		usernameField2.setFocusTraversalKeysEnabled(false);
		usernameField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_TAB) {
					if ((key.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
						if (passwordField1.isEnabled()) {
							usernameField2.transferFocus();
						} else {
							btnLoginPlayer2.transferFocus();
						}
					} else {
						passwordField1.transferFocus();
					}
				}
			}
		});
		usernameField2.setBounds(333, 72, 84, 28);
		panel.add(usernameField2);
		usernameField2.setColumns(10);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(22, 84, 85, 16);
		panel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(22, 118, 81, 16);
		panel.add(lblPassword);

		lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(257, 78, 84, 16);
		panel.add(lblUsername_1);

		lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(257, 118, 84, 16);
		panel.add(lblPassword_1);

		lblIfYouDont = new JLabel("Click \"Register!\" if you don't have an account");
		lblIfYouDont.setBounds(94, 206, 296, 16);
		panel.add(lblIfYouDont);

		btnRegister = new JButton("Register!");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationFrame frame = new RegistrationFrame();
				frame.setVisible(true);
			}
		});
		btnRegister.setBounds(168, 234, 117, 29);
		panel.add(btnRegister);

		lblLogin = new JLabel("Login");
		lblLogin.setFont(LARGE_FONT);
		lblLogin.setBounds(184, 16, 101, 45);
		panel.add(lblLogin);

		add(panel);
	}
}
