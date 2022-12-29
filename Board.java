import java.lang.Math;
import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;  

public class Board implements Cloneable{
    public int[][] board;
    public int size;
    

    //for random board
    public Board(int n, int nb_shuffle) throws Exception{
        this.size = n; // size of the board
        this.board = random_setBoard(n, nb_shuffle);
    }

    // for coping 
    public Board(Board b) {
		this.board = new int[b.size][b.size];
        this.size = b.size;
		for(int i=0;i < b.size;i++){
            for(int j=0 ; j<b.size; j++){
                this.board[i][j] = b.board[i][j];
            }
        }
	}
    // when we have already the matrix
    public Board(int n, int[][] mat) throws Exception{
        if(n != mat.length){ throw new Exception();}
        this.size = n;
        this.board = mat;
    }
    // creating a board froma string 
    public Board(String board){
        this.board = set_board_from_string(board);
        this.size = this.board.length;
        
    }
    // becareful the string is something that looks like this "1,2,3,5,10,...,0,...""
    public int[][] set_board_from_string(String board_s){
        String[] s_plit = board_s.split(",");
        int size = (int) Math.sqrt(s_plit.length);
        int[][] board = new int[size][size];
        int cpt = 0;
        for(int i=0;i < size;i++){
            for(int j=0 ; j< size; j++){
                board[i][j] = Integer.parseInt(s_plit[cpt]);
                cpt++;
            }
        }
        return board;
    }

    public int[][] get_matrix(){
        return this.board;
    }

    public String get_action_available(){
      int[] rep = find_coo_zero();
      int zero_row = rep[0];
      int zero_column = rep[1];
      List<Character> action_available = new ArrayList<Character>();
        
      if (zero_row <= this.size-2){
        action_available.add('u');
      }
      if (zero_row >= 1){
        action_available.add('d');
      } 
      if (zero_column <= this.size - 2){
        action_available.add('l');
      }
      if (zero_column >= 1){
        action_available.add('r');
      }
      // getting a real string
      String str = action_available.stream()
                  .map(e->e.toString())
                  .collect(Collectors.joining());
      return str;
    }

    

    public Object clone(){
		return new Board(this);
	}

    public static int[][] random_setBoard(int n, int k) throws Exception{
        //Create final board
        int[][] final_mat = new int[n][n];
        int cpt = 1;
        for(int i = 0; i<n; i++){
            for(int j=0; j<n; j++){
                if((i==n-1) && (j==n-1)){
                    final_mat[i][j] = 0;
                }
                else{
                    final_mat[i][j] = cpt;
                }
                cpt++;
            }
        }

        Board shuffled = new Board(n, final_mat);
        //SHUFLLE
        String available_action;
        int indiceRand;
        char move;
        for(int l = 0; l < k; l++){
            available_action = shuffled.get_action_available();
            indiceRand = (int)(Math.random()*(available_action.length()));
            move = available_action.charAt(indiceRand);

            shuffled.move(move);

        }
        return shuffled.get_matrix();
    }

    public void show_board(){
        for(int i=0; i< this.size; i++){
            for(int j=0; j< this.size; j++){
                 System.out.print(this.board[i][j] + "\t");   
            }
            System.out.println();
        }
        System.out.println();
    }

    public Boolean end_test(){
        int cpt =1;
        for(int i=0; i< this.size;i++){
            for(int j=0; j< this.size; j++){

                if ((i == this.size - 1) && (j == this.size - 1)){
                    if (this.board[i][j] != 0){
                       return false;      
                    }
                }

                else{
                    if (this.board[i][j] != cpt){
                        return false;
                    }
                }
                cpt++;
            }
        }
        return true;
    }
    public static Boolean same(Board b1, Board b2){
        if(b1.size == b2.size){
            for(int i=0; i< b1.size;i++){
                for(int j=0; j< b1.size; j++){
                    if (b1.board[i][j] != b2.board[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    
    // find the coordonnate of the empty space in the board
    public int[] find_coo_zero(){
        int[] rep = new int[2];
        for(int i=0; i < this.size;i++){
            for(int j=0; j < this.size;j++){
                if (this.board[i][j] == 0){
                    rep[0] = i ; rep[1] = j;
                    return rep;
                }
            }
        }
        return rep;
    }
    
    // move the tile up
    public void moveUp(){
        //First find the 0
        int[] rep = find_coo_zero();
        int zero_row = rep[0];
        int zero_column = rep[1];

        //Check if zero is not on the last line
        if(zero_row==this.size-1){
            System.out.println("Can't move :'(");
        }
        else{
            int number = this.board[zero_row+1][zero_column];
            this.board[zero_row][zero_column] = number;
            this.board[zero_row+1][zero_column] = 0;
        }

    }

     // move the tile down
    public void moveDown(){//Move tile under the 0
        //First find the 0
        int[] rep = find_coo_zero();
        int zero_row = rep[0];
        int zero_column = rep[1];

        //Check if zero is not on the first line
        if(zero_row==0){
            System.out.print("Can't move :'(");
        }
        else{
            int number = this.board[zero_row-1][zero_column];
            this.board[zero_row][zero_column] = number;
            this.board[zero_row-1][zero_column] = 0;
        }

    }
     // move the tile right
    public void moveRight(){
        //First find the 0
        int[] rep = find_coo_zero();
        int zero_row = rep[0];
        int zero_column = rep[1];

        //Check if zero is not on the first column
        if(zero_column==0){
            System.out.println("Can't move :'(");
        }
        else{
            this.board[zero_row][zero_column] = this.board[zero_row][zero_column-1];
            this.board[zero_row][zero_column-1] = 0;
        }

    }

     // move the tile left
    public void moveLeft(){
        //First find the 0
        int[] rep = find_coo_zero();
        int zero_row = rep[0];
        int zero_column = rep[1];

        //Check if zero is not on the last column
        if(zero_column == this.size-1){
            System.out.println("Can't move :'(");
        }
        else{
            this.board[zero_row][zero_column] = this.board[zero_row][zero_column + 1];
            this.board[zero_row][zero_column + 1] = 0;
        }

    }
    // move a tile according to an feasible instruction
    public void move(char x){
        switch(x){
            case 'u':
                this.moveUp();
                break;

            case 'd':
                this.moveDown();
                break;

            case 'l':
                this.moveLeft();
                break;

            case 'r':
                this.moveRight();
                break;
            default:
                break;
        }
    }
    // convert the board to a string (use as Hashmap key in the Priority queue function)
    public String toString() {
		String s = "";
		for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size ; j++){
                if ((i== this.size-1) && (j==this.size-1)){
                    s += Integer.toString(this.board[i][j]);
                }else{
                    s += Integer.toString(this.board[i][j]);
                    s +=",";
                }
                

            }
		}
		return s;
	}

    // to implement the Bidrectional, we need to take the opposite moves from the
    // final state to the common state in the frontier. Otherwise, the output path
    // (father actions will not work)
    public static char opposite_move(char c){
        switch(c){
            case 'd': return 'u'; 
            case 'u' : return 'd'; 
            case 'r' : return 'l'; 
            case 'l' : return 'r';
            default: return '\0';
        }
    }

}