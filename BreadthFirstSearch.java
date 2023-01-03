import java.util.ArrayList;
import java.util.Collections;



// compmlexity of BFS O(|S| + |E|) = O(2|S|) = 0(|S|) it's also O(b^d)
public class BreadthFirstSearch {
    public SearchNode root;
    public ArrayList<SearchNode> frontier;
    public ArrayList<SearchNode> explored;
    public int max_size_frontier;
    public int max_size_explore_set;

    public BreadthFirstSearch(Board root){
        this.root = new SearchNode(root,0,null,'\0',0);
        this.frontier = new ArrayList<SearchNode>(); // used as a FIFO queue
        this.explored = new ArrayList<SearchNode>(); // set of searchNodes
        this.max_size_frontier = 0;
        this.max_size_explore_set = 0;
    }

    public ArrayList<Character> solve(){
        SearchNode curr = this.root;
        if(curr.state.end_test()){
            return new ArrayList<Character>();
        }

        this.frontier.add(curr);
        boolean finished = false;
        ArrayList<SearchNode> next = null;
        while(!finished){
            if(this.frontier.isEmpty()){
                return null;
            }
            if (this.max_size_frontier < this.frontier.size()){
                this.max_size_frontier = this.frontier.size();
            }

            curr = this.frontier.remove(0);
            this.explored.add(curr);
            next = curr.expand();
            if(next.isEmpty()){
                finished = true;
            }
            else{
                for (SearchNode child : next) {
                    if(!(this.frontier.contains(child) || this.explored.contains(child))){
                        if(child.state.end_test()){
                            max_size_explore_set = this.explored.size();
                            //System.out.println("number of expanded state node to find the best solution using BFS : " + this.explored.size()); 
                            //System.out.println("the maximum size of the frontier is " + max_size_frontier);
                            ArrayList<Character> res = new ArrayList<Character>();
                            while(child.father != null){
                                res.add(child.actionFather);
                                child = child.father;
                            }
                            Collections.reverse(res);
                            return res;
                        }
                        else{
                            this.frontier.add(child);
                        }
                    }
                }
            }
        }
        return null;
    }
}