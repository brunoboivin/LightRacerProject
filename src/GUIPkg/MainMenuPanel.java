package GUIPkg;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import GamePkg.TronGame;
import UserPkg.User;
import UserPkg.UserManagement;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TronGame tron = new TronGame(UserManagement.user1, UserManagement.user2);
			}
		});
		btnNewButton.setBounds(168, 124, 112, 44);
		panel.add(btnNewButton);
		
		JButton btnTopPlayers = new JButton("Top 10 Players");
		btnTopPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTopPlayers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainFrame.topTenPanel = new TopTenPanel();
			    (MainFrame.deck).add("topTenPanel", MainFrame.topTenPanel);
				((MainFrame) getTopLevelAncestor()).swapView("topTenPanel");
			}
		});
		btnTopPlayers.setBounds(307, 234, 121, 23);
		panel.add(btnTopPlayers);
		
		JLabel lblPlayer = new JLabel((UserManagement.user1).getUsername());
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPlayer.setBounds(44, 57, 72, 38);
		panel.add(lblPlayer);
		
		JLabel lblNewLabel = new JLabel(UserManagement.user2.getUsername());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(337, 57, 72, 38);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(41, 213, 46, 14);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("MAIN MENU");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(168, 32, 112, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame) getTopLevelAncestor()).swapView("loginPanel");
			}
		});
		btnBack.setBounds(27, 234, 121, 23);
		panel.add(btnBack);
		
		JButton btnPlayerStats = new JButton("Statistics");
		btnPlayerStats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.playerStatsPanel = new PlayerStatsPanel(UserManagement.user1, UserManagement.user2);
			    (MainFrame.deck).add("playerStatsPanel", MainFrame.playerStatsPanel);
				((MainFrame) getTopLevelAncestor()).swapView("playerStatsPanel");
			}
		});
		btnPlayerStats.setBounds(307, 199, 121, 23);
		panel.add(btnPlayerStats);
		
		
		final JLabel lblSelectedMap = new JLabel("");
		lblSelectedMap.setBounds(168, 269, 97, 19);
		panel.add(lblSelectedMap);
		
		JButton btnChooseMap = new JButton("Choose map");
		btnChooseMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GridSelectorOptionPane gridSelector = new GridSelectorOptionPane();
				lblSelectedMap.setText("");
			}
		});
		btnChooseMap.setBounds(307, 269, 121, 25);
		panel.add(btnChooseMap);
	}
}
