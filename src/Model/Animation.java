package Model;

public class Animation {
    private String spritePath;
    private String jsonPath;

    public Animation() {
    }

    public Animation(String spritePath, String jsonPath) {
        this.spritePath = spritePath;
        this.jsonPath = jsonPath;
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

}
