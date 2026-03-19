import java.util.Random;

public class Game {
    private final int[][] board = new int[4][4];
    private final Random rand = new Random();

    public Game() {
        addRandomTiles(2, 10);
    }

    public int[][] getBoard() {
        return board;
    }

    public final boolean addRandomTiles(int count, int attempts) {
        for (int i = 0; i < count; i++) {
            int row = rand.nextInt(4);
            int col = rand.nextInt(4);
            if (board[row][col] == 0) {
                board[row][col] = 2;
            } else {
                if (attempts > 0) {
                    return addRandomTiles(1, attempts - 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canMove() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) return true;
                if (i < 3 && board[i][j] == board[i + 1][j]) return true;
                if (j < 3 && board[i][j] == board[i][j + 1]) return true;
            }
        }
        return false;
    }

    public void moveRight() {
        for (int row = 0; row < 4; row++) {
            int[] temp = board[row];

            compressRight(temp);
            for (int i = 3; i > 0; i--) {
                if (temp[i] != 0 && temp[i] == temp[i - 1]) {
                    temp[i] *= 2;
                    temp[i - 1] = 0;
                }
            }
            compressRight(temp);
        }
    }

    public void moveLeft() {
        for (int row = 0; row < 4; row++) {
            int[] temp = board[row];

            compressLeft(temp);
            for (int i = 0; i < 3; i++) {
                if (temp[i] != 0 && temp[i] == temp[i + 1]) {
                    temp[i] *= 2;
                    temp[i + 1] = 0;
                }
            }
            compressLeft(temp);
        }
    }

    public void moveDown() {
        for (int col = 0; col < 4; col++) {
            int[] temp = new int[4];
            for (int i = 0; i < 4; i++) {
                temp[i] = board[i][col];
            }

            compressRight(temp);
            for (int i = 3; i > 0; i--) {
                if (temp[i] != 0 && temp[i] == temp[i - 1]) {
                    temp[i] *= 2;
                    temp[i - 1] = 0;
                }
            }
            compressRight(temp);

            for (int i = 0; i < 4; i++) {
                board[i][col] = temp[i];
            }
        }
    }

    public void moveUp() {
        for (int col = 0; col < 4; col++) {
            int[] temp = new int[4];
            for (int i = 0; i < 4; i++) {
                temp[i] = board[i][col];
            }

            compressLeft(temp);
            for (int i = 0; i < 3; i++) {
                if (temp[i] != 0 && temp[i] == temp[i + 1]) {
                    temp[i] *= 2;
                    temp[i + 1] = 0;
                }
            }
            compressLeft(temp);

            for (int i = 0; i < 4; i++) {
                board[i][col] = temp[i];
            }
        }
    }

    private void compressLeft(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[index++] = arr[i];
            }
        }
        for (int i = index; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    private void compressRight(int[] arr) {
        int index = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                arr[index--] = arr[i];
            }
        }
        for (int i = index; i >= 0; i--) {
            arr[i] = 0;
        }
    }
}