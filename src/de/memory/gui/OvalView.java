package de.memory.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class OvalView extends JPanel {

	private static final long serialVersionUID = -7804199612568847200L;
	private Color c;
	public OvalView(Color color) {
		this.c = color;
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawOval(1, 1, 10, 10);
		g.setColor(c);
		g.fillOval(1, 1, 10, 10);
	}
	
	public void setColor(Color color) {
		this.c = color;
	}

}
