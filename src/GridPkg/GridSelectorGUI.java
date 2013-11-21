package GridPkg;

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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import GameGuiPkg.SidePanel;
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

public class GridSelectorGUI extends JOptionPane {
	
	private String gridSelected;
	
	private JPanel panel;
	private JDialog dialog;
	private JOptionPane optionPane;
	
	private JRadioButton map1;
	private JRadioButton map2;
	private JRadioButton map3;
	
	public static final int TILE_SIZE = 5;

	public GridSelectorGUI () {
		
		this.gridSelected = "map1";
		loadElements ();
	    this.dialog.setVisible(true);
	    
	}
	
	private void loadElements () {
		
		this.dialog = null;
		this.optionPane = new JOptionPane();
	    this.optionPane.setMessage("SELECT A MAP");
	    this.optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
	    this.panel = new JPanel();
	    this.panel.setLayout(new GridLayout(3,1));
	    
	    loadSelection ();
	    setSelectionActions ();
	    
	    this.optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
	    this.optionPane.add(panel);
	    this.dialog = optionPane.createDialog(null, "MAPS");
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
	    		setMap1 ();
	        }
	    });
		this.map2.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e)
	        {
	            //Execute when pressed
	    		setMap2 ();
	        }
	    });
		this.map3.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e)
	        {
	            //Execute when pressed
	    		setMap3 ();
	        }
	    });
	}
	
	private void setMap1 (){
    	this.gridSelected = "map1";
    }
	
	private void setMap2 (){
    	this.gridSelected = "map2";
    }
	
	private void setMap3 (){
    	this.gridSelected = "map3";
    }
	
	public String getGridSelected (){
    	return this.gridSelected;
    }
	
}

