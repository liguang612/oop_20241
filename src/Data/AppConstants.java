package Data;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Model.PokeType;
import Model.Pokemon;

public class AppConstants {
    public static Font FONT_AGENCYFB;
    public static Font FONT_CHAVA;

    public static final int SCREEN_WIDTH = 1694;
    public static final int SCREEN_HEIGHT = 953;

    public static final int BORDER_RADIUS = 16;

    public static ImageIcon IMG_BACKGROUND1;
    public static ImageIcon IMG_CURSOR;
    public static ImageIcon IMG_CURSOR_SMALL;
    public static ImageIcon IMG_GENDER_FEMALE;
    public static ImageIcon IMG_GENDER_MALE;
    public static ImageIcon IMG_SELECT_CURSOR;

    public static Pokemon BULBASAUR;

    public static List<Pokemon> ALL_OF_POKEMONS = new ArrayList<>();

    public AppConstants() {
        // Initialize font
        agencyFB();
        chava();

        // Initialize image
        IMG_BACKGROUND1 = new ImageIcon(getClass().getResource("../assets/img/game_background.png"));
        IMG_CURSOR = new ImageIcon(getClass().getResource("../assets/img/cursor.png"));
        IMG_CURSOR_SMALL = new ImageIcon(getClass().getResource("../assets/img/cursor_small.png"));
        IMG_GENDER_MALE = new ImageIcon(getClass().getResource("../assets/img/male.png"));
        IMG_SELECT_CURSOR = new ImageIcon(getClass().getResource("../assets/img/select_cursor.png"));

        // Initialize pokemon
        ALL_OF_POKEMONS.add(bulbasaur());
    }

    // POKEMON
    private Pokemon bulbasaur() {
        PokeType[] types = { PokeType.GRASS, PokeType.POSION };

        return BULBASAUR = new Pokemon("Overgrown", null,
                new ImageIcon(getClass().getResource("../assets/icon/bulbasaur.png")), 100, 1, 3, "Bulbasaur",
                "Dolice (-)", null, types);
    }

    // FONT
    private void agencyFB() {
        try {
            InputStream is = new FileInputStream(
                    new File(getClass().getResource("../assets/font/agency_fb.ttf").getPath()));

            FONT_AGENCYFB = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chava() {
        try {
            InputStream is = new FileInputStream(
                    new File(getClass().getResource("../assets/font/chava.ttf").getPath()));

            FONT_CHAVA = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
