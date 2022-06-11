package valorant.valorant.util;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static String toColor(String string) {
        if (string == null) return null;
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
