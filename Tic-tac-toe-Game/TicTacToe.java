import javax.swing.*;

public class TicTacToe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToeGUI();
        });
    }
}
