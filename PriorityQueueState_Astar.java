import java.util.HashMap;
import java.util.TreeSet;

public class PriorityQueueState_Astar{
	public TreeSet<SearchNode> queue;
	HashMap<String,SearchNode> set;
    Heuristics h;
	
	public PriorityQueueState_Astar() {
		this.queue = new TreeSet<SearchNode>();
		this.set = new HashMap<String,SearchNode>();
        this.h = new Heuristics();
	}

    public void push(SearchNode node) {
		String stateString = node.toString();
		SearchNode stateFrontier = this.set.get(stateString);
		if(stateFrontier == null) {
			this.queue.add(node);
			this.set.put(stateString,node);
		}
		else {
            // this is where the priority is checked
			if(stateFrontier.nbrActions > node.nbrActions) {
				this.queue.remove(stateFrontier);
				this.set.remove(stateFrontier.toString());
				this.queue.add(node);
				this.set.put(stateString,node);
			}
		}
	}    
	
	public void push_h1(SearchNode node) {
		String stateString = node.toString();
		SearchNode stateFrontier = this.set.get(stateString);
		if(stateFrontier == null) {
			this.queue.add(node);
			this.set.put(stateString,node);
		}
		else {
            // this is where the priority is checked
			if(stateFrontier.nbrActions + this.h.h1(stateFrontier.state) > node.nbrActions + this.h.h1(stateFrontier.state)) {
				this.queue.remove(stateFrontier);
				this.set.remove(stateFrontier.toString());
				this.queue.add(node);
				this.set.put(stateString,node);
			}
		}
	}

    public void push_h2(SearchNode node) {
		String stateString = node.toString();
		SearchNode stateFrontier = this.set.get(stateString);
		if(stateFrontier == null) {
			this.queue.add(node);
			this.set.put(stateString,node);
		}
		else {
            // this is where the priority is checked
			if(stateFrontier.nbrActions + this.h.h2(stateFrontier.state) > node.nbrActions + this.h.h2(stateFrontier.state)) {
				this.queue.remove(stateFrontier);
				this.set.remove(stateFrontier.toString());
				this.queue.add(node);
				this.set.put(stateString,node);
			}
		}
	}
	
	public SearchNode pop() {
		SearchNode current = this.queue.first();
		this.queue.remove(current);
		this.set.remove(current.toString());
		return current;
	}
	
	public int size() {
		return this.set.size();
	}
}
