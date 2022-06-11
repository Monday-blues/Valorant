package valorant.valorant.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemUtil {

    public static ItemStack createGuiItem(final Material material, final Integer value, final String name, final String... lore) {
        final ItemStack itemStack = new ItemStack(material, value);
        final ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
