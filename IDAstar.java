import java.util.ArrayList;
import java.util.Collections;

public class IDAstar {
    // IDA star is a Depth First Search algo (DFS) using the evaluation of Heuristic. 
    // DFS properties of IDA star make him spatialy more efficient than A star.
    // with the heuristic evaluation, the algo is concentrate on exploring the most promising node
    // the heuristics must be admissible (and consistent)
    // does not keep track of the visited nodes 
    public SearchNode root;
    public int heuristic;
    public ArrayList<Character> answer;

    public IDAstar(Board root, int heuristic){
        this.heuristic = heuristic;
        this.root = new SearchNode(root, 0, null, '\0', heuristic);
    }

    public String solve(){
        float threshold = this.root.valH;
        while(true){
            int temp = this.search(this.root,0,threshold); // start from the root node
            
            if (temp == -1){ // if goal found
                return "FOUND";
            }
            if (temp == Integer.MAX_VALUE){ // threshold larger than maximum possible 
                return null;
            }
            threshold = temp; // the threshold is increased to the minimum of the f ( = path cost + heuristic) of the nodes that exceeds the threshold.   
        }
            

    }
    /*** During the recursion whenever a node with higher f score than the threshold is reached that node is not futher explored but he f scored is noted, since we encounter many such nodes, the minimum of these f score is returned as the new threshold. */
    public int search(SearchNode node, int g, float threshold){
        int f = (int) (g + node.valH);
        
        
        if (node.state.end_test()){
            ArrayList<Character> res = new ArrayList<Character>();
            while(node.father != null){
                res.add(node.actionFather);
                node = node.father;
            }
            Collections.reverse(res);
            // return the list of moves to solve the taquin
            this.answer = res;
            return -1;
        }
        if (f > threshold){ // stop the expanding the node here
            return f;
        }
        int min = Integer.MAX_VALUE;

        for (SearchNode n : node.expand()){
            // g is the cost from the root to this state, increase by one (one move) at each search call
            int temp = this.search(n, g + 1,threshold);
            if (temp == -1){ // node found
                return -1;
            } 
            else if (temp < min){
                min = temp;
            }
        }
        return min;
    }


    
}
