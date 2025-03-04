package net.danh.mmocheck;

import net.danh.mmocheck.command.MMC_CMD;
import net.danh.mmocheck.papi.Placeholder;
import org.bukkit.plugin.java.JavaPlugin;

public final class MMOCheck extends JavaPlugin {

    private static MMOCheck mmoCheck;

    public static MMOCheck getMMOCheck() {
        return mmoCheck;
    }

    @Override
    public void onEnable() {
        mmoCheck = this;
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new MMC_CMD();
            new Placeholder().register();
        } else getServer().getPluginManager().disablePlugin(this);
    }

    @Override
    public void onDisable() {
    }
}
