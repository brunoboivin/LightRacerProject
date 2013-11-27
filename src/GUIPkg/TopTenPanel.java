package GUIPkg;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;
import java.awt.Color;

public class TopTenPanel extends JPanel {

	public static JTable createTable() {
		//generate table
		ArrayList<PlayerRecord> records = Statistics.getHighestScores();

		Object columnNames[] = {  "Rank", "Username", "Games Won", "Games Played" };
		Object rowData[][] = new Object [records.size()][columnNames.length];

		for (int j = 0; j < records.size(); j++){
			PlayerRecord record = records.get(j);
			rowData[j][0] = j+1;
			rowData[j][1] = record.getUsername();
			rowData[j][2] = record.getGamesWon();
			rowData[j][3] = record.getGamesPlayed();
		}

		JTable table = new JTable(rowData, columnNames);

		//center table data
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i=0; i < columnNames.length; i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		return table;
	}

	/**
	 * Panel that contains a table in which the best 10 players and their respective statistics are displayed.
	 */
	public TopTenPanel() {
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		setPreferredSize(new Dimension(650,650));
		setLayout(null);
		
		JBackgroundPanel container = new JBackgroundPanel();
		container.setBounds(0, 0, 650, 650);
		container.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(createTable());
		scrollPane.setBounds(135, 150, 382, 311);
		container.add(scrollPane);	

		//top label
		JLabel lblTopPlayers = new JLabel("Top 10 Players");
		lblTopPlayers.setBackground(new Color(0, 0, 0));
		lblTopPlayers.setForeground(new Color(255, 255, 0));
		lblTopPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblTopPlayers.setFont(new Font("STARWARS", Font.BOLD, 30));
		lblTopPlayers.setBounds(199, 46, 259, 61);
		container.add(lblTopPlayers);

		//back button
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("STARWARS", Font.PLAIN, 20));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setForeground(new Color(255, 255, 0));
		btnBack.setBounds(261, 510, 134, 50);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame) getTopLevelAncestor()).swapView("loginPanel");
			}
		});
		container.add(btnBack);

		//add container to TopTen JPanel
		add(container);
	}
}
