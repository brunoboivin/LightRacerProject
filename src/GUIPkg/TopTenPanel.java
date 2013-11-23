package GUIPkg;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;


public class TopTenPanel extends JPanel {

	static JButton back = new JButton("Back");
	
	/**
	 * Create the panel.
	 */
	public TopTenPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel topLabel = new JLabel("Top 10 Players");
		topLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		topLabel.setBorder(new EmptyBorder(10,0,10,0));
		add(topLabel, BorderLayout.NORTH);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(1, 1));
		
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
	    table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD)); //makes table headers bold
	    
	    //center table data
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	    
	    for (int i=0; i < columnNames.length; i++){
	    	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    }
		
	    JScrollPane scrollPane = new JScrollPane(table);
	    tablePanel.add(scrollPane);
	    
		add(tablePanel, BorderLayout.CENTER);
		
		
		//button panel
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
	    back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//((MainFrame) getTopLevelAncestor()).swapView("loginPanel");
			}
		});
	    buttonPanel.add(back);
	    add(buttonPanel, BorderLayout.SOUTH);
	}

}
