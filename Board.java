import java.util.ArrayList;
import java.lang.Math;
import java.util.*;

public class Board{
    public int[][] board;
    public int size;
    

    public Board(int n){
        this.size = n;
        this.board = random_setBoard(this.size);
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
            //System.out.print("| ");  
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
            }
            cpt++;
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
    
    public void moveUp(){
        //First find the 0
        int zero_line = 0;
        int zero_column = 0;
        boolean find = false;

        for(int i = 0; i<this.size; i++){
            for(int j = 0; j<this.size; j++){
                if(this.board[i][j]==0){
                    zero_line = i;
                    zero_column = j;
                    find = true;
                    break;
                }
            }
            if(find){break;}
        }

        //Check if zero is not on the last line
        if(zero_line==this.size-1){
            System.out.println("Can't move :'(");
        }
        else{
            int number = this.board[zero_line+1][zero_column];
            this.board[zero_line][zero_column] = number;
            this.board[zero_line+1][zero_column] = 0;
        }

    }

    public void moveDown(){
        //First find the 0
        int zero_line = 0;
        int zero_column = 0;
        boolean find = false;

        for(int i = 0; i<this.size; i++){
            for(int j = 0; j<this.size; j++){
                if(this.board[i][j]==0){
                    zero_line = i;
                    zero_column = j;
                    find = true;
                    break;
                }
            }
            if(find){break;}
        }

        //Check if zero is not on the first line
        if(zero_line==0){
            System.out.print("Can't move :'(");
        }
        else{
            int number = this.board[zero_line-1][zero_column];
            this.board[zero_line][zero_column] = number;
            this.board[zero_line-1][zero_column] = 0;
        }

    }

    public void moveLeft(){
        //First find the 0
        int zero_line = 0;
        int zero_column = 0;
        boolean find = false;

        for(int i = 0; i<this.size; i++){
            for(int j = 0; j<this.size; j++){
                if(this.board[i][j]==0){
                    zero_line = i;
                    zero_column = j;
                    find = true;
                    break;
                }
            }
            if(find){break;}
        }

        //Check if zero is not on the first column
        if(zero_column==0){
            System.out.print("Can't move :'(");
        }
        else{
            int number = this.board[zero_line][zero_column-1];
            this.board[zero_line][zero_column] = number;
            this.board[zero_line][zero_column-1] = 0;
        }

    }

    public void moveRight(){
        //First find the 0
        int zero_line = 0;
        int zero_column = 0;
        boolean find = false;

        for(int i = 0; i<this.size; i++){
            for(int j = 0; j<this.size; j++){
                if(this.board[i][j]==0){
                    zero_line = i;
                    zero_column = j;
                    find = true;
                    break;
                }
            }
            if(find){break;}
        }

        //Check if zero is not on the last column
        if(zero_column==this.size-1){
            System.out.print("Can't move :'(");
        }
        else{
            int number = this.board[zero_line][zero_column+1];
            this.board[zero_line][zero_column] = number;
            this.board[zero_line][zero_column+1] = 0;
        }

    }

}