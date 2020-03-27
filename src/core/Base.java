package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import graphics.Text;
import graphics.Texture;
import graphics.controls.Button;
import graphics.controls.CheckBox;
import graphics.controls.TextField;
import input.Keyboard;
import input.Mouse;
import renderer.Renderer;
import states.StatesManager;
import toolbox.Vector;

@SuppressWarnings("serial")
public abstract class Base extends Canvas implements Runnable {

	/** It is a frame. In this variable you can change some options like: resizable or visible */
	protected static JFrame frame;
	/** It boolean is on true when you game is open. If game was closed, it is changing to false */
	protected boolean running;
	/** If it boolean is on true, then screen will be clear on every frame */
	protected boolean clear;
	
	private static final int CLEAR_OFFSET = 2;
	
	/** Use this variable, when you want to check what key was pressed or released and action with keys */
	protected static Keyboard keyboard;
	/** Use this variable, when you want to check what mouse button was pressed or released and action with mouse */
	protected static Mouse mouse;
	
	/** This class is boss of every states in application */
	protected static StatesManager sm;
	
	/** It is title of your game window */
	private String title;
	
	@SuppressWarnings("unused")
	public int width, height, framerate;
	private static int staticWidth, staticHeight;
	
	/** Fps stuff */
	protected double timer = System.currentTimeMillis();
	/** Fps stuff */
	protected int fps = 0;
	/** Fps stuff */
	protected double delta;
	/** Fps stuff */
	protected double frametime;
	/** Fps stuff */
	protected long currentTime = System.nanoTime();
	/** Fps stuff */
	protected long lastTime = System.nanoTime();
	
	/** Standard Java graphics */
	public static Graphics g;
	/** Standard Java advanced graphics */
	public static Graphics2D g2d;
	
	/** List of all rendered textures */
	public static List<Texture> textures = new ArrayList<Texture>();
	/** List of all rendered texts */
	public static List<Text> texts = new ArrayList<Text>();
	/** List of all rendered buttons */
	public static List<Button> buttons = new ArrayList<Button>();
	/**List of all rendered check boxes */
	public static List<CheckBox> checkBoxes = new ArrayList<CheckBox>();
	/**List of all rendered text fields */
	public static List<TextField> textFields = new ArrayList<TextField>();
	
	private BufferedImage background;
	
	public Base(String title, int width, int height, int framerate, Texture icon) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.framerate = framerate;
		
		Base.keyboard = new Keyboard();
		Base.mouse = new Mouse();
		addKeyListener(keyboard);
		addMouseListener(mouse);
		
		Base.sm = new StatesManager();
		
		staticWidth = width;
		staticHeight = height;
		
		frametime = 1000000000 / framerate;
		
		setPreferredSize(new Dimension(width, height));
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.requestFocus();
		frame.add(this);
		frame.setIconImage(icon.getModel());
		
