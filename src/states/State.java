package states;

public abstract class State {

	public abstract void onStart();
	public abstract void render();
	public abstract void update();
	public abstract void updateOnSec();
}
