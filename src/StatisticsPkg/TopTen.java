package StatisticsPkg;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopTen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopTen frame = new TopTen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TopTen() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	    
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
	    scrollPane.setBounds(0, 41, 434, 184);
	    getContentPane().add(scrollPane);
	    
	    JLabel lblTopPlayers = new JLabel("Top 10 Players");
	    lblTopPlayers.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    lblTopPlayers.setBounds(172, 11, 152, 30);
	    getContentPane().add(lblTopPlayers);
	    
	    JButton btnClose = new JButton("Close");
	    btnClose.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		dispose();
	    	}
	    });
	    btnClose.setBounds(10, 231, 89, 23);
	    contentPane.add(btnClose);
		
		
	}

}
