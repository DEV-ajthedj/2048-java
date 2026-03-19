import java.util.Random;
import java.util.Scanner;

public class Game {
    private final int[][] board = new int[4][4];
    private final Random rand = new Random();
    private final Scanner scanner = new Scanner(System.in);


    public Game() {
        System.out.println("Welcome to 2048!\n");
    }

    public void main() {
        addRandomTiles(8);
        while (true) {
            printBoard();
            System.out.print("> ");
            String input = scanner.nextLine();
            switch (input) {
                case "w" -> moveUp();
                case "s" -> moveDown();
                case "d" -> moveRight();
                case "a" -> moveLeft();
            }
            addRandomTiles(1);
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void printBoard() {
        for (int[] row : board) {
            for (int tile : row) {
                System.out.print(tile + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void addRandomTiles(int count) {
        for (int i = 0; i < count; i++) {
            int row = rand.nextInt(4);
            int col = rand.nextInt(4);
            if (board[row][col] == 0) {
                board[row][col] = 2;
            } else {
                addRandomTiles(1);
            }
        }
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