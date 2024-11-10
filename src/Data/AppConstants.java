package Data;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Model.Animation;
import Model.PokeType;
import Model.Pokemon;
import Model.Skill;

public class AppConstants {
    public static Font FONT_AGENCYFB;
    public static Font FONT_CHAVA;
    public static Font FONT_CONSOLAS;
    public static Font FONT_PIXEL;

    public static final int SCREEN_WIDTH = 1694;
    public static final int SCREEN_HEIGHT = 953;

    public static final int BORDER_RADIUS = 16;

    public static ImageIcon IMG_BACKGROUND1;
    public static ImageIcon IMG_CURSOR;
    public static ImageIcon IMG_CURSOR_SMALL;
    public static ImageIcon IMG_GENDER_FEMALE;
    public static ImageIcon IMG_GENDER_MALE;
    public static ImageIcon IMG_SELECT_CURSOR;
    public static ImageIcon IMG_SELECT_CURSOR_HIGHLIGHT;

    public static Pokemon BULBASAUR;
    public static Pokemon CHARMANDER;
    public static Pokemon CHESPIN;
    public static Pokemon CHIKORTIA;
    public static Pokemon CHIMCHAR;
    public static Pokemon CYNDAQUIL;
    public static Pokemon FENNEKIN;
    public static Pokemon FROAKIE;
    public static Pokemon FUECOCO;
    public static Pokemon GROOKEY;
    public static Pokemon LITTEN;
    public static Pokemon MUDKIP;
    public static Pokemon OSHAWOTT;
    public static Pokemon PIPLUP;
    public static Pokemon POPPLIO;
    public static Pokemon QUAXLY;
    public static Pokemon ROWLET;
    public static Pokemon SCORBUNNY;
    public static Pokemon SNIVY;
    public static Pokemon SOBBLE;
    public static Pokemon SPEAROW;
    public static Pokemon SPRIGATITO;
    public static Pokemon SQUIRTLE;
    public static Pokemon SUNKERN;
    public static Pokemon TEPIG;
    public static Pokemon TORCHIC;
    public static Pokemon TOTODILE;
    public static Pokemon TREECKO;
    public static Pokemon TURTWIG;

    public static List<Pokemon> ALL_OF_POKEMONS = new ArrayList<>();

    public AppConstants() {
        // Initialize font
        agencyFB();
        chava();
        consolas();
        pixel();

        // Initialize image
        IMG_BACKGROUND1 = new ImageIcon(getClass().getResource("../assets/img/game_background.png"));
        IMG_CURSOR = new ImageIcon(getClass().getResource("../assets/img/cursor.png"));
        IMG_CURSOR_SMALL = new ImageIcon(getClass().getResource("../assets/img/cursor_small.png"));
        IMG_GENDER_MALE = new ImageIcon(getClass().getResource("../assets/img/male.png"));
        IMG_SELECT_CURSOR = new ImageIcon(getClass().getResource("../assets/img/select_cursor.png"));
        IMG_SELECT_CURSOR_HIGHLIGHT = new ImageIcon(
                getClass().getResource("../assets/img/select_cursor_highlight.png"));

        // Initialize pokemon
        ALL_OF_POKEMONS.add(bulbasaur());
        ALL_OF_POKEMONS.add(charmander());
        ALL_OF_POKEMONS.add(squirtle());
        ALL_OF_POKEMONS.add(spearow());
        ALL_OF_POKEMONS.add(chikorita());
        ALL_OF_POKEMONS.add(cyndaquil());
        ALL_OF_POKEMONS.add(totodile());
        ALL_OF_POKEMONS.add(sunkern());
        ALL_OF_POKEMONS.add(treecko());
        ALL_OF_POKEMONS.add(torchic());
        ALL_OF_POKEMONS.add(mudkip());
        ALL_OF_POKEMONS.add(turtwig());
        ALL_OF_POKEMONS.add(chimchar());
        ALL_OF_POKEMONS.add(piplup());
        ALL_OF_POKEMONS.add(snivy());
        ALL_OF_POKEMONS.add(tepig());
        ALL_OF_POKEMONS.add(oshawott());
        ALL_OF_POKEMONS.add(chespin());
        ALL_OF_POKEMONS.add(fennekin());
        ALL_OF_POKEMONS.add(froakie());
        ALL_OF_POKEMONS.add(rowlet());
        ALL_OF_POKEMONS.add(litten());
        ALL_OF_POKEMONS.add(popplio());
        ALL_OF_POKEMONS.add(grookey());
        ALL_OF_POKEMONS.add(scorbunny());
        ALL_OF_POKEMONS.add(sobble());
        ALL_OF_POKEMONS.add(sprigatito());
        ALL_OF_POKEMONS.add(fuecoco());
        ALL_OF_POKEMONS.add(quaxly());
    }

