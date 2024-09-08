package me.flyingtaco725.indigorpg.Utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;

public class utils {

    public static ItemStack createClassItem(Material material, String displayName, String weapon, String desc) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(displayName);
            if (!Objects.equals(weapon, "") && !Objects.equals(desc, "")){
                meta.setLore(List.of("Weapon: " + weapon, "Role: " + desc));
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    public static void freezePlayer(Player player) {
        // Define various potion effects
        PotionEffect slowEffect = new PotionEffect(PotionEffectType.SLOWNESS,Integer.MAX_VALUE, 255);
        PotionEffect jumpEffect = new PotionEffect(PotionEffectType.JUMP_BOOST, Integer.MAX_VALUE, 128);
        PotionEffect blindEffect = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 2);
        PotionEffect godEffect = new PotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 255);
        // Apply various potion effect
        player.addPotionEffect(godEffect);
        player.addPotionEffect(blindEffect);
        player.addPotionEffect(jumpEffect);
        player.addPotionEffect(slowEffect);
    }

    public static void unfreezePlayer(Player player) {
        // remove freeze effect
        player.removePotionEffect(PotionEffectType.SLOWNESS);
        player.removePotionEffect(PotionEffectType.JUMP_BOOST);
        player.removePotionEffect(PotionEffectType.BLINDNESS);
        player.removePotionEffect(PotionEffectType.RESISTANCE);
    }

}
