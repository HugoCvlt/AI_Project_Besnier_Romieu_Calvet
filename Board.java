import java.lang.Math;
import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

public class Board{
    public int[][] board;
    public int size;
    

    public Board(int n){
        this.size = n;
        this.board = new int[n][n];
    }

    public Board(Board b) {
		this.board = new int[b.size][b.size];
        this.size = b.size;
		for(int i=0;i < b.size;i++){
            for(int j=0 ; j<b.size; j++){
                this.board[i][j] = b.board[i][j];
            }
        }
	}

    public Board(int n, int[][] mat) throws Exception{
        if(n != mat.length){ throw new Exception();}
        this.size = n;
        this.board = mat;
    }

    public Object clone(){
		return new Board(this);
	}

    public Board random_setBoard(int n, int k){
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
        for(int l = 0; l<k; l++){
            available_action = shuffled.get_action_available();
            indiceRand = Math.random()*(available_action.length-1);
            move = available_action.charAt(indiceRand);

            shuffled.move(move);

        }
        return shuffled;
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

    public static int h1(Board b){
        int cpt = 1;
        int h = 0;
        System.out.print("test");
        for (int i=0; i< b.size; i++){
            for (int j=0; j< b.size; j++){
                if ((i != b.size - 1) && (j != b.size - 1)){
                    if (b.board[i][j] != cpt){
                       h++;      
                    }
                }else{
                    if (b.board[i][j] != 0){
                        h++;
                    }
                }
                cpt++;
            }
        }
        return h;

    }

    public static int h2(Board b){
        int h = 0;
        for (int i=0; i< b.size; i++){
            for (int j=0; j< b.size; j++){
                if (b.board[i][j] != 0){
                    h = h + (Math.abs( ((b.board[i][j] - 1 ) % b.size) - j ) + Math.abs((b.board[i][j] / b.size) - i));
                }else{
                    h = h + (Math.abs(b.size - 1 - i) + Math.abs(b.size - 1 - j));
                }
            }
        }
    return h;
    }

    public ArrayList<Integer> find_coo_zero(){
        List<Integer> rep = new ArrayList<Integer>(2);
        for(int i=0; i < this.size;i++){
            for(int j=0; j < this.size;j++){
                if (this.board[i][j] == 0){
                    rep.add(i);rep.add(j);
                    return (ArrayList<Integer>) rep;
                }
            }
        }
        return (ArrayList<Integer>) rep;
    }
    
    public void moveUp(){
        //First find the 0
        ArrayList<Integer> rep = find_coo_zero();
        int zero_row = rep.get(0);
        int zero_column = rep.get(1);

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
        ArrayList<Integer> rep = find_coo_zero();
        int zero_row = rep.get(0);
        int zero_column = rep.get(1);

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
        ArrayList<Integer> rep = find_coo_zero();
        int zero_row = rep.get(0);
        int zero_column = rep.get(1);

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
        ArrayList<Integer> rep = find_coo_zero();
        int zero_row = rep.get(0);
        int zero_column = rep.get(1);

        //Check if zero is not on the last column
        if(zero_column == this.size-1){
            System.out.println("Can't move :'(");
        }
        else{
            this.board[zero_row][zero_column] = this.board[zero_row][zero_column + 1];
            this.board[zero_row][zero_column + 1] = 0;
        }

    }

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

        }//End switch
    }

}