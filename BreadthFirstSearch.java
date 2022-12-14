import java.util.ArrayList;
import java.util.Collections;

public class BreadthFirstSearch {
    public SearchNode root;
    public ArrayList<SearchNode> frontier;
    public ArrayList<SearchNode> explored;

    public BreadthFirstSearch(Board root){
        this.root = new SearchNode(root,0,null,'\0');
        this.frontier = new ArrayList<SearchNode>();
        this.explored = new ArrayList<SearchNode>();
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