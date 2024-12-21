/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #16
 * 1 - 5026231145 - Abrorus Shobah
 * 2 - 5026231076 - James Melvin Chandra
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private char currentPlayer;
    private Board board;
    private Audio audio;
    private String playerXName;
    private boolean isVsComputer;

    public TicTacToeGUI() {
        board = new Board();
        audio = new Audio();
        currentPlayer = 'X';
        initializeGUI();
    }

    private void initializeGUI() {
        // Popup selamat datang
        audio.playSound("welcome.wav");
        JOptionPane.showMessageDialog(null, "Selamat Datang di Tic Tac Toe!");

        // Mode permainan
        int mode = JOptionPane.showOptionDialog(null, 
            "Pilih Mode Permainan:",
            "Mode Permainan",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"Manusia vs Manusia", "Manusia vs Komputer"},
            "Manusia vs Manusia");

        isVsComputer = (mode == 1); // Jika mode adalah "Manusia vs Komputer"
        
        if (isVsComputer) {
            playerXName = JOptionPane.showInputDialog(null, "Masukkan nama Anda (Pemain X):");
            if (playerXName == null || playerXName.isEmpty()) playerXName = "Player X";
        } else {
            inputPlayerNames();
        }

        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 5, 5));
        panel.setBackground(new Color(230, 230, 250));

        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setForeground(Color.DARK_GRAY);
                int row = i;
                int col = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (board.updateBoard(row, col, currentPlayer)) {
                            buttons[row][col].setText(String.valueOf(currentPlayer));
                            buttons[row][col].setForeground(currentPlayer == 'X' ? Color.RED : Color.BLACK);
                            audio.playSound("move.wav");

                            if (board.isWin(currentPlayer)) {
                                handleWin(currentPlayer == 'X' ? playerXName : "Komputer");
                            } else if (board.isDraw()) {
                                handleDraw();
                            } else {
                                switchTurn();
                                if (isVsComputer && currentPlayer == 'O') {
                                    computerMove();
                                }
                            }
                        }
                    }
                });

                panel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel((isVsComputer ? playerXName : "Player X") + "'s turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statusLabel.setForeground(new Color(50, 50, 50));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(new Color(173, 216, 230));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void computerMove() {
        int[] bestMove = Minimax.findBestMove(board);
        board.updateBoard(bestMove[0], bestMove[1], 'O');
        buttons[bestMove[0]][bestMove[1]].setText("O");
        buttons[bestMove[0]][bestMove[1]].setForeground(Color.BLACK);

        if (board.isWin('O')) {
            handleWin("Komputer");
        } else if (board.isDraw()) {
            handleDraw();
        } else {
            switchTurn();
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText((currentPlayer == 'X' ? playerXName : "Komputer") + "'s turn");
    }

    private void handleWin(String winner) {
        audio.playSound("Win.wav");
        JOptionPane.showMessageDialog(frame, winner + " menang! Selamat!");
        if (askRestartGame()) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void handleDraw() {
        audio.playSound("Seri.wav");
        JOptionPane.showMessageDialog(frame, "Permainan seri!");
        if (askRestartGame()) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private boolean askRestartGame() {
        int option = JOptionPane.showConfirmDialog(
                frame,
                "Apakah Anda ingin bermain lagi?",
                "Restart Game",
                JOptionPane.YES_NO_OPTION
        );
        return option == JOptionPane.YES_OPTION;
    }

    private void restartGame() {
        frame.dispose();
        new TicTacToeGUI();
    }

    private void inputPlayerNames() {
        playerXName = JOptionPane.showInputDialog(null, "Masukkan nama pemain X:");
        String playerOName = JOptionPane.showInputDialog(null, "Masukkan nama pemain O:");
        if (playerXName == null || playerXName.isEmpty()) playerXName = "Player X";
        if (playerOName == null || playerOName.isEmpty()) playerOName = "Player O";
    }
}
