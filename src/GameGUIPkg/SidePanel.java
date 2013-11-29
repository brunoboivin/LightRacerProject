package GameGUIPkg;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JLabel;

import GamePkg.Game;
import RacerPkg.ID;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JLayeredPane;

import net.miginfocom.swing.MigLayout;


/** Class used to display the sidepanel for the game.
 * @author Shahrzad Tighnavardmollasaraei <shahrzad.tighnavardmollasaraei@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public class SidePanel extends JPanel {
	
	private JLayeredPane layeredPane;
	
	private Game game;
	
	private String yCell;
	private String dCell;
	
	private boolean painted1;
	private boolean painted2;
	private boolean painted3;
	
	private JLabel lblRound1;
	private JLabel lblRound2;
	private JLabel lblRound3;
	
	private JLabel lblUserA;
	private JLabel lblUserB;
	/**
	 * The username of the racer as Yoda.
	 */
	private String YODA;
	
	/**
	 * The username of the racer as DarthVader.
	 */
	private String DARTHVADER;
	/**
	 * The current round.
	 */
	private String round;
	/**
	 * The counter used for displaying the round labels.
	 */
	private int counter;
	
	
	private static final int CONTROLS_OFFSET = 430;
	
	private static final int MESSAGE_STRIDE = 40;
	
	private static final int SIDE_OFFSET = 20;
	private int WIDTH=305;
	private int HEIGHT;
	private Image backgroundImg;
	/**
	 * The small font to draw with.
	 */
	private static final Font SMALL_FONT = new Font("STARWARS", Font.PLAIN, 15);
	private static final Font HEADER_FONT = new Font("STARWARS", Font.PLAIN, 20);
	private static final Font TITLE_FONT = new Font("STARWARS", Font.PLAIN, 30);
	private static final Color TEXT_COLOR=new Color(255, 255, 0);
	/**
	 * Create the SidePanel.
	 * @param game The Game.
	 * @param height The height of the desired side panel.
	 */
	public SidePanel(Game game,int height) 
	{
		
		this.game = game;
		this.HEIGHT=height;
		setPreferredSize(new Dimension(WIDTH,height));
		
		setBackground(new Color(211, 211, 211));
		setLayout(null);
		this.YODA=game.yodaUser;
		this.DARTHVADER=game.darthUser;
		paintHeaders(1);
		
		ImageIcon background = new ImageIcon("res/img/starWarsBackground.gif");
		
		backgroundImg=background.getImage();
		
	
	}
	/**
	 * Based on the round create the sidepanel labels.
	 * @param number The current round
	 */
	public void paintHeaders(int number)
	{
		
		
		JLabel lblLightBattles = DefaultComponentFactory.getInstance().createTitle("LIGHT BATTLES");
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
		
		
		
		lblUserA =DefaultComponentFactory.getInstance().createTitle(this.YODA);
		lblUserA.setForeground(TEXT_COLOR);
		lblUserA.setFont(SMALL_FONT);
		layeredPane.add(lblUserA, "cell 0 2,alignx center,aligny center");
		
		lblUserB =DefaultComponentFactory.getInstance().createTitle(this.DARTHVADER);
		lblUserB.setForeground(TEXT_COLOR);
		lblUserB.setFont(SMALL_FONT);
		layeredPane.add(lblUserB, "cell 0 6,alignx center,aligny center");
		
		
		
		JLabel Yoda = new JLabel("");
		layeredPane.add(Yoda, "cell 0 1");
		Yoda.setIcon(new ImageIcon("res/img/yodaSideIcon.png"));
		
		JLabel DarthVader = new JLabel("");
		layeredPane.add(DarthVader, "cell 0 5");
		DarthVader.setIcon(new ImageIcon("res/img/darthVaderSideIcon.png"));
		this.painted1=false;
		this.painted2=false;
		this.painted3=false;
	}
	
	
	
	
	/**
	 * Paints the sidepanel with the appropriate components.	
	 */
	@Override
	public void paintComponent(Graphics g) 
	{
		
		super.paintComponent(g);
		
		
		//g.drawImage(backgroundImg,0,0,null);
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImg, 0, 0,WIDTH,HEIGHT, this); 
		
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
			g.drawLine(0, CONTROLS_OFFSET-30, WIDTH,CONTROLS_OFFSET-30 );
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
	/**
	 * Based on the id of the winner update the sidepanel component on the appropite celll in the pane.
	 * @param id ID of the Winner
	 * @param layeredPane The pane where rounds are shown
	 * @param round The current round
	 */
	private void winnerCell (ID id,JLayeredPane layeredPane,int round)
	{
		ImageIcon yoda=new ImageIcon("res/img/greenLightSaber.png");
		ImageIcon darthVader=new ImageIcon("res/img/redLightSaber.png");
		ImageIcon blank=new ImageIcon("res/img/grayLightSaber.png");
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
	/**
	 * Generate the cell location for placement of a component in the layeredPane on the sidepanel.
	 * @param column The column of the cell on the pane
	 * @param row	The row of the cell on the pane
	 * @return The String indicating the location for the cell
	 */
	private String cellString(int column,int row )
	{
		return "cell"+column+" "+row;
	}
}
