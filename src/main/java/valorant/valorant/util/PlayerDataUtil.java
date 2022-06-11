package valorant.valorant.util;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import valorant.valorant.VALORANT;


public class PlayerDataUtil {

    static VALORANT getPlugin() {
        return ((VALORANT) Bukkit.getPluginManager().getPlugin("VALORANT"));
    }

    static FileConfiguration getPluginConfig() {
        return getPlugin().getConfig();
    }

    public static void setDataInteger(Player player, String value, Integer i){
        String path = player.getUniqueId() + "." + value;
        FileConfiguration conf = getPluginConfig();
        conf.set(path, i);
        getPlugin().saveConfig();
    }

    public static int getDataInteger(Player player, String value){
        String path = player.getUniqueId() + "." + value;
        FileConfiguration conf = getPluginConfig();
        return (conf.getInt(path));
    }


    public static void  setDataString(Player player, String value, String data){
        String path = player.getUniqueId() + "." + value;
        FileConfiguration conf = getPluginConfig();
        conf.set(path, data);
        getPlugin().saveConfig();
    }

    public static String getDataString(Player player, String value){
        String path = player.getUniqueId() + "." + value;
        FileConfiguration conf = getPluginConfig();
        return (conf.getString(path));
    }




    public static void setDataBool(Player player, String value, Boolean data){
        String path = player.getUniqueId() + "." + value;
        FileConfiguration conf = getPluginConfig();
        conf.set(path, data);
        getPlugin().saveConfig();
    }

    public static boolean getDataBool(Player player, String value){
        String path = player.getUniqueId() + "." + value;
        FileConfiguration conf = getPluginConfig();
        return (conf.getBoolean(path));
    }


}
