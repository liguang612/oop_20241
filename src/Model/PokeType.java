package Model;

import java.awt.Color;

public enum PokeType {
    NORMAL("Normal", new Color(173, 165, 148)),
    FIGHT("Fight", new Color(165, 82, 57)),
    FLYING("Flying", new Color(156, 173, 247)),
    POSION("Poison", new Color(145, 65, 203)),
    GROUND("Ground", new Color(174, 122, 59)),
    ROCK("Rock", new Color(189, 165, 90)),
    BUG("Bug", new Color(173, 189, 33)),
    GHOST("Ghost", new Color(99, 99, 181)),
    STEEL("Steel", new Color(129, 166, 190)),
    FIRE("Fire", new Color(247, 82, 49)),
    WATER("Water", new Color(57, 156, 255)),
    GRASS("Grass", new Color(123, 206, 82)),
    ELECTR("Electr", new Color(255, 198, 49)),
    PSYCHC("Psychc", new Color(239, 65, 121)),
    ICE("Ice", new Color(90, 206, 231)),
    DRAGON("Dragon", new Color(123, 99, 231)),
    DARK("Dark", new Color(115, 90, 74)),
    FAIRY("Fairy", new Color(239, 112, 239));

    private final String name;
    private final Color color;

    PokeType(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name;
    }
}
