import java.util.ArrayList;
import java.util.Collections;


public class Uniform_Cost_Search {
    public SearchNode root;
	public State_set explored;
	public PriorityQueueState frontier;

    public Uniform_Cost_Search(Board initialState){
        this.root = new SearchNode(initialState,0,null,'\0');
		this.frontier = new PriorityQueueState();
		this.frontier.push(this.root);
		this.explored = new State_set();
    }
	
    public ArrayList<Character> solve(){
		
		SearchNode node = this.root;
		
		do {
            // if frontier is empty then return a failure 
			if (this.frontier.size() == 0) {
				return null;
			}
			node = this.frontier.pop(); // priority queue pop the lowest cost one
			ArrayList<Character> L = new ArrayList<Character>();
			
			if (node.state.end_test()) {
				while (node.father != null){ //find the root - initial state
					L.add(node.actionFather); // u d l r 
					node = node.father; // go to the node father
				}
				Collections.reverse(L);
				return L; // the array of instruction to go from the shuffle initial board to the solution
			}

			this.explored.add(node);
			
			for (SearchNode child : node.expand()) {
				if (!((this.explored.contains(child)) && (this.frontier.queue.contains(child)))){
					this.frontier.push(child);
				}else if(this.frontier.queue.contains(child)){
                    // if the child is in the frontier with a higher path cost (nbrActions)
                    // then replace that frontier node with child
					this.frontier.push(child);
				}	
			}
			
		}while(true);
		
	}
    
}
