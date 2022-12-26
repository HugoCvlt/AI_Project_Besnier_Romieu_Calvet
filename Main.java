import java.util.Scanner;
import java.util.*;
public class Main {

    public static void play_the_game() throws Exception{
        
    
        Board board = new Board(4,20);
        
        board.show_board();
        //System.out.println(board.end_test());
        System.out.println(" h1 :" + Heuristics.h1(board));
        System.out.println(" h2 :" + Heuristics.h2(board));

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

    public static void apply_Uniform_search_cost(Scanner input) throws Exception{
        System.out.println();
        System.out.println("========== TIME FOR USC ==========");
        System.out.println("enter the number of random moves ");
        int k = input.nextInt();
        input.nextLine();

        Board board = new Board(3,k);
        board.show_board();
        System.out.println(" h1 :" + Heuristics.h1(board));
        System.out.println(" h2 :" + Heuristics.h2(board));
        System.out.println("Let's find the answer for you !!!");
        UniformCostSearch ucs = new UniformCostSearch(board);
        ArrayList<Character> answer = ucs.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer.size() + " moves");
        System.out.print(answer);
        System.out.println();
        System.out.println("========== END OF UCS ==========");
    }

    public static void breadthSearch(Scanner input) throws Exception{
        System.out.println();
        System.out.println("========== TIME FOR BFS ==========");
        
        System.out.println("enter the number of random moves ");
        int k = input.nextInt();
        input.nextLine();

        Board board = new Board(3,k);
        System.out.println(" h1 :" + Heuristics.h1(board));
        System.out.println(" h2 :" + Heuristics.h2(board));
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        BreadthFirstSearch bfs = new BreadthFirstSearch(board);
        ArrayList<Character> answer = bfs.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer.size() + " moves");
        System.out.print(answer);
        System.out.println();
        System.out.println("========== END OF BFS ==========");
    }

    public static void bidirectional(Scanner input) throws Exception{
        System.out.println();
        System.out.println("========== TIME FOR Bidirectional search ==========");
        
        System.out.println("enter the number of random moves ");
        int k = input.nextInt();
        input.nextLine();

        Board board = new Board(4,k);
        System.out.println(" h1 :" + Heuristics.h1(board));
        System.out.println(" h2 :" + Heuristics.h2(board));
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        BiDirectionalSearch bidirec = new BiDirectionalSearch(board);
        ArrayList<Character> answer = bidirec.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer.size() + " moves");
        System.out.print(answer);
        System.out.println();
        System.out.println("========== END OF Bidirectional search ==========");
    }

    public static void Astar(Scanner input) throws Exception{
        System.out.println();
        System.out.println("========== TIME FOR Astar search ==========");
        
        System.out.println("enter the number of random moves ");
        int k = input.nextInt();
        input.nextLine();

        System.out.println("Wich heuristic do you want to use ?");
        int heuristic = input.nextInt();
        input.nextLine();

        Board board = new Board(4,k);
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        Astar ast = new Astar(board, heuristic);
        ArrayList<Character> answer = ast.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer.size() + " moves");
        System.out.print(answer);
        System.out.println();
        System.out.println("========== END OF Astar search ==========");
    }

    public static void main(String[] args) throws Exception {
        if(args.length > 0){
            Board b = FileReader.readFile(args[0]);
            if(b!=null){ 
                b.show_board();
            }
        }
        //GetResults.get_results_BFS(8,20,5);
        Scanner input = new Scanner(System.in);
        //apply_Uniform_search_cost(input);
        //breadthSearch(input);
        //input.close();
        //GetResults.compare_algo(4,40);
        //bidirectional(input);
        Astar(input);
        input.close();
    }

}
