import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Board board = new Board(4,20);
        
        board.show_board();
        //System.out.println(board.end_test());
        //System.out.println(" h1 :" + Board.h1(board));
        //System.out.println(" h2 :" + Board.h2(board));

        Scanner input = new Scanner(System.in);

        while (!board.end_test()){
            
            System.out.print("Available actions : | ");
            board.get_action_available().chars().forEach(i -> System.out.print((char)i +" | "));
            System.out.println();
            System.out.print("=> ");
            char play = input.nextLine().charAt(0);
            System.out.println();

            board.move(play);

            board.show_board();
            System.out.println(board.end_test());

        }//End game
        System.out.println("Well done you win !!!!!");
        input.close();
    }

}
