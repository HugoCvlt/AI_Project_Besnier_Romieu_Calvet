import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(4);
        
        board.print_board();
        //System.out.println(board.end_test());
        //System.out.println(" h1 :" + Board.h1(board));
        //System.out.println(" h2 :" + Board.h2(board));

        Scanner sc = new Scanner(System.in);

        while(!board.end_test()){

            System.out.println("(u, d, l, r) ? ");
            String play = sc.nextLine();
            
            switch(play){

                case "u":
                    board.moveUp();

                case "d":
                    board.moveDown();

                case "l":
                    board.moveLeft();

                case "r":
                    board.moveRight();

            }//End switch

            board.print_board();

        }//End game

        sc.close();
    }

}
