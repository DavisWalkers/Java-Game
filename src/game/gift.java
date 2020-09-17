package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class gift {
    public Image img;
    public int x, y;
    public Boolean act;
    Timer timerUpdate;
    private int direction;

    public gift(Image img) {
        timerUpdate = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                down();
            }
        });
        direction = (int) (Math.pow(-1, (int) (Math.random() * 10)));
        this.img = img;
        act = false;
    }

    public void start() {
        timerUpdate.start();
        y = 0;
        x = (int) (Math.random() * 1440);
        act = true;
    }

    public void down() {
        if (act) {
            y += 1;
            x += direction;
            if (x >= 1398) {
                x = 0;
            }
            else if (x < 0) {
                x = 1398;
            }
        }
        if ((y + img.getHeight(null)) >= 1920) {
            timerUpdate.stop();
        }
    }

    public void draw(Graphics gr) {
        if (act) {
            gr.drawImage(img, x, y, null);
        }
    }
}
