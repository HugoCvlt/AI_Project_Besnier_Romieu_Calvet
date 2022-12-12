import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(3);
        
        board.print_board();
        //System.out.println(board.end_test());
        //System.out.println(" h1 :" + Board.h1(board));
        //System.out.println(" h2 :" + Board.h2(board));

        Scanner input = new Scanner(System.in);

        while (!board.end_test()){

            System.out.println("(u, d, l, r) ? ");
            char play = input.next().charAt(0);
            
            board.move(play);

            board.print_board();
            System.out.println(board.end_test());

        }//End game
        System.out.println("Well done you win !!!!!");

        input.close();
    }

}
