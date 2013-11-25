package GameGuiPkg;

import javax.swing.JPanel;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.List;
import javax.swing.JLabel;

import GamePkg.TronGame;
import RacerPkg.ID;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JSeparator;
import javax.swing.JLayeredPane;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;

public class SidePanel extends JPanel {
	
	private JLayeredPane layeredPane;
	
	private TronGame game;
	//private int cellRow;
	private String yCell;
	private String dCell;
	
	private boolean painted1;
	private boolean painted2;
	private boolean painted3;
	
	private JLabel lblRound1;
	private JLabel lblRound2;
	private JLabel lblRound3;
	private String round;
	private int counter;
	
	
	
	private static final int CONTROLS_OFFSET = 390;
	
	private static final int MESSAGE_STRIDE = 40;
	
	private static final int SIDE_OFFSET = 20;
	private int WIDTH=305;
	
	/**
	 * The small font to draw with.
	 */
	private static final Font SMALL_FONT = new Font("STARWARS", Font.PLAIN, 15);
	private static final Font HEADER_FONT = new Font("STARWARS", Font.PLAIN, 20);
	private static final Font TITLE_FONT = new Font("STARWARS", Font.PLAIN, 40);
	private static final Color TEXT_COLOR=new Color(25, 25, 112);
	//private 
	/**
	 * Create the panel.
	 */
	public SidePanel(TronGame game,int height) 
	{
		
		this.game = game;
		
		setPreferredSize(new Dimension(WIDTH, height* GridPanel.TILE_SIZE));
		setBackground(new Color(211, 211, 211));
		setLayout(null);
		
		paintHeaders(1);
	
		
	
	}
	public void paintHeaders(int number)
	{
		
		
		JLabel lblLightBattles = DefaultComponentFactory.getInstance().createTitle("Light Battles");
		lblLightBattles.setBounds(0, 0, WIDTH, 50);
		add(lblLightBattles);
		lblLightBattles.setForeground(TEXT_COLOR);
		lblLightBattles.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightBattles.setFont(TITLE_FONT);
		
		
		this.layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 90, WIDTH, 300);
		add(layeredPane);
		layeredPane.setLayout(new MigLayout("", "[][][][]", "[][][][][][][][]"));
		
		counter=number;
		round="Round"+counter;
		
		lblRound1 = DefaultComponentFactory.getInstance().createTitle(round);
		lblRound1.setForeground(TEXT_COLOR);
		lblRound1.setFont(SMALL_FONT);
		layeredPane.add(lblRound1, "cell 1 0");


		counter++;
		round="Round"+counter;
		
		lblRound2 = DefaultComponentFactory.getInstance().createTitle(round);
		lblRound2.setForeground(TEXT_COLOR);
		lblRound2.setFont(SMALL_FONT);
		layeredPane.add(lblRound2, "cell 2 0");

		counter++;
		round="Round"+counter;
		
		lblRound3 = DefaultComponentFactory.getInstance().createTitle(round);
		lblRound3.setForeground(TEXT_COLOR);
		lblRound3.setFont(SMALL_FONT);
		layeredPane.add(lblRound3, "cell 3 0");
		
		
		
		JLabel Yoda = new JLabel("");
		layeredPane.add(Yoda, "cell 0 1");
		Yoda.setIcon(new ImageIcon("img/yodaSideIcon.gif"));
		
		JLabel DarthVader = new JLabel("");
		layeredPane.add(DarthVader, "cell 0 5");
		DarthVader.setIcon(new ImageIcon("img/darthVaderSideIcon.gif"));
		this.painted1=false;
		this.painted2=false;
		this.painted3=false;
	}
	
	
	
	
	
	
	@Override
	public void paintComponent(Graphics g) 
	{
		
		super.paintComponent(g);
		
		
		//ID winner=this.game.winnerId();
		int round=this.game.status.getRoundNumber();
		//System.out.println("current round is "+round);
		if(round>3)
			{
				round=round%3;
				if(round==0)
					round=3;
			}
		switch(round)
		{
			case 1:
				if(painted1==false)
					{
						//System.out.println("current round is "+round);
						winnerCell(this.game.winnerId(),this.layeredPane,1);
						painted1=true;
					}
					
				break;
			case 2:
				if(painted2==false)
					{
						//System.out.println("current round is "+round);
						winnerCell(this.game.winnerId(),this.layeredPane,2);
						painted2=true;
					}
				break;
			case 3:
				if(painted3==false)
					{
						//System.out.println("current round is "+round);
						winnerCell(this.game.winnerId(),this.layeredPane,3);
						painted3=true;
					}
				break;
		
		}
		
		
			g.setColor(TEXT_COLOR);
			g.setFont(HEADER_FONT);
			g.drawString("Controls", SIDE_OFFSET, CONTROLS_OFFSET);
			g.setFont(SMALL_FONT);
			int drawY = CONTROLS_OFFSET;
			g.drawString("Move Up: W / Up Arrowkey", SIDE_OFFSET, drawY += MESSAGE_STRIDE);
			g.drawString("Move Down: S / Down Arrowkey", SIDE_OFFSET, drawY += MESSAGE_STRIDE);
			g.drawString("Move Left: A / Left Arrowkey", SIDE_OFFSET, drawY += MESSAGE_STRIDE);
			g.drawString("Move Right: D / Right Arrowkey", SIDE_OFFSET, drawY += MESSAGE_STRIDE);
			g.drawString("Pause Game: P", SIDE_OFFSET, drawY += MESSAGE_STRIDE);

	}
	private void winnerCell (ID id,JLayeredPane layeredPane,int round)
	{
		ImageIcon yoda=new ImageIcon("img/greenLightSaber.gif");
		ImageIcon darthVader=new ImageIcon("img/redLightSaber.gif");
		ImageIcon blank=new ImageIcon("img/grayLightSaber.gif");
		switch(id)
		{
			case YODA:
				yCell=cellString(round,1);
				
				JLabel yS = new JLabel("");
				layeredPane.add(yS, yCell);
				yS.setIcon(yoda);
				
				dCell=cellString(round,5);
				
				JLabel dVS = new JLabel("");
				layeredPane.add(dVS, dCell);
				dVS.setIcon(blank);
				
				break;
			
			case DARTHVADER:
				yCell=cellString(round,1);
				
				JLabel yS2 = new JLabel("");
				layeredPane.add(yS2, yCell);
				yS2.setIcon(blank);
				
				dCell=cellString(round,5);
				
				JLabel dVS2 = new JLabel("");
				layeredPane.add(dVS2, dCell);
				dVS2.setIcon(darthVader);
				
				break;
			
			case NULL:
				yCell=cellString(round,1);
				
				JLabel yS3 = new JLabel("");
				layeredPane.add(yS3, yCell);
				yS3.setIcon(blank);
				
				dCell=cellString(round,5);
				
				JLabel dVS3 = new JLabel("");
				layeredPane.add(dVS3, dCell);
				dVS3.setIcon(blank);
				break;
		}
	}
	private String cellString(int column,int row )
	{
		return "cell"+column+" "+row;
	}
}
