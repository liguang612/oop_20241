package Data;

import javax.swing.ImageIcon;

public class AppConstants {
    public static final int SCREEN_WIDTH = 1694;
    public static final int SCREEN_HEIGHT = 953;

    public static final int BORDER_RADIUS = 16;

    public static ImageIcon IMG_BACKGROUND1;
    public static ImageIcon IMG_CURSOR;
    public static ImageIcon IMG_CURSOR_SMALL;
    public static ImageIcon IMG_SELECT_CURSOR;

    public AppConstants() {
        IMG_BACKGROUND1 = new ImageIcon(getClass().getResource("../assets/img/game_background.png"));
        IMG_CURSOR = new ImageIcon(getClass().getResource("../assets/img/cursor.png"));
        IMG_CURSOR_SMALL = new ImageIcon(getClass().getResource("../assets/img/cursor_small.png"));
        IMG_SELECT_CURSOR = new ImageIcon(getClass().getResource("../assets/img/select_cursor.png"));
    }
}
