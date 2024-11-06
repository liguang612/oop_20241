package Model;

import javax.swing.ImageIcon;

public class Pokemon {
    private String ability;
    private ImageIcon[] animations;
    private ImageIcon avatar;
    private boolean gender; // true: male; false: female
    private int hp;
    private int id;
    private int IVs;
    private String name;
    private String nature;
    private Skill[] skills;
    private PokeType[] type;

    public Pokemon(String ability, ImageIcon[] animations, ImageIcon avatar, int hp, int id, int IVs, String name,
            String nature, Skill[] skills, PokeType[] type) {
        this.ability = ability;
        this.animations = animations;
        this.avatar = avatar;
        gender = true;
        this.hp = hp;
        this.id = id;
        this.IVs = IVs;
        this.name = name;
        this.nature = nature;
        this.skills = skills;
        this.type = type;
    }

    public void switchGender() {
        gender = !gender;
    }

    public String getAbility() {
        return ability;
    }

    public ImageIcon[] getAnimations() {
        return animations;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public boolean getGender() {
        return gender;
    }

    public int getHp() {
        return hp;
    }

    public int getId() {
        return id;
    }

    public int getIVs() {
        return IVs;
    }

    public String getName() {
        return name;
    }

    public String getNature() {
        return nature;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public PokeType[] getType() {
        return type;
    }
}
