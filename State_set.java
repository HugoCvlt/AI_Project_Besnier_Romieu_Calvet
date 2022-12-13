import java.util.HashMap;

public class State_set {
	HashMap<String,SearchNode> set;
	
	public State_set() {
		this.set = new HashMap<String,SearchNode>();
	}
	
	public void add(SearchNode state) {
		this.set.put(state.toString(),state);
	}
	
	public boolean contains(SearchNode state) {
		return this.set.containsKey(state.toString());
	}
	
	public int size() {
		return this.set.size();
	}
}


