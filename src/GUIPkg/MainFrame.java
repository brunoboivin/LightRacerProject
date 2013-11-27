package GUIPkg;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import UserPkg.User;
import UserPkg.UserManagement;
import GUIPkg.GridSelectorOptionPane;
import GridPkg.Grid;

import javax.swing.BoxLayout;

public class MainFrame extends JFrame{

	public static MainMenuPanel mainMenuPanel;
	public static PlayerStatsPanel playerStatsPanel;
	public static TopTenPanel topTenPanel;
	public static Grid grid;
	static CardLayout cardLayout;
	public static JPanel deck = new JPanel();
	private JPanel contentPane;
	public static int x;
	public static int y;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(); //would need to pass 2 users as parameters
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
	public MainFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = (JPanel) getContentPane();
		
		
		grid = new Grid();
		deck.setLayout(cardLayout = new CardLayout());
		
		LoginPanel loginPanel = new LoginPanel();
		topTenPanel = new TopTenPanel();
		
		deck.add("loginPanel", loginPanel);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));

//		deck.add("mainMenuPanel", mainMenuPanel);
//		if (mainMenuPanel != null && mainMenuPanel.isFocusOwner()) {
//			mainMenuPanel.revalidate();
//		}
		deck.add("topTenPanel", topTenPanel);
		
		//default card 
		cardLayout.show(deck, "loginPanel");
		
		//set contentPane to add static card panel
		contentPane.add(deck);
		
		//set frame parameters
		setSize(450,400);
		
		//center frame
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	}
	
	public void swapView(String key){
		cardLayout.show(deck, key);
	}

}
