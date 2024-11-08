package Model;

public enum Skill {
    TACKLE(10, "Tackle", PokeType.NORMAL),
    GROWL(10, "Growl", PokeType.NORMAL),
    VINE_WHIP(30, "Vine Whip", PokeType.GRASS);

    private int damage;
    private String name;
    private PokeType type;

    Skill(int damage, String name, PokeType type) {
        this.damage = damage;
        this.name = name;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public PokeType getType() {
        return type;
    }
}
