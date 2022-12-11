import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(2);
        
        board.print_board();
        //System.out.println(board.end_test());
        //System.out.println(" h1 :" + Board.h1(board));
        //System.out.println(" h2 :" + Board.h2(board));

        Scanner input = new Scanner(System.in);

        while (!board.end_test()){

            System.out.println("(u, d, l, r) ? ");
            String play = input.nextLine();
            
            switch(play){
                case "u":
                    board.moveUp();
                    break;

                case "d":
                    board.moveDown();
                    break;

                case "l":
                    board.moveLeft();
                    break;

                case "r":
                    board.moveRight();
                    break;

            }//End switch

            board.print_board();

        }//End game

        input.close();
    }

}
