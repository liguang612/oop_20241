package View.Share;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimatedPanel extends JPanel implements ActionListener {
    private boolean pos = false; // false = DOWN, true = UP
    private ImageIcon img;
    private Timer timer;

    private int X = 0;

    public AnimatedPanel() {
        super();
    }

    public AnimatedPanel(ImageIcon img) {
        super();
        this.img = img;

        setOpaque(false);

        timer = new Timer(250, this);
        timer.start();
    }

    public void start() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void stop() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pos = !pos;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (X == 0) {
            X = (getWidth() - img.getIconWidth()) / 2;
        }

        if (pos) {
            g2d.drawImage(img.getImage(), X, 10, img.getIconWidth(), img.getIconHeight(), this);
        } else {
            g2d.drawImage(img.getImage(), X, 20, img.getIconWidth(), img.getIconHeight(), this);
        }
    }

    public void setImg(ImageIcon img) {
        this.img = img;
        X = 0;
        repaint();
    }
}
