package valorant.valorant.gun;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import valorant.valorant.util.ColorUtil;
import valorant.valorant.util.ItemUtil;

import java.util.ArrayList;
import java.util.List;

public class command implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 5){
            Player p = (Player) sender;
            ItemStack gun = p.getItemInHand();
            ItemMeta meta = gun.getItemMeta();
            String name = args[0];
            Double maxammo = Double.parseDouble(args[1]);
            Double damage = Double.parseDouble(args[2]);
            Double delay = Double.parseDouble(args[3]);
            Double zoom = Double.parseDouble(args[4]);

            meta.setDisplayName(ColorUtil.toColor(name));
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ColorUtil.toColor("&6최대 장탄수 : ") + maxammo);
            lore.add(ColorUtil.toColor("&6데미지 : ") + damage);
            lore.add(ColorUtil.toColor("&6딜레이 : ") + delay);
            lore.add(ColorUtil.toColor("&6줌 배수 : ") + zoom);
            lore.add(ChatColor.AQUA + "THIS IS GUN");

            meta.setLore(lore);

            gun.setItemMeta(meta);

            p.getInventory().setItem(p.getInventory().getHeldItemSlot(), gun);
        }
        return true;
    }
}
