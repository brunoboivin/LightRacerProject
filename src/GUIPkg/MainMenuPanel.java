package GUIPkg;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import GamePkg.TronGame;
import StatisticsPkg.PairRecord;
import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;
import UserPkg.User;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenuPanel(final User userA, final User userB) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		PlayerRecord playerRecord1 = Statistics.getPlayerStat(userA.getUsername());
		PlayerRecord playerRecord2 = Statistics.getPlayerStat(userB.getUsername());
		
		PairRecord pairRecord = Statistics.getPairRecord(userA.getUsername(), userB.getUsername());
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TronGame tron = new TronGame(userA, userB);
			}
		});
		btnNewButton.setBounds(179, 213, 112, 44);
		panel.add(btnNewButton);
		
		JButton btnTopPlayers = new JButton("Top 10 Players");
		btnTopPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTopPlayers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				((MainFrame)getTopLevelAncestor()).swapView("topTenPanel");
			}
		});
		btnTopPlayers.setBounds(307, 234, 121, 23);
		panel.add(btnTopPlayers);
		
		JLabel lblPlayer = new JLabel(playerRecord1.getUsername());
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPlayer.setBounds(44, 57, 72, 38);
		panel.add(lblPlayer);
		
		JLabel lblNewLabel = new JLabel(playerRecord2.getUsername());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(337, 57, 72, 38);
		panel.add(lblNewLabel);
		
		JLabel lblGamesWon = new JLabel("Games Played");
		lblGamesWon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGamesWon.setBounds(179, 127, 95, 23);
		panel.add(lblGamesWon);
		
		JLabel lblGamesWon_2 = new JLabel("Games Won");
		lblGamesWon_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGamesWon_2.setBounds(179, 169, 88, 14);
		panel.add(lblGamesWon_2);
		
		JLabel label = new JLabel("");
		label.setBounds(41, 213, 46, 14);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("MAIN MENU");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(168, 32, 112, 14);
		panel.add(lblNewLabel_1);
		
		JLabel GamesPlayedP1 = new JLabel(playerRecord1.getGamesPlayed()+ "");
		GamesPlayedP1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GamesPlayedP1.setBounds(61, 127, 26, 14);
		panel.add(GamesPlayedP1);
		
		JLabel GamesPlayedP2 = new JLabel(playerRecord2.getGamesPlayed()+ "");
		GamesPlayedP2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GamesPlayedP2.setBounds(349, 127, 26, 14);
		panel.add(GamesPlayedP2);
		
		JLabel GamesWonP1 = new JLabel(playerRecord1.getGamesWon()+ "");
		GamesWonP1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GamesWonP1.setBounds(61, 169, 26, 14);
		panel.add(GamesWonP1);
		
		JLabel GamesWonP2 = new JLabel(playerRecord2.getGamesWon()+ "");
		GamesWonP2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GamesWonP2.setBounds(349, 169, 17, 14);
		panel.add(GamesWonP2);
		
		JLabel PairScore = new JLabel(pairRecord.getGamesWonPlayerA() + " - " + pairRecord.getGamesWonPlayerB());
		if(pairRecord.getPlayerA().equals(userB.getUsername())){
			PairScore = new JLabel(pairRecord.getGamesWonPlayerB() + " - " + pairRecord.getGamesWonPlayerA());
		}
		PairScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PairScore.setBounds(200, 71, 46, 14);
		panel.add(PairScore);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame) getTopLevelAncestor()).swapView("loginPanel");
			}
		});
		btnBack.setBounds(27, 234, 121, 23);
		panel.add(btnBack);

	}
}
