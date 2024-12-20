public class Minimax {
    public static int[] findBestMove(Board board) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    board.updateBoard(i, j, 'O');
                    int moveVal = minimax(board, 0, false);
                    board.undoMove(i, j);

                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int minimax(Board board, int depth, boolean isMaximizing) {
        if (board.isWin('O')) return 10 - depth;
        if (board.isWin('X')) return depth - 10;
        if (board.isDraw()) return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.updateBoard(i, j, 'O');
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board.undoMove(i, j);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.updateBoard(i, j, 'X');
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board.undoMove(i, j);
                    }
                }
            }
            return best;
        }
    }
}
