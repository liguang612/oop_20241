package Model;

import javax.swing.ImageIcon;

import View.Share.SpriteAnimation;

public class Pokemon {
    private String ability;
    private int armor;
    private SpriteAnimation animation, animationFromBack;
    private ImageIcon avatar;
    private boolean gender; // true: male; false: female
    private int hp, hpLeft; // The HP left (after being attacked)
    private int id;
    private int IVs;
    private String name;
    private String nature;
    private Skill[] skills;
    private PokeType[] type;
    private int mana;

    public Pokemon(String ability, Animation animation, ImageIcon avatar, int hp, int armor, int id, int IVs,
            String name,
            String nature, Skill[] skills, PokeType[] type) {
        this.ability = ability;
        if (animation != null) {
            this.animation = new SpriteAnimation(animation.getSpritePath(), animation.getJsonPath());
            this.animationFromBack = new SpriteAnimation(animation.getSpritePathBack(), animation.getJsonPathBack());
        }
        this.avatar = avatar;
        gender = true;
        this.hp = this.hpLeft = hp;
        this.id = id;
        this.IVs = IVs;
        this.name = name;
        this.nature = nature;
        this.skills = skills;
        this.type = type;

        mana = 175;
    }

    public void switchGender() {
        gender = !gender;
    }

    public Pokemon clone() {
        Pokemon pokemon = new Pokemon(ability, null, avatar, hp, armor, id, IVs, name, nature, skills, type);
        pokemon.setAnimation(animation);
        pokemon.setAnimationFromBack(animationFromBack);

        return pokemon;
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

    public int getArmor() {
        return armor;
    }

    public int getHpLeft() {
        return hpLeft;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setAnimation(SpriteAnimation animation) {
        this.animation = animation;
    }

    public void setAnimationFromBack(SpriteAnimation animationFromBack) {
        this.animationFromBack = animationFromBack;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setHp(int hp) {
        hpLeft = hpLeft * hp / this.hp;
        this.hp = hp;
    }

    public void setHpLeft(int hpLeft) {
        this.hpLeft = hpLeft;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIVs(int iVs) {
        IVs = iVs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public void setType(PokeType[] type) {
        this.type = type;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

}
