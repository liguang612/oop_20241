package View.Menu;

import javax.swing.JFrame;

import Controller.MenuController;
import Data.AppConstants;

public class Menu extends JFrame {
    public Menu(MenuController controller) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        MainMenu mainMenu = controller.getMainMenu();
        this.setContentPane(mainMenu);

        this.setVisible(true);
    }
}
