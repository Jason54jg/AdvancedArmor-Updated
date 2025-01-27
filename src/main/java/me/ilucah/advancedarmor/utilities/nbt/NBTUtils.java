package me.ilucah.advancedarmor.utilities.nbt;

import de.tr7zw.nbtapi.*;
import me.ilucah.advancedarmor.handler.Handler;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;

public class NBTUtils {

    private final Handler handler;

    public NBTUtils(Handler handler) {
        this.handler = handler;
    }

    public static boolean hasArmorNBTTag(ItemStack item) {
        if (item == null)
            return false;
        if (item.getType() == Material.AIR)
            return false;
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasKey("CustomArmor"))
            return true;
        return false;
    }

    public static String getArmorName(ItemStack item) {
        return new NBTItem(item).getString("CustomArmor");
    }
}
