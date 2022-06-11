package valorant.valorant;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import valorant.valorant.gun.command;
import valorant.valorant.gun.event;

public final class VALORANT extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("gunmaker").setExecutor(new command());

        Bukkit.getPluginManager().registerEvents(new event(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }
}
