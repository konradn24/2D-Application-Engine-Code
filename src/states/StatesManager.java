package states;

import java.util.ArrayList;
import java.util.List;

public class StatesManager {

	private List<State> states = new ArrayList<State>();
		
	private State current;
	
	public void onStart() {
		for(State state : states) {
			state.onStart();
		}
	}
	
	public void render() {
		if(current != null) {
			current.render();
		}
	}
		
	public void update() {
		if(current != null) {
			current.update();
		}
	}
	
	public void updateOnSec() {
		if(current != null) {
			current.updateOnSec();
		}
	}
	
	public void addState(State state) {
		states.add(state);
	}
	
	public void changeState(int ID) {
		if(ID >= 0 && ID < states.size() && current != states.get(ID)) {
			current = states.get(ID);
		}
	}
		
	public List<State> getStates() {
		return states;
	}

	public State getCurrent() {
		return current;
	}
}
