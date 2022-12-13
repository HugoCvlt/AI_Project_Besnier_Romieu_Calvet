import java.lang.Math;
import java.util.*;
import java.util.ArrayList;

public class Board{
    public int[][] board;
    public int size;
    private int[] coor_empty_space;
    

    public Board(int n){
        this.size = n; // size of the board
        this.board = random_setBoard(this.size);
        this.coor_empty_space = this.find_coo_zero();
    }
    public Board(Board b) {
		this.board = new int[b.size][b.size];
        this.size = b.size;
        this.coor_empty_space = b.coor_empty_space;
		for(int i=0;i < b.size;i++){
            for(int j=0 ; j<b.size; j++){
                this.board[i][j] = b.board[i][j];
            }
        }
	}

    public String  get_action_available(){
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
      return action_available.toString();

    }

    public Object clone(){
		return new Board(this);
	}

    public static int[][] random_setBoard(int n){
        ArrayList<Integer> mylist = new ArrayList<Integer>();
        int [][] board = new int[n][n];

        for (int i=0; i< Math.pow(n,2); i++){
            mylist.add(i);
        }
        Collections.shuffle(mylist);
        int cpt = 0;
        for(int i=0; i< n; i++){
            for(int j=0; j< n; j++){
                board[i][j] = mylist.get(cpt);
                cpt++;   
            }
        }
        return board;
    }

    public void print_board(){
        for(int i=0; i< this.size; i++){
            for(int j=0; j< this.size; j++){
                 System.out.print(this.board[i][j] + "\t");   
            }
            System.out.println();
        }
    }

    public Boolean end_test(){
        int cpt = 1;
        for(int i=0; i< this.size;i++){
            for(int j=0; j< this.size; j++){
                if ((i != this.size - 1) && (j != this.size - 1)){
                    if (this.board[i][j] != cpt){
                       return false;      
                    }
                }else{
                    if (this.board[i][j] != 0){
                        return false;
                    }
                }
                cpt++;
            }
           
        }
        return true;
    }

    

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

}