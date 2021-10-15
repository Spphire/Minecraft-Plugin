package apricity.genshin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class DamageListener implements Listener {
    private Apricity p;
    public DamageListener(Apricity apricity) {
        this.p=apricity;
    }
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player){
            Player player =(Player) event.getDamager();
            double damage=Tools.Damage_Calculator(player,event.getEntity());
            if(damage!=0) {
                event.setDamage(damage);
                SpawnCarrier.SpawnVisualDigit(p,ChatColor.WHITE,event.getEntity(),Integer.parseInt(new java.text.DecimalFormat("0").format(event.getFinalDamage())) );
                //player.sendMessage(""+Integer.parseInt(new java.text.DecimalFormat("0").format(event.getFinalDamage())));
            }
        }
    }
}
