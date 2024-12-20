public class TicTacToe {
    public static void main(String[] args) {
        Board board = new Board();
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        char currentPlayer = 'X';

        while (true) {
            board.displayBoard();
            System.out.println("Player " + currentPlayer + ", make your move.");
            int[] move = currentPlayer == 'X' ? player1.getMove(board) : player2.getMove(board);

            if (board.updateBoard(move[0], move[1], currentPlayer)) {
                if (board.isWin(currentPlayer)) {
                    board.displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (board.isDraw()) {
                    board.displayBoard();
                    System.out.println("It's a draw!");
                    break;
                }
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }
}
