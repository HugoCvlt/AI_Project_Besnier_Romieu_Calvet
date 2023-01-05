import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;


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

        long space_astar_h1 = 0;
        long space_astar_h2 = 0;
        long space_idastar = 0;
        long space_bds = 0;
        long space_bfs = 0;
        long space_ucs = 0;

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
                    space_astar_h1 = space_astar_h1 + ast.max_size_frontier;
                }
                
                long moy_astar_h1 = timer_astar_h1/boards_test.keySet().size();
                long moy_space_astar_h1 = space_astar_h1/boards_test.keySet().size();

                w = new FileWriter("Performance_and_stat/Astar_h1_perf.csv", true);
                w.write(size+","+shuffle+","+moy_astar_h1+","+moy_space_astar_h1+"\n");
                w.close();

                //Astra_h2
                for(Board b : boards_test.keySet()){
                    Astar ast2 = new Astar(b, 1);

                    long startTime = System.currentTimeMillis();
                    ast2.solve();
                    long endTime = System.currentTimeMillis();

                    timer_astar_h2 = timer_astar_h2 + (endTime - startTime);
                    space_astar_h2 = space_astar_h2 + ast2.max_size_frontier;
                }

                long moy_astar_h2 = timer_astar_h2/boards_test.keySet().size();
                long moy_space_astar_h2 = space_astar_h2/boards_test.keySet().size();

                w = new FileWriter("Performance_and_stat/Astar_h2_perf.csv", true);
                w.write(size+","+shuffle+","+moy_astar_h2+","+moy_space_astar_h2+"\n");
                w.close();

                //BDS
                for(Board b : boards_test.keySet()){
                    BiDirectionalSearch bds = new BiDirectionalSearch(b);

                    long startTime = System.currentTimeMillis();

                    final ExecutorService service = Executors.newSingleThreadExecutor();
                    try {
                        final Future<Object> f = service.submit(() -> {
                            return bds.solve();
                        });

                        f.get(10, TimeUnit.SECONDS);
                    } catch (final TimeoutException e) {
                        System.err.println("Calculation took to long");
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        service.shutdown();
                    }

                    long endTime = System.currentTimeMillis();
                    timer_bds = timer_bds + (endTime - startTime);
                    space_bds = space_bds + bds.max_size_frontier;

                } 
                long moy_bds = timer_bds/boards_test.keySet().size();
                long moy_space_bds = space_bds/boards_test.keySet().size();

                w = new FileWriter("Performance_and_stat/BDS_perf.csv", true);
                w.write(size+","+shuffle+","+moy_bds+","+moy_space_bds+"\n");
                w.close();

                //BFS
                for(Board b : boards_test.keySet()){
                    BreadthFirstSearch bfs = new BreadthFirstSearch(b);

                    long startTime = System.currentTimeMillis();

                    final ExecutorService service = Executors.newSingleThreadExecutor();
                    try {
                        final Future<Object> f = service.submit(() -> {
                            return bfs.solve();
                        });

                        f.get(10, TimeUnit.SECONDS);
                    } catch (final TimeoutException e) {
                        System.err.println("Calculation took to long");
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        service.shutdown();
                    }

                    long endTime = System.currentTimeMillis();
                    timer_bfs = timer_bfs + (endTime - startTime);
                    space_bfs = space_bfs + bfs.max_size_frontier;
                } 
                long moy_bfs = timer_bfs/boards_test.keySet().size();
                long moy_space_bfs = space_bfs/boards_test.keySet().size();

                w = new FileWriter("Performance_and_stat/BFS_perf.csv", true);
                w.write(size+","+shuffle+","+moy_bfs+","+moy_space_bfs+"\n");
                w.close();

                //IDAStar
                for(Board b : boards_test.keySet()){
                    IDAstar idast = new IDAstar(b, 1);

                    long startTime = System.currentTimeMillis();

                    final ExecutorService service = Executors.newSingleThreadExecutor();
                    try {
                        final Future<Object> f = service.submit(() -> {
                            return idast.solve();
                        });

                        f.get(10, TimeUnit.SECONDS);
                    } catch (final TimeoutException e) {
                        System.err.println("Calculation took to long");
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        service.shutdown();
                    }

                    long endTime = System.currentTimeMillis();
                    timer_idastar = timer_idastar + (endTime - startTime);
                } 
                long moy_idastar = timer_idastar/boards_test.keySet().size();

                w = new FileWriter("Performance_and_stat/IDAstar_perf.csv", true);
                w.write(size+","+shuffle+","+moy_idastar+"\n");
                w.close();

                //UCS
                for(Board b : boards_test.keySet()){
                    UniformCostSearch ucs = new UniformCostSearch(b);

                    long startTime = System.currentTimeMillis();

                    final ExecutorService service = Executors.newSingleThreadExecutor();
                    try {
                        final Future<Object> f = service.submit(() -> {
                            return ucs.solve();
                        });

                        f.get(10, TimeUnit.SECONDS);
                    } catch (final TimeoutException e) {
                        System.err.println("Calculation took to long");
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        service.shutdown();
                    }

                    long endTime = System.currentTimeMillis();
                    timer_ucs = timer_ucs + (endTime - startTime);
                    space_ucs = space_ucs + ucs.max_size_frontier;
                } 
                long moy_ucs = timer_ucs/boards_test.keySet().size();
                long moy_space_ucs = space_ucs/boards_test.keySet().size();

                w = new FileWriter("Performance_and_stat/UCS_perf.csv", true);
                w.write(size+","+shuffle+","+moy_ucs+","+moy_space_ucs+"\n");
                w.close();


            }
        }
    }

    public void calcul_perf_difficulty(String  url) throws Exception{

        //Time to find the solution, depends of the difficulty, ie length of the solution.
        //We use board already created and solve by Astar

        Board b = FileReader.readFile(url);

        Astar ast_h1 = new Astar(b, 1);
        Astar ast_h2 = new Astar(b, 2);
        BiDirectionalSearch bds = new BiDirectionalSearch(b);
        BreadthFirstSearch bfs = new BreadthFirstSearch(b);
        UniformCostSearch ucs = new UniformCostSearch(b);
        IDAstar ida = new IDAstar(b, 1);

        long timer = 0;
    

        int difficulty = ast_h1.solve().size();

        //Reset file
        FileWriter w = new FileWriter("Performance_and_stat/Difficulty/Astar_h1_perf_difficulty"+difficulty+".csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/Difficulty/Astar_h2_perf_difficulty"+difficulty+".csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/Difficulty/BDS_perf_difficulty"+difficulty+".csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/Difficulty/BFS_perf_difficulty"+difficulty+".csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/Difficulty/UCS_perf_difficulty"+difficulty+".csv");
        w.write("");
        w.close();
        w = new FileWriter("Performance_and_stat/Difficulty/IDAstar_perf_difficulty"+difficulty+".csv");
        w.write("");
        w.close();

        //Time to solve
        //AStar_h1
        long startTime = System.currentTimeMillis();
        int size = ast_h1.solve().size();
        long endTime = System.currentTimeMillis();

        timer = endTime - startTime;

        w = new FileWriter("Performance_and_stat/Difficulty/Astar_h1_perf_difficulty"+difficulty+".csv", true);
        w.write(size+","+timer);
        w.close();

        //AStar_h1
        startTime = System.currentTimeMillis();
        ast_h2.solve();
        endTime = System.currentTimeMillis();

        timer = endTime - startTime;

        w = new FileWriter("Performance_and_stat/Difficulty/Astar_h2_perf_difficulty"+difficulty+".csv", true);
        w.write(size+","+timer+"\n");
        w.close();

        //BDS
        final ExecutorService service = Executors.newSingleThreadExecutor();
        startTime = System.currentTimeMillis();
        try {
            final Future<Object> f = service.submit(() -> {
                return bds.solve();
            });
            f.get(10, TimeUnit.SECONDS);
            } catch (final TimeoutException e) {
                System.err.println("Calculation took to long");
            } catch (final Exception e) {
                throw new RuntimeException(e);
            } finally {
                service.shutdown();
            }
        endTime = System.currentTimeMillis();

        timer = endTime - startTime;

        w = new FileWriter("Performance_and_stat/Difficulty/BDS_perf_difficulty"+difficulty+".csv", true);
        w.write(size+","+timer+"\n");
        w.close();

        //BFS
        final ExecutorService service1 = Executors.newSingleThreadExecutor();
        startTime = System.currentTimeMillis();
        try {
            final Future<Object> f = service1.submit(() -> {
                return bfs.solve();
            });
            f.get(10, TimeUnit.SECONDS);
            } catch (final TimeoutException e) {
                System.err.println("Calculation took to long");
            } catch (final Exception e) {
                throw new RuntimeException(e);
            } finally {
                service1.shutdown();
            }
        endTime = System.currentTimeMillis();

        timer = endTime - startTime;

        w = new FileWriter("Performance_and_stat/Difficulty/BFS_perf_difficulty"+difficulty+".csv", true);
        w.write(size+","+timer+"\n");
        w.close();

        //UCS
        final ExecutorService service2 = Executors.newSingleThreadExecutor();
        startTime = System.currentTimeMillis();
        try {
            final Future<Object> f = service2.submit(() -> {
                return ucs.solve();
            });
            f.get(10, TimeUnit.SECONDS);
            } catch (final TimeoutException e) {
                System.err.println("Calculation took to long");
            } catch (final Exception e) {
                throw new RuntimeException(e);
            } finally {
                service2.shutdown();
            }
        endTime = System.currentTimeMillis();

        timer = endTime - startTime;

        w = new FileWriter("Performance_and_stat/Difficulty/UCS_perf_difficulty"+difficulty+".csv", true);
        w.write(size+","+timer+"\n");
        w.close();

        //IDA
        final ExecutorService service3 = Executors.newSingleThreadExecutor();
        startTime = System.currentTimeMillis();
        try {
            final Future<Object> f = service3.submit(() -> {
                return ida.solve();
            });
            f.get(10, TimeUnit.SECONDS);
            } catch (final TimeoutException e) {
                System.err.println("Calculation took to long");
            } catch (final Exception e) {
                throw new RuntimeException(e);
            } finally {
                service3.shutdown();
            }
        endTime = System.currentTimeMillis();

        timer = endTime - startTime;

        w = new FileWriter("Performance_and_stat/Difficulty/IDAstar_perf_difficulty"+difficulty+".csv", true);
        w.write(size+","+timer+"\n");
        w.close();
    }


}