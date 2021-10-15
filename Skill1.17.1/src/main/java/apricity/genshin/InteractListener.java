package apricity.genshin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class InteractListener implements Listener {
    private Apricity p;
    public InteractListener(Apricity apricity) {
        this.p=apricity;
    }
    @EventHandler
    public void onIta(PlayerInteractEvent event) {
        if(event!=null&& event.hasBlock()&&event.hasItem()){
            Player player = event.getPlayer();
            ItemStack is = player.getEquipment().getItemInMainHand();
            ItemStack io = new ItemStack(Material.STICK);
            if (is.getType().equals(io.getType())) {
                event.setCancelled(true);
                Location l = event.getClickedBlock().getLocation();
                l = l.clone();
                l.setY(l.getY() + 1);
                Bukkit.getLogger().info("" + l.getY());
                Zombie e = (Zombie) Bukkit.getWorld("world").spawn(l, Zombie.class, t -> {
                    t.setCustomName("测试qq人");
                    t.setCustomNameVisible(true);
                    t.setMaxHealth(1000);
                    t.setSilent(true);
                });
            } else {
            }
        }
    }
}
