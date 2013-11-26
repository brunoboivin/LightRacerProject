package GUIPkg;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import StatisticsPkg.PairRecord;
import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;
import UserPkg.User;

public class PlayerStatsPanel extends JPanel {
	
	/**
	 * Panel used to display both individual and pair scores.
	 */
	public PlayerStatsPanel(final User userA, final User userB) {
		setBounds(100, 100, 322, 241);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		//get stats
		PlayerRecord playerRecord1 = Statistics.getPlayerStat(userA.getUsername());
		PlayerRecord playerRecord2 = Statistics.getPlayerStat(userB.getUsername());
		PairRecord pairRecord = Statistics.getPairRecord(userA.getUsername(), userB.getUsername());

		//create labels and update their values using the records fetched from the csv file
		JLabel username1 = new JLabel(playerRecord1.getUsername());
		username1.setHorizontalAlignment(SwingConstants.CENTER);
		username1.setFont(new Font("Tahoma", Font.BOLD, 14));
		username1.setBounds(10, 11, 110, 27);
		add(username1);
		
		JLabel username2 = new JLabel(playerRecord2.getUsername());
		username2.setHorizontalAlignment(SwingConstants.CENTER);
		username2.setFont(new Font("Tahoma", Font.BOLD, 14));
		username2.setBounds(187, 11, 110, 27);
		add(username2);
		
		JLabel gamesWonP1 = new JLabel(playerRecord1.getGamesWon()+"");
		gamesWonP1.setHorizontalAlignment(SwingConstants.CENTER);
		gamesWonP1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gamesWonP1.setBounds(10, 63, 77, 27);
		add(gamesWonP1);
		
		JLabel gamesPlayedP1 = new JLabel(playerRecord1.getGamesPlayed()+"");
		gamesPlayedP1.setHorizontalAlignment(SwingConstants.CENTER);
		gamesPlayedP1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gamesPlayedP1.setBounds(10, 113, 77, 27);
		add(gamesPlayedP1);
		
		JLabel gamesWonP2 = new JLabel(playerRecord2.getGamesWon()+"");
		gamesWonP2.setHorizontalAlignment(SwingConstants.CENTER);
		gamesWonP2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gamesWonP2.setBounds(218, 63, 77, 27);
		add(gamesWonP2);
		
		JLabel gamesPlayedP2 = new JLabel(playerRecord2.getGamesPlayed()+"");
		gamesPlayedP2.setHorizontalAlignment(SwingConstants.CENTER);
		gamesPlayedP2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gamesPlayedP2.setBounds(218, 113, 77, 27);
		add(gamesPlayedP2);
		
		JLabel lblGamesWon = new JLabel("Games Won");
		lblGamesWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamesWon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGamesWon.setBounds(116, 63, 77, 27);
		add(lblGamesWon);
		
		JLabel lblGamesPlayed = new JLabel("Games Played");
		lblGamesPlayed.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamesPlayed.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGamesPlayed.setBounds(109, 113, 96, 27);
		add(lblGamesPlayed);
		
		JLabel pairScore = 
				pairRecord.getPlayerA().equals(userA.getUsername()) //condition
				? new JLabel(pairRecord.getGamesWonPlayerA() + " - " + pairRecord.getGamesWonPlayerB()) //result if true
				: new JLabel(pairRecord.getGamesWonPlayerB() + " - " + pairRecord.getGamesWonPlayerA()); //result if false
		pairScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pairScore.setHorizontalAlignment(SwingConstants.CENTER);
		pairScore.setBounds(116, 9, 76, 33);
		add(pairScore);
		
		//close button
		JButton btnClose = new JButton("Back");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame)getTopLevelAncestor()).swapView("mainMenuPanel");
			}
		});
		btnClose.setBounds(10, 192, 89, 23);
		add(btnClose);
	}

}
