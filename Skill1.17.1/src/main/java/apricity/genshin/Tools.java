package apricity.genshin;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Tools {

    public static String decode(String s){
        int l=s.length(),r=-1;
        for(int i=0;i<s.length();){
            if(s.charAt(i)=='§'||s.charAt(i)=='%'){
                i+=2;
            }else{
                l=Math.min(l,i);
                r=Math.max(r,i);
                i++;
            }
        }
        return s.substring(l,r+1);
    }

    public static double Damage_Calculator(Player player, Entity entity){
        double ATK = 0;
        double CRIT_Rate = 5;
        double CRIT_Damage = 50;
        double damage=0;
        ItemStack[] armor = player.getEquipment().getArmorContents();
        ItemStack main_hand = player.getEquipment().getItemInMainHand();
        ItemStack off_hand = player.getEquipment().getItemInOffHand();
        if (main_hand == null || !main_hand.hasItemMeta() || !main_hand.getItemMeta().hasLore()) {
        }else {
            for (String s : main_hand.getItemMeta().getLore()) {
                decode(s);
                String v[] = s.split(" ");

                if (v[0].contains("ATK")) {
                    ATK = ATK + Double.parseDouble(v[1]);
                } else if (v[0].contains("CRIT_Rate")) {
                    CRIT_Rate += Double.valueOf(v[1]);
                } else if (v[0].contains("CRIT_Damage")) {
                    CRIT_Damage += Double.valueOf(v[1]);
                }

            }
        }
        if (off_hand == null || !off_hand.hasItemMeta() || !off_hand.getItemMeta().hasLore()) {
        }else {
            for (String s : off_hand.getItemMeta().getLore()) {
                decode(s);
                String v[] = s.split(" ");

                if (v[0].contains("ATK")) {
                    ATK = ATK + Double.parseDouble(v[1]);
                } else if (v[0].contains("CRIT_Rate")) {
                    CRIT_Rate += Double.valueOf(v[1]);
                } else if (v[0].contains("CRIT_Damage")) {
                    CRIT_Damage += Double.valueOf(v[1]);
                }

            }
        }
        for(ItemStack is:armor){
            if (is == null || !is.hasItemMeta() || !is.getItemMeta().hasLore()) {
            }else {
                for (String s : is.getItemMeta().getLore()) {
                    decode(s);
                    String v[] = s.split(" ");

                    if (v[0].contains("ATK")) {
                        ATK = ATK + Double.parseDouble(v[1]);
                    } else if (v[0].contains("CRIT_Rate")) {
                        CRIT_Rate += Double.valueOf(v[1]);
                    } else if (v[0].contains("CRIT_Damage")) {
                        CRIT_Damage += Double.valueOf(v[1]);
                    }

                }
            }
        }
        Random r = new Random();
        double i = r.nextInt(1000)/10;

        if(CRIT_Rate>=i){
            damage = ATK*(1+CRIT_Damage/100);
        }else{
            damage = ATK;
        }
        return damage;
    }

    public static ItemStack createArtifact(ItemStack is,String string) {
        is=is.clone();
        List<String> t= new ArrayList<>();;
        if(is!=null && is.getType()!=Material.AIR && is.getAmount()!=0){
            ItemMeta im = is.getItemMeta();
            if ( !is.getItemMeta().hasLore()) {
            }else {
                t = im.getLore();
            }
            if(string.equals("ATK")) {
                t.add(RateData.getRandomATK());
            } else if(string.equals("CRIT_Rate")){
                t.add(RateData.getRandomCRIT_Rate());
            }else if(string.equals("CRIT_Damage")) {
                t.add(RateData.getRandomCRIT_Damage());
            }else if(string.equals("clear")) {
                t.clear();
            }
            im.setLore(t);
            is.setItemMeta(im);
        }else{
            is=new ItemStack(Material.AIR);
        }
        return is;
    }

    public static ItemStack rewriteLore(ItemStack is,String string1,String string2){
        is=is.clone();
        List<String> t;
        if(is!=null && is.getType()!=Material.AIR && is.getAmount()!=0){
            ItemMeta im = is.getItemMeta();
            if ( !is.getItemMeta().hasLore()) {
                t= new ArrayList<>();
            }else {
                t = im.getLore();
            }
            t.add(string1+" "+string2);
            im.setLore(t);
            is.setItemMeta(im);
        }else{
            is=new ItemStack(Material.AIR);
        }
        return is;
    }

    public static void sleep(int i){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
