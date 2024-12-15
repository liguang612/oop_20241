package View.Game.Widget;

import javax.swing.*;

import Controller.GameController;
import Controller.MenuController;
import Data.AppColor;
import Data.AppConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Loser extends JPanel implements ActionListener, KeyListener {
    private GameController controller;

    private float opacity = 0;
    private Timer timer;

    public Loser(GameController controller) {
        super();
        this.controller = controller;

        setBackground(AppColor.purple01);
        setFocusable(true);
        setLayout(new GridLayout(1, 1));
        setOpaque(false);
        setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);

        setOpacity(opacity);

        add(new JLabel("YOU LOSE!", JLabel.CENTER));

        timer = new Timer(75, this);
        timer.start();

        addKeyListener(this);

        SwingUtilities.invokeLater(() -> {
            requestFocusInWindow();
        });
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
        setOpacity(Math.min(opacity += 0.1f, 1.0f));

        if (opacity >= 1.0f) {
            timer.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        new MenuController();
        controller.getGame().setVisible(false);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
