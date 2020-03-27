package graphics;

import java.awt.Color;
import java.awt.Font;

import renderer.Renderer;
import toolbox.Vector;

public class Text extends GraphicsObject {

	private String string;
	private Font font;
	private Color color;
	
	private boolean render = true;
	
	public Text(String string, Font font, Color color, Vector position) {
		super(position, null);
		
		this.string = string;
		this.font = font;
		this.color = color;
		
		if(string == "") {
			string = "MISSING TEXT";
		}
		
		Renderer.texts++;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
	public void addString(String string) {
		this.string += string;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}
}
