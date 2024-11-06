package Model;

public class Skill {
    private int damage;
    private String name;
    private PokeType type;

    public Skill(int damage, String name, PokeType type) {
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
