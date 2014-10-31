package de.memory.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;

import de.memory.api.ICard;
import de.memory.api.IMemoryModel;
import de.memory.api.IMemoryView;
import de.memory.api.IPlayer;

public abstract class MemoryGUI implements IMemoryView {
	private final String name;
	private IMemoryModel model;
	private MemoryFrame frame;
	protected int playerNumber;
	protected IPlayer player;
	
	protected abstract JComponent northView();
	protected abstract JComponent southView();
	protected abstract JComponent eastView();
	protected abstract JComponent westView();
	
	protected MemoryGUI(String name) { // should be public
		this.name = name;
	}
	
	public IMemoryModel getModel() {
		return model;
	}
	
	@Override
	public void update(IMemoryModel m) {
		if(model == null) { //first time 
			model = m;
			player = model.getPlayer(playerNumber);
			if(player != null) initMemoryFieldActions(); //init player actions
			SwingUtils.invokeLater(new Runnable() {
				@Override
				public void run() {
					frame.register();
					updateView();
				}
			});
		}
		else if(model == m) {
			SwingUtils.invokeLater( new Runnable() {
				@Override
				public void run() {
					updateView();
				}
			});
		}
	}
	
	public void activate() {
		SwingUtils.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				frame = new MemoryFrame(MemoryGUI.this,name);
				frame.setVisible(true);
			}
		});
	}
	
	public void deactivate() {
		SwingUtils.invokeLater( new Runnable() {
			@Override
			public void run() {
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
	
	protected List<ColorButton> getMemoryFieldElements() {
		return this.frame.getMemoryFieldElements();
	}
	
	/* Colors from model
	 * {"green","blue","red","yellow","orange","brown","pink","purple"}
	 */
	protected Color getColorForString(String color) {
		if(color.equals("green")) return Color.GREEN;
		else if(color.equals("blue")) return Color.BLUE;
		else if(color.equals("red")) return Color.RED;
		else if(color.equals("yellow")) return Color.YELLOW;
		else if(color.equals("orange")) return Color.ORANGE;
		else if(color.equals("brown")) return Color.GRAY;
		else if(color.equals("pink")) return Color.PINK;
		else if(color.equals("purple")) return Color.MAGENTA;
		else {
			System.out.println("getColorForString: Color not available in model");
			return Color.BLACK;
		}
	}
	
	//update all memoryFieldElements , immer gleich junge
	protected void updateView() {
		List<ColorButton> memoryFieldElements = this.getMemoryFieldElements();
		for(int i = 0; i < memoryFieldElements.size(); i++) {
			ICard card = getModel().getCard(i);
			if(card.isFrontVisible()) {
				memoryFieldElements.get(i).setBackground(this.getColorForString(card.getFrontColor()));
			}
			else if(card.isCardUnplayable()) {
				memoryFieldElements.get(i).setVisible(false); //hide all played cards
			}
			else {
				memoryFieldElements.get(i).setBackground(Color.BLACK);
			}
		}
	}
	private void initMemoryFieldActions() {
		for(int i = 0; i < getMemoryFieldElements().size(); i++) {
			getMemoryFieldElements().get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					player.flipCard(Integer.parseInt(((ColorButton)e.getSource()).getName()));
				}
			});
		}
	}
	
	protected void showMessageDialog(String text) {
		this.frame.showMessageDialog(text);
	}
	
	protected void resetVisibilityMemoryFieldElements() {
		for(ColorButton b : getMemoryFieldElements()) {
			b.setVisible(true);
		}
	}
	
}
