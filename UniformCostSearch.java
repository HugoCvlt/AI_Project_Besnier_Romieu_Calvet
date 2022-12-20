import java.util.ArrayList;
import java.util.Collections;


// the UCS has a complexity of O(b ^(1 + floor(C/eps)) where C is the cost of the optimal solution , eps is the cost of the minimum cost 
public class UniformCostSearch {
    public SearchNode root;
	public StateSet explored;
	public PriorityQueueState frontier;

    public UniformCostSearch(Board initialState){
        this.root = new SearchNode(initialState,0,null,'\0');
		this.frontier = new PriorityQueueState();
		this.frontier.push(this.root);
		this.explored = new StateSet();
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
				System.out.println("number of expanded node states to find the best solution using UCS " +  node.identifiant);
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
