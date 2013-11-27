package GUIPkg;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import GamePkg.TronGame;
import GridPkg.Grid;
import UserPkg.User;
import UserPkg.UserManagement;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class MainMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public MainMenuPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("center:21px"),
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("112px"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("121px"),},
			new RowSpec[] {
				RowSpec.decode("32px"),
				RowSpec.decode("14px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("35px"),
				RowSpec.decode("40px"),
				RowSpec.decode("40px"),
				RowSpec.decode("31px"),
				RowSpec.decode("28px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("25px"),}));
		
		JButton btnPlayerStats = new JButton("Statistics");
		btnPlayerStats.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnPlayerStats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.playerStatsPanel = new PlayerStatsPanel(UserManagement.user1, UserManagement.user2);
			    (MainFrame.deck).add("playerStatsPanel", MainFrame.playerStatsPanel);
				((MainFrame) getTopLevelAncestor()).swapView("playerStatsPanel");
			}
		});
		panel.add(btnPlayerStats, "3, 5, 3, 1, fill, fill");
		
		JLabel lblPlayer = new JLabel((UserManagement.user1).getUsername());
		lblPlayer.setFont(new Font("STARWARS", Font.BOLD, 17));
		panel.add(lblPlayer, "2, 4, center, fill");
		
		JLabel lblNewLabel = new JLabel(UserManagement.user2.getUsername());
		lblNewLabel.setFont(new Font("STARWARS", Font.BOLD, 17));
		panel.add(lblNewLabel, "6, 4, center, fill");
		
		JButton btnChooseMap = new JButton("Choose map");
		btnChooseMap.setFont(new Font("STARWARS", Font.PLAIN, 15));
		btnChooseMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//GridSelectorOptionPane gridSelector = new GridSelectorOptionPane();
				MainFrame.grid = Grid.promptMapSelection(MainFrame.grid);
				//lblSelectedMap.setText("");
			}
		});
		panel.add(btnChooseMap, "3, 6, 3, 1, fill, fill");
		
		JLabel label = new JLabel("");
		panel.add(label, "2, 8, center, center");
		
		JLabel lblNewLabel_1 = new JLabel("MAIN MENU");
		lblNewLabel_1.setFont(new Font("STARWARS", Font.BOLD, 30));
		panel.add(lblNewLabel_1, "3, 1, 3, 2, center, fill");
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("STARWARS", Font.PLAIN, 14));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame) getTopLevelAncestor()).swapView("loginPanel");
			}
		});
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.setFont(new Font("STARWARS", Font.PLAIN, 20));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TronGame tron = new TronGame(UserManagement.user1, UserManagement.user2, MainFrame.grid);
			}
		});
		panel.add(btnNewButton, "3, 8, 3, 3, fill, fill");
		panel.add(btnBack, "4, 12, fill, fill");
		
		
		final JLabel lblSelectedMap = new JLabel("");
		panel.add(lblSelectedMap, "4, 14, fill, fill");
	}
}