    // POKEMON
    private Pokemon bulbasaur() {
        PokeType[] types = { PokeType.GRASS, PokeType.POSION };
        Skill[] skills = { Skill.TACKLE, Skill.GROWL, Skill.VINE_WHIP };

        return BULBASAUR = new Pokemon("Overgrown",
                new Animation(getClass().getResource("../assets/animation/bulbasaur.png").getPath(),
                        getClass().getResource("../assets/animation/bulbasaur.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/bulbasaur.png")), 100, 1, 3,
                "Bulbasaur",
                "Dolice (-)", skills, types);
    }

    private Pokemon charmander() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.SCRATCH, Skill.GROWL, Skill.EMBER };

        return CHARMANDER = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/charmander.png").getPath(),
                        getClass().getResource("../assets/animation/charmander.json")
                                .getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/charmander.png")), 100, 4, 3,
                "Charmander",
                "Quirky (-)", skills, types);
    }

    private Pokemon chespin() {
        PokeType[] types = { PokeType.GRASS };
        Skill[] skills = { Skill.TACKLE, Skill.GROWL, Skill.VINE_WHIP };

        return CHESPIN = new Pokemon("Overgrow",
                new Animation(getClass().getResource("../assets/animation/chespin.png").getPath(),
                        getClass().getResource("../assets/animation/chespin.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/chespin.png")), 100, 650, 3,
                "Chespin",
                "Bashful (-)", skills, types);
    }

    private Pokemon chikorita() {
        PokeType[] types = { PokeType.GRASS };
        Skill[] skills = { Skill.TACKLE, Skill.GROWL, Skill.RAZOR_LEAF };

        return CHIKORTIA = new Pokemon("Overgrow",
                new Animation(getClass().getResource("../assets/animation/chikorita.png").getPath(),
                        getClass().getResource("../assets/animation/chikorita.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/chikorita.png")), 100, 152, 2,
                "Chikorita",
                "Quirky (-)", skills, types);
    }

    private Pokemon chimchar() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.SCRATCH, Skill.LEER, Skill.EMBER };

        return CHIMCHAR = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/chimchar.png").getPath(),
                        getClass().getResource("../assets/animation/chimchar.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/chimchar.png")), 100, 390, 3,
                "Chimchar",
                "Hardy (-)", skills, types);
    }

    private Pokemon cyndaquil() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.TACKLE, Skill.LEER, Skill.EMBER };

        return CYNDAQUIL = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/cyndaquil.png").getPath(),
                        getClass().getResource("../assets/animation/cyndaquil.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/cyndaquil.png")), 100, 155, 3,
                "Cyndaquil",
                "Serious (-)", skills, types);
    }

    private Pokemon fennekin() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.SCRATCH, Skill.TALL_WHIP, Skill.EMBER };

        return FENNEKIN = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/fennekin.png").getPath(),
                        getClass().getResource("../assets/animation/fennekin.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/fennekin.png")), 100, 653, 3,
                "Fennekin",
                "Bashful (-)", skills, types);
    }

    private Pokemon froakie() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.POUND, Skill.GROWL, Skill.WATER_GUN };

        return FROAKIE = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/froakie.png").getPath(),
                        getClass().getResource("../assets/animation/froakie.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/froakie.png")), 100, 656, 4,
                "Froakie",
                "Hardy (-)", skills, types);
    }

    private Pokemon fuecoco() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.TACKLE, Skill.LEER, Skill.EMBER };

        return FUECOCO = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/fuecoco.png").getPath(),
                        getClass().getResource("../assets/animation/fuecoco.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/fuecoco.png")), 100, 909, 4,
                "Fuecoco",
                "Bashful (-)", skills, types);
    }

    private Pokemon grookey() {
        PokeType[] types = { PokeType.GRASS };
        Skill[] skills = { Skill.SCRATCH, Skill.GROWL, Skill.BRANCH_POKE };

        return GROOKEY = new Pokemon("Overgrow",
                new Animation(getClass().getResource("../assets/animation/grookey.png").getPath(),
                        getClass().getResource("../assets/animation/grookey.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/grookey.png")), 100, 810, 3,
                "Grookey",
                "Hardy (-)", skills, types);
    }

    private Pokemon litten() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.SCRATCH, Skill.GROWL, Skill.EMBER };

        return LITTEN = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/litten.png").getPath(),
                        getClass().getResource("../assets/animation/litten.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/litten.png")), 100, 725, 3,
                "Litten",
                "Bashful (-)", skills, types);
    }

    private Pokemon mudkip() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.TACKLE, Skill.GROWL, Skill.WATER_GUN };

        return MUDKIP = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/mudkip.png").getPath(),
                        getClass().getResource("../assets/animation/mudkip.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/mudkip.png")), 100, 258, 3,
                "Mudkip",
                "Bashful (-)", skills, types);
    }

    private Pokemon oshawott() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.TACKLE, Skill.TALL_WHIP, Skill.WATER_GUN };

        return OSHAWOTT = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/oshawott.png").getPath(),
                        getClass().getResource("../assets/animation/oshawott.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/oshawott.png")), 100, 501, 3,
                "Oshawott",
                "Serious (-)", skills, types);
    }

    private Pokemon piplup() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.POUND, Skill.GROWL, Skill.WATER_GUN };

        return PIPLUP = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/piplup.png").getPath(),
                        getClass().getResource("../assets/animation/piplup.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/piplup.png")), 100, 393, 3,
                "Piplup",
                "Serious (-)", skills, types);
    }

    private Pokemon popplio() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.POUND, Skill.GROWL, Skill.WATER_GUN };

        return POPPLIO = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/popplio.png").getPath(),
                        getClass().getResource("../assets/animation/popplio.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/popplio.png")), 100, 728, 4,
                "Popplio",
                "Bashful (-)", skills, types);
    }

    private Pokemon quaxly() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.POUND, Skill.GROWL, Skill.WATER_GUN };

        return QUAXLY = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/quaxly.png").getPath(),
                        getClass().getResource("../assets/animation/quaxly.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/charmander.png")), 100, 912, 4,
                "Quaxly",
                "Bashful (-)", skills, types);
    }

    private Pokemon rowlet() {
        PokeType[] types = { PokeType.GRASS, PokeType.FLYING };
        Skill[] skills = { Skill.TACKLE, Skill.GROWL, Skill.LEAFAGE };

        return ROWLET = new Pokemon("Overgrow",
                new Animation(getClass().getResource("../assets/animation/rowlet.png").getPath(),
                        getClass().getResource("../assets/animation/rowlet.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/rowlet.png")), 100, 722, 3,
                "Rowlet",
                "Quirky (-)", skills, types);
    }

    private Pokemon scorbunny() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.TACKLE, Skill.GROWL, Skill.EMBER };

        return SCORBUNNY = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/scorbunny.png").getPath(),
                        getClass().getResource("../assets/animation/scorbunny.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/scorbunny.png")), 100, 813, 4,
                "Scorbunny",
                "Quirky (-)", skills, types);
    }

    private Pokemon snivy() {
        PokeType[] types = { PokeType.GRASS };
        Skill[] skills = { Skill.TACKLE, Skill.LEER, Skill.VINE_WHIP };

        return SNIVY = new Pokemon("Overgrow",
                new Animation(getClass().getResource("../assets/animation/snivy.png").getPath(),
                        getClass().getResource("../assets/animation/snivy.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/snivy.png")), 100, 495, 3, "Snivy",
                "Quirky (-)", skills, types);
    }

    private Pokemon sobble() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.POUND, Skill.GROWL, Skill.WATER_GUN };

        return SOBBLE = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/sobble.png").getPath(),
                        getClass().getResource("../assets/animation/sobble.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/sobble.png")), 100, 816, 3,
                "Sobble",
                "Serious (-)", skills, types);
    }

    private Pokemon spearow() {
        PokeType[] types = { PokeType.NORMAL, PokeType.FLYING };
        Skill[] skills = { Skill.GROWL, Skill.PECK, Skill.LEER };

        return SPEAROW = new Pokemon("Keen Eye",
                new Animation(getClass().getResource("../assets/animation/spearow.png").getPath(),
                        getClass().getResource("../assets/animation/spearow.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/spearow.png")), 100, 21, 1,
                "Spearow",
                "Naughty (+Afk/-SpDef)", skills, types);
    }

    private Pokemon sprigatito() {
        PokeType[] types = { PokeType.GRASS };
        Skill[] skills = { Skill.SCRATCH, Skill.TALL_WHIP, Skill.LEAFAGE };

        return SPRIGATITO = new Pokemon("Overgrow",
                new Animation(getClass().getResource("../assets/animation/sprigatito.png").getPath(),
                        getClass().getResource("../assets/animation/sprigatito.json")
                                .getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/sprigatito.png")), 100, 906, 4,
                "Sprigatito",
                "Hardry (-)", skills, types);
    }

    private Pokemon squirtle() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.TACKLE, Skill.TALL_WHIP, Skill.WATER_GUN };

        return SQUIRTLE = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/squirtle.png").getPath(),
                        getClass().getResource("../assets/animation/squirtle.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/squirtle.png")), 100, 7, 3,
                "Squirtle",
                "Hardy (-)", skills, types);
    }

    private Pokemon sunkern() {
        PokeType[] types = { PokeType.GRASS };
        Skill[] skills = { Skill.TACKLE, Skill.GROWTH };

        return SUNKERN = new Pokemon("Solar Power",
                new Animation(getClass().getResource("../assets/animation/sunkern.png").getPath(),
                        getClass().getResource("../assets/animation/sunkern.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/sunkern.png")), 100, 191, 1,
                "Sunkern",
                "Naughty (+Atk/-SpDef)", skills, types);
    }

    private Pokemon tepig() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.TACKLE, Skill.TALL_WHIP, Skill.EMBER };

        return TEPIG = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/tepig.png").getPath(),
                        getClass().getResource("../assets/animation/tepig.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/tepig.png")), 100, 498, 3, "Tepig",
                "Serious (-)", skills, types);
    }

    private Pokemon torchic() {
        PokeType[] types = { PokeType.FIRE };
        Skill[] skills = { Skill.SCRATCH, Skill.GROWL, Skill.EMBER };

        return TORCHIC = new Pokemon("Blaze",
                new Animation(getClass().getResource("../assets/animation/torchic.png").getPath(),
                        getClass().getResource("../assets/animation/torchic.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/torchic.png")), 100, 255, 4,
                "Torchic",
                "Serious (-)", skills, types);
    }

    private Pokemon totodile() {
        PokeType[] types = { PokeType.WATER };
        Skill[] skills = { Skill.SCRATCH, Skill.LEER, Skill.WATER_GUN };

        return TOTODILE = new Pokemon("Torrent",
                new Animation(getClass().getResource("../assets/animation/totodile.png").getPath(),
                        getClass().getResource("../assets/animation/totodile.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/totodile.png")), 100, 158, 3,
                "Totodile",
                "Serious (-)", skills, types);
    }

    private Pokemon treecko() {
        PokeType[] types = { PokeType.GRASS };
        Skill[] skills = { Skill.POUND, Skill.LEER, Skill.LEAFAGE };

        return TREECKO = new Pokemon("Overgrow",
                new Animation(getClass().getResource("../assets/animation/treecko.png").getPath(),
                        getClass().getResource("../assets/animation/treecko.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/treecko.png")), 100, 252, 3,
                "Treecko",
                "Dolice (-)", skills, types);
    }

    private Pokemon turtwig() {
        PokeType[] types = { PokeType.GRASS };
        Skill[] skills = { Skill.SCRATCH, Skill.WITHDRAW, Skill.LEAFAGE };

        return TURTWIG = new Pokemon("Overgrow",
                new Animation(getClass().getResource("../assets/animation/turtwig.png").getPath(),
                        getClass().getResource("../assets/animation/turtwig.json").getPath()),
                new ImageIcon(getClass().getResource("../assets/icon/turtwig.png")), 100, 387, 3,
                "Turtwig",
                "Bashful (-)", skills, types);
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

            FONT_CHAVA = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(48f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consolas() {
        FONT_CONSOLAS = new Font("Consolas", Font.PLAIN, 32);
    }

    private void pixel() {
        try {
            InputStream is = new FileInputStream(
                    new File(getClass().getResource("../assets/font/pixel_operator.ttf")
                            .getPath()));

            FONT_PIXEL = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(48f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
