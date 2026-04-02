package scriptservice.uhc.worldcreator;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import scriptservice.uhc.worldcreator.commands.*;
import scriptservice.uhc.worldcreator.listeners.*;
import scriptservice.uhc.worldcreator.utils.mapUtils;
import scriptservice.uhc.worldcreator.utils.playerUtils;

public final class Main extends JavaPlugin {
    //---- noms des inventaires ----//
    public String worldMenuName = (ChatColor.GOLD+""+ChatColor.BOLD+"Creation du monde");
    //---- noms des inventaires ----//

    //---- prefix pour le chat, etc ----//
    public String chatPrefix = (ChatColor.DARK_GRAY+"["+ChatColor.YELLOW+ChatColor.BOLD+"worldCreator"+ChatColor.DARK_GRAY+"] "+ChatColor.WHITE);
    public String chatPrefix_debug = (ChatColor.DARK_GRAY+"["+ChatColor.RED+ChatColor.BOLD+"worldCreator"+ChatColor.DARK_GRAY+"] "+ChatColor.GRAY);
    //---- prefix pour le chat, etc ----//

    //---- utils ----//
    public mapUtils mapUtils = new mapUtils(this);
    public playerUtils playerUtils = new playerUtils(this);
    //---- utils ----//

    @Override
    public void onEnable() {
        // on register la commande (l'unique)
        getCommand("wc").setExecutor(new wc(this));
        getCommand("wc").setTabCompleter(new wcTabComplete());

        // on register tout les events
        getServer().getPluginManager().registerEvents(new menuListener(this), this);
        getServer().getPluginManager().registerEvents(new chunkLoad(), this);
    }

    @Override
    public void onDisable() {}
}
