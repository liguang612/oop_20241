package View.Game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Data.AppConstants;

public class ChangePokemon extends JPanel {
    public ChangePokemon() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(AppConstants.IMG_CHANGE_POKEMON_BACKGROUND.getImage(),
                0, 0,
                AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT,
                this);
    }
}
