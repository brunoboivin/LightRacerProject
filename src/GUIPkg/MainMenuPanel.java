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

public class MainMenuPanel extends JPanel {

	/**
	 * Create the panel.
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
				RowSpec.decode("5px"),
				RowSpec.decode("40px"),
				RowSpec.decode("5px"),
				RowSpec.decode("40px"),
				RowSpec.decode("5px"),
				RowSpec.decode("40px"),
				RowSpec.decode("5px"),
				RowSpec.decode("40px"),
				RowSpec.decode("40px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("75px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("40px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_1 = new JLabel("MAIN MENU");
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setFont(new Font("STARWARS", Font.BOLD, 30));
		panel.add(lblNewLabel_1, "4, 2, 5, 1, center, fill");
		
		JLabel lblPlayer = new JLabel((UserManagement.user1).getUsername());
		lblPlayer.setForeground(new Color(255, 99, 71));
		lblPlayer.setFont(new Font("STARWARS", Font.BOLD, 17));
		panel.add(lblPlayer, "4, 4, center, fill");
		
		JLabel lblNewLabel = new JLabel(UserManagement.user2.getUsername());
		lblNewLabel.setForeground(new Color(144, 238, 144));
		lblNewLabel.setFont(new Font("STARWARS", Font.BOLD, 17));
		panel.add(lblNewLabel, "8, 4, center, fill");
		
		JButton btnPlayerStats = new JButton("Statistics");
		btnPlayerStats.setBackground(new Color(0, 0, 0));
		btnPlayerStats.setForeground(new Color(255, 255, 0));
		btnPlayerStats.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnPlayerStats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.playerStatsPanel = new StatisticsPanel(UserManagement.user1, UserManagement.user2);
			    (MainFrame.deck).add("playerStatsPanel", MainFrame.playerStatsPanel);
				((MainFrame) getTopLevelAncestor()).swapView("playerStatsPanel");
			}
		});
		panel.add(btnPlayerStats, "6, 6, fill, fill");
		
		JLabel label = new JLabel("");
		panel.add(label, "2, 8, center, center");
		
		JButton btnChooseMap = new JButton("Choose map");
		btnChooseMap.setForeground(new Color(255, 255, 0));
		btnChooseMap.setBackground(new Color(0, 0, 0));
		btnChooseMap.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnChooseMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//GridSelectorOptionPane gridSelector = new GridSelectorOptionPane();
				MainFrame.grid = Grid.promptMapSelection(MainFrame.grid);
				//lblSelectedMap.setText("");
			}
		});
		panel.add(btnChooseMap, "6, 8, fill, fill");
		
		
		final JLabel lblSelectedMap = new JLabel("");
		panel.add(lblSelectedMap, "4, 14, fill, fill");
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 0));
		btnNewButton.setFont(new Font("STARWARS", Font.PLAIN, 25));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TronGame tron = new TronGame(UserManagement.user1, UserManagement.user2, MainFrame.grid);
			}
		});
		panel.add(btnNewButton, "4, 23, 5, 1, fill, fill");
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setForeground(new Color(255, 255, 0));
		btnBack.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame) getTopLevelAncestor()).swapView("loginPanel");
			}
		});
		panel.add(btnBack, "6, 27, fill, fill");
	}
}
