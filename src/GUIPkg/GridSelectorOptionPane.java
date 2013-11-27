package GUIPkg;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import GridPkg.Grid;
import GridPkg.GridCell;


public class GridSelectorOptionPane {
	
	//Path to map file
	private String selectedMapPath;
	
	private String selectedMapName;
	
	private GridCell [][] gridCells1;
	private GridCell [][] gridCells2;
	private GridCell [][] gridCells3;
	private GridCell [][] gridCellsCustom;
	
	//GUI swing elements
	private JDialog dialog;
	private JOptionPane optionPane;
	
	private JPanel mainPanel;
	private JPanel previewContainerPanel;
	
	private JPanel previewMap1;
	private JPanel previewMap2;
	private JPanel previewMap3;
	private JPanel previewCustom;
	
	private JRadioButton map1;
	private JRadioButton map2;
	private JRadioButton map3;
	private ButtonGroup buttonGroup;
	
	private JButton customMap;
	
		
	private FileDialog selectFile;
	//Set map names used throughout class
	final private String MAP_NAME_1 = "map1";
	final private String MAP_NAME_2 = "map2";
	final private String MAP_NAME_3 = "map3";
	final private String MAP_NAME_4 = "custom";

	//Constructor
	public GridSelectorOptionPane () {
		
//		this.selectedMapPath = "maps/map1.txt";
		this.selectedMapName = null;
		initializeGUIElements ();
		
	}
	
