package GUIPkg;

import java.awt.CardLayout;
import java.awt.Color;
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
import GridPkg.PreviewGrid;

import java.awt.Font;

/** 
 * Description: Prompts user to select a map;
 * preview of map is displayed in a grid and corresponding cells of grid are saved
 * @authors	Anita Szilagyi, Bruno Boivin, Kaichen Wang, Salman Hashmi, Shahrzad Ti
 * @version	1.0
 * @since	2013-11-23	
 */

public class GridSelectorOptionPane {
	
	/**
	 * Path to selected map file
	 */
	private String selectedMapPath;
	/**
	 * Name of selected map file
	 */
	private String selectedMapName;	
	/**
	 * Cells of Grid corresponding to map 1 (pre-loaded)
	 */
	private GridCell [][] gridCells1;
	/**
	 * Cells of Grid corresponding to map 2 (pre-loaded)
	 */
	private GridCell [][] gridCells2;
	/**
	 * Cells of Grid corresponding to map 3 (pre-loaded)
	 */
	private GridCell [][] gridCells3;
	/**
	 * Cells of Grid corresponding to custom map (loaded when selected)
	 */
	private GridCell [][] gridCellsCustom;
	
	//GUI swing elements
	/**
	 * pop-up dialog window
	 */
	private JDialog dialog;
	/**
	 * OK option for user to confirm selection (or close to cancel)
	 */
	private JOptionPane optionPane;
	
	/**
	 * panel with radio buttons/button for user to select maps
	 */
	private JPanel mainPanel;
	/**
	 * panel which wraps selected map previews
	 */
	private JPanel previewContainerPanel;
	/**
	 * panel which contains individual map 1 preview (pre-loaded)
	 */
	private JPanel previewMap1;
	/**
	 * panel which contains individual map 2 preview (pre-loaded)
	 */
	private JPanel previewMap2;
	/**
	 * panel which contains individual map 3 preview (pre-loaded)
	 */
	private JPanel previewMap3;
	/**
	 * panel which contains individual custom map preview (loaded when selected)
	 */
	private JPanel previewCustom;
	/**
	 * for user to choose option of map 1
	 */
	private JRadioButton map1;
	/**
	 * for user to choose option of map 2
	 */
	private JRadioButton map2;
	/**
	 * for user to choose option of map 3
	 */
	private JRadioButton map3;
	/**
	 * regroups 3 default options
	 */
	private ButtonGroup buttonGroup;
	/**
	 * for user to choose option of custom map
	 */
	private JButton customMap;
	/**
	 * for user to browse disk for file of custom map
	 */
	private FileDialog selectFile;
	
	//Set map names used throughout class
	final private String MAP_NAME_1 = "map1";
	final private String MAP_NAME_2 = "map2";
	final private String MAP_NAME_3 = "map3";
	final private String MAP_NAME_4 = "custom";

	//Constructor
	public GridSelectorOptionPane () {
		this.selectedMapName = null;
		initializeGUIElements ();
	}
	
