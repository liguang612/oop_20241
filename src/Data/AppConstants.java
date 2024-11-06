package Data;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Model.PokeType;
import Model.Pokemon;

public class AppConstants {
    public static final int SCREEN_WIDTH = 1694;
    public static final int SCREEN_HEIGHT = 953;

    public static final int BORDER_RADIUS = 16;

    public static ImageIcon IMG_BACKGROUND1;
    public static ImageIcon IMG_CURSOR;
    public static ImageIcon IMG_CURSOR_SMALL;
    public static ImageIcon IMG_SELECT_CURSOR;

    public static Pokemon BULBASAUR;

    public static List<Pokemon> ALL_OF_POKEMONS = new ArrayList<>();

    public AppConstants() {
        // Initialize image
        IMG_BACKGROUND1 = new ImageIcon(getClass().getResource("../assets/img/game_background.png"));
        IMG_CURSOR = new ImageIcon(getClass().getResource("../assets/img/cursor.png"));
        IMG_CURSOR_SMALL = new ImageIcon(getClass().getResource("../assets/img/cursor_small.png"));
        IMG_SELECT_CURSOR = new ImageIcon(getClass().getResource("../assets/img/select_cursor.png"));

        // Initialize pokemon
        ALL_OF_POKEMONS.add(bulbasaur());
    }

    private Pokemon bulbasaur() {
        PokeType[] types = { PokeType.GRASS, PokeType.POSION };

        return BULBASAUR = new Pokemon("Overgrown", null,
                new ImageIcon(getClass().getResource("../assets/icon/bulbasaur.png")), 100, 1, 3, "Bulbasaur",
                "Dolice (-)", null, types);
    }
}
