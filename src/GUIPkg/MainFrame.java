package GUIPkg;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GridPkg.Grid;

import javax.swing.BoxLayout;

/**
 * MainFrame class is the controller of the panels.
 * 
 * @authors Anita Szilagyi <anita.szilagyi@mail.mcgill.ca>, Shahrzad
 *          Tighnavardmollasaraei
 *          <shahrzad.tighnavardmollasaraei@mail.mcgill.ca>, Bruno Boivin
 *          <bruno.boivin@mail.mcgill.ca>, Salman Hashmi
 *          <salman.hashmi2@mail.mcgill.ca>
 * @version 1.0
 * @since	2013-11-23
 */
public class MainFrame extends JFrame {

	public static MainMenuPanel mainMenuPanel;
	public static StatisticsPanel playerStatsPanel;
	public static TopTenPanel topTenPanel;
	public static Grid grid;
	static CardLayout cardLayout;
	public static JPanel deck = new JPanel();
	private JPanel contentPane;
	public static int x;
	public static int y;

	/**
	 * Launches the game.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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

		super("Light Battles Demo");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = (JPanel) getContentPane();
		setResizable(false);

		/*
		 * Create the default grid used if the players do not select any of the
		 * custom grids
		 */
		grid = new Grid();

		deck.setLayout(cardLayout = new CardLayout());

		LoginPanel loginPanel = new LoginPanel();
		deck.add("loginPanel", loginPanel);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));

		topTenPanel = new TopTenPanel();
		deck.add("topTenPanel", topTenPanel);

		// Default card
		cardLayout.show(deck, "loginPanel");

		// Set contentPane to add static card panel
		contentPane.add(deck);

		// Set frame parameters
		pack();
		
		// Center frame
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		x = (int) ((dimension.getWidth() - getWidth()) / 2);
		y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);

	}
	/**
	 * Method used to swap the panels based on user input
	 * @param key the name of the next panel to display
	 * @return void
	 */
	public void swapView(String key) {
		cardLayout.show(deck, key);
	}

}
