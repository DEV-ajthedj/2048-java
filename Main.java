import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {
    private static void createAndShowGUI(Game game) {
        JFrame frame = new JFrame("2048 Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(400, 420);
        frame.setResizable(false);

        JLabel title = new JLabel("Welcome to 2048!", SwingConstants.CENTER);
        title.setFont(new Font("Calibri", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JLabel instructions = new JLabel("Use the arrow keys to move the tiles.\n", SwingConstants.CENTER);
        instructions.setFont(new Font("Calibri", Font.PLAIN, 16));
        frame.add(instructions, BorderLayout.SOUTH);

        JPanel boardPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        boardPanel.setBorder(margin);
        for (int i = 0; i < 16; i++) {
            JLabel tile = new JLabel("", SwingConstants.CENTER);
            tile.setFont(new Font("Calibri", Font.BOLD, 24));
            tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tile.setBackground(Color.LIGHT_GRAY);
            tile.setOpaque(true);
            boardPanel.add(tile);
        }
        frame.add(boardPanel, BorderLayout.CENTER);

        KeyListener listener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> game.moveUp();
                    case KeyEvent.VK_DOWN -> game.moveDown();
                    case KeyEvent.VK_LEFT -> game.moveLeft();
                    case KeyEvent.VK_RIGHT -> game.moveRight();
                    case KeyEvent.VK_W -> game.moveUp();
                    case KeyEvent.VK_S -> game.moveDown();
                    case KeyEvent.VK_A -> game.moveLeft();
                    case KeyEvent.VK_D -> game.moveRight();
                }

                int[][] board = game.getBoard();

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        JLabel tile = (JLabel) boardPanel.getComponent(i * 4 + j);
                        int value = board[i][j];
                        tile.setText(value == 0 ? "" : String.valueOf(value));
                        switch (value) {
                            case 0 -> tile.setBackground(Color.LIGHT_GRAY);
                            case 2 -> tile.setBackground(new Color(0xeee4da));
                            case 4 -> tile.setBackground(new Color(0xede0c8));
                            case 8 -> tile.setBackground(new Color(0xf2b179));
                            case 16 -> tile.setBackground(new Color(0xf59563));
                            case 32 -> tile.setBackground(new Color(0xf67c5f));
                            case 64 -> tile.setBackground(new Color(0xf65e3b));
                            case 128 -> tile.setBackground(new Color(0xedcf72));
                            case 256 -> tile.setBackground(new Color(0xedcc61));
                            case 512 -> tile.setBackground(new Color(0xedc850));
                            case 1024 -> tile.setBackground(new Color(0xedc53f));
                            case 2048 -> tile.setBackground(new Color(0xedc22e));
                            default -> tile.setBackground(Color.BLACK);
                        }
                        if (value >= 128) {
                            tile.setForeground(Color.WHITE);
                        } else {
                            tile.setForeground(Color.BLACK);
                        }
                    }
                }

                if (!game.addRandomTiles(1, 10) && !game.canMove()) {
                    JOptionPane.showMessageDialog(frame, "Game Over!", "2048", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        };
        frame.addKeyListener(listener);
        listener.keyPressed(new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, KeyEvent.CHAR_UNDEFINED));

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            createAndShowGUI(game);
        });
    }
}