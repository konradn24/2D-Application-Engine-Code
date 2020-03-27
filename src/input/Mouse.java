package input;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Mouse implements MouseListener {

	/** It is all mouse buttons and it says that is mouse button pressed */
	public boolean[] clicked = new boolean[15];
	/** It is all mouse buttons and it says that is mouse button released */
	public boolean[] released = new boolean[15];
	/** If mouse entered game window it is on true and in next frame it will be change to false (so true if only one time when mouse entered) */
	public boolean entered;
	/** If mouse exited game window it is on true and in next frame it will be change to false (so true if only one time when mouse exited) */
	public boolean exited;
	/** If mouse is on game window it is on true, but if mouse is not on the game window it is on false */
	public boolean onFrame;
	
	public int mouseX, mouseY;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		released[e.getButton()] = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicked[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicked[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		entered = true;
		onFrame = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		exited = true;
		onFrame = false;
	}
	
	public void update(JFrame frame) {
		mouseX = (int) (((int) MouseInfo.getPointerInfo().getLocation().getX() - frame.getLocation().getX()));
		mouseY = (int) (((int) MouseInfo.getPointerInfo().getLocation().getY() - frame.getLocation().getY())) - 25;
	}
	
	public void clear() {
		for(int i = 0; i < released.length; i++) {
			released[i] = false;
		}

		entered = false;
		exited = false;
	}
	
	public boolean isMouseOn(int x1, int y1, int x2, int y2) {
		if(mouseX > x1 && mouseY > y1 && mouseX < x2 && mouseY < y2) return true;
		else return false;
	}
}
