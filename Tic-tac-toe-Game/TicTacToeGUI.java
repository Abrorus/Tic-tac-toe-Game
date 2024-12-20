import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    public JFrame frame;
    public JButton[][] buttons;
    public JLabel statusLabel;
    public char currentPlayer;
    public Board board;
    public Audio audio;

    public TicTacToeGUI() {
        board = new Board();
        audio = new Audio();  // Menggunakan kelas Audio untuk suara
        currentPlayer = 'X';
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                int row = i;
                int col = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (board.updateBoard(row, col, currentPlayer)) {
                            buttons[row][col].setText(String.valueOf(currentPlayer));
                            audio.playSound("move.wav");  // Suara gerakan

                            if (board.isWin(currentPlayer)) {
                                statusLabel.setText("Player " + currentPlayer + " wins!");
                                audio.playSound("win.wav");  // Suara kemenangan
                                disableButtons();
                            } else if (board.isDraw()) {
                                statusLabel.setText("It's a draw!");
                                audio.playSound("draw.wav");  // Suara imbang
                                disableButtons();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                                statusLabel.setText("Player " + currentPlayer + "'s turn");
                            }
                        }
                    }
                });

                panel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));

        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void handleLoss() {
        audio.playSound("lose.wav");  // Suara ketika kalah
        statusLabel.setText("Player " + currentPlayer + " loses!");
        disableButtons();
    }
}
