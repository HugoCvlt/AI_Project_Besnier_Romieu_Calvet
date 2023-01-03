import java.util.HashMap;
import java.util.TreeSet;

public class PriorityQueueState{
	public TreeSet<SearchNode> queue;
	HashMap<String,SearchNode> set;
	
	public PriorityQueueState() {
		this.queue = new TreeSet<SearchNode>();
		this.set = new HashMap<String,SearchNode>();
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
			if(stateFrontier.nbrActions + stateFrontier.valH > node.nbrActions + node.valH ) {
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
