package valorant.valorant.util;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantUtil {
    public static ItemStack addEnchant(ItemStack item, Enchantment enchant, int level){
        ItemMeta testEnchantMeta = item.getItemMeta();
        testEnchantMeta.addEnchant(enchant, level, true);
        item.setItemMeta(testEnchantMeta);
        return item;
    }
}
