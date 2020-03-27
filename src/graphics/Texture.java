package graphics;

import java.awt.image.BufferedImage;

import renderer.Renderer;
import toolbox.Loader;
import toolbox.Vector;

public class Texture extends GraphicsObject {

	private BufferedImage model;
	
	private boolean render = true;
	
	public Texture(BufferedImage model, Vector position, Vector size) {
		super(position, size);
		
		this.model = model;
		this.position = position;
		this.size = size;
		
		Renderer.textures++;
	}
	
	public Texture(BufferedImage model) {
		super(null, null);
		
		this.model = model;

		Renderer.textures++;
	}

	public static Texture appIcon() {
		return new Texture(Loader.loadModel("appIcon"));
	}
	
	public BufferedImage getModel() {
		return model;
	}
	
	public void setModel(BufferedImage model) {
		this.model = model;
	}

	public boolean isRender() {
		return render;
	}
	
	public void setRender(boolean render) {
		this.render = render;
	}
}
