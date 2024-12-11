package Model;

public class Animation {
    private String spritePath;
    private String jsonPath;
    private String spritePathBack;
    private String jsonPathBack;

    public Animation() {
    }

    public Animation(String spritePath, String jsonPath) {
        this.spritePath = spritePath;
        this.jsonPath = jsonPath;

        if (!spritePath.endsWith("bulbasaur.png")) {
            return;
        }
        spritePathBack = spritePath.substring(0, spritePath.length() - 4);
        spritePathBack += "_back.png";

        jsonPathBack = jsonPath.substring(0, jsonPath.length() - 5);
        jsonPathBack += "_back.json";
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public String getSpritePathBack() {
        return spritePathBack;
    }

    public String getJsonPathBack() {
        return jsonPathBack;
    }

}
