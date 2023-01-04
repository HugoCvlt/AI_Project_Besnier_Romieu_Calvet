import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.Timer;
import java.util.TimerTask;


public class Performance{

    public LinkedHashMap<Board, Integer> generate_board_test(int nb_board, int board_size, int nb_shuffle) throws Exception{

        //Generate boards and hashmap of difficulties
        
        ArrayList<Board> boards_test = new ArrayList<Board>();
        HashMap<Board, Integer> board_difficulty = new HashMap<>();
        
        for(int i=0; i<nb_board; i++){
            Board b = new Board(board_size, nb_shuffle);
            Astar a = new Astar(b, 1);
            boards_test.add(b);
            board_difficulty.put(b, a.solve().size());
        }

        //Sort our hashmap by difficulty, ie sort by the length of the solution find by A*

        TreeSet<Integer> list = new TreeSet<Integer>();
        LinkedHashMap<Board, Integer> sorted_board_by_difficulty = new LinkedHashMap<>();

        for (Map.Entry<Board, Integer> entry : board_difficulty.entrySet()){
            list.add(entry.getValue());
        }

        for(int num : list){
            for (Map.Entry<Board, Integer> entry : board_difficulty.entrySet()){
                if(entry.getValue().equals(num)){
                    sorted_board_by_difficulty.put(entry.getKey(), num);
                }
            }
        }

        return sorted_board_by_difficulty;

    }

    public void calcul_performance() throws Exception{

        int[] size_list = {3, 4, 5, 8, 10};
        int[] shuffle_list = {5, 10, 15, 20, 30};

        long timer_astar_h1 = 0;
        long timer_astar_h2 = 0;
        long timer_idastar = 0;
        long timer_bds = 0;
        long timer_bfs = 0;
        long timer_ucs = 0;

        //Reset file
        FileWriter w = new FileWriter("Performance_and_stat/Astar_h1_perf.csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/Astar_h2_perf.csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/IDAstar_perf.csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/BDS_perf.csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/UCS_perf.csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/BFS_perf.csv");
        w.write("");
        w.close();


        for(int size : size_list){
            for(int shuffle : shuffle_list){

                LinkedHashMap<Board, Integer> boards_test = this.generate_board_test(20, size, shuffle);

                //Astar_h1
                for(Board b : boards_test.keySet()){
                    Astar ast = new Astar(b, 1);

                    long startTime = System.currentTimeMillis();
                    ast.solve();
                    long endTime = System.currentTimeMillis();

                    timer_astar_h1 = timer_astar_h1 + (endTime - startTime);
                }
                
                long moy_astar_h1 = timer_astar_h1/boards_test.keySet().size();

                w = new FileWriter("Performance_and_stat/Astar_h1_perf.csv", true);
                w.write(size+","+shuffle+","+moy_astar_h1+"\n");
                w.close();

                //Astra_h2
                for(Board b : boards_test.keySet()){
                    Astar ast2 = new Astar(b, 1);

                    long startTime = System.currentTimeMillis();
                    ast2.solve();
                    long endTime = System.currentTimeMillis();

                    timer_astar_h2 = timer_astar_h2 + (endTime - startTime);
                }

                long moy_astar_h2 = timer_astar_h2/boards_test.keySet().size();

                w = new FileWriter("Performance_and_stat/Astar_h2_perf.csv", true);
                w.write(size+","+shuffle+","+moy_astar_h2+"\n");
                w.close();

                //BDS
                for(Board b : boards_test.keySet()){
                    BiDirectionalSearch bds = new BiDirectionalSearch(b);

                    long startTime = System.currentTimeMillis();
                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run(){
                            bds.solve();
                            bds.endTime = true;
                            timer.cancel();
                        }
                    }, 4000);
                    long endTime = System.currentTimeMillis();
                    timer_bds = timer_bds + (endTime - startTime);
                } 
                long moy_bds = timer_bds/boards_test.keySet().size();
                System.out.println("Mean time to BDS for taquin " + size+"x"+size + " shuffled " + shuffle + " times : " + moy_bds + " milliseconds");

            }
        }
    }


}