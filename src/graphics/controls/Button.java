package graphics.controls;

import java.awt.Color;
import java.awt.event.MouseEvent;

import graphics.GraphicsObject;
import graphics.Text;
import graphics.Texture;
import input.Mouse;
import renderer.Renderer;
import toolbox.Vector;

public class Button extends GraphicsObject {

	private Texture t1, t2, t3;
	private Color c1, c2, c3;
	private Vector size;
	
	private Text txt;
	
	private boolean isOn, clicked, released;
	
	private boolean render = true;
	private boolean usingTextures;
	
	public Button(Texture t1, Texture t2, Texture t3, Vector position, Vector size) {
		super(position, size);
		
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.size = size;
		this.usingTextures = true;
		
		Renderer.buttons++;
	}
	
	/** Create button from fill rectangle and text */
	public Button(Color c1, Color c2, Color c3, Vector position, Vector size) {
		super(position, size);
		
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.position = position;
		this.size = size;
		
		Renderer.buttons++;
	}
	
	public void update(Mouse mouse) {
		if(mouse.isMouseOn(position.getX(), position.getY(), position.getX() + size.getX(), position.getY() + size.getY())) isOn = true;
		else isOn = false;
		
		if(mouse.clicked[MouseEvent.BUTTON1] && mouse.isMouseOn(position.getX(), position.getY(), position.getX() + size.getX(), position.getY() + size.getY())) clicked = true;
		else clicked = false;
		
		if(mouse.released[MouseEvent.BUTTON1] && mouse.isMouseOn(position.getX(), position.getY(), position.getX() + size.getX(), position.getY() + size.getY())) released = true;
		else released = false;
	}
	
	public Texture getT1() {
		return t1;
	}

	public void setT1(Texture t1) {
		this.t1 = t1;
	}

	public Texture getT2() {
		return t2;
	}

	public void setT2(Texture t2) {
		this.t2 = t2;
	}

	public Texture getT3() {
		return t3;
	}

	public void setT3(Texture t3) {
		this.t3 = t3;
	}

	public Color getC1() {
		return c1;
	}

	public void setC1(Color c1) {
		this.c1 = c1;
	}

	public Color getC2() {
		return c2;
	}

	public void setC2(Color c2) {
		this.c2 = c2;
	}

	public Color getC3() {
		return c3;
	}

	public void setC3(Color c3) {
		this.c3 = c3;
	}

	public Text getTxt() {
		return txt;
	}

	public void setTxt(Text txt) {
		this.txt = txt;
	}

	public boolean isOn() {
		return isOn;
	}

	public boolean isClicked() {
		return clicked;
	}

	public boolean isRender() {
		return render;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public boolean isUsingTextures() {
		return usingTextures;
	}
}
