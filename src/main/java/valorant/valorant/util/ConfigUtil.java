package valorant.valorant.util;




import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import valorant.valorant.VALORANT;

import javax.annotation.Nullable;


public class ConfigUtil {

    static VALORANT getPlugin() {
        return ((VALORANT) Bukkit.getPluginManager().getPlugin("VALORANT"));
    }

    static FileConfiguration getPluginConfig() {
        return getPlugin().getConfig();
    }


    public static void setDataInteger(String value, Integer i){
        String path = value;
        FileConfiguration conf = getPluginConfig();
        conf.set(path, i);
        getPlugin().saveConfig();
    }

    public static int getDataInteger(String value){
        String path = value;
        FileConfiguration conf = getPluginConfig();
        return (conf.getInt(path));
    }


    public static void  setDataString(String value, String data){
        String path = value;
        FileConfiguration conf = getPluginConfig();
        conf.set(path, data);
        getPlugin().saveConfig();
    }

    public static String getDataString(String value){
        String path = value;
        FileConfiguration conf = getPluginConfig();
        return (conf.getString(path));
    }




    public static void setDataBool( String value, Boolean data){
        String path = value;
        FileConfiguration conf = getPluginConfig();
        conf.set(path, data);
        getPlugin().saveConfig();
    }

    public static boolean getDataBool( String value){
        String path = value;
        FileConfiguration conf = getPluginConfig();
        return (conf.getBoolean(path));
    }



    public static void setDataMaterial(String value, Material data){
        String path = value;
        FileConfiguration conf = getPluginConfig();
        conf.set(path, data.toString());
        getPlugin().saveConfig();
    }

    @Nullable
    public static Material getDataMaterial(String value){
        String path = value;
        FileConfiguration conf = getPluginConfig();
        return Material.matchMaterial(conf.getString(path));
    }
}
