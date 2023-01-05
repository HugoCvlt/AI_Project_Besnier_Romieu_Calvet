import java.util.ArrayList;


public class GetResults{

    public static void get_results_Uniform_search_cost(int n_limit,int k_shuffle,int nb_test_by_size) throws Exception{
        // n_limit : size limits of the matrix 
        // k_shuffle : number of random moves to shuffle
        // nb_test : number of test for each size.
        // the number of moves to solve the problem must be at most the number of shuffle

        //ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        for (int i= 2; i<n_limit; i++){
            int sol = 0;
            for (int j=0 ; j<nb_test_by_size;j++){
                Board board = new Board(i,k_shuffle);
                UniformCostSearch ucs = new UniformCostSearch(board);
                ArrayList<Character> answer = ucs.solve();
                sol = sol + answer.size(); 
            }
            //ArrayList<Integer> tuple = new ArrayList<Integer>(2);
            System.out.println(i + " mean : " + sol/nb_test_by_size);
            //tuple.add(i);tuple.add(sol/nb_test_by_size);
            //results.add(tuple);
        }
        //for (ArrayList<Integer> e : results){
          //  System.out.println(e);
        //}
    }

    public static void get_results_BFS(int n_limit,int k_shuffle,int nb_test_by_size) throws Exception{
        // n_limit : size limits of the matrix 
        // k_shuffle : number of random moves to shuffle
        // nb_test : number of test for each size.
        // the number of moves to solve the problem must be at most the number of shuffle

        //ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        for (int i= 2; i<n_limit; i++){
            int sol = 0;
            for (int j=0 ; j<nb_test_by_size;j++){
                Board board = new Board(i,k_shuffle);
                BreadthFirstSearch bfs = new BreadthFirstSearch(board);
                ArrayList<Character> answer = bfs.solve();
                sol = sol + answer.size(); 
            }
            //ArrayList<Integer> tuple = new ArrayList<Integer>(2);
            System.out.println(i + " mean : " + sol/nb_test_by_size);
            //tuple.add(i);tuple.add(sol/nb_test_by_size);
            //results.add(tuple);
        }
        //for (ArrayList<Integer> e : results){
          //  System.out.println(e);
        //}
    }
    public static void get_results_A_star(int n_limit,int k_shuffle,int nb_test_by_size,int heuristic) throws Exception{
        // n_limit : size limits of the matrix 
        // k_shuffle : number of random moves to shuffle
        // nb_test : number of test for each size.
        // the number of moves to solve the problem must be at most the number of shuffle

        //ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        for (int i= 2; i<n_limit; i++){
            for (int j=0 ; j<nb_test_by_size;j++){
                Board board = new Board(i,k_shuffle);
                Astar a_star = new Astar(board,heuristic);
                ArrayList<Character> answer = a_star.solve();
                System.out.println("n = " + i + ", k = " + k_shuffle + " , size of the answer " + answer.size());
            }
            //ArrayList<Integer> tuple = new ArrayList<Integer>(2);
            
            //tuple.add(i);tuple.add(sol/nb_test_by_size);
            //results.add(tuple);
        }
        //for (ArrayList<Integer> e : results){
          //  System.out.println(e);
        //}
    }

    /**
     * @param n
     * @param nb_shuffle
     * @throws Exception
     */
    public static void compare_algo(int n , int nb_shuffle) throws Exception{
        Board board = new Board(n,nb_shuffle); 
        board.show_board();

        IDAstar ida_star = new IDAstar(board, 1);
        long startTime_ida = System.currentTimeMillis();
        ida_star.solve();
        long endTime_ida = System.currentTimeMillis();
        System.out.println("solution found by IDA* h1 " + ida_star.answer + " in " + (endTime_ida - startTime_ida) + " milliseconds");

        Astar a_star = new Astar(board,1);
        long startTime_a_star = System.currentTimeMillis();
        ArrayList<Character> ans_star = a_star.solve();
        long endTime_a_star = System.currentTimeMillis();
        System.out.println("solution found by A* h1 " + ans_star + " in " + (endTime_a_star - startTime_a_star) + " milliseconds");

        System.out.println();

        Astar a_star_bis = new Astar(board,2);
        long startTime_a_star_bis = System.currentTimeMillis();
        ArrayList<Character> ans_star_bis = a_star_bis.solve();
        long endTime_a_star_bis = System.currentTimeMillis();
        System.out.println("solution found by A* h2" + ans_star_bis + " in " + (endTime_a_star_bis - startTime_a_star_bis) + " milliseconds");

        System.out.println();

        BiDirectionalSearch bds = new BiDirectionalSearch(board);
        long startTime_bds = System.currentTimeMillis();
        ArrayList<Character> ans_bds = bds.solve();
        long endTime_bds = System.currentTimeMillis();
        System.out.println("solution found by BDS " + ans_bds + " in " + (endTime_bds - startTime_bds) + " milliseconds");

        System.out.println();
        UniformCostSearch ucs = new UniformCostSearch(board);
        long startTime_ucs = System.currentTimeMillis();
        ArrayList<Character> ans_ucs = ucs.solve();
        long endTime_ucs = System.currentTimeMillis();
        System.out.println("solution found by UCS " + ans_ucs + " in " + (endTime_ucs - startTime_ucs) + " milliseconds");
        System.out.println();
        
        BreadthFirstSearch bfs = new BreadthFirstSearch(board);
        long startTime_bfs = System.currentTimeMillis();
        ArrayList<Character> ans_bfs = bfs.solve();
        long endTime_bfs = System.currentTimeMillis();
        System.out.println("solution found by BFS " + ans_bfs + " in " + (endTime_bfs - startTime_bfs) + " milliseconds");
        System.out.println();

        

    


        
      
       

    }
}