	private void initializeGUIElements () {
		
		//Level 1 (Top Level): Load Dialog
		this.dialog = null;

		//Level 2: Load OptionPane (within Dialog)
		this.optionPane = new JOptionPane();
		
	    this.optionPane.setMessage("SELECT A MAP");
//	    this.optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
	    this.optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
//	    this.optionPane.setBackground(Color.BLACK);  

	    //Level 3: Load Panels (within OptionPane)
	    this.mainPanel = new JPanel();
	    this.mainPanel.setOpaque(false);
	    this.optionPane.add(mainPanel);
	    
	    this.previewContainerPanel = new JPanel(new CardLayout());
	    this.previewContainerPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
	    this.previewContainerPanel.setBackground(Color.gray);
	    this.optionPane.add(this.previewContainerPanel);
	    
	    //Level 4: Load Elements (within Panels)
	    initializeSelection ();
		initializePreview ();
		
		//Show everything after they have all been loaded
		this.dialog = optionPane.createDialog(null, "MAPS"); 
		this.dialog.setDefaultCloseOperation(
			    JDialog.DISPOSE_ON_CLOSE);
			dialog.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
//			        System.out.println("Thwarted user attempt to close window.");
			        cancelMapSeletion();
			    }
		});
	    this.dialog.setVisible(true);
	}
	
	private void cancelMapSeletion (){
		this.selectedMapPath = null;
	}
	
	private void initializeSelection (){
		this.map1 = new JRadioButton(this.MAP_NAME_1);
		this.mainPanel.add(this.map1);
		
		this.map2 = new JRadioButton(this.MAP_NAME_2);
		this.mainPanel.add(this.map2);
		
		this.map3 = new JRadioButton(this.MAP_NAME_3);
		this.mainPanel.add(this.map3);
	
		this.buttonGroup = new ButtonGroup();
		buttonGroup.add(this.map1);
		buttonGroup.add(this.map2);
		buttonGroup.add(this.map3);
//		this.map1.setSelected(true);
		
		this.customMap = new JButton("Select File");
		this.selectFile = new FileDialog(this.dialog, "Select a map file");
	    
	    setButtonActions (this.customMap, this.MAP_NAME_4);
	    this.mainPanel.add(this.customMap);
		
		setButtonActions (this.map1, this.MAP_NAME_1);
		setButtonActions (this.map2, this.MAP_NAME_2);
		setButtonActions (this.map3, this.MAP_NAME_3);
	}
	
	private void initializePreview (){
	
	    this.previewMap1 = new JPanel();
	    this.previewMap1.setLayout(new GridLayout(50,75,1,1));
	    this.previewMap1.setOpaque(false);
	    this.previewMap2 = new JPanel();
	    this.previewMap2.setLayout(new GridLayout(50,75,1,1));
	    this.previewMap2.setOpaque(false);
	    this.previewMap3 = new JPanel();
	    this.previewMap3.setLayout(new GridLayout(50,75,1,1));
	    this.previewMap3.setOpaque(false);
	    
	    createGridPreview (this.previewMap1, this.MAP_NAME_1, "maps/" + this.MAP_NAME_1 + ".txt");
	    createGridPreview (this.previewMap2, this.MAP_NAME_2, "maps/" + this.MAP_NAME_2 + ".txt");
	    createGridPreview (this.previewMap3, this.MAP_NAME_3, "maps/" + this.MAP_NAME_3 + ".txt");
	}
	
	//Displays the Preview grid corresponding to the selected file
	private void updateCustomPreview (String mapName){
		
		this.previewCustom = new JPanel();
	    this.previewCustom.setLayout(new GridLayout(50,75,1,1));    
	    this.previewCustom.setOpaque(false);
	    
	    createGridPreview (this.previewCustom, mapName, this.selectedMapPath);

		CardLayout cl = (CardLayout) previewContainerPanel.getLayout();
		cl.show(previewContainerPanel, mapName);
	}
	
	//Displays the Preview grid corresponding to the selected file
	private void updatePreview (String mapName){
		 CardLayout cl = (CardLayout) previewContainerPanel.getLayout();
		 cl.show(previewContainerPanel, mapName);
	}
	
	
	private void createGridPreview (JPanel mapPreview, String mapName, String selectedMapPath) {
	
		Grid gridPreview = new Grid();
	    gridPreview.addObstacles(selectedMapPath);
	    GridCell[][] cellsPreview = gridPreview.getGridCells();
	    
	    if (mapName.equals(this.MAP_NAME_1)){
	    	this.gridCells1 = cellsPreview;
	    }
	    else if (mapName.equals(this.MAP_NAME_2)){
	    	this.gridCells2 = cellsPreview;
	    }
	    else if (mapName.equals(this.MAP_NAME_3)){
	    	this.gridCells3 = cellsPreview;
	    }
	    else {
	    	this.gridCellsCustom = cellsPreview;
	    }
		
		int rowCount = gridPreview.getGridRow();
		int colCount = gridPreview.getGridCol();
		
		JPanel[][] cellsToDisplay = new JPanel[colCount][rowCount];   
	    Dimension dim=new Dimension(5,5);
    	for(int i = 0; i < colCount; i++) {
 	       for(int j = 0; j < rowCount; j++) {
 	    	  cellsToDisplay[i][j] = new JPanel();
 	    	  cellsToDisplay[i][j].setPreferredSize(dim);
 	          if(cellsPreview [i][j] == GridCell.Obstacle){
 	        	 cellsToDisplay[i][j].setBackground(Color.decode("#A8E2FF"));
 	          }
 	          else{
 	        	 cellsToDisplay[i][j].setBackground(Color.lightGray);
 	          }
 	    	  mapPreview.add(cellsToDisplay[i][j]);
 	       }  
 	    }
		
		JPanel[][] cellsToDisplayRotate = new JPanel[cellsToDisplay[0].length][cellsToDisplay.length];
		
		for(int i=0; i<cellsToDisplay[0].length; i++){
	        for(int j=cellsToDisplay.length-1; j>=0; j--){
	        	cellsToDisplayRotate[i][j] = cellsToDisplay[j][i];
	        }
	    }

    	for(int i = 0; i < cellsToDisplayRotate.length; i++) {
   	       for(int j = 0; j < cellsToDisplayRotate[i].length; j++) {
   	    	   mapPreview.add(cellsToDisplayRotate[i][j]);
   	       }
   	    }
    	
     	this.previewContainerPanel.add (mapPreview, mapName);
	}
	
	
	//Adds actions to perform when RADIO BUTTON is clicked by user
	private void setButtonActions (JRadioButton button, String mapName) {
		final String MAP_NAME = mapName; 
		button.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e) {
	    		setMapPath (MAP_NAME);
	    		updatePreview (MAP_NAME);
	        }
	    });
	}
	
	//Adds actions to perform when BUTTON is clicked by user (overloaded)
	private void setButtonActions (JButton button, String mapName) {
		final String MAP_NAME = mapName; 
		button.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e) {
	    		setMapPath (MAP_NAME);
	    		updateCustomPreview (MAP_NAME);
	        }
	    });
	}
	
	/**
	 * Saves the path corresponding to the selected file
	 * @param mapName is the name of the map
	 */
	private void setMapPath (String mapName){
		if (mapName.equals(this.MAP_NAME_4)){
			//selectFile.setDirectory("C:\\");
			this.selectFile.setFile("*.txt");
		    this.selectFile.setVisible(true);
		    String directory = this.selectFile.getDirectory();
		    String filename = this.selectFile.getFile(); 
		    if (filename != null){
		    	if(filename.endsWith(".txt")){
				      this.selectedMapPath = (directory + filename);
				      this.selectedMapName = mapName;
				      this.buttonGroup.clearSelection();
				 }
            }
		}
		else{
			this.selectedMapPath = "maps/" + mapName + ".txt";
			this.selectedMapName = mapName;
		}
//		System.out.println (this.getSelectedMapPath());
    }
	
	//getters
	public String getSelectedMapPath (){
    	return this.selectedMapPath;
    }
	
	public GridCell [][] getSelectedGridCells (){
    	if (this.selectedMapName.equals(MAP_NAME_1)){
    		return this.gridCells1;
    	}
    	if (this.selectedMapName.equals(MAP_NAME_2)){
    		return this.gridCells2;
    	}
    	if (this.selectedMapName.equals(MAP_NAME_3)){
    		return this.gridCells3;
    	}
    	if (this.selectedMapName.equals(MAP_NAME_4)){
    		return this.gridCellsCustom;
    	}
    	else{
    		System.out.println("YO");
    		return null;
    	}
    }
	
	public static void main (String [] args){
		GridSelectorOptionPane test = new GridSelectorOptionPane();
	}
}