package net.danh.mmocheck.utils;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Items {

    public static boolean checkItems(ItemStack itemStack, String type, String id) {
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem != null)
            return (nbtItem.hasType() && nbtItem.getType().equalsIgnoreCase(type))
                    && (nbtItem.getString("MMOITEMS_ITEM_ID").equalsIgnoreCase(id));
        else return false;
    }

    public static void removeCheckItems(Player p, String type, String id, int amount) {
        if (getPlayerAmount(p, type, id) >= amount) {
            ItemStack itemStack = MMOItems.plugin.getItem(type, id);
            if (itemStack != null) {
                removeItems(p, type, id, amount);
            }
        }
    }

    public static boolean checkItemID(ItemStack itemStack, String type, String id) {
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem != null) {
            return (nbtItem.hasType() && nbtItem.getType().equalsIgnoreCase(type))
                    && (nbtItem.getString("MMOITEMS_ITEM_ID").equalsIgnoreCase(id));
        } else return false;
    }

    public static boolean checkItemIDEnd(ItemStack itemStack, String type, String id) {
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem != null) {
            return (nbtItem.hasType() && nbtItem.getType().equalsIgnoreCase(type))
                    && (nbtItem.getString("MMOITEMS_ITEM_ID").endsWith(id));
        } else return false;
    }

    public static boolean checkItemIDStart(ItemStack itemStack, String type, String id) {
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem != null) {
            return (nbtItem.hasType() && nbtItem.getType().equalsIgnoreCase(type))
                    && (nbtItem.getString("MMOITEMS_ITEM_ID").startsWith(id));
        } else return false;
    }

    public static boolean checkItemIDContain(ItemStack itemStack, String type, String id) {
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem != null) {
            return (nbtItem.hasType() && nbtItem.getType().equalsIgnoreCase(type))
                    && (nbtItem.getString("MMOITEMS_ITEM_ID").contains(id));
        } else return false;
    }

    public static int getPlayerAmount(@NotNull HumanEntity player, String type, String id) {
        final PlayerInventory inv = player.getInventory();
        final ItemStack[] items = inv.getContents();
        int c = 0;
        for (final ItemStack is : items) {
            if (is != null) {
                if (checkItems(is, type, id)) {
                    c += is.getAmount();
                }
            }
        }
        return c;
    }

    public static void removeItems(@NotNull Player player, String type, String id, long amount) {
        final PlayerInventory inv = player.getInventory();
        final ItemStack[] items = inv.getContents();
        int c = 0;
        for (int i = 0; i < items.length; ++i) {
            final ItemStack is = items[i];
            if (is != null) {
                if (checkItems(is, type, id)) {
                    if (c + is.getAmount() > amount) {
                        final long canDelete = amount - c;
                        is.setAmount((int) (is.getAmount() - canDelete));
                        items[i] = is;
                        break;
                    }
                    c += is.getAmount();
                    items[i] = null;
                }
            }
        }
        inv.setContents(items);
        player.updateInventory();
    }

    public static int getRandomInteger(int min, int max) {
        if (max >= min + 2) {
            return ThreadLocalRandom.current().nextInt(min, max);
        } else {
            return min;
        }
    }

    public static int getInteger(String s) {
        try {
            if (!s.contains("-")) {
                return BigDecimal.valueOf(Long.parseLong(s)).intValue();
            } else {
                return getRandomInteger(BigDecimal.valueOf(Long.parseLong(s.split("-")[0])).intValue(), BigDecimal.valueOf(Long.parseLong(s.split("-")[1])).intValue());
            }
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }
}
