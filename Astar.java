import java.util.ArrayList;
import java.util.Collections;


// the UCS has a complexity of O(b ^(1 + floor(C/eps)) where C is the cost of the optimal solution , eps is the cost of the minimum cost 
public class Astar {
    public SearchNode root;
	public StateSet explored;
	public PriorityQueueState_Astar frontier;
	public int heuristics;
	public int max_size_frontier;
	public int max_size_explore_set;

    public Astar(Board initialState, int heuristics){
        this.root = new SearchNode(initialState,0,null,'\0', heuristics);
		this.frontier = new PriorityQueueState_Astar();
		this.frontier.push_h1(this.root);
		this.explored = new StateSet();
		this.heuristics = heuristics;
		this.max_size_frontier = 0;
		this.max_size_explore_set = 0;
    }
	
    public ArrayList<Character> solve(){
		
		SearchNode node = this.root;
		
		do {
            // if frontier is empty then return a failure 
			if (this.frontier.size() == 0) {
				return null;
			}
			if (this.max_size_frontier < this.frontier.size()){
                this.max_size_frontier = this.frontier.size();
            }
			node = this.frontier.pop(); // priority queue pop the lowest cost one
			
			
			if (node.state.end_test()) {
				ArrayList<Character> L = new ArrayList<Character>();
				this.max_size_explore_set = this.explored.size();
				//System.out.println("number of expanded node states to find the best solution using Astar with the heuristic "+ this.heuristics + " : " + this.explored.size());
				//System.out.println("the maximum size of the frontier is " + this.max_size_frontier);
				while (node.father != null){ //find the root - initial state
					L.add(node.actionFather); // u d l r 
					node = node.father; // go to the node father
				}
				Collections.reverse(L);
				return L; // the array of instruction to go from the shuffle initial board to the solution
			}

			this.explored.add(node);
			
			for (SearchNode child : node.expand()) {
				if (!((this.explored.contains(child)) || (this.frontier.queue.contains(child)))){

					switch(this.heuristics){

						case 1:
							this.frontier.push_h1(child);
							break;

						case 2:
							this.frontier.push_h2(child);
							break;

						default:
							System.out.println("Heuristic doesn't exist");
					}
					
					
				}else if(this.frontier.queue.contains(child)){
                    // if the child is in the frontier with a higher path cost (nbrActions)
                    // then replace that frontier node with child
					switch(this.heuristics){

						case 1:
							this.frontier.push_h1(child);
							break;

						case 2:
							this.frontier.push_h2(child);
							break;

						default:
							System.out.println("Heuristic doesn't exist");
					}
				}	
			}
			
		}while(true);
		
	}
    
}
