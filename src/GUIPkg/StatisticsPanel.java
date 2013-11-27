package GUIPkg;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import StatisticsPkg.PairRecord;
import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;
import UserPkg.User;
import java.awt.Color;

public class StatisticsPanel extends JPanel {
	
	/**
	 * Panel used to display both individual and pair scores.
	 */
	public StatisticsPanel(final User userA, final User userB) {
		
		setPreferredSize(new Dimension(650,650));
		
		setLayout(null);
		
		JBackgroundPanel panel = new JBackgroundPanel();
		panel.setBounds(0, 0, 650, 650);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		
		//get stats
		PlayerRecord playerRecord1 = Statistics.getPlayerStat(userA.getUsername());
		PlayerRecord playerRecord2 = Statistics.getPlayerStat(userB.getUsername());
		PairRecord pairRecord = Statistics.getPairRecord(userA.getUsername(), userB.getUsername());

		//create labels and update their values using the records fetched from the csv file
		JLabel username1 = new JLabel(playerRecord1.getUsername());
		username1.setForeground(new Color(255, 255, 0));
		username1.setBackground(new Color(0, 0, 0));
		username1.setHorizontalAlignment(SwingConstants.CENTER);
		username1.setFont(new Font("STARWARS", Font.PLAIN, 19));
		username1.setBounds(95, 139, 142, 27);
		panel.add(username1);
		
		JLabel username2 = new JLabel(playerRecord2.getUsername());
		username2.setForeground(new Color(255, 255, 0));
		username2.setBackground(new Color(0, 0, 0));
		username2.setHorizontalAlignment(SwingConstants.CENTER);
		username2.setFont(new Font("STARWARS", Font.PLAIN, 20));
		username2.setBounds(421, 139, 133, 27);
		panel.add(username2);
		
		JLabel gamesWonP1 = new JLabel(playerRecord1.getGamesWon()+"");
		gamesWonP1.setForeground(new Color(255, 255, 0));
		gamesWonP1.setBackground(new Color(0, 0, 0));
		gamesWonP1.setHorizontalAlignment(SwingConstants.CENTER);
		gamesWonP1.setFont(new Font("STARWARS", Font.PLAIN, 18));
		gamesWonP1.setBounds(122, 191, 77, 27);
		panel.add(gamesWonP1);
		
		JLabel gamesPlayedP1 = new JLabel(playerRecord1.getGamesPlayed()+"");
		gamesPlayedP1.setBackground(new Color(0, 0, 0));
		gamesPlayedP1.setForeground(new Color(255, 255, 0));
		gamesPlayedP1.setHorizontalAlignment(SwingConstants.CENTER);
		gamesPlayedP1.setFont(new Font("STARWARS", Font.PLAIN, 18));
		gamesPlayedP1.setBounds(122, 230, 77, 27);
		panel.add(gamesPlayedP1);
		
		JLabel gamesWonP2 = new JLabel(playerRecord2.getGamesWon()+"");
		gamesWonP2.setForeground(new Color(255, 255, 0));
		gamesWonP2.setBackground(new Color(0, 0, 0));
		gamesWonP2.setHorizontalAlignment(SwingConstants.CENTER);
		gamesWonP2.setFont(new Font("STARWARS", Font.PLAIN, 18));
		gamesWonP2.setBounds(454, 191, 77, 27);
		panel.add(gamesWonP2);
		
		JLabel gamesPlayedP2 = new JLabel(playerRecord2.getGamesPlayed()+"");
		gamesPlayedP2.setForeground(new Color(255, 255, 0));
		gamesPlayedP2.setBackground(new Color(0, 0, 0));
		gamesPlayedP2.setHorizontalAlignment(SwingConstants.CENTER);
		gamesPlayedP2.setFont(new Font("STARWARS", Font.PLAIN, 18));
		gamesPlayedP2.setBounds(454, 230, 77, 27);
		panel.add(gamesPlayedP2);
		
		JLabel lblGamesWon = new JLabel("Games Won");
		lblGamesWon.setForeground(new Color(255, 255, 0));
		lblGamesWon.setBackground(new Color(0, 0, 0));
		lblGamesWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamesWon.setFont(new Font("STARWARS", Font.PLAIN, 18));
		lblGamesWon.setBounds(262, 191, 124, 27);
		panel.add(lblGamesWon);
		
		JLabel lblGamesPlayed = new JLabel("Games Played");
		lblGamesPlayed.setForeground(new Color(255, 255, 0));
		lblGamesPlayed.setBackground(new Color(0, 0, 0));
		lblGamesPlayed.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamesPlayed.setFont(new Font("STARWARS", Font.PLAIN, 18));
		lblGamesPlayed.setBounds(253, 230, 148, 27);
		panel.add(lblGamesPlayed);
		
		JLabel label = new JLabel(pairRecord.getGamesWonPlayerA() + " - " + pairRecord.getGamesWonPlayerB());
		
		
		
		JLabel pairScore = 
				pairRecord.getPlayerA().equals(userA.getUsername()) //condition
				? label //result if true
				: new JLabel(pairRecord.getGamesWonPlayerB() + " - " + pairRecord.getGamesWonPlayerA()); //result if false
		pairScore.setHorizontalAlignment(SwingConstants.CENTER);
		pairScore.setFont(new Font("STARWARS", Font.BOLD, 20));
		pairScore.setSize(88, 40);
		pairScore.setLocation(286, 133);
		pairScore.setForeground(new Color(255, 255, 0));
		pairScore.setBackground(new Color(0, 0, 0));
		
		/*
		pairScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pairScore.setHorizontalAlignment(SwingConstants.CENTER);
		pairScore.setBounds(116, 9, 76, 33);*/
		panel.add(pairScore);
		
		JScrollPane scrollPane = new JScrollPane(TopTenPanel.createTable());
		scrollPane.setBounds(131, 343, 388, 184);
		panel.add(scrollPane);	
		
		//close button
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 0));
		btnBack.setBackground(Color.BLACK);
		btnBack.setFont(new Font("STARWARS", Font.PLAIN, 20));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame)getTopLevelAncestor()).swapView("mainMenuPanel");
			}
		});
		btnBack.setBounds(253, 555, 142, 47);
		panel.add(btnBack);
		
		add(panel);
		
		JLabel lblTop = new JLabel("Top 10");
		lblTop.setFont(new Font("STARWARS", Font.PLAIN, 25));
		lblTop.setForeground(new Color(255, 255, 0));
		lblTop.setBackground(new Color(0, 0, 0));
		lblTop.setBounds(277, 284, 97, 47);
		panel.add(lblTop);
		
		JLabel lblPairScores = new JLabel("Pair Scores");
		lblPairScores.setHorizontalAlignment(SwingConstants.CENTER);
		lblPairScores.setBackground(new Color(0, 0, 0));
		lblPairScores.setForeground(new Color(255, 255, 0));
		lblPairScores.setFont(new Font("STARWARS", Font.PLAIN, 25));
		lblPairScores.setBounds(245, 82, 179, 61);
		panel.add(lblPairScores);
		
		JLabel lblStatistics = new JLabel("STATISTICS");
		lblStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatistics.setFont(new Font("STARWARS", Font.BOLD, 30));
		lblStatistics.setBackground(new Color(0, 0, 0));
		lblStatistics.setForeground(new Color(255, 255, 0));
		lblStatistics.setBounds(0, 17, 650, 72);
		panel.add(lblStatistics);
	}

}
