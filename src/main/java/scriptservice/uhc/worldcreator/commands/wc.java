package scriptservice.uhc.worldcreator.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import scriptservice.uhc.worldcreator.Main;

public class wc implements CommandExecutor {
    private final Main main;
    public wc(Main main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(new String[]{
                    main.chatPrefix+ ChatColor.BOLD+"Sous-commandes "+ChatColor.DARK_GRAY+"("+ChatColor.GRAY+"/wc ..."+ChatColor.DARK_GRAY+")",
                    ChatColor.DARK_GRAY+"> "+ChatColor.GREEN+"create",
                    ChatColor.DARK_GRAY+"> "+ChatColor.GREEN+"teleport",
                    ChatColor.DARK_GRAY+"> "+ChatColor.GREEN+"menu"
            });
        } else {
            String sousCommande = args[0];

            if (sousCommande.equalsIgnoreCase("create") || sousCommande.equalsIgnoreCase("cr") || sousCommande.equalsIgnoreCase("c")) {
                main.mapUtils.createWorld(player);
            } else if (sousCommande.equalsIgnoreCase("teleport") || sousCommande.equalsIgnoreCase("tp") || sousCommande.equalsIgnoreCase("t")) {
                main.mapUtils.teleportWorld(player);
            } else if (sousCommande.equalsIgnoreCase("menu") || sousCommande.equalsIgnoreCase("m")) {
                main.playerUtils.openMenu(player);
            }
        }

        return true;
    }
}
