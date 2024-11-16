package View.Prepare;

import javax.swing.JFrame;

import Controller.PrepareController;
import Data.AppConstants;

public class Prepare extends JFrame {
    private PrepareController controller;

    public Prepare() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void setController(PrepareController controller) {
        this.controller = controller;
    }

}
