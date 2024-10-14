package View.Game;

import javax.swing.JFrame;

import Data.AppConstants;

public class Game extends JFrame {
    private BuildTeam buildTeam;

    public Game() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        buildTeam = new BuildTeam();
        this.setLayeredPane(buildTeam);

        this.setVisible(true);
    }
}
