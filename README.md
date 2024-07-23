# Snake

A classic Snake Game implemented in Java.

## Description

This project is a simple implementation of the classic Snake game using Java and the Swing library for the graphical user interface. The player controls a snake, and the objective is to eat food to grow the snake. The game ends when the snake collides with itself or the boundaries of the game board.

# Output

![Screenshot (412)](https://github.com/user-attachments/assets/4820261a-121a-4f15-b38c-9efedfdd45ef)

## Installation

To run the game, you need to have Java installed on your system. Follow these steps to get the game up and running:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/snake.git
    cd snake
    ```

2. **Compile the Java files**:
    ```bash
    javac App.java SnakeGame.java
    ```

3. **Run the game**:
    ```bash
    java App
    ```

## Usage

Use the arrow keys to control the direction of the snake:
- **Up Arrow**: Move up
- **Down Arrow**: Move down
- **Left Arrow**: Move left
- **Right Arrow**: Move right

The objective is to eat the red food blocks to grow the snake. The game ends when the snake collides with itself or the boundaries of the game board. Your score is the number of food blocks eaten by the snake.

## Code Overview

### `App.java`

This file contains the main method which initializes the game window.

```java
import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardwidth = 600;
        int boardheight = boardwidth;
        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SnakeGame snakegame = new SnakeGame(boardwidth, boardheight);
        frame.add(snakegame);
        frame.pack();
        snakegame.requestFocus();
    }
}

# 'SnakeGame.java'
This file contains the logic for the Snake game, including the game loop, rendering, and handling user input.


