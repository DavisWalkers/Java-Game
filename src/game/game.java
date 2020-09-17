package game;

import javax.swing.*;

public class game {

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Введите сложность игры от 1 до 7:", "Сложность игры", 1);

        int difficulty = Integer.parseInt(input);

        if ((difficulty >= 1) & (difficulty <= 7)) {
            window game_window = new window(difficulty);
        }
    }
}