		start();
	}
	
	/** Every line in this void is doing on application start (so one time) */
	protected abstract void onStart();
	/** Every line in this void is doing on every second */
	protected abstract void updateOnSec();
	/** Every line in this void is doing on every frame */
	protected abstract void update();
	/** Every line in this void must be rendering stuff and it is doing on max computer power */
	protected abstract void render(Graphics g);
	
	public void run() {
		onStart();
		sm.onStart();
		
		while(running) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / frametime;
			lastTime = currentTime;
			
			if(delta >= 1){
				update();
				sm.update();
				for(Button button : buttons) button.update(mouse);
				for(CheckBox checkBox : checkBoxes) checkBox.update(mouse);
				for(TextField textField : textFields) textField.update(mouse);
				keyboard.update();
				mouse.update(frame);
				mouse.clear();
				fps++;
				delta -= 1;
			}
			
			prepare();
			render(g);
			sm.render();
			
			for(int i = 0; i < textures.size(); i++) if(textures.size() > Renderer.textures || !textures.get(i).isRender()) textures.remove(i);
			for(int i = 0; i < texts.size(); i++) if(texts.size() > Renderer.texts || !texts.get(i).isRender()) texts.remove(i);
			for(int i = 0; i < buttons.size(); i++) if(buttons.size() > Renderer.buttons || !buttons.get(i).isRender()) buttons.remove(i);
			for(int i = 0; i < checkBoxes.size(); i++) if(checkBoxes.size() > Renderer.checkBoxes || !checkBoxes.get(i).isRender()) checkBoxes.remove(i);
			for(int i = 0; i < textFields.size(); i++) if(textFields.size() > Renderer.textFields || !textFields.get(i).isRender()) textFields.remove(i);
			
			if(System.currentTimeMillis() - timer >= 1000) {
				updateOnSec();
				sm.updateOnSec();
				
				timer = System.currentTimeMillis();
				fps = 0;
			}
		}
		
		stop();
	}
	
	/** It void starts your application */
	protected void start() {
		if (running)return;
		running = true;
		
		new Thread(this, title).start();
	}
	
	/** It void stops application */
	protected void stop() {
		if (running) return;
		running = false;
		frame.dispose();
		System.exit(0);
	}
	
	public static void close() {
		frame.dispose();
		System.exit(0);
	}
	
	private void processTextures() {
		for(int i = 0; i < textures.size(); i++) {
			if(clear) g.fillRect(textures.get(i).getPosition().getX() - CLEAR_OFFSET, textures.get(i).getPosition().getY(), textures.get(i).getSize().getX(), textures.get(i).getSize().getY());
			g.drawImage(textures.get(i).getModel(), textures.get(i).getPosition().getX(), textures.get(i).getPosition().getY(), textures.get(i).getSize().getX(), textures.get(i).getSize().getY(), null);
		}
	}
	
	private void processTexts() {
		for(int i = 0; i < texts.size(); i++) {
			if(clear) g2d.fillRect(texts.get(i).getPosition().getX() - CLEAR_OFFSET, texts.get(i).getPosition().getY(), texts.get(i).getFont().getSize(), texts.get(i).getFont().getSize());
			g.setFont(texts.get(i).getFont());
			g.setColor(texts.get(i).getColor());
			g.drawString(texts.get(i).getString(), texts.get(i).getPosition().getX(), texts.get(i).getPosition().getY());
		}
	}
	
	private void processButtons() {
		for(int i = 0; i < buttons.size(); i++) {
			if(clear) g.fillRect(buttons.get(i).getPosition().getX(), buttons.get(i).getPosition().getY(), buttons.get(i).getSize().getX(), buttons.get(i).getSize().getY());
			if(buttons.get(i).isUsingTextures()) {
				if(!buttons.get(i).isClicked() && !buttons.get(i).isOn()) g.drawImage(buttons.get(i).getT1().getModel(), buttons.get(i).getPosition().getX(), buttons.get(i).getPosition().getY(), buttons.get(i).getSize().getX(), buttons.get(i).getSize().getY(), null);
				if(buttons.get(i).isOn() && !buttons.get(i).isClicked()) g.drawImage(buttons.get(i).getT2().getModel(), buttons.get(i).getPosition().getX(), buttons.get(i).getPosition().getY(), buttons.get(i).getSize().getX(), buttons.get(i).getSize().getY(), null);
				if(buttons.get(i).isClicked()) g.drawImage(buttons.get(i).getT3().getModel(), buttons.get(i).getPosition().getX(), buttons.get(i).getPosition().getY(), buttons.get(i).getSize().getX(), buttons.get(i).getSize().getY(), null);
			}
			
			if(!buttons.get(i).isUsingTextures()) {
				if(!buttons.get(i).isClicked() && !buttons.get(i).isOn()) { g.setColor(buttons.get(i).getC1()); g.fillRect(buttons.get(i).getPosition().getX(), buttons.get(i).getPosition().getY(), buttons.get(i).getSize().getX(), buttons.get(i).getSize().getY()); }
				if(buttons.get(i).isOn() && !buttons.get(i).isClicked()) { g.setColor(buttons.get(i).getC2()); g.fillRect(buttons.get(i).getPosition().getX(), buttons.get(i).getPosition().getY(), buttons.get(i).getSize().getX(), buttons.get(i).getSize().getY()); }
				if(buttons.get(i).isClicked()) { g.setColor(buttons.get(i).getC3()); g.fillRect(buttons.get(i).getPosition().getX(), buttons.get(i).getPosition().getY(), buttons.get(i).getSize().getX(), buttons.get(i).getSize().getY()); }
			}
		}
	}
	
	private void processCheckBoxes() {
		for(int i = 0; i < checkBoxes.size(); i++) {
			if(clear) g.fillRect(checkBoxes.get(i).getPosition().getX(), checkBoxes.get(i).getPosition().getY(), checkBoxes.get(i).getSize().getX(), checkBoxes.get(i).getSize().getY());
			if(!checkBoxes.get(i).isChecked()) g.drawImage(checkBoxes.get(i).getBox().getModel(), checkBoxes.get(i).getPosition().getX(), checkBoxes.get(i).getPosition().getY(), checkBoxes.get(i).getSize().getX(), checkBoxes.get(i).getSize().getY(), null);
			if(checkBoxes.get(i).isChecked()) g.drawImage(checkBoxes.get(i).getBoxWithTick().getModel(), checkBoxes.get(i).getPosition().getX(), checkBoxes.get(i).getPosition().getY(), checkBoxes.get(i).getSize().getX(), checkBoxes.get(i).getSize().getY(), null);
		}
	}
	
	private void processTextFields() {
		for(int i = 0; i < textFields.size(); i++) {
			if(clear) g.fillRect(textFields.get(i).getPosition().getX(), textFields.get(i).getPosition().getY(), textFields.get(i).getSize().getX(), textFields.get(i).getSize().getY());
			g.drawImage(textFields.get(i).getTextFieldTex().getModel(), textFields.get(i).getPosition().getX(), textFields.get(i).getPosition().getY(), textFields.get(i).getSize().getX(), textFields.get(i).getSize().getY(), null);
			g.setFont(textFields.get(i).getInputText().getFont());
			g.setColor(textFields.get(i).getInputText().getColor());
			g.drawString(textFields.get(i).getInputText().getString(), textFields.get(i).getInputText().getPosition().getX(), textFields.get(i).getInputText().getPosition().getY());
		}
	}
	
	private void prepare() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g2d = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		if(background != null)
			g.drawImage(background, 0, 0, width, height, null);
		else
			g.fillRect(0, 0, width, height);
		
		processTextures();
		processButtons();
		processTexts();
		processCheckBoxes();
		processTextFields();
		
		g.dispose();
		bs.show();
	}
	
	protected void setBackground(BufferedImage backgroundImage) {
		background = backgroundImage;
	}
	
	public static JFrame window() {
		return frame;
	}
	
	/** @return Width of game window */
	public static int getSWidth() {
		return staticWidth;
	}
	
	/** @return Height of game window */
	public static int getSHeight() {
		return staticHeight;
	}
	
	public static Keyboard getKeyboard() {
		return keyboard;
	}
	
	public static Mouse getMouse() {
		return mouse;
	}
	
	public static StatesManager getSM() {
		return sm;
	}
}
