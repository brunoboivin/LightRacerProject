package GUIPkg;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import GamePkg.TronGame;
import GridPkg.Grid;
import UserPkg.User;
import UserPkg.UserManagement;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Label;

public class MainMenuPanel extends JPanel {

	/** Description: Class creates the Main Menu Panel and buttons and labels on it.
	 * @authors	Anita Szilagyi, Bruno Boivin, Kaichen Wang, Salman Hashmi, Shahrzad Ti
	 * @version	1.4
	 * @since	2013-11-23	
	 */
	
	public MainMenuPanel() {
		
		setPreferredSize(new Dimension(650,650));
		setLayout(null);
		
		JBackgroundPanel panel = new JBackgroundPanel();
		add(panel);
		panel.setBounds(0, 0, 650, 650);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("5px"),
				ColumnSpec.decode("5px"),
				ColumnSpec.decode("180px"),
				ColumnSpec.decode("36px"),
				ColumnSpec.decode("148px"),
				ColumnSpec.decode("36px"),
				ColumnSpec.decode("180px"),
				ColumnSpec.decode("5px"),
				ColumnSpec.decode("5px"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				RowSpec.decode("25px"),
				RowSpec.decode("70px"),
				RowSpec.decode("6px"),
				RowSpec.decode("40px"),
				RowSpec.decode("6px"),
				RowSpec.decode("260px"),
				RowSpec.decode("5px"),
				RowSpec.decode("40px"),
				RowSpec.decode("6px"),
				RowSpec.decode("40px"),
				RowSpec.decode("6px"),
				RowSpec.decode("75px"),
				RowSpec.decode("6px"),
				RowSpec.decode("40px"),
				RowSpec.decode("25px"),}));
		
		JLabel lblMainMenu = new JLabel("MAIN MENU");
		lblMainMenu.setForeground(new Color(255, 255, 0));
		lblMainMenu.setFont(new Font("STARWARS", Font.BOLD, 30));
		panel.add(lblMainMenu, "4, 2, 5, 1, center, fill");
		
		JLabel lblPlayerDV = new JLabel((UserManagement.user1).getUsername());
		lblPlayerDV.setForeground(new Color(255, 99, 71));
		lblPlayerDV.setFont(new Font("STARWARS", Font.BOLD, 20));
		panel.add(lblPlayerDV, "4, 4, center, fill");
		
		JLabel lblPlayerY = new JLabel(UserManagement.user2.getUsername());
		lblPlayerY.setForeground(new Color(144, 238, 144));
		lblPlayerY.setFont(new Font("STARWARS", Font.BOLD, 20));
		panel.add(lblPlayerY, "8, 4, center, fill");
		
		JButton btnPlayerStats = new JButton("Statistics");
		btnPlayerStats.setBackground(new Color(0, 0, 0));
		btnPlayerStats.setForeground(new Color(255, 255, 0));
		btnPlayerStats.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnPlayerStats.addMouseListener(new MouseAdapter() {
			/**
			 * Description: Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	e of MouseEvent which indicates if a mouse action occurred in a component in this case a click to pull player stats.
			 * @return void
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.playerStatsPanel = new StatisticsPanel(UserManagement.user1, UserManagement.user2);
			    (MainFrame.deck).add("playerStatsPanel", MainFrame.playerStatsPanel);
				((MainFrame) getTopLevelAncestor()).swapView("playerStatsPanel");
			}
		});
		
		JLabel MainDarthVader = new JLabel("");
		MainDarthVader.setIcon(new ImageIcon("img/Main_Vader.png"));
		panel.add(MainDarthVader, "4, 6, 2, 1, left, center");
		
		JLabel MainYoda = new JLabel("");
		MainYoda.setIcon(new ImageIcon("img/Main_Yoda.png"));
		panel.add(MainYoda, "7, 6, 2, 1, right, center");
		
		JLabel DVKeys = new JLabel("");
		DVKeys.setIcon(new ImageIcon("img/Key_WASD.png"));
		panel.add(DVKeys, "4, 8, 1, 3, center, center");
		panel.add(btnPlayerStats, "6, 8, fill, fill");
		
		JButton btnChooseMap = new JButton("Choose map");
		btnChooseMap.setForeground(new Color(255, 255, 0));
		btnChooseMap.setBackground(new Color(0, 0, 0));
		btnChooseMap.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnChooseMap.addMouseListener(new MouseAdapter() {
			/**
			 * Description: Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	arg0 of MouseEvent which indicates if a mouse action occurred in a component in this case a click to prompt for map selection.
			 * @return void
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//GridSelectorOptionPane gridSelector = new GridSelectorOptionPane();
				MainFrame.grid = Grid.promptMapSelection(MainFrame.grid);
				//lblSelectedMap.setText("");
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img/Key_Arrow.png"));
		panel.add(label, "8, 8, 1, 3, center, fill");
		panel.add(btnChooseMap, "6, 10, fill, fill");
		
		
		final JLabel lblSelectedMap = new JLabel("");
		panel.add(lblSelectedMap, "4, 14, fill, fill");
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 0));
		btnNewButton.setFont(new Font("STARWARS", Font.PLAIN, 25));
		btnNewButton.addMouseListener(new MouseAdapter() {
			/**
			 * Description: Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	arg0 of MouseEvent which indicates if a mouse action such as a click occurred in a component.
			 * @return void
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new TronGame(UserManagement.user1, UserManagement.user2, MainFrame.grid);
			}
		});
		panel.add(btnNewButton, "4, 12, 5, 1, fill, fill");
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setForeground(new Color(255, 255, 0));
		btnBack.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnBack.addMouseListener(new MouseAdapter() {
			/**
			 * Description: Invoked when the mouse button has been clicked (pressed and released) on a component.
			 * @param	e of MouseEvent which indicates if a mouse action occurred in a component in this case a click to swap panels.
			 * @return void
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame) getTopLevelAncestor()).swapView("loginPanel");
			}
		});
		panel.add(btnBack, "6, 14, fill, fill");
	}
}
