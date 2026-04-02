package scriptservice.uhc.worldcreator.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class wcTabComplete implements TabCompleter {
    // the first setting (give/settings)
    private static final ArrayList<String> commandsOne = new ArrayList<>(); {
        commandsOne.add("create");
        commandsOne.add("tp");
        commandsOne.add("menu");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], commandsOne, completions);
        }

        return completions;
    }
}
