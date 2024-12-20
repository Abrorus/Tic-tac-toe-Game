public class Board {
    private char[][] board;
    private final int SIZE = 3; // Ukuran papan 3x3

    public Board() {
        board = new char[SIZE][SIZE];
        // Inisialisasi papan dengan karakter kosong
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' '; // Setiap sel diisi dengan ' ' (kosong)
            }
        }
    }

    // Fungsi untuk menampilkan papan
    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) System.out.print("|"); // Tampilkan garis vertikal
            }
            System.out.println();
            if (i < SIZE - 1) System.out.println("-----"); // Tampilkan garis horizontal
        }
    }

    // Fungsi untuk mengupdate papan dengan langkah pemain
    public boolean updateBoard(int row, int col, char player) {
        // Cek apakah baris dan kolom valid dan apakah sel kosong
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ') {
            board[row][col] = player; // Update papan dengan simbol pemain (X atau O)
            return true; // Langkah berhasil
        }
        return false; // Langkah gagal (sel sudah terisi)
    }

    // Fungsi untuk memeriksa apakah pemain telah menang
    public boolean isWin(char player) {
        // Cek baris dan kolom untuk kemenangan
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || 
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true; // Kemenangan ditemukan pada baris atau kolom
            }
        }

        // Cek diagonal untuk kemenangan
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) || 
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true; // Kemenangan ditemukan pada diagonal
        }

        return false; // Tidak ada kemenangan
    }

    // Fungsi untuk memeriksa apakah permainan berakhir seri
    public boolean isDraw() {
        // Cek apakah masih ada sel kosong
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false; // Masih ada sel kosong, permainan belum seri
                }
            }
        }
        return true; // Semua sel terisi, permainan berakhir seri
    }

    // Fungsi untuk memeriksa apakah sebuah sel kosong
    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == ' '; // Cek apakah sel kosong (' ')
    }

    // Fungsi untuk membatalkan langkah terakhir (digunakan oleh AI)
    public void undoMove(int row, int col) {
        board[row][col] = ' '; // Set kembali sel menjadi kosong
    }
}
