package GUIPkg;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import GamePkg.TronGame;
import GridPkg.GridFileLoader;
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

public class ChooseMapsDisplay extends JFrame  {

	private JPanel topPanel;
	private JPanel bottomPanel;
	
	private JRadioButton map1;
	private JRadioButton map2;
	private JRadioButton map3;
	
	private JButton proceedBtn; 
	
	private boolean isGridChosen;
	private String gridChosen;
	
	
	public ChooseMapsDisplay() {
		
		this.isGridChosen = false;
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 450);
		
		loadPanels ();
		loadTopElements ();
		loadBottomElements ();
		
//		GridFileLoader fileLoader = new GridFileLoader();
//		String obtainedCoords = fileLoader.readFile("maps/map2.txt");
//		System.out.println(obtainedCoords);
		
		setVisible(true);

	}
	
	private void loadPanels () {

		this.topPanel = new JPanel();
		this.topPanel.setBounds(0, 99, 513, 98);
		getContentPane().add(this.topPanel);

		this.bottomPanel = new JPanel();
		this.bottomPanel.setBounds(0, 196, 513, 251);
		getContentPane().add(this.bottomPanel);
		
	}
	
	private void loadTopElements (){
		
		this.map1 = new JRadioButton("Map 1");
		this.topPanel.add(this.map1);
		
		this.map2 = new JRadioButton("Map 2");
		this.topPanel.add(this.map2);
		
		this.map3 = new JRadioButton("Map 3");
		this.topPanel.add(this.map3);
		
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.map1);
		buttonGroup.add(this.map2);
		buttonGroup.add(this.map3);
	}
	
	private void loadBottomElements (){
		
		this.proceedBtn = new JButton("START");
		this.bottomPanel.add(this.proceedBtn);
		
		//Add action listener to button
	    this.proceedBtn.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e)
	        {
	            //Execute when button is pressed
	            clickButton ();
	            dispose();
	        }
	    }); 
		
	}
	
	private void clickButton () {
		if ((this.map1).isSelected()) {
			this.gridChosen = "map1";
		}
		else if ((this.map2).isSelected()) {
			this.gridChosen = "map2";
		}
		else if ((this.map3).isSelected()) {
			this.gridChosen = "map3";
		}
		this.isGridChosen = true;
	}
	
    public String getGridChosen (){
    	return this.gridChosen;
    }
    
    public boolean getIsGridChosen (){
    	return this.isGridChosen;
    }
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChooseMapsDisplay frame = new ChooseMapsDisplay();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}			
//		});
//	}

}