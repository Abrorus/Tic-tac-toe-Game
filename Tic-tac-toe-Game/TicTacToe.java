import javax.swing.*;

public class TicTacToe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI gameGUI = new TicTacToeGUI();
        });
    }
}
