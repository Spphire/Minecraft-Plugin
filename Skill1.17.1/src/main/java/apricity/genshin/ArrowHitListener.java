package apricity.genshin;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ArrowHitListener implements Listener {
    private Apricity p;
    public ArrowHitListener(Apricity apricity) {
        this.p=apricity;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow){
            if(event.getDamage()<8){
                SpawnCarrier.SpawnVisualDigit(p,ChatColor.WHITE,event.getEntity(),Integer.parseInt(new java.text.DecimalFormat("0").format(event.getDamage())) );
                return;
            }
            Player player =(Player) ((Arrow) event.getDamager()).getShooter();
            if(player==null){
                return;
            }
            double damage=Tools.Damage_Calculator(player,event.getEntity());
            event.setDamage(0);
            event.setDamage(damage);
            SpawnCarrier.SpawnVisualDigit(p,ChatColor.BLUE,event.getEntity(),Integer.parseInt(new java.text.DecimalFormat("0").format(damage)) );
            //player.sendMessage(""+Integer.parseInt(new java.text.DecimalFormat("0").format(event.getFinalDamage())));


            if(false){
                return;
            }
            double h=3;
            Location l=event.getEntity().getLocation().clone();
            l.setY(l.getY()+h);

            Entity tempAS= Bukkit.getWorld("world").spawn(
                    event.getEntity().getLocation(),
                    ArmorStand.class,
                    t -> {
                        t.setCustomName("");
                        t.setCustomNameVisible(false);
                        t.setMarker(false);
                        t.setBasePlate(false);
                        t.setCustomNameVisible(true);
                        t.setInvisible(true);
                        t.setSmall(true);
                        t.setSilent(true);
                        //Bukkit.getLogger().info("Spawning.");
                    });

            Bukkit.getScheduler().runTaskLaterAsynchronously(p, new Runnable() {
                //runTaskAsynchronously异步延迟
                @Override
                public void run() {

                    Bukkit.getScheduler().runTask(p, new Runnable() {
                        //runTask强制主线程
                        @Override
                        public void run() {
                            SpawnParticle.spawnConeParticle(p,Particle.BLOCK_CRACK,Material.ICE.createBlockData(),l,h,2.5);
                        }
                    });
                    Tools.sleep(350);
                    Bukkit.getScheduler().runTask(p, new Runnable() {
                        //runTask强制主线程
                        @Override
                        public void run() {
                            List<Entity> list = tempAS.getNearbyEntities(2, 2, 2);
                            tempAS.remove();
                            for(int i=0;i<list.size();i++){
                                if(list.get(i) instanceof Damageable && !(list.get(i) instanceof Player)){
                                    SpawnParticle.spawnPointParticle(Particle.SNOWBALL,list.get(i).getLocation(),5);
                                    //SpawnParticle.spawnLineParticle(Particle.ELECTRIC_SPARK,list.get(i).getLocation(),l);
                                    ((Damageable) list.get(i)).damage(damage);
                                    SpawnCarrier.SpawnVisualDigit(p,ChatColor.BLUE,list.get(i),Integer.parseInt(new java.text.DecimalFormat("0").format(damage)) );
                                }else if(list.get(i) instanceof ArmorStand){
                                    list.get(i).remove();
                                }
                            }


                        }
                    });


                }


            }, 0);











        }
    }
}
