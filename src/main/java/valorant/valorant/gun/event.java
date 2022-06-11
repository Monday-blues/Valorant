package valorant.valorant.gun;

import com.destroystokyo.paper.event.player.PlayerHandshakeEvent;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import valorant.valorant.VALORANT;
import valorant.valorant.util.MetaUtil;

public class event implements Listener {

    static VALORANT getPlugin() {
        return ((VALORANT) Bukkit.getPluginManager().getPlugin("VALORANT"));
    }

    @EventHandler
    public void onEvent(PlayerInteractEvent event) {
        try {
            ItemStack item = event.getItem();
            Action action = event.getAction();
            String itemname = item.getItemMeta().getLore().get(4);
            Player p = event.getPlayer();
            Integer slot = p.getInventory().getHeldItemSlot();
            if (p.getCooldown(p.getItemInHand().getType()) != 0 || !itemname.equals(ChatColor.AQUA + "THIS IS GUN")) {return;}
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK ) {
                event.setCancelled(true);
                p.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
                shoot(p, item);
                BukkitRunnable task = new BukkitRunnable() {@Override public void run() { selfloopfunc(p, item, slot);}};
                task.runTaskLater(getPlugin(), 5);
            }   if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK ) {
                event.setCancelled(true);
                Boolean isOn = MetaUtil.getMetaDataAsBoolean(p, "isZoom");
                if (isOn){
                    MetaUtil.setMetaDataAsBoolean(p, "isZoom", Boolean.FALSE);
                    p.removePotionEffect(PotionEffectType.SLOW);
                }else {
                    MetaUtil.setMetaDataAsBoolean(p, "isZoom", Boolean.TRUE);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, (int) Math.round(((Double.parseDouble(ChatColor.stripColor(item.getLore().get(3).replaceAll("줌 배수 : ", "")))-1)/0.15)-1), false,false,false));
                }

            }} catch (Exception ignored) {} }



    @EventHandler
    public void onF(PlayerSwapHandItemsEvent e){
        try {
            Player p = e.getPlayer();
            ItemStack item = e.getOffHandItem();
            String itemname = item.getItemMeta().getLore().get(4);
            if (!itemname.equals(ChatColor.AQUA + "THIS IS GUN")){return;}
            e.setCancelled(true);
            if (p.getCooldown(p.getItemInHand().getType()) != 0) {return;}
            reloading(p, item);
        } catch (Exception ignored) {} }

    @EventHandler
    public void onSwap(PlayerChangedMainHandEvent e){
        try {
            Player p = e.getPlayer();
            MetaUtil.setMetaDataAsBoolean(p, "isZoom", Boolean.FALSE);
            p.removePotionEffect(PotionEffectType.SLOW);
        }catch (Exception ignored) {}
    }

    public void selfloopfunc(Player p, ItemStack item, Integer slot){
        BukkitRunnable task = new BukkitRunnable() {
            @Override public void run() {
                if (p.isBlocking() && p.getInventory().getHeldItemSlot() == slot){
                    shoot(p, item);
                    selfloopfunc(p, item, slot);
                    return;
                }/*else if(!p.isBlocking()){
                    BukkitRunnable task = new BukkitRunnable() {@Override public void run() { p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));}};
                    task.runTaskLater(getPlugin(), 1);
                }*/
            }
        };
        task.runTaskLater(getPlugin(), Math.round(Double.parseDouble(ChatColor.stripColor(item.getLore().get(2).replaceAll("딜레이 : ", "")))));

    }

    public void reloading(Player p, ItemStack item){
        if (item.getAmount() >= (int) Double.parseDouble(ChatColor.stripColor(item.getLore().get(0).replaceAll("최대 장탄수 : ", "")))){return;}
        p.setCooldown(item.getType(), 120);
        item.setAmount((int) Double.parseDouble(ChatColor.stripColor(item.getLore().get(0).replaceAll("최대 장탄수 : ", ""))));
        p.getInventory().setItem(p.getInventory().getHeldItemSlot(), item);
        MetaUtil.setMetaDataAsBoolean(p, "isZoom", Boolean.FALSE);
        p.removePotionEffect(PotionEffectType.SLOW);
    }

    public void shoot(Player p, ItemStack item){



        if (p.getCooldown(p.getItemInHand().getType()) != 0 || !item.getItemMeta().getLore().get(4).equals(ChatColor.AQUA + "THIS IS GUN")) {return;}


        p.getWorld().spawn(p.getEyeLocation(), Arrow.class, en -> {
            Arrow entity = (Arrow) en;
            entity.setVelocity(p.getLocation().getDirection().multiply(Double.parseDouble(ChatColor.stripColor(item.getLore().get(1).replaceAll("데미지 : ", ""))) / 2));
            entity.setShooter(p);
            entity.setGlowing(true);
            entity.setColor(Color.AQUA);
            entity.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        });


        p.setCooldown(p.getItemInHand().getType(), (int) Double.parseDouble(ChatColor.stripColor(item.getLore().get(2).replaceAll("딜레이 : ", ""))));
        if (p.getItemInHand().getAmount() > 1){
            item.setAmount(item.getAmount() - 1);
            p.getInventory().setItem(p.getInventory().getHeldItemSlot(), item);
        }else if (p.getItemInHand().getAmount() == 1){ reloading(p, item); }
    }




}
