package apricity.genshin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Apricity extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new InteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ArrowHitListener(this), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            return false;
        }
        if (args[0].equalsIgnoreCase("create") && sender.isOp()) {
            Player player = Bukkit.getPlayerExact(args[1]);
            if (player == null || !player.hasPlayedBefore() || !player.isOnline()) {
                sender.sendMessage("找不到玩家");
                return true;
            }
            ItemStack is = player.getEquipment().getItemInMainHand();
            is=is.clone();
            player.getEquipment().setItemInMainHand(Tools.createArtifact(is, args[2]));

            return true;
        } else if (args[0].equalsIgnoreCase("rewrite") && sender.isOp()) {
            Player player = Bukkit.getPlayerExact(args[1]);
            if (player == null || !player.hasPlayedBefore() || !player.isOnline()) {
                sender.sendMessage("找不到玩家");
                return true;
            }
            ItemStack is = player.getEquipment().getItemInMainHand();
            is=is.clone();
            player.getEquipment().setItemInMainHand(Tools.rewriteLore(is, args[2], args[3]));

            return true;
        }

        return false;
    }
}
