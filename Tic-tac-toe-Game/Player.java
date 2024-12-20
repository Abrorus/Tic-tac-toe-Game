public class Player {
    private char mark;

    public Player(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }

    public int[] getMove(Board board) {
        // Implementasi sederhana untuk mendapatkan input pemain (misalnya koordinat)
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int row, col;
        System.out.println("Enter row (0-2): ");
        row = scanner.nextInt();
        System.out.println("Enter col (0-2): ");
        col = scanner.nextInt();
        return new int[]{row, col};
    }
}
