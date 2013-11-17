package UserPkg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.apache.commons.lang3.StringUtils;
import javax.swing.SwingConstants;

public class register_frame extends JFrame {

	public int registerResult;

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
					register_frame frame = new register_frame();
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
	public register_frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblRegister.setBounds(172, 19, 106, 52);
		contentPane.add(lblRegister);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblUsername.setBounds(117, 83, 106, 16);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblPassword.setBounds(117, 125, 106, 16);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(249, 121, 93, 28);
		contentPane.add(passwordField);

		usernameField = new JTextField();
		usernameField.setBounds(249, 79, 93, 28);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		final JLabel lblRegisterResult = new JLabel("");
		lblRegisterResult.setVerticalAlignment(SwingConstants.TOP);
		lblRegisterResult.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblRegisterResult.setBounds(52, 202, 361, 37);
		contentPane.add(lblRegisterResult);

		JButton btnCreate = new JButton("Create!");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!StringUtils.isAlphanumeric(usernameField.getText())) {
					lblRegisterResult.setText("Username must only be alpha-numeric!");
				} else {
					if (StringUtils.isBlank(passwordField.getPassword().toString())) {
						lblRegisterResult.setText("Password cannot be left empty!");
					} else {
						registerResult = UserManagement.registerUser(usernameField.getText(), (passwordField.getPassword()).toString());
						if (registerResult == 2) {
							lblRegisterResult.setText("Success!");
						} 
						if (registerResult == 1) {
							lblRegisterResult.setText("Username already in use");
						}
						if (registerResult == 0) {
							lblRegisterResult.setText("Attempt failed. Try again.");
						}
					}
				}
			}
		});

		btnCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnCreate.setBounds(171, 161, 117, 29);
		contentPane.add(btnCreate);
	}

}
