package valorant.valorant.util;



import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import valorant.valorant.VALORANT;


public class MoneyUtil {
    public static void setMoney(Player player, Integer i){
        String path = player.getUniqueId() + ".money";
        FileConfiguration conf = getPlugin().getConfig();
        conf.set(path, i);
        getPlugin().saveConfig();
    }

    public static void addMoney(Player player, Integer i){
        Integer in = getMoney(player) + i;
        setMoney(player, in);
    }

    public static void subMoney(Player player, Integer i){
        Integer in = getMoney(player) - i;
        setMoney(player, in);
    }

    public static int getMoney(Player player){
        String path = player.getUniqueId() + ".money";
        FileConfiguration conf = getPlugin().getConfig();
        return (conf.getInt(path));
    }

    static VALORANT getPlugin() {
        return ((VALORANT) Bukkit.getPluginManager().getPlugin("VALORANT"));
    }

}
