package valorant.valorant.util;




import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import valorant.valorant.VALORANT;

public interface LocationUtil {

    enum LocationType {
        CUSTOM, DEFAULT
    }

    String DOT = ".";
    String X = ".X";
    String Y = ".Y";
    String Z = ".Z";
    String YAW = ".YAW";
    String PITCH = ".PITCH";
    String WORLD = ".WORLD";

    /**
     * 위치를 파일에서 가죠옵니다
     */
    static Location getLocation(Player player, LocationType type, String homeName) {
        FileConfiguration conf = getPluginConfig();
        String path = "HOME." + player.getUniqueId() + DOT + type.name() + DOT + homeName;
        double x = conf.getDouble(path + X);
        double y = conf.getDouble(path + Y);
        double z = conf.getDouble(path + Z);
        float yaw = ((float) conf.getDouble(path + YAW));
        float pitch = ((float) conf.getDouble(path + PITCH));
        String worldString = conf.getString(path + WORLD);
        if (worldString == null) return null;
        World world = Bukkit.getWorld(worldString);
        return new Location(world, x, y, z, yaw, pitch);
    }

    /**
     * 위치를 파일에 저장합니다
     */
    static void setLocation(Player player, LocationType type, String homeName, Location location) {
        FileConfiguration conf = getPluginConfig();
        String path = "HOME." + player.getUniqueId() + DOT + type.name() + DOT + homeName;
        conf.set(path + X, location.getX());
        conf.set(path + Y, location.getY());
        conf.set(path + Z, location.getZ());
        conf.set(path + YAW, location.getYaw());
        conf.set(path + PITCH, location.getPitch());
        conf.set(path + WORLD, location.getWorld().getName());
        getPlugin().saveConfig();
    }

    static VALORANT getPlugin() {
        return ((VALORANT) Bukkit.getPluginManager().getPlugin("VALORANT"));
    }

    static FileConfiguration getPluginConfig() {
        return getPlugin().getConfig();
    }


    static Location get(String world, double x, double y, double z, float yaw, float pitch) {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

}
