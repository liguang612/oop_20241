import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

import Controller.MenuController;
import Data.AppConstants;

public class App {
    public static void main(String[] args) throws Exception {
        new AppConstants();

        UIManager.put("Label.font", AppConstants.FONT_PIXEL);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("Panel.opaque", false);
        UIManager.put("RadioButton.contentAreaFilled", false);
        UIManager.put("RadioButton.font", new Font("Consolas", Font.PLAIN, 32));
        UIManager.put("RadioButton.foreground", Color.WHITE);

        new MenuController();
    }
}
