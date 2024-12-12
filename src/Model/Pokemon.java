package Model;

import javax.swing.ImageIcon;

import View.Share.SpriteAnimation;

public class Pokemon {
    private String ability;
    private SpriteAnimation animation, animationFromBack;
    private ImageIcon avatar;
    private boolean gender; // true: male; false: female
    private int hp;
    private int armor;
    private int id;
    private int IVs;
    private String name;
    private String nature;
    private Skill[] skills;
    private PokeType[] type;

    public Pokemon(String ability, Animation animation, ImageIcon avatar, int hp,int armor, int id, int IVs, String name,
            String nature, Skill[] skills, PokeType[] type) {
        this.ability = ability;
        this.animation = new SpriteAnimation(animation.getSpritePath(), animation.getJsonPath());
        this.animationFromBack = new SpriteAnimation(animation.getSpritePathBack(), animation.getJsonPathBack());
        this.avatar = avatar;
        gender = true;
        this.hp = hp;
        this.armor = armor;
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

    public SpriteAnimation getAnimation() {
        return animation;
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

    public int getArmor() {
        return armor;
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

    public SpriteAnimation getAnimationFromBack() {
        return animationFromBack;
    }
}
