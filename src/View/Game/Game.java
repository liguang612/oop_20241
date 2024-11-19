package View.Game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Data.AppColor;
import Data.AppConstants;

public class Game extends JFrame {
    private JLayeredPane layers = new JLayeredPane();

    public Game() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        this.setContentPane(layers);
        this.setLocationRelativeTo(null);

        layers.setSize(this.getSize());

        Background bg = new Background();
        bg.setSize(layers.getSize());
        layers.add(bg, JLayeredPane.DEFAULT_LAYER);

        this.setVisible(true);
    }

    private class Background extends JPanel {
        private Background() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(AppColor.blue01);
            g2d.fillRect(0, 0, AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT / 3);

            g2d.setColor(AppColor.green01);
            g2d.fillRect(0, AppConstants.SCREEN_HEIGHT / 3, AppConstants.SCREEN_WIDTH,
                    AppConstants.SCREEN_HEIGHT * 2 / 3);
        }
    }
}
