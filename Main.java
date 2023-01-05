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

    public static void play_the_game_auto(Board board,ArrayList<Character> answer){
        Board b = (Board) board.clone();
        b.show_board();
        for (int j = 0 ;  j < answer.size(); j++){
            char move = answer.get(j);
            System.out.println(j + "th : " + move);
            b.move(move);
            b.show_board();
        }
    }

    public static void apply_Uniform_search_cost(Scanner input) throws Exception{
        System.out.println();
        System.out.println("========== TIME FOR USC ==========");
        System.out.println("enter the number of random moves ");
        int k = input.nextInt();
        input.nextLine();

        Board board = new Board(2,k);
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        UniformCostSearch ucs = new UniformCostSearch(board);
        ArrayList<Character> answer = ucs.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer.size() + " moves");
        System.out.println("the number of expanded nodes is : " + ucs.max_size_explore_set);
        System.out.println("max size of the frontier is : " + ucs.max_size_frontier);
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

        Board board = new Board(4,k);
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        BreadthFirstSearch bfs = new BreadthFirstSearch(board);
        ArrayList<Character> answer = bfs.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer.size() + " moves");
        System.out.println("the number of expanded nodes is : " + bfs.max_size_explore_set);
        System.out.println("max size of the frontier is : " + bfs.max_size_frontier);
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

        Board board = new Board(8,k);
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        BiDirectionalSearch bidirec = new BiDirectionalSearch(board);
        ArrayList<Character> answer = bidirec.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer.size() + " moves");
        System.out.println(answer);
        System.out.println("the number of expanded nodes is : " + bidirec.max_size_explore_set);
        System.out.println("max size of the frontier is : " + bidirec.max_size_frontier);
        System.out.println();
        System.out.println("========== END OF Bidirectional search ==========");
    }

    public static void Astar(Scanner input) throws Exception{
        System.out.println();
        System.out.println("========== TIME FOR Astar search ==========");
        
        System.out.println("enter the number of random moves ");
        int k = input.nextInt();
        input.nextLine();

        //System.out.println("Wich heuristic do you want to use ?");
        //int heuristic = input.nextInt();
        //input.nextLine();

        Board board = new Board(3,k);
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        Astar ast_h1 = new Astar(board, 1);
        Astar ast_h2 = new Astar(board,2);
        ArrayList<Character> answer_h1 = ast_h1.solve();
        ArrayList<Character> answer_h2 = ast_h2.solve();
        System.out.println("the fastest way to solve the puzzle has " + answer_h1.size() + " moves");
        System.out.println("the number of expanded nodes is : " + ast_h1.max_size_explore_set);
        System.out.println("max size of the frontier is : " + ast_h1.max_size_frontier);
        System.out.print(answer_h1);
        System.out.println();
        System.out.println("the fastest way to solve the puzzle has " + answer_h2.size() + " moves");
        System.out.println("the number of expanded nodes is : " + ast_h2.max_size_explore_set);
        System.out.println("max size of the frontier is : " + ast_h2.max_size_frontier);
        System.out.print(answer_h2);
        System.out.println();
        System.out.println("========== END OF Astar search ==========");
    }

    public static void apply_IDA_star(Scanner input) throws Exception{
        System.out.println();
        System.out.println("========== TIME FOR IDA* search ==========");
        
        System.out.println("enter the number of random moves ");
        int k = input.nextInt();
        input.nextLine();


        Board board = new Board(2,k);
        board.show_board();
        System.out.println("Let's find the answer for you !!!");
        Astar ast_h1 = new Astar(board, 2);
        IDAstar ida_h1 = new IDAstar(board, 1);
        IDAstar ida_h2 = new IDAstar(board, 2);
        ArrayList<Character> answer =  ast_h1.solve();
        System.out.println("the fastest way with A* h2 to solve the puzzle has " + answer.size() + " moves");
        System.out.println("A* answer : " + answer);
        play_the_game_auto(board,answer);

        ida_h1.solve();
        System.out.println("the fastest way with IDA* h1 to solve the puzzle has " + ida_h1.answer.size() + " moves");
        System.out.println("IDA* h1 answer : " +  ida_h1.answer);
        play_the_game_auto(board,ida_h1.answer);
        ida_h2.solve();
        System.out.println("the fastest way with IDA* h2 to solve the puzzle has " + ida_h2.answer.size() + " moves");
        System.out.println("IDA* h2 answer : " +  ida_h2.answer);
        play_the_game_auto(board,ida_h2.answer);
        System.out.println("========== END OF IDA* search ==========");
        
    }

    public static void generate_solvable_problem() throws Exception{
        int k = 70;
        for (int i=0;i<10;i++){
            int n = 5;
            Board board = new Board(n,k);
            board.show_board();
            Astar ast = new Astar(board, 1);
            int moves = ast.solve().size();
            System.out.println(moves);
            System.out.println();
            if (moves <=10){
                FileReader.writeFile(board,"ex_" + n +"x" + n +"_" + moves + "_moves.txt" ,"Taquin_1_10_moves");

            }else if(moves <= 15){
                FileReader.writeFile(board,"ex_" + n +"x" + n +"_" + moves + "_moves.txt" ,"Taquin_11_15_moves");

            }else if(moves <= 20){
                FileReader.writeFile(board,"ex_" + n +"x" + n +"_" + moves + "_moves.txt" ,"Taquin_16_20_moves");

            }else if(moves <= 25){
                FileReader.writeFile(board,"ex_" + n +"x" + n +"_" + moves+ "_moves.txt" ,"Taquin_21_25_moves");

            }else {
                FileReader.writeFile(board,"ex_" + n +"x" + n +"_" + moves+ "_moves.txt" ,"Taquin_26_plus_moves");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if(args.length > 0){
            Board b = FileReader.readFile(args[0]);
            if(b!=null){ 
                b.show_board();
            }
        }
        //generate_solvable_problem();
        
        //GetResults.get_results_A_star(10,50,10,1);
        //Scanner input = new Scanner(System.in);
        //apply_Uniform_search_cost(input);
        //breadthSearch(input);
        //apply_IDA_star(input);
        //GetResults.compare_algo(5,30);
        //bidirectional(input);
        //Astar(input);
        //input.close();

        Performance p = new Performance();
        p.calcul_performance();


    }

}
