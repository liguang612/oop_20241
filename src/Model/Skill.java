package Model;

public enum Skill {
    BRANCH_POKE(30, "Branch Poke", PokeType.GRASS),
    EMBER(30, "Ember", PokeType.FIRE),
    GROWL(10, "Growl", PokeType.NORMAL),
    GROWTH(10, "Growth", PokeType.NORMAL),
    LEAFAGE(30, "Leafage", PokeType.GRASS),
    LEER(10, "Leer", PokeType.NORMAL),
    RAZOR_LEAF(30, "Razor Leaf", PokeType.GRASS),
    PECK(30, "Peck", PokeType.FLYING),
    POUND(10, "Pound", PokeType.NORMAL),
    SCRATCH(10, "Scratch", PokeType.NORMAL),
    TACKLE(10, "Tackle", PokeType.NORMAL),
    TALL_WHIP(10, "Tall Whip", PokeType.NORMAL),
    VINE_WHIP(30, "Vine Whip", PokeType.GRASS),
    WATER_GUN(30, "Water Gun", PokeType.WATER),
    WITHDRAW(30, "Withdraw", PokeType.WATER);

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
