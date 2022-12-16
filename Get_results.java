import java.util.ArrayList;

public class Get_results{

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
                Uniform_Cost_Search ucs = new Uniform_Cost_Search(board);
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

    public static void compare_algo(int n , int nb_shuffle) throws Exception{
        Board board = new Board(n,nb_shuffle); 
        board.show_board();
        Uniform_Cost_Search ucs = new Uniform_Cost_Search(board);
        long startTime_ucs = System.currentTimeMillis();
        ArrayList<Character> ans_ucs = ucs.solve();
        long endTime_ucs = System.currentTimeMillis();
        
        BreadthFirstSearch bfs = new BreadthFirstSearch(board);
        long startTime_bfs = System.currentTimeMillis();
        ArrayList<Character> ans_bfs = bfs.solve();
        long endTime_bfs = System.currentTimeMillis();
        
        System.out.println("solutin found by UCS" + ans_ucs + " in " + (endTime_ucs - startTime_ucs) + " milliseconds");
        System.out.println("solutin found by BFS" + ans_bfs + " in " + (endTime_bfs - startTime_bfs) + " milliseconds");

    }
}
