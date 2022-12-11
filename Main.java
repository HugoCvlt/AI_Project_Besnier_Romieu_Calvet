public class Main {
    public static void main(String[] args) {
        Board board = new Board(4);
        
        board.print_board();
        System.out.println(board.end_test());
        System.out.println(" h1 :" + Board.h1(board));
        System.out.println(" h2 :" + Board.h2(board));
    }
}
