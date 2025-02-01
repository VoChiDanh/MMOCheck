package net.danh.mMOCheck.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.mMOCheck.MMOCheck;
import net.danh.mMOCheck.utils.Items;
import org.bukkit.entity.Player;
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
        }
        return null;
    }
}
