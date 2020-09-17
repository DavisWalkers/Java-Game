package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class window extends JFrame {
    private field gameF;
    private int difficulty;

    public window(int difficulty) {
        this.difficulty = difficulty;
        addKeyListener(new myKey());
        setFocusable(true);
        setBounds(0, 0, 1440, 1980);
        setTitle("Игра: Новогодний дождь");
        gameF = new field(difficulty);
        Container con = getContentPane();
        con.add(gameF);
        setVisible(true);
    }

    private class myKey implements KeyListener {

        public void keyPressed(KeyEvent e) {

            int key_ = e.getKeyCode();

            if (key_ == 27) System.exit(0);
            else if (key_ == 37) {
                if (gameF.x - 30 > -48) gameF.x -= 30;
                else gameF.x = 1398;
            } else if (key_ == 39) {
                if (gameF.x + 30 < 1398) gameF.x += 30;
                else gameF.x = -48;
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    }
}
