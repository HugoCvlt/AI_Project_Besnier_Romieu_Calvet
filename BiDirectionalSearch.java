import java.util.ArrayList;
import java.util.Collections;

public class BiDirectionalSearch {
    // this algo has a complexity of b^d/2 + b^d/2 (means O(b^d/2) so it's much less than b^d of BFS (implementation = 2 BFS one from the starting node the 
    // b is the branching factor (max 4 in our case) and d is the depth of the solution (number of moves required)
    // the other from the bottom)
    // we can use it because the initial and the goal states are unique and the branching factor is the same in both directions. 
    public SearchNode root;
    public SearchNode end;
    public ArrayList<SearchNode> frontier_s; // starting node 
    public StateSet explored_s;
    public ArrayList<SearchNode> frontier_t; // end state
    public StateSet explored_t;

    public BiDirectionalSearch(Board root) throws Exception{
        this.root = new SearchNode(root,0,null,'\0');
        this.end = new SearchNode(end_board(root.size),0,null,'\0');
        this.frontier_s =  new ArrayList<SearchNode>();// used as a FIFO queue
        this.explored_s = new StateSet(); // set of searchNodes
        this.frontier_t = new ArrayList<SearchNode>(); // FIFO
        this.explored_t = new StateSet();
    }

    // give the goal board
    public static Board end_board(int size) throws Exception{
        int[][] final_mat = new int[size][size];
        int cpt = 1;
        for(int i = 0; i<size; i++){
            for(int j=0; j<size; j++){
                if((i==size-1) && (j==size-1)){
                    final_mat[i][j] = 0;
                }
                else{
                    final_mat[i][j] = cpt;
                }
                cpt++;
            }
        }
        return new Board(size,final_mat);
    }

    //
    //     Bidirectional search is implemented by replacing the goal test with a check to see
    //     whether the frontiers of the two searches intersect; if they do, a solution has been found.
    //     (It is important to realize that the first such solution found may not be optimal, even if the
    //     two searches are both breadth-first; some additional search is required to make sure there
    //     isn’t another short-cut across the gap.) The check can be done when each node is generated
    //     or selected for expansion and, with a hash table, will take constant time.
    //         

    public ArrayList<Character> solve(){
        SearchNode curr_s = this.root;
        SearchNode curr_t = this.end;

        if(curr_s.state.end_test()){
            return new ArrayList<Character>();
        }
        this.frontier_s.add(curr_s);
        this.frontier_t.add(curr_t);

        boolean finished = false;
        ArrayList<SearchNode> next_s = null;
        ArrayList<SearchNode> next_t = null;
        while(!finished){
            if(this.frontier_s.isEmpty()){
                return null;
            }
            if(this.frontier_t.isEmpty()){
                return null;
            }
            curr_s = this.frontier_s.remove(0);
            curr_t = this.frontier_t.remove(0);

            this.explored_s.add(curr_s);
            this.explored_t.add(curr_t);
            next_s = curr_s.expand();
            next_t = curr_t.expand();

            if(next_s.isEmpty()){
                finished = true;
            }
            else{
                 for (SearchNode child : next_s) {
                     if(!(this.frontier_s.contains(child) || this.explored_s.contains(child))){
                        ArrayList<SearchNode> common_states=  SearchNode.isIntersecting(this.frontier_s,this.frontier_t);
                        if (common_states != null){
                            int r = common_states.get(0).identifiant + common_states.get(1).identifiant;
                            System.out.println("number of expanded state node to find the best solution using Bidirectional " + r);
                            ArrayList<Character> res = new ArrayList<Character>();
                            SearchNode temp_s = common_states.get(0);
                            while(temp_s.father != null){ // get the path from the root to the intersect state
                                res.add(temp_s.actionFather);
                                temp_s = temp_s.father;
                            }
                            Collections.reverse(res);
                            SearchNode temp_t = common_states.get(1);  
                            while(temp_t.father != null){ // get the path from the root to the intersect state
                                res.add(Board.opposite_move(temp_t.actionFather));
                                temp_t = temp_t.father;
                            } 
                            return res;
                        }else{
                            this.frontier_s.add(child);
                        }
                    }
                }
            }
            if(next_t.isEmpty()){
                finished = true;
            }else{
                for (SearchNode child : next_t) {
                    if(!(this.frontier_t.contains(child) || this.explored_t.contains(child))){
                       ArrayList<SearchNode> common_states =  SearchNode.isIntersecting(this.frontier_s,this.frontier_t);
                       if (common_states != null){
                           int r = common_states.get(0).identifiant + common_states.get(1).identifiant;
                           System.out.println("number of expanded state node to find the best solution using Bidirectional " + r);
                           ArrayList<Character> res = new ArrayList<Character>();
                           SearchNode temp_s = common_states.get(0);
                           while(temp_s.father != null){ // get the path from the root to the intersect state
                               res.add(temp_s.actionFather);
                               temp_s = temp_s.father;
                           }
                           Collections.reverse(res);
                           SearchNode temp_t = common_states.get(1);  
                           while(temp_t.father != null){ // get the path from the root to the intersect state
                               res.add(Board.opposite_move(temp_t.actionFather));
                               temp_t = temp_t.father;
                           } 
                           return res;
                       }else{
                           this.frontier_t.add(child);
                       }
                   }
               }
            }
        }
        return null;
    }

}


