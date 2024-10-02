import java.awt.Font;

import javax.swing.UIManager;

import View.Menu.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        UIManager.put("Label.font", new Font("Consolas", Font.PLAIN, 16));

        new Menu();
    }
}
