import java.util.Scanner;
import java.util.*;
public class Main {

    public static void play_the_game() throws Exception{
        
    
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
        }//End game
        System.out.println("Well done you win !!!!!");
        input.close();
    }

    public static void apply_Uniform_search_cost() throws Exception{
        Scanner input = new Scanner(System.in);
        
        System.out.println("enter the number of random move ");
        int k = input.nextInt();
        input.nextLine();
        input.close();

        Board board = new Board(3,k);
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        Uniform_Cost_Search ucs = new Uniform_Cost_Search(board);
        ArrayList<Character> answer = ucs.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer.size() + " moves");
        System.out.print(answer);
    }
    public static void main(String[] args) throws Exception {
        apply_Uniform_search_cost();

    }

}
