package Utils;

public class Utils {
    public static String formatPokeId(int id) {
        String res = "";
        for (int i = 0; i < 3 - id / 10; i++) {
            res += '0';
        }

        return res + id;
    }
}
