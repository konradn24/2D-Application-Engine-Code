package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	/** This is all keys and it says that is key pressed */
	public boolean[] keys = new boolean[500];
	public boolean[] releasedKeys = new boolean[500];
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		releasedKeys[e.getKeyCode()] = true;
	}
	
	public void update() {
		for(int i = 0; i < 500; i++)
			releasedKeys[i] = false;
	}
}
