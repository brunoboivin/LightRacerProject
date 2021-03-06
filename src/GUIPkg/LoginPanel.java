package GUIPkg;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Cursor;

/**
 * Panel used by the users when logging in; it is the first panel of the game.
 * 
 * @authors Anita Szilagyi <anita.szilagyi@mail.mcgill.ca>, Shahrzad Tighnavardmollasaraei <shahrzad.tighnavardmollasaraei@mail.mcgill.ca>
 * @version 1.0
 */
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

	int usersLoggedIn = 0;
	boolean btn1AsLogout = false;
	boolean btn2AsLogout = false;
	UserLoginStatus loginResult;
	Color diabledField, enabledField;
	private JButton btnTopPlayers;
	private JLabel DarthVader;
	private JLabel Yoda;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public LoginPanel() {
		setPreferredSize(new Dimension(650, 650));
		setLayout(null);

		JBackgroundPanel panel = new JBackgroundPanel();
		panel.setBounds(0, 0, 650, 650);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("25px"), ColumnSpec.decode("116px"),
				ColumnSpec.decode("5px"), ColumnSpec.decode("116px"),
				ColumnSpec.decode("5px"), ColumnSpec.decode("116px"),
				ColumnSpec.decode("5px"), ColumnSpec.decode("116px"),
				ColumnSpec.decode("5px"), ColumnSpec.decode("116px"),
				ColumnSpec.decode("25px"), }, new RowSpec[] {
				RowSpec.decode("25px"), RowSpec.decode("70px"),
				RowSpec.decode("5px"), RowSpec.decode("260px"),
				RowSpec.decode("5px"), RowSpec.decode("40px"),
				RowSpec.decode("10px"), RowSpec.decode("40px"),
				RowSpec.decode("15px"), RowSpec.decode("40px"),
				RowSpec.decode("5px"), RowSpec.decode("40px"),
				RowSpec.decode("5px"), RowSpec.decode("40px"),
				RowSpec.decode("5px"), RowSpec.decode("20px"),
				RowSpec.decode("25px"), }));

		add(panel);
		diabledField = Color.LIGHT_GRAY;
		usersLoggedIn = 0;

		// Define the specifications for the second password field
		passwordField2 = new JPasswordField();
		passwordField2.setDisabledTextColor(new Color(0, 0, 0));
		passwordField2.setCaretColor(new Color(255, 255, 0));
		passwordField2.setForeground(new Color(255, 255, 255));
		passwordField2.setBackground(new Color(0, 0, 0));
		passwordField2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField2.setFocusTraversalKeysEnabled(false);
		/*
		 * In order to avoid having to add a FocusListener, a KeyListener was
		 * implemented to create a more intuitive navigation among the fields by
		 * pressing the TAB and SHIFT + TAB keys
		 */
		passwordField2.addKeyListener(new KeyAdapter() {
			/**
			 * Invoked when the keyboard key has been pressed.
			 * @param key pressed by user (Action taken for either TAB or SHIFT + TAB).
			 */
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

		// Define the specifications for the first password field
		passwordField1 = new JPasswordField();
		passwordField1.setCaretColor(new Color(255, 255, 0));
		passwordField1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		passwordField1.setDisabledTextColor(Color.BLACK);
		passwordField1.setForeground(new Color(255, 255, 255));
		passwordField1.setBackground(Color.BLACK);
		passwordField1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField1.setFocusTraversalKeysEnabled(false);
		/*
		 * In order to avoid having to add a FocusListener, a KeyListener was
		 * implemented to create a more intuitive navigation among the fields by
		 * pressing the TAB and SHIFT + TAB keys
		 */
		passwordField1.addKeyListener(new KeyAdapter() {
			/**
			 * Invoked when the keyboard key has been pressed.
			 * @param key pressed by user (Action taken for either TAB or SHIFT + TAB).
			 */
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
		enabledField = passwordField1.getBackground();

		// Add image of Darth Vader for user 1
		DarthVader = new JLabel("");
		DarthVader.setIcon(new ImageIcon("res/img/Login_Vader.png"));
		panel.add(DarthVader, "2, 4, 4, 1, center, center");

		// Add image of Yoda for user 2
		Yoda = new JLabel("");
		Yoda.setIcon(new ImageIcon("res/img/Login_Yoda.png"));
		panel.add(Yoda, "8, 4, 3, 1, center, center");

		panel.add(passwordField1, "4, 8, fill, fill");
		panel.add(passwordField2, "10, 8, fill, fill");

		// Define the button for logging in user 1 (i.e. Darth Vader)
		final JButton btnLoginPlayer1 = new JButton("Login DarthVader");
		btnLoginPlayer1.setBackground(new Color(0, 0, 0));
		btnLoginPlayer1.setForeground(new Color(255, 99, 71));
		btnLoginPlayer1.setFont(new Font("STARWARS", Font.PLAIN, 20));
		/*
		 * The login button has 2 roles: 
		 * - when the user first clicks it, he/she gets logged in and the button
		 * changes to a logout button
		 * - when the user logs out, he/she gets logged out and the button changes 
		 * back to a login button
		 */
		btnLoginPlayer1.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	e of MouseEvent which indicates if a mouse action occurred in a component in this case a click to log in a user.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// Action taken if button is used as a login button
				if (btn1AsLogout == false) {
					// Check to see if the user entered the password and username
					if (StringUtils.isBlank(passwordField1.getText()) || (StringUtils.isBlank(usernameField1.getText()))) {
						JOptionPane.showMessageDialog(null, "Please enter the username and/or the password.",
								"Warning", JOptionPane.WARNING_MESSAGE);
					// If he/she did, log him/her in
					} else {
						loginResult = UserManagement.login(1, usernameField1.getText(),
								(passwordField1.getText()));
						// Based on the result of the login method, display the proper message
						switch (loginResult) {
						case UserLoggedIn:
							JOptionPane.showMessageDialog(null, "User already logged in!", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						case UsernameNotFound:
							JOptionPane.showMessageDialog(null, "Username not found.", "Warning",
									JOptionPane.WARNING_MESSAGE);
							break;
						case WrongPassword:
							JOptionPane.showMessageDialog(null, "Provided username and password combination is invalid!",
									"Error", JOptionPane.ERROR_MESSAGE);
							break;
						// In case of Success, change the button to a logout button and make changes to the panel	
						case Success:
							btnLoginPlayer1.setText("Logout " + usernameField1.getText());
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
						/*
						 * If this is the second user logging in, enable the Main Menu button (i.e. allow
						 * the users to play
						 */
						if (usersLoggedIn == 2) {
							btnMainMenu.setEnabled(true);
						}
					}
				// Action taken if the button is a logout button
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
		panel.add(btnLoginPlayer1, "2, 10, 3, 1, fill, fill");

		/*
		 * The login button has 2 roles: 
		 * - when the user first clicks it, he/she gets logged in and the button
		 * changes to a logout button
		 * - when the user logs out, he/she gets logged out and the button changes 
		 * back to a login button
		 */
		final JButton btnLoginPlayer2 = new JButton("Login Yoda");
		btnLoginPlayer2.setBackground(new Color(0, 0, 0));
		btnLoginPlayer2.setForeground(new Color(144, 238, 144));
		btnLoginPlayer2.setFont(new Font("STARWARS", Font.PLAIN, 20));
		btnLoginPlayer2.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	e of MouseEvent which indicates if a mouse action occurred in a component in this case a click to login a user.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// Action taken if button is used as a login button
				if (btn2AsLogout == false) {
					// Check to see if the user entered the password and username
					if (StringUtils.isBlank(passwordField2.getText()) || (StringUtils.isBlank(usernameField2.getText()))) {
						JOptionPane.showMessageDialog(null, "Please enter the username and/or the password.",
								"Warning", JOptionPane.WARNING_MESSAGE);
					// If he/she did, log him/her in
					} else {
						loginResult = UserManagement.login(2, usernameField2.getText(),
								(passwordField2.getText()));
						// Based on the result of the login method, display the proper message
						switch (loginResult) {
						case UserLoggedIn:
							JOptionPane.showMessageDialog(null, "User already logged in!", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						case UsernameNotFound:
							JOptionPane.showMessageDialog(null, "Username not found.", "Warning",
									JOptionPane.WARNING_MESSAGE);
							break;
						case WrongPassword:
							JOptionPane.showMessageDialog(null, "Provided username and password combination is invalid.",
									"Error", JOptionPane.ERROR_MESSAGE);
							break;
						
						// In case of Success, change the button to a logout button and make changes to the panel		
						case Success:
							btnLoginPlayer2.setText("Logout " + usernameField2.getText());
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
						/*
						 * If this is the second user logging in, enable the Main Menu button (i.e. allow
						 * the users to play
						 */
						if (usersLoggedIn == 2) {
							btnMainMenu.setEnabled(true);
						}
					}
				// Action taken if the button is a logout button	
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
		panel.add(btnLoginPlayer2, "8, 10, 3, 1, fill, fill");

		// Define the specifications for the first username field
		usernameField1 = new JTextField();
		usernameField1.setCaretColor(new Color(255, 255, 0));
		usernameField1.setDisabledTextColor(Color.BLACK);
		usernameField1.setForeground(new Color(255, 255, 255));
		usernameField1.setBackground(Color.BLACK);
		usernameField1.setFont(new Font("STARWARS", Font.PLAIN, 15));
		usernameField1.setFocusTraversalKeysEnabled(false);
		usernameField1.addKeyListener(new KeyAdapter() {
			/**
			 * Invoked when the keyboard key has been pressed.
			 * @param key pressed by user (Action taken for either TAB or SHIFT + TAB).
			 */
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
		panel.add(usernameField1, "4, 6, center, fill");
		usernameField1.setColumns(10);

		// Define the specifications for the second username field
		usernameField2 = new JTextField();
		usernameField2.setDisabledTextColor(new Color(0, 0, 0));
		usernameField2.setCaretColor(new Color(255, 255, 0));
		usernameField2.setForeground(new Color(255, 255, 255));
		usernameField2.setBackground(new Color(0, 0, 0));
		usernameField2.setFont(new Font("STARWARS", Font.PLAIN, 15));
		usernameField2.setFocusTraversalKeysEnabled(false);
		usernameField2.addKeyListener(new KeyAdapter() {
			/**
			 * Invoked when the keyboard key has been pressed.
			 * @param key pressed by user (Action taken for either TAB or SHIFT + TAB).
			 */
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
		panel.add(usernameField2, "10, 6, fill, fill");
		usernameField2.setColumns(10);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 0));
		lblUsername.setFont(new Font("STARWARS", Font.PLAIN, 15));
		panel.add(lblUsername, "2, 6, center, center");

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 0));
		lblPassword.setFont(new Font("STARWARS", Font.PLAIN, 15));
		panel.add(lblPassword, "2, 8, center, center");

		lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setForeground(new Color(255, 255, 0));
		lblUsername_1.setFont(new Font("STARWARS", Font.PLAIN, 15));
		panel.add(lblUsername_1, "8, 6, center, center");

		lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setForeground(new Color(255, 255, 0));
		lblPassword_1.setFont(new Font("STARWARS", Font.PLAIN, 15));
		panel.add(lblPassword_1, "8, 8, center, center");

		lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(new Color(255, 255, 0));
		lblLogin.setFont(new Font("STARWARS", Font.BOLD, 30));
		panel.add(lblLogin, "4, 2, 5, 1, center, center");
		
		btnTopPlayers = new JButton("Top 10 players");
		btnTopPlayers.setBackground(new Color(0, 0, 0));
		btnTopPlayers.setForeground(new Color(255, 255, 0));
		btnTopPlayers.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnTopPlayers.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	e of MouseEvent which indicates if a mouse action occurred in a component in this case a click to display top ten players.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.topTenPanel = new TopTenPanel();
				(MainFrame.deck).add("topTenPanel", MainFrame.topTenPanel);
				((MainFrame) getTopLevelAncestor()).swapView("topTenPanel");
			}
		});
		panel.add(btnTopPlayers, "2, 12, 3, 1, fill, fill");

		// Main menu button, disabled by default
		btnMainMenu = new JButton("Go to Main Menu!");
		btnMainMenu.setBackground(new Color(0, 0, 0));
		btnMainMenu.setForeground(new Color(255, 255, 0));
		btnMainMenu.setEnabled(false);
		btnMainMenu.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	e of MouseEvent which indicates if a mouse action occurred in a component in this case a click to move to the main menu.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnMainMenu.isEnabled()) {
					// Add MainMenuPanel to MainFrame
					MainFrame.mainMenuPanel = new MainMenuPanel();
					MainFrame.deck.add("mainMenuPanel", MainFrame.mainMenuPanel);
					((MainFrame) getTopLevelAncestor()).swapView("mainMenuPanel");

					// Add PlayerStatsPanel to MainFrame
					MainFrame.playerStatsPanel = new StatisticsPanel(UserManagement.user1, UserManagement.user2);
					(MainFrame.deck).add("playerStatsPanel",MainFrame.playerStatsPanel);
				}
			}
		});
		btnMainMenu.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnMainMenu, "8, 12, 3, 1, fill, fill");

		// Register button
		btnRegister = new JButton("Register!");
		btnRegister.setBackground(new Color(0, 0, 0));
		btnRegister.setForeground(new Color(255, 255, 0));
		btnRegister.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnRegister.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	e of MouseEvent which indicates if a mouse action occurred in a component in this case a click to launch the registration frame.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationFrame frame = new RegistrationFrame();
				frame.setVisible(true);
			}
		});
		panel.add(btnRegister, "6, 14, fill, fill");

		lblIfYouDont = new JLabel(
				"Click \"Register!\" if you don't have an account");
		lblIfYouDont.setForeground(new Color(255, 255, 0));
		lblIfYouDont.setFont(new Font("STARWARS", Font.PLAIN, 10));
		panel.add(lblIfYouDont, "4, 16, 5, 1, center, fill");

	}

}
