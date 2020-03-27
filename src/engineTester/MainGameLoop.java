package engineTester;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import core.Base;
import graphics.Text;
import graphics.Texture;
import graphics.controls.Button;
import graphics.controls.CheckBox;
import renderer.Renderer;
import toolbox.Direction;
import toolbox.Loader;
import toolbox.Vector;

public class MainGameLoop extends Base {

	private static final long serialVersionUID = 8580288022728684524L;

	public MainGameLoop() {
		super("My Game", 1200, 800, 60, new Texture(Loader.loadModel("image")));
		
//		Renderer.textures += 10;
		Renderer.texts += 10;
	}
	
	private Texture rainbow;
	private Texture night;
	private Texture hearth;
	private Text logo;
	private Text fps;
	private Text changeVisibleText;
	private Text showFpsText;
	private Button changeVisible;
	private CheckBox showFps;
	
	@Override
	protected void onStart() {
		night = new Texture(Loader.loadModel("nightTop"), new Vector(0, 0), new Vector(1200, 800));
		rainbow = new Texture(Loader.loadModel("image"), new Vector(0, 0), new Vector(50, 50));
		hearth = new Texture(Loader.loadModel("hearth"));
		logo = new Text("BUILD WORLD", new Font("Comic Sans MS", Font.BOLD, 100), Color.BLUE, new Vector(300, 400));
		fps = new Text("fps: ", new Font("Times New Roman", 0, 30), Color.YELLOW, new Vector(1100, 30));
		changeVisibleText = new Text("Off Visible", new Font("Calibri", 0, 20), Color.RED, new Vector(1110, 100));
		showFpsText = new Text("Show Fps Counter", new Font("Calibri", 0, 20), Color.RED, new Vector(1040, 170));
		changeVisible = new Button(Color.YELLOW, Color.ORANGE, Color.RED, new Vector(1100, 50), new Vector(100, 100));
		showFps = new CheckBox(new Vector(1100, 180), new Vector(100, 100));
	
		showFps.setChecked(true);
	}

	@Override
	protected void render(Graphics g) {
		Renderer.renderTexture(night, rainbow);
		Renderer.renderText(logo, fps, changeVisibleText, showFpsText);
		Renderer.renderButton(changeVisible);
		Renderer.renderCheckBox(showFps);
	}

	private int pX, pY, timer;
	
	@Override
	protected void update() {
		if(keyboard.keys[KeyEvent.VK_W]) rainbow.move(Direction.NORTH, 1);
		if(keyboard.keys[KeyEvent.VK_D]) rainbow.move(Direction.EAST, 1);
		if(keyboard.keys[KeyEvent.VK_S]) rainbow.move(Direction.SOUTH, 1);
		if(keyboard.keys[KeyEvent.VK_A]) rainbow.move(Direction.WEST, 1);
		
		logo.move(Direction.NORTH, 1);
		if(logo.getPosition().getY() < 0) logo.setRender(false);
		
		if(changeVisible.isReleased() && rainbow.isRender()) { rainbow.setRender(false); changeVisibleText.setString("On Visible"); timer++; }
		if(changeVisible.isReleased() && !rainbow.isRender() && timer > 1) { rainbow.setRender(true); changeVisibleText.setString("Off Visible"); timer = 0; }
		if(timer >= 1) timer++;
		
		fps.setRender(showFps.isChecked());
	}
	
	@Override
	protected void updateOnSec() {
		fps.setString("fps: " + super.fps);
	}
	
	public static void main(String[] args) {
		new MainGameLoop();
	}
}