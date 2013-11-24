package GUIPkg;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import UserPkg.User;
import GridPkg.GridSelectorGUI;

public class MainFrame extends JFrame{

	static CardLayout cardLayout;
	static JPanel card = new JPanel();
	private JPanel contentPane;
	public LoginFrame loginFrame;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(new LoginFrame(), new User("Neve", "123.Neve"), new User("Bruno", "123.Bruno")); //would need to pass 2 users as parameters
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * Create the frame.
	 */
	public MainFrame(LoginFrame loginFrame, User userA, User userB) {

		this.loginFrame = loginFrame;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = (JPanel) getContentPane();
		card.setLayout(cardLayout = new CardLayout());
		
		

		MainMenuPanel mainMenuPanel = new MainMenuPanel(userA, userB);
		TopTenPanel topTenPanel = new TopTenPanel();
		
		card.add("mainMenuPanel", mainMenuPanel);
		card.add("topTenPanel", topTenPanel);
		
		//default card 
		cardLayout.show(card, "mainMenuPanel");
		
		//set contentPane to add static card panel
		contentPane.add(card);
		
		//set frame parameters
		setSize(500,350);
		
		//center frame
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	}
	
	public void swapView(String key){
		cardLayout.show(card, key);
	}

}