	/**
	 * initializes all Swing GUI elements (except selection buttons) and sets their basic properties
	 */
	private void initializeGUIElements () {
		
		//Level 1 (Top Level): Load Dialog
		this.dialog = null;

		//Level 2: Load OptionPane (within Dialog)
		this.optionPane = new JOptionPane();
		this.optionPane.setFont(new Font("STARWARS", Font.PLAIN, 17));
		this.optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
	    this.optionPane.setMessage("SELECT A MAP");
	    this.optionPane.setOptionType(JOptionPane.DEFAULT_OPTION); 

	    //Level 3: Load Panels (within OptionPane)
	    this.mainPanel = new JPanel();
	    this.mainPanel.setOpaque(false);
	  
	    this.previewContainerPanel = new JPanel(new CardLayout());
	    this.previewContainerPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
	    this.previewContainerPanel.setBounds(0, 0, 375, 250);
	    this.previewContainerPanel.setBackground(Color.gray);
	    
	    this.optionPane.add(this.mainPanel);
	    this.optionPane.add(this.previewContainerPanel);

	    //Level 4: Load Elements (within Panels)
	    initializeSelection ();
		initializePreview ();
		
		//Add final actions to dialog and show everything after they have been loaded
		this.dialog = optionPane.createDialog(null, "MAPS"); 
		this.dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
			   cancelMapSeletion();
			}
		});
		this.dialog.setVisible(true);
	}
	
	/**
	 * helper method for signaling that user has cancelled selection and closed dialog
	 */
	private void cancelMapSeletion (){
		this.selectedMapPath = null;
	}
	
	/**
	 * initializes radio button and file select button for user map selection
	 */
	private void initializeSelection (){
		
		this.map1 = new JRadioButton(this.MAP_NAME_1);
		map1.setFont(new Font("STARWARS", Font.PLAIN, 11));
		this.mainPanel.add(this.map1);
		
		this.map2 = new JRadioButton(this.MAP_NAME_2);
		map2.setFont(new Font("STARWARS", Font.PLAIN, 11));
		this.mainPanel.add(this.map2);
		
		this.map3 = new JRadioButton(this.MAP_NAME_3);
		map3.setFont(new Font("STARWARS", Font.PLAIN, 11));
		this.mainPanel.add(this.map3);
	
		this.buttonGroup = new ButtonGroup();
		buttonGroup.add(this.map1);
		buttonGroup.add(this.map2);
		buttonGroup.add(this.map3);
		
		this.customMap = new JButton("Select File");
		this.customMap.setFont(new Font("STARWARS", Font.PLAIN, 11));
		this.selectFile = new FileDialog(this.dialog, "Select a map file");
	    this.mainPanel.add(this.customMap);
		
		setButtonActions (this.map1, this.MAP_NAME_1);
		setButtonActions (this.map2, this.MAP_NAME_2);
		setButtonActions (this.map3, this.MAP_NAME_3);
	    setButtonActions (this.customMap, this.MAP_NAME_4);
	}
	
	/**
	 * initialized (loads as cards) previews of 3 default maps
	 */
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
	    
	    createGridPreview (this.previewMap1, this.MAP_NAME_1, "res/maps/" + this.MAP_NAME_1 + ".txt");
	    createGridPreview (this.previewMap2, this.MAP_NAME_2, "res/maps/" + this.MAP_NAME_2 + ".txt");
	    createGridPreview (this.previewMap3, this.MAP_NAME_3, "res/maps/" + this.MAP_NAME_3 + ".txt");
	}
	
	/**
	 * Updates the preview of grid corresponding to the selected default map
	 * @param name of the map
	 */
	private void updatePreview (String mapName){
		 CardLayout cl = (CardLayout) previewContainerPanel.getLayout();
		 cl.show(previewContainerPanel, mapName);
	}
	
	/**
	 * Updates the preview of grid corresponding to the selected file of custom map 
	 * @param name of the map
	 */
	private void updateCustomPreview (String mapName){
		this.previewCustom = new JPanel();
	    this.previewCustom.setLayout(new GridLayout(50,75,1,1));    
	    this.previewCustom.setOpaque(false);
	    
	    createGridPreview (this.previewCustom, mapName, this.selectedMapPath);
	    
		CardLayout cl = (CardLayout) previewContainerPanel.getLayout();
		cl.show(previewContainerPanel, mapName);
	}
	
	/**
	 * 
	 * @param initilizes the preview of 3 default maps
	 * @param name of map
	 * @param map file path
	 */
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

		PreviewGrid gridPrev=new PreviewGrid(cellsPreview);
		gridPrev.repaint();
     	this.previewContainerPanel.add (gridPrev, mapName);
	}
	
	/**
	 * Adds actions to perform when RADIO BUTTON is clicked by user
	 * @param radio button
	 * @param name of corresponding map selected
	 */
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
	
	/**
	 * Adds actions to perform when BUTTON is clicked by user (overloaded)
	 * @param file select button
	 * @param name of corresponding map selected
	 */
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
	 * @param name of the map
	 */
	private void setMapPath (String mapName){
		//if custom
		if (mapName.equals(this.MAP_NAME_4)){
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
			this.selectedMapPath = "res/maps/" + mapName + ".txt";
			this.selectedMapName = mapName;
		}
    }
	
	//getters
	/**
	 * gets path of selected map
	 * @return path of selected map
	 */
	public String getSelectedMapPath (){
    	return this.selectedMapPath;
    }
	
	/**
	 * gets cells of Grid corresponding to map selected
	 * @return cells of selected Grid
	 */
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
    		return null;
    	}
    }
}
