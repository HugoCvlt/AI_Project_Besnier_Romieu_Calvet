
import java.util.HashMap;
import java.util.Map.Entry;

public class StateSet {
	HashMap<String,SearchNode> set;
	
	public StateSet() {
		this.set = new HashMap<String,SearchNode>();
	}
	
	public void add(SearchNode state) {
		this.set.put(state.toString(),state);
	}

	public static SearchNode isIntersecting(StateSet s1, StateSet s2){
		// find if there is a search node that is present in s1 and s2
		// go through a HashMap

		for (Entry<String,SearchNode> s : s1.set.entrySet()){
			if (s2.set.keySet().contains(s.getKey())){
				return s.getValue();
			}
		}
		return null;
	}
	
	
	public boolean contains(SearchNode state) {
		return this.set.containsKey(state.toString());
	}
	
	public int size() {
		return this.set.size();
	}
}


