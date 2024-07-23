import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;

        }

    }

    int boardwidth;
    int boardheight;
    int tileSize = 25;
    // snake
    Tile snakehead;
    ArrayList<Tile> snakeBody;
    // food
    Tile food;

    Random random;
    // game logic
    Timer gamerLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    SnakeGame(int boardwidth, int boardheight) {
        this.boardheight = boardheight;
        this.boardwidth = boardwidth;
        setPreferredSize(new Dimension(this.boardwidth, this.boardheight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakehead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 1;

        gamerLoop = new Timer(130, this);
        gamerLoop.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // grid
        /*
         * for (int i = 0; i < boardwidth / tileSize; i++) {
         * g.drawLine(i * tileSize, 0, i * tileSize, boardheight);
         * 
         * g.drawLine(0, i * tileSize, boardwidth, i * tileSize);
         * 
         * }
         */
        // food
        g.setColor(Color.red);
        // g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);
        // snake

        g.setColor(Color.green);
        g.fill3DRect(snakehead.x * tileSize, snakehead.y * tileSize, tileSize, tileSize, true);
        // tileSize);
        // g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);
        // snakebody
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            // g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize,
            // tileSize);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);

        }
        // score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);

        } else {
            g.drawString("score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
    }

    public void placeFood() {
        food.x = random.nextInt(boardwidth / tileSize);
        food.y = random.nextInt(boardheight / tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {
        // eat food
        if (collision(snakehead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }
        // snakebody
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) { // right before the head
                snakePart.x = snakehead.x;
                snakePart.y = snakehead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        // snakehead
        snakehead.x += velocityX;
        snakehead.y += velocityY;
        // gameover
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);

            // collide with snake head
            if (collision(snakehead, snakePart)) {
                gameOver = true;
            }
        }
        if (snakehead.x * tileSize < 0 || snakehead.x * tileSize > boardwidth || // passed left border or right border
                snakehead.y * tileSize < 0 || snakehead.y * tileSize > boardheight) { // passed top border or bottom
                                                                                      // border
            gameOver = true;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gamerLoop.stop();
        }
    }

    public void keyPressed(KeyEvent e) {
        // System.out.println("KeyEvent: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
