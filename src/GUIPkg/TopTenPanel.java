package GUIPkg;

import javax.swing.JPanel;
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

public class TopTenPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TopTenPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel container = new JPanel();
		container.setLayout(null);

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
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 61, 388, 184);
        container.add(scrollPane);	
		
        
		//top label
		JLabel lblTopPlayers = new JLabel("Top 10 Players");
		lblTopPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblTopPlayers.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTopPlayers.setBounds(154, 7, 151, 42);
		container.add(lblTopPlayers);
		
		//back button
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(30, 257, 89, 23);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MainFrame) getTopLevelAncestor()).swapView("mainMenuPanel");
			}
		});
		container.add(btnBack);
		
		//add container to TopTen JPanel
		add(container);
	}
}
