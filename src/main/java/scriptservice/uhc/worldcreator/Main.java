package scriptservice.uhc.worldcreator;

import org.bukkit.plugin.java.JavaPlugin;

import scriptservice.uhc.worldcreator.commands.*;
import scriptservice.uhc.worldcreator.listeners.*;
import scriptservice.uhc.worldcreator.utils.mapUtils;
import scriptservice.uhc.worldcreator.utils.playerUtils;

public final class Main extends JavaPlugin {
    //---- color strings ----//
    private final String _black = "§0";
    private final String _dblue = "§1";
    private final String _dgreen = "§2";
    private final String _daqua = "§3";
    private final String _dred = "§4";
    private final String _purple = "§5";
    private final String _gold = "§6";
    private final String _gray = "§7";
    private final String _dgray = "§8";
    private final String _blue = "§9";
    private final String _green = "§a";
    private final String _aqua = "§b";
    private final String _red = "§c";
    private final String _pink = "§d";
    private final String _yellow = "§e";
    private final String _white = "§f";

    private final String _random = "§k";
    private final String _bold = "§l";
    private final String _strikethrough = "§m";
    private final String _underline = "§n";
    private final String _italic = "§o";
    private final String _reset = "§r";
    //---- color strings ----//

    //---- noms des inventaires ----//
    public String worldMenuName = (_gold+_bold+"Creation du monde");
    //---- noms des inventaires ----//

    //---- prefix pour le chat, etc ----//
    public String chatPrefix = (_dgray+"["+_yellow+_bold+"worldCreator"+_dgray+"] "+_white);
    public String chatPrefix_debug = (_dgray+"["+_red+_bold+"worldCreator"+_dgray+"] "+_gray);
    //---- prefix pour le chat, etc ----//

    //---- utils ----//
    public mapUtils mapUtils = new mapUtils(this);
    public playerUtils playerUtils = new playerUtils(this);
    //---- utils ----//

    @Override
    public void onEnable() {
        // on register la commande (l'unique)
        getCommand("wc").setExecutor(new wc(this));

        // on register tout les events
        getServer().getPluginManager().registerEvents(new menuListener(this), this);
        getServer().getPluginManager().registerEvents(new chunkLoad(), this);
    }

    @Override
    public void onDisable() {}
}
