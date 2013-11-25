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

public class RegistrationFrame extends JFrame {

	public UserRegistrationStatus registerResult;

	private JPanel contentPane;
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnCreate = new JButton("Create!");
		btnCreate.setBounds(171, 166, 95, 29);
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (StringUtils.isBlank(usernameField.getText())) {
					JOptionPane.showMessageDialog(null, "Please provide a username.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					if (!StringUtils.isAlphanumeric(usernameField.getText())) {
						JOptionPane.showMessageDialog(null, "Username must only contain letters and numbers.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (StringUtils.isBlank(passwordField.getText())) {
							JOptionPane.showMessageDialog(null, "Please provide a password.", "Warning",
									JOptionPane.WARNING_MESSAGE);
						} else {
							registerResult = UserManagement.registerUser(usernameField.getText(), (passwordField.getText()));
							switch (registerResult) {
							case Success: JOptionPane.showMessageDialog(null, "Success!");
							        dispose();
									break;
							case BadPassword: JOptionPane.showMessageDialog(null, "Password must be at least 8 characters, "
											+ "have at least \none upper case and one lower case letter, "
											+ "a digit and \na special character (i.e. ~!@#$%^&*()_+=-.,<>?{}[];)",
											"Error", JOptionPane.ERROR_MESSAGE);
									break;
							case UsernameTaken: JOptionPane.showMessageDialog(null, "Username already in use!", "Error",
										JOptionPane.ERROR_MESSAGE);
									break;
							case FileError: JOptionPane.showMessageDialog(null, "Attempt failed, try again!", "Error",
										JOptionPane.ERROR_MESSAGE);
									break;
							}
						}
					}
				}
			}
		});

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(97, 20, 113, 35);
		lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 23));

		btnCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(33, 75, 87, 21);
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 17));

		passwordField = new JPasswordField();
		passwordField.setBounds(132, 113, 134, 29);

		usernameField = new JTextField();
		usernameField.setBounds(132, 73, 134, 28);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(33, 116, 82, 21);
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		contentPane.setLayout(null);
		contentPane.add(lblRegister);
		contentPane.add(btnCreate);
		contentPane.add(lblUsername);
		contentPane.add(passwordField);
		contentPane.add(usernameField);
		contentPane.add(lblPassword);

		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnExit.setBounds(25, 166, 95, 29);
		contentPane.add(btnExit);
	}
}
