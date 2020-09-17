package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class field extends JPanel {
    public int x = 700;
    public Timer timerUpdate, timerDraw, lifeTimer;
    public int ct = 0;
    public int lifelong_sec = 0;
    public int caught_pokemons = 0;
    private Image hat;
    private Image background;
    private int difficulty;
    private gift[] gameGifts;
    private Image end_game;

    public field(int difficulty) {
        this.difficulty = difficulty;

        try {
            hat = ImageIO.read(new File("./hattt.png"));
            //JOptionPane.showMessageDialog(null, "Картинка для шапки успешно загружена");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка для шапки не загружена");
        }

        try {
            background = ImageIO.read(new File("./back3.jpg"));
            //JOptionPane.showMessageDialog(null, "Картинка фона загружена");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка фона не загружена");
        }

        try {
            end_game = ImageIO.read(new File("./end_game.jpg"));
            //JOptionPane.showMessageDialog(null, "Картинка конца игры загружена");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка конца игры не загружена");
        }

        gameGifts = new gift[9];
        for (int i = 0; i < 9; i++) {
            try {
                gameGifts[i] = new gift(ImageIO.read(new File("./gift" + i + ".png")));
                //JOptionPane.showMessageDialog(null, "Картинка подарка #" + i + " успешно загружена");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Картинка подарка #" + i + " не загружена");
            }
        }

        timerUpdate = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStart();
            }
        });

        timerUpdate.start();

        timerDraw = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        timerDraw.start();
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(background, 0, 0, null);
        gr.drawImage(hat, x, 620, null);

        for (int i = 0; i < 9; i++) {
            gameGifts[i].draw(gr);
            if (gameGifts[i].act) {
                if (gameGifts[i].y + gameGifts[i].img.getHeight(null) >= 700)
                    if (Math.abs(gameGifts[i].x - x) > 100) {
                        gr.drawImage(end_game, 0, 0, null);
                        timerDraw.stop();
                        timerUpdate.stop();
                        break;
                    } else {
                        caught_pokemons++;
                        gameGifts[i].act = false;
                    }
            }
        }
    }

    private void updateStart() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (!gameGifts[i].act) {
                if (count < difficulty) {
                    gameGifts[i].start();
                    ct += 1;
                    break;
                } else count++;
            }
        }
    }
}
