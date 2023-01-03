public class Heuristics { 
    
    // Displaced elements
    public static int h1(Board b){
        int cpt = 1;
        int h = 0;
        for (int i=0; i< b.size; i++){
            for (int j=0; j< b.size; j++){
                if ((i != b.size - 1) || (j != b.size - 1)){
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

    // Manhattan Distance
    public static int h2(Board b){
        int h = 0;
        for (int i=0; i< b.size; i++){
            for (int j=0; j< b.size; j++){
                if (b.board[i][j] != 0){
                    // 
                    h = h + Math.abs( ((b.board[i][j] - 1 ) % b.size) - j ) + Math.abs(((b.board[i][j] - 1) / b.size) - i);
                }else{
                    h = h + (Math.abs(b.size - 1 - i) + Math.abs(b.size - 1 - j));
                }
            }
        }
    return h;
    }
    /*
    public static int row_nb(int value,Board b){
        int cpt = 1;
        for(int i=0; i < b.size;i++){
            for(int j=0 ; j< b.size; j++){
                
                if (value == 0){
                    return b.size - 1;
                }
                if (cpt == value){
                    return i;
                }
                cpt++;
            }
        }
        return -1;
    } */

}
