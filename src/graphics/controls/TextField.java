package graphics.controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import core.Base;
import graphics.GraphicsObject;
import graphics.Text;
import graphics.Texture;
import input.Mouse;
import toolbox.Loader;
import toolbox.Vector;

public class TextField extends GraphicsObject {
	
	private Texture textFieldTex;
	private Text inputText;
	
	private int maxLength;
	
	private boolean render = true;
	
	public TextField(Text inputText, int maxLength, Vector position, Vector size) {
		super(position, size);
		
		this.inputText = inputText;
		this.maxLength = maxLength;
		
		textFieldTex = new Texture(Loader.loadModel("textField"), position, size);
	}
	
	boolean clicked = false;
	
	int timer = 50, length;
	
	public void update(Mouse mouse) {
		if(mouse.isMouseOn(position.getX(), position.getY(), position.getX() + size.getX(), position.getY() + size.getY()) && mouse.clicked[MouseEvent.BUTTON1]) {
			clicked = true;
		}
		
		if(clicked && length <= maxLength) {
			if(Base.getKeyboard().keys[KeyEvent.VK_0] && timer <= 0) { inputText.addString("0"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_1] && timer <= 0) { inputText.addString("1"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_2] && timer <= 0) { inputText.addString("2"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_3] && timer <= 0) { inputText.addString("3"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_4] && timer <= 0) { inputText.addString("4"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_5] && timer <= 0) { inputText.addString("5"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_6] && timer <= 0) { inputText.addString("6"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_7] && timer <= 0) { inputText.addString("7"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_8] && timer <= 0) { inputText.addString("8"); timer = 50; length++; }
			if(Base.getKeyboard().keys[KeyEvent.VK_9] && timer <= 0) { inputText.addString("9"); timer = 50; length++; }
			timer--;
		}
	}

	public Text getInputText() {
		return inputText;
	}

	public void setInputText(Text inputText) {
		this.inputText = inputText;
	}

	public Texture getTextFieldTex() {
		return textFieldTex;
	}

	public void setTextFieldTex(Texture textFieldTex) {
		this.textFieldTex = textFieldTex;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}
}
