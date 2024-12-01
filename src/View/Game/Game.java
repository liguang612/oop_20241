package View.Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Controller.GameController;
import Data.AppConstants;

public class Game extends JFrame {
    private JLayeredPane layers = new JLayeredPane();

    public Game(GameController controller) {
        super();

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
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Image background = AppConstants.IMG_GAME_BACKGROUND.getImage();

            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(background, 0, 0, AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT, this);
        }
    }
}
