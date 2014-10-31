package de.memory.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MemoryFrame extends JFrame {

	private final static int amountOfCards = 16;
	private static final long serialVersionUID = -527144623559365859L;
	private final MemoryGUI memoryGUI;
	
	private List<ColorButton> memoryFieldElements = new ArrayList<ColorButton>();
	
	public MemoryFrame(MemoryGUI memoryGUI, String name) {
		super(name);
		this.memoryGUI = memoryGUI;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		//add custom GUI elements to frame
		addView(this.memoryGUI.northView(), BorderLayout.NORTH);
		addView(this.memoryGUI.eastView(), BorderLayout.EAST);
		addView(this.memoryGUI.southView(), BorderLayout.SOUTH);
		addView(this.memoryGUI.westView(), BorderLayout.WEST);
		//add memory field to center
		getContentPane().add(getMemoryField(), BorderLayout.CENTER);
		pack();
		setSize(300,300); // looks better at this size!!! can be removed..
	}
	
	private JComponent getMemoryField() {
		initMemoryFieldElements(); // fill list of buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		for(ColorButton b : this.memoryFieldElements) {
			panel.add(b);
		}
		return panel;
	}
	
	private void initMemoryFieldElements() {
		for(int i = 0; i < amountOfCards; i++) {
			ColorButton b = new ColorButton();
			b.setName(i+"");
			b.setBackground(Color.BLACK);
			this.memoryFieldElements.add(b);
		}
	}
	public void register() {
		
	}
	
	private void addView(JComponent comp, String location) {
		if(comp != null) {
			getContentPane().add(comp,location);
		}
	}
	
	public List<ColorButton> getMemoryFieldElements() {
		return this.memoryFieldElements;
	}
	
	public void showMessageDialog(String text) {
		JOptionPane.showMessageDialog(this, text);
	}
}
