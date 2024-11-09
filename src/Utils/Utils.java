package Utils;

import java.awt.Rectangle;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {
    public static String formatPokeId(int id) {
        String res = "";
        for (int i = 0; i < 3 - (int) Math.log10(id); i++) {
            res += '0';
        }

        return res + id;
    }

    public static List<Rectangle> parseSpriteCoordinates(String filePath) {
        List<Rectangle> spriteCoordinates = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(new File(filePath).toPath()));
            JSONObject jsonObj = new JSONObject(content);

            JSONArray textures = jsonObj.getJSONArray("textures");
            JSONObject texture = textures.getJSONObject(0);

            JSONArray frames = texture.getJSONArray("frames");

            for (int i = 0; i < frames.length(); i++) {
                JSONObject frame = frames.getJSONObject(i).getJSONObject("frame");
                spriteCoordinates.add(new Rectangle(
                        frame.getInt("x"),
                        frame.getInt("y"),
                        frame.getInt("w"),
                        frame.getInt("h")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spriteCoordinates;
    }
}
