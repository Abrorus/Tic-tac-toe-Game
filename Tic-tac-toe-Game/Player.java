import java.util.Scanner;

public class Player {
    private char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public int[] getMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row and column (0, 1, 2) separated by space: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new int[] {row, col};
    }
}
