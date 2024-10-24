package View.Menu;

import javax.swing.JFrame;

import Data.AppConstants;

public class Menu extends JFrame {
    private MainMenu mainMenu;

    public Menu() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        mainMenu = new MainMenu(this);
        this.setContentPane(mainMenu);

        this.setVisible(true);
    }
}
