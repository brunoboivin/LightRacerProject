package GUIPkg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.apache.commons.lang3.StringUtils;

import UserPkg.UserManagement;
import UserPkg.UserRegistrationStatus;
import java.awt.Color;

/**
 * Frame used to registers users into the game system.
 * 
 * @author Anita Szilagyi <anita.szilagyi@mail.mcgill.ca>
 * @version 1.0
 */
public class RegistrationFrame extends JFrame {

	public UserRegistrationStatus registerResult;

	private JBackgroundPanel contentPane;
	private JPasswordField passwordField;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationFrame frame = new RegistrationFrame();
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
	public RegistrationFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JBackgroundPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnCreate = new JButton("Create!");
		btnCreate.setForeground(new Color(255, 255, 0));
		btnCreate.setBackground(new Color(0, 0, 0));
		btnCreate.setBounds(171, 166, 95, 29);
		btnCreate.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse button has been clicked (pressed and
			 * released) on a component.
			 * 
			 * @param e
			 *            of MouseEvent which indicates if a mouse action
			 *            occurred in a component in this case a click to
			 *            register a user.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (StringUtils.isBlank(usernameField.getText())) {
					// Check if the fields were submitted empty
					JOptionPane.showMessageDialog(null, "Please provide a username.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// Check for username to have only letters and numbers
					if (!StringUtils.isAlphanumeric(usernameField.getText())) {
						JOptionPane.showMessageDialog(null, "Username must only contain letters and numbers.",
										"Warning", JOptionPane.WARNING_MESSAGE);
					} else {
						// Check for an empty password field submitted
						if (StringUtils.isBlank(passwordField.getText())) {
							JOptionPane.showMessageDialog(null, "Please provide a password.", "Warning",
									JOptionPane.WARNING_MESSAGE);
						} else {
							// Register the user with the provided username and password
							registerResult = UserManagement.registerUser(usernameField.getText(),
									(passwordField.getText()));
							// Based on the result of the registration status, take different actions
							switch (registerResult) {
							// User registered correctly
							case Success:
								JOptionPane.showMessageDialog(null, "Success!");
								dispose();
								break;
							// User's choice of password does not meet the requirements
							case BadPassword:
								JOptionPane.showMessageDialog(null, "Password must be at least 8 characters, "
														+ "have at least \none upper case and one lower case letter, "
														+ "a digit and \na special character (i.e. ~!@#$%^&*()_+=-.,<>?{}[];)",
												"Error", JOptionPane.ERROR_MESSAGE);
								break;
							// User's choice of username is already taken
							case UsernameTaken:
								JOptionPane.showMessageDialog(null, "Username already in use!", "Error",
										JOptionPane.ERROR_MESSAGE);
								break;
							// Registration failed due to internal reasons
							case FileError:
								JOptionPane.showMessageDialog(null, "Attempt failed, try again!", "Error",
										JOptionPane.ERROR_MESSAGE);
								break;
							}
						}
					}
				}
			}
		});

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBackground(new Color(0, 0, 0));
		lblRegister.setForeground(new Color(255, 255, 0));
		lblRegister.setBounds(97, 20, 113, 35);
		lblRegister.setFont(new Font("STARWARS", Font.PLAIN, 23));

		btnCreate.setFont(new Font("STARWARS", Font.PLAIN, 15));

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBackground(new Color(0, 0, 0));
		lblUsername.setForeground(new Color(255, 255, 0));
		lblUsername.setBounds(22, 78, 113, 21);
		lblUsername.setFont(new Font("STARWARS", Font.PLAIN, 17));

		passwordField = new JPasswordField();
		passwordField.setCaretColor(new Color(255, 255, 0));
		passwordField.setForeground(new Color(255, 255, 0));
		passwordField.setBackground(new Color(0, 0, 0));
		passwordField.setBounds(132, 113, 134, 29);

		usernameField = new JTextField();
		usernameField.setCaretColor(new Color(255, 255, 0));
		usernameField.setForeground(new Color(255, 255, 0));
		usernameField.setBackground(new Color(0, 0, 0));
		usernameField.setBounds(132, 73, 134, 28);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBackground(new Color(0, 0, 0));
		lblPassword.setForeground(new Color(255, 255, 0));
		lblPassword.setBounds(22, 118, 102, 21);
		lblPassword.setFont(new Font("STARWARS", Font.PLAIN, 17));
		contentPane.setLayout(null);
		contentPane.add(lblRegister);
		contentPane.add(btnCreate);
		contentPane.add(lblUsername);
		contentPane.add(passwordField);
		contentPane.add(usernameField);
		contentPane.add(lblPassword);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(255, 255, 0));
		btnExit.setBackground(new Color(0, 0, 0));
		btnExit.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse button has been clicked
			 * (pressed and released) on a component.
			 * 
			 * @param e
			 *            of MouseEvent which indicates if a mouse action
			 *            occurred in a component in this case a click to exit
			 *            the registration frame.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnExit.setBounds(25, 166, 95, 29);
		contentPane.add(btnExit);
	}
}
