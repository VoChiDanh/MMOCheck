package net.danh.mmocheck.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.mmocheck.MMOCheck;
import net.danh.mmocheck.utils.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "mmocheck";
    }

    @Override
    public @NotNull String getAuthor() {
        return MMOCheck.getMMOCheck().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return MMOCheck.getMMOCheck().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player p, @NotNull String args) {
        if (args.startsWith("amount_")) {
            String itemData = args.substring(7);
            String type = itemData.split(";")[0];
            String id = itemData.split(";")[1];
            return String.valueOf(Items.getPlayerAmount(p, type, id));
        } else if (args.startsWith("check_id_start_")) {
            ItemStack itemStack = p.getInventory().getItemInMainHand();
            if (!itemStack.getType().equals(Material.AIR)) {
                String itemData = args.substring(15);
                String type = itemData.split(";")[0];
                String id = itemData.split(";")[1];
                return String.valueOf(Items.checkItemIDStart(itemStack, type, id));
            }
        } else if (args.startsWith("check_id_end_")) {
            ItemStack itemStack = p.getInventory().getItemInMainHand();
            if (!itemStack.getType().equals(Material.AIR)) {
                String itemData = args.substring(13);
                String type = itemData.split(";")[0];
                String id = itemData.split(";")[1];
                return String.valueOf(Items.checkItemIDEnd(itemStack, type, id));
            }
        } else if (args.startsWith("check_id_contain_")) {
            ItemStack itemStack = p.getInventory().getItemInMainHand();
            if (!itemStack.getType().equals(Material.AIR)) {
                String itemData = args.substring(17);
                String type = itemData.split(";")[0];
                String id = itemData.split(";")[1];
                return String.valueOf(Items.checkItemIDContain(itemStack, type, id));
            }
        } else if (args.startsWith("check_id_")) {
            ItemStack itemStack = p.getInventory().getItemInMainHand();
            if (!itemStack.getType().equals(Material.AIR)) {
                String itemData = args.substring(9);
                String type = itemData.split(";")[0];
                String id = itemData.split(";")[1];
                return String.valueOf(Items.checkItemID(itemStack, type, id));
            }
        }
        return null;
    }
}
