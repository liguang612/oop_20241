package Controller;

import View.Menu.MainMenu;
import View.Menu.Menu;
import View.Menu.Settings;

public class MenuController {
    private Menu menu;
    private MainMenu mainMenu;
    private Settings settings;

    public MenuController() {
        mainMenu = new MainMenu(this);
        menu = new Menu(this);
        settings = new Settings(this);
    }

    public Menu getMenu() {
        return menu;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Settings getSettings() {
        return settings;
    }

}
