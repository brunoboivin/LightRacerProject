package GridPkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import GameGuiPkg.GridPanel;
import GameGuiPkg.SidePanel;
import GamePkg.GameStatus;
import GamePkg.TronGame;
import GridPkg.GridFileLoader;
import UserPkg.User;
import UserPkg.UserManagement;
import UserPkg.login_frame;
import UserPkg.register_frame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;

public class GridSelectorGUI extends JOptionPane {
	
	private String selectedMapPath;
	private Grid previewGrid;
	
	private JPanel panel;
	private JPanel panelGridPreview;
	private JDialog dialog;
	private JOptionPane optionPane;
	
	private FileDialog selectFile;
	
	private JButton customMap;
	
	private JRadioButton map1;
	private JRadioButton map2;
	private JRadioButton map3;
	
	private JPanel[][] panelHolder;

	public GridSelectorGUI () {
		this.previewGrid = new Grid();
		initializeElements ();
		initializePreview ();
		this.dialog = optionPane.createDialog(null, "MAPS"); 
	    this.dialog.setVisible(true);
	}
	
	private void initializeElements () {
		
		selectedMapPath = "maps/map1.txt";
		
		//Level 1: Dialog
		this.dialog = null;

		//Level 2: OptionPane
		this.optionPane = new JOptionPane();
	    this.optionPane.setMessage("SELECT A MAP");
	    this.optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
	    this.optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

	    //Level 3: Panels
	    this.panel = new JPanel();

	    this.optionPane.add(panel);
	    
	    //Level 4: Elements within Panels
	    loadSelection ();
	    this.customMap = new JButton("Select File");
	    this.selectFile = new FileDialog(this.dialog, "Select a map file");
	    this.panel.add(this.customMap);

		setSelectionActions ();
	}
	
	private void initializePreview (){
		
	    this.panelGridPreview = new JPanel();
	    this.panelGridPreview.setLayout(new GridLayout(50,75,1,1));
	    
	    this.panelHolder = new JPanel[50][75];    
	    
	    for(int i = 0; i < panelHolder.length; i++) {
	       for(int j = 0; j < panelHolder[i].length; j++) {
	          panelHolder[i][j] = new JPanel();
	          panelHolder[i][j].setBackground(Color.lightGray);
	  	      panelGridPreview.add(panelHolder[i][j]);
	       }
	    }
	    
	    this.panelGridPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.optionPane.add(panelGridPreview);
	    
	    

	}
	
	private void updatePreview (){
		
//		this.previewGrid.addObstacles(mapPath);
////		GridCell[][] updatedCells = this.previewGrid.getGridCells();
////		int updatedColCount = previewGrid.getGridCol();
////		int updatedRowCount = previewGrid.getGridRow();
//		
//	    this.panelHolder = new JPanel[50][75];    
//
//	    for(int i = 0; i < panelHolder.length; i++) {
//	       for(int j = 0; j < panelHolder[i].length; j++) {
//	          panelHolder[i][j] = new JPanel();
//	          if(i%2 ==0 && j%2==0){
//	        	  panelHolder[i][j].setBackground(Color.red);
//	          }
//	          panelGridPreview.add(panelHolder[i][j]);
//	       }
//	    }
//	    
//	    this.optionPane.add(panelGridPreview);
//	    myPanel.revalidate();
//	    myPanel.repaint();
//	 
	}
	

	private void loadSelection (){
		this.map1 = new JRadioButton("Map 1");
		this.panel.add(this.map1);
		
		this.map2 = new JRadioButton("Map 2");
		this.panel.add(this.map2);
		
		this.map3 = new JRadioButton("Map 3");
		this.panel.add(this.map3);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.map1);
		buttonGroup.add(this.map2);
		buttonGroup.add(this.map3);
		this.map1.setSelected(true);

	}

	private void setSelectionActions () {
		this.map1.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e)
	        {
	            //Execute when pressed
	    		setMap1Path ();
	        }
	    });
		this.map2.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e)
	        {
	            //Execute when pressed
	    		setMap2Path ();
	        }
	    });
		this.map3.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e)
	        {
	            //Execute when pressed
	    		setMap3Path ();
	    		updatePreview ();
	        }
	    });
		this.customMap.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e)
	        {
	    		setCustomPath ();
	        }
	    });
	}
	
	private void setMap1Path (){
    	this.selectedMapPath = "maps/map1.txt";
    }
	private void setMap2Path (){
    	this.selectedMapPath = "maps/map2.txt";
    }
	private void setMap3Path (){
    	this.selectedMapPath = "maps/map3.txt";
    }
	private void setCustomPath (){
	    this.selectFile.setDirectory("C:\\");
	    this.selectFile.setFile("*.xml");
	    this.selectFile.setVisible(true);
	    String directory = this.selectFile.getDirectory();
	    String filename = this.selectFile.getFile();
	    if (filename != null){
	      this.selectedMapPath = (directory + filename);
	    }
    }

	public String getSelectedMapPath (){
    	return this.selectedMapPath;
    }
	
	public static void main (String [] args){
		GridSelectorGUI test = new GridSelectorGUI();
		System.out.println (test.getSelectedMapPath());
	}
}

