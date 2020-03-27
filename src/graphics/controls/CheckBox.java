package graphics.controls;

import java.awt.event.MouseEvent;

import graphics.GraphicsObject;
import graphics.Texture;
import input.Mouse;
import renderer.Renderer;
import toolbox.Loader;
import toolbox.Vector;

public class CheckBox extends GraphicsObject {

	private Texture box, boxWithTick;
	
	private boolean isOn, released, checked;
	
	private boolean render = true;
	private boolean isUsingCustomTextures;
	
	public CheckBox(Texture box1, Texture boxWithTick, Vector position, Vector size) {
		super(position, size);
		
		this.box = box1;
		this.boxWithTick = boxWithTick;
		this.isUsingCustomTextures = true;
		
		Renderer.checkBoxes++;
	}
	
	public CheckBox(Vector position, Vector size) {
		super(position, size);
		
		this.box = new Texture(Loader.loadModel("box"), position, size);
		this.boxWithTick = new Texture(Loader.loadModel("boxWithTick"), position, size);
		
		Renderer.checkBoxes++;
	}
	
	public void update(Mouse mouse) {
		if(mouse.isMouseOn(position.getX(), position.getY(), position.getX() + size.getX(), position.getY() + size.getY())) isOn = true;
		else isOn = false;
		
		if(mouse.released[MouseEvent.BUTTON1] && mouse.isMouseOn(position.getX(), position.getY(), position.getX() + size.getX(), position.getY() + size.getY())) released = true;
		else released = false;
		
		if(released && !checked)
			checked = true;
		else if(released && checked)
			checked = false;
	}
	
	public Texture getBox() {
		return box;
	}

	public void setBox(Texture box) {
		this.box = box;
	}

	public Texture getBoxWithTick() {
		return boxWithTick;
	}

	public void setBoxWithTick(Texture boxWithTick1) {
		this.boxWithTick = boxWithTick1;
	}

	public boolean isOn() {
		return isOn;
	}

	public boolean isClicked() {
		return released;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public boolean isUsingCustomTextures() {
		return isUsingCustomTextures;
	}
}
