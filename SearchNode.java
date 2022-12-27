import java.util.ArrayList;

public class SearchNode implements Comparable<SearchNode>{
    public Board state;
	public int nbrActions;
	public SearchNode father;
	char actionFather;
	public int identifiant;
	public float valH;
    public int heuristic;
	public static int id = 0;


    public SearchNode(Board board, int iter, SearchNode father, char action,int heuristic) {
		this.state =  board;
		nbrActions = iter;
		this.father = father;
		this.actionFather = action;
		this.identifiant = id++;
        this.heuristic = heuristic;
        if (this.heuristic == 0){
            this.valH = 1;
        }
        if (this.heuristic == 1){
            this.valH = Heuristics.h1(board);
        }
        if (this.heuristic == 2){
            this.valH = Heuristics.h2(board);
        }
	}

    public ArrayList<SearchNode> expand() {
		ArrayList<SearchNode> succ = new ArrayList<SearchNode>();
		String action = this.state.get_action_available();
        for(int j=0; j < action.length(); j++){
			Board tmp = (Board) state.clone();
            char play = action.charAt(j);
			switch(play){
                case 'u':
                    tmp.moveUp();
                    break;

                case 'd':
                    tmp.moveDown();
                    break;

                case 'l':
                    tmp.moveLeft();
                    break;

                case 'r':
                    tmp.moveRight();
                    break;
            }
            succ.add(new SearchNode(tmp,this.nbrActions+1,this,play,this.heuristic));
		
	    }
        return succ;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    // This method is used in the priority queue (implement with a TreeSet)
    // the search nodes are sorted according their nb of actions and for some algos their own heuristics 
    public int compareTo(SearchNode s) {
		float value = this.nbrActions + this.valH - s.nbrActions - s.valH;
		if(value > 0.0001 || value < -0.0001)
			return value > 0? 1 : -1;
		//if(this.nbrActions != s.nbrActions)
			//return this.nbrActions < s.nbrActions? 1: -1;
		return this.identifiant - s.identifiant;
	}

    public static ArrayList<SearchNode> isIntersecting(ArrayList<SearchNode> l1, ArrayList<SearchNode> l2){
        for(SearchNode s1 : l1){
            for(SearchNode s2 : l2){
                if (SearchNode.same(s1,s2) == true){
                    ArrayList<SearchNode> c = new ArrayList<SearchNode>(2);
                    c.add(s1);c.add(s2);
                    return c;
                }
            }
        } return null;
    }

    public static Boolean same(SearchNode s, SearchNode s1){
        return Board.same(s.state,s1.state);
    }

    public String toString(){
        return this.state.toString();
    }

}


