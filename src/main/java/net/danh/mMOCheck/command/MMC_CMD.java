package net.danh.mMOCheck.command;

import net.Indyuce.mmoitems.MMOItems;
import net.danh.mMOCheck.utils.CMDBase;
import net.danh.mMOCheck.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MMC_CMD extends CMDBase {
    public MMC_CMD() {
        super("mmocheck");
    }

    @Override
    public void execute(CommandSender c, String[] args) {
        if (c.hasPermission("mmocheck.admin")) {
            if (args.length == 5) {
                if (args[0].equalsIgnoreCase("remove")) {
                    String type = args[1];
                    String id = args[2];
                    Player p = Bukkit.getPlayer(args[3]);
                    int amount = Items.getInteger(args[4]);
                    if (p != null && amount > 0) {
                        Items.removeCheckItems(p, type, id, amount);
                    }
                }
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("mmocheck.admin")) {
                commands.add("remove");
            }
            StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if (args.length == 2) {
            if (sender.hasPermission("mmocheck.admin")) {
                if (args[0].equalsIgnoreCase("remove")) {
                    commands.addAll(MMOItems.plugin.getTypes().getAllTypeNames());
                }
            }
            StringUtil.copyPartialMatches(args[1], commands, completions);
        } else if (args.length == 3) {
            if (sender.hasPermission("mmocheck.admin")) {
                if (args[0].equalsIgnoreCase("remove")) {
                    if (MMOItems.plugin.getTypes().has(args[1])) {
                        commands.add("?");
                        commands.add("Type your item id instead of suggestion because of I don't know how to get all item id in type :skull:");
                    }
                }
            }
            StringUtil.copyPartialMatches(args[2], commands, completions);
        } else if (args.length == 4) {
            if (sender.hasPermission("mmocheck.admin")) {
                if (args[0].equalsIgnoreCase("remove")) {
                    if (MMOItems.plugin.getTypes().has(args[1])) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            commands.add(p.getName());
                        }
                    }
                }
            }
            StringUtil.copyPartialMatches(args[3], commands, completions);
        } else if (args.length == 5) {
            if (sender.hasPermission("mmocheck.admin")) {
                if (args[0].equalsIgnoreCase("remove")) {
                    if (MMOItems.plugin.getTypes().has(args[1])) {
                        for (int i = 1; i <= 10; i++) {
                            commands.add(String.valueOf(i));
                        }
                    }
                }
            }
            StringUtil.copyPartialMatches(args[4], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
