package apricity.genshin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemsPlayers {
    public static void safeGiveItem(Player p, ItemStack is) {

        if (p == null || is == null) {
            return;
        }

        is = is.clone();
        if (p.getInventory().firstEmpty() == -1 && is.getMaxStackSize() == 1) { // 背包满了
            ItemsPlayers.safeDropItem(p, is);
            return;
        }
        int allowCount = 0;
        for (ItemStack sInvItem : p.getInventory().getContents()) {
            if (sInvItem != null && sInvItem.getType() != Material.AIR) {
                if (sInvItem.isSimilar(is)) {
                    allowCount += sInvItem.getMaxStackSize() - sInvItem.getAmount();
                }
            } else {
                allowCount += is.getMaxStackSize();
            }
            if (allowCount >= is.getAmount()) {
                break;
            }
        }
        if (allowCount < is.getAmount()) {
            ItemStack dropItems = is.clone();
            dropItems.setAmount(is.getAmount() - allowCount);
            is.setAmount(allowCount);
            ItemsPlayers.safeDropItem(p, dropItems);
        }
        if (is.getMaxStackSize() != 0) {
            for (int i = 0; i < is.getAmount() / is.getMaxStackSize(); i++) {
                ItemStack giveItem = is.clone();
                giveItem.setAmount(giveItem.getMaxStackSize());
                p.getInventory().addItem(giveItem);
            }
        }
        if (is.getMaxStackSize() > 1) {
            int leftItemCount = is.getAmount() % is.getMaxStackSize();
            if (leftItemCount != 0) {
                ItemStack giveItem = is.clone();
                giveItem.setAmount(leftItemCount);
                p.getInventory().addItem(giveItem);
            }
        }
        p.updateInventory();
    }

    public static void safeDropItem(Player p, ItemStack is) {
        if (is == null) {
            return;
        }
        if (is.getAmount() > 64) {
            do {
                ItemStack s = is.clone();
                s.setAmount(64);
                p.getWorld().dropItem(p.getLocation(), s);
                is.setAmount(is.getAmount() - 64);
            } while (is.getAmount() <= 64);
            p.getWorld().dropItem(p.getLocation(), is);
            return;
        }
        p.getWorld().dropItem(p.getLocation(), is);
    }
}
