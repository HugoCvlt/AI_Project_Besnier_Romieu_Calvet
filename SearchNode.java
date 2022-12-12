import java.util.ArrayList;

public class SearchNode{
    public Board state;
	public int nbrActions;
	public SearchNode father;
	char actionFather;
	public int identifiant;
	public float valH;
	public static int id = 0;

    //Heuristics.h1;
    //Heuristics.h2;

    public SearchNode(Board board, int iter, SearchNode father, char action) {
		this.state =  board;
		nbrActions = iter;
		this.father = father;
		this.actionFather = action;
		this.identifiant = id++;
	}
    public SearchNode(int size, int iter) {
		this.state = new Board(size);
		nbrActions = iter;
		this.identifiant = id++;
	}

    public ArrayList<SearchNode> expand() {
		ArrayList<SearchNode> succ = new ArrayList<SearchNode>();
		String action = this.state.get_action_available();
        for(int i=0; i < action.length(); i++){
			Board tmp = (Board) state.clone();
            char play = action.charAt(i);
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
                succ.add(new SearchNode(tmp,this.nbrActions+1,this,play));
            }
		return succ;
	    }
    }
}
