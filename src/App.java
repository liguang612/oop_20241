import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

import View.Menu.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        UIManager.put("Label.font", new Font("Consolas", Font.PLAIN, 32));
        UIManager.put("Label.foreground", Color.WHITE);

        new Menu();
    }
}
