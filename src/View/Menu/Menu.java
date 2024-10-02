package View.Menu;

import javax.swing.JFrame;

import Data.AppConstants;

public class Menu extends JFrame {
    private MainMenu mainMenu;

    public Menu() {
        mainMenu = new MainMenu();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        this.setContentPane(mainMenu);

        this.setVisible(true);
    }
}
