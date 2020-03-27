package graphics;

import toolbox.Direction;
import toolbox.Vector;

public abstract class GraphicsObject {
	
	protected Vector position, size;
	
	public GraphicsObject(Vector position, Vector size) {
		this.position = position;
		this.size = size;
	}

	public void move(Direction direction, int speed) {
		int x = position.getX(), y = position.getY();
		
		switch(direction) {
		case NORTH: y -= speed; break;
		case EAST: x += speed; break;
		case SOUTH: y += speed; break;
		case WEST: x -= speed; break;
		default: System.err.println("Type correct direction!"); break;
		}
		
		position.setX(x);
		position.setY(y);
	}
	
	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public Vector getSize() {
		return size;
	}

	public void setSize(Vector size) {
		this.size = size;
	}
}
