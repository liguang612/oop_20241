package View.Game.Widget;

import javax.swing.*;

import Data.AppColor;
import Data.AppConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loser extends JPanel implements ActionListener {
    private float opacity = 0;
    private Timer timer;

    public Loser() {
        setBackground(AppColor.purple01);
        setLayout(new GridLayout(1, 1));
        setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);

        setOpacity(opacity);

        add(new JLabel("YOU LOSE!", JLabel.CENTER));

        timer = new Timer(15, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(opacity));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
        super.paintComponent(g);
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setOpacity(opacity += 0.1);

        if (opacity >= 1) {
            timer.stop();
        }
    }
}
