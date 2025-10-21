package scriptservice.uhc.worldcreator.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import scriptservice.uhc.worldcreator.Main;

public class wc implements CommandExecutor {
    private final Main main;
    public wc(Main main) {this.main = main;}

    //---- color strings ----//
    private final String _gray = "§7";
    private final String _dgray = "§8";
    private final String _green = "§a";

    private final String _bold = "§l";
    //---- color strings ----//

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(new String[]{
                    main.chatPrefix+_bold+"Sous-commandes "+_dgray+"("+_gray+"/wc ..."+_dgray+")",
                    _dgray+"> "+_green+"create",
                    _dgray+"> "+_green+"teleport",
                    _dgray+"> "+_green+"menu"
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
