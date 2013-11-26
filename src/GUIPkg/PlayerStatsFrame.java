package GUIPkg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import StatisticsPkg.PairRecord;
import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;
import UserPkg.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerStatsFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerStatsFrame frame = new PlayerStatsFrame(new User("Bruno", "123.Bruno"), new User("Neve", "123.Neve"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * Frame used to display both individual and pair scores.
	 */
	public PlayerStatsFrame(final User userA, final User userB) {
		setBounds(100, 100, 322, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//get stats
		PlayerRecord playerRecord1 = Statistics.getPlayerStat(userA.getUsername());
		PlayerRecord playerRecord2 = Statistics.getPlayerStat(userB.getUsername());
		PairRecord pairRecord = Statistics.getPairRecord(userA.getUsername(), userB.getUsername());

		//create labels and update their values using the records fetched from the csv file
		JLabel username1 = new JLabel(playerRecord1.getUsername());
		username1.setHorizontalAlignment(SwingConstants.CENTER);
		username1.setFont(new Font("Tahoma", Font.BOLD, 14));
		username1.setBounds(10, 11, 110, 27);
		contentPane.add(username1);
		
		JLabel username2 = new JLabel(playerRecord2.getUsername());
		username2.setHorizontalAlignment(SwingConstants.CENTER);
		username2.setFont(new Font("Tahoma", Font.BOLD, 14));
		username2.setBounds(187, 11, 110, 27);
		contentPane.add(username2);
		
		JLabel gamesWonP1 = new JLabel(playerRecord1.getGamesWon()+"");
		gamesWonP1.setHorizontalAlignment(SwingConstants.CENTER);
		gamesWonP1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gamesWonP1.setBounds(10, 63, 77, 27);
		contentPane.add(gamesWonP1);
		
		JLabel gamesPlayedP1 = new JLabel(playerRecord1.getGamesPlayed()+"");
		gamesPlayedP1.setHorizontalAlignment(SwingConstants.CENTER);
		gamesPlayedP1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gamesPlayedP1.setBounds(10, 113, 77, 27);
		contentPane.add(gamesPlayedP1);
		
		JLabel gamesWonP2 = new JLabel(playerRecord2.getGamesWon()+"");
		gamesWonP2.setHorizontalAlignment(SwingConstants.CENTER);
		gamesWonP2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gamesWonP2.setBounds(218, 63, 77, 27);
		contentPane.add(gamesWonP2);
		
		JLabel gamesPlayedP2 = new JLabel(playerRecord2.getGamesPlayed()+"");
		gamesPlayedP2.setHorizontalAlignment(SwingConstants.CENTER);
		gamesPlayedP2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gamesPlayedP2.setBounds(218, 113, 77, 27);
		contentPane.add(gamesPlayedP2);
		
		JLabel lblGamesWon = new JLabel("Games Won");
		lblGamesWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamesWon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGamesWon.setBounds(116, 63, 77, 27);
		contentPane.add(lblGamesWon);
		
		JLabel lblGamesPlayed = new JLabel("Games Played");
		lblGamesPlayed.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamesPlayed.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGamesPlayed.setBounds(109, 113, 96, 27);
		contentPane.add(lblGamesPlayed);
		
		JLabel pairScore = 
				pairRecord.getPlayerA().equals(userA.getUsername()) //condition
				? new JLabel(pairRecord.getGamesWonPlayerA() + " - " + pairRecord.getGamesWonPlayerB()) //result if true
				: new JLabel(pairRecord.getGamesWonPlayerB() + " - " + pairRecord.getGamesWonPlayerA()); //result if false
		pairScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pairScore.setHorizontalAlignment(SwingConstants.CENTER);
		pairScore.setBounds(116, 9, 76, 33);
		contentPane.add(pairScore);
		
		//close button
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(116, 168, 89, 23);
		contentPane.add(btnClose);
	}
}
