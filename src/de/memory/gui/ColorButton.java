package de.memory.gui;

import java.awt.Graphics;

import javax.swing.JButton;

public class ColorButton extends JButton {
	
	private static final long serialVersionUID = -9011366861559839569L;

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(10, 10, this.getWidth()-20, this.getHeight()-20);
	}

}
