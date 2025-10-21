package scriptservice.uhc.worldcreator.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import scriptservice.uhc.worldcreator.Main;
import scriptservice.uhc.worldcreator.enums.settings;

import java.util.Arrays;

public class playerUtils {
    private final Main main;
    public playerUtils(Main main) {this.main = main;}

    //---- color strings ----//
    private final String _red = "§c";
    private final String _white = "§f";

    private final String _bold = "§l";
    private final String _reset = "§r";
    //---- color strings ----//

    //---- fonctions ----//
    public boolean isOwner(Player player) {
        return (player.getUniqueId().toString().equals("0fc289a2-8dda-429a-b727-7f1e9811d747"));
    }

    public void sendMessage(Player player, String string, boolean isDebug) {
        if (isDebug) {
            player.sendMessage(main.chatPrefix_debug+string);
        } else {
            player.sendMessage(main.chatPrefix+string);
        }

    }

    public void openMenu(Player player) {
        // nouveau inventaire
        Inventory menu = Bukkit.createInventory(player, 27, main.worldMenuName);

        // les items selectable
        ItemStack iCaveBoost = new ItemStack(Material.STONE);
        ItemStack iGoldBoost = new ItemStack(Material.GOLD_ORE);
        ItemStack iDiamondBoost = new ItemStack(Material.DIAMOND_ORE);

        ItemStack iBaseSize = new ItemStack(Material.BEDROCK);
        ItemStack iStretchY = new ItemStack(Material.GLASS);
        ItemStack iBarrierSize = new ItemStack(Material.BARRIER);

        ItemStack iLaunchGeneration = new ItemStack(Material.EXP_BOTTLE);
        ItemStack iTpMap = new ItemStack(Material.EYE_OF_ENDER);

        // lores des items
        ItemMeta iCaveBoostMeta = iCaveBoost.getItemMeta();
        iCaveBoostMeta.setDisplayName(String.format(settings.CaveBoost.getName(), settings.CaveBoost.getValue()));
        iCaveBoostMeta.setLore(Arrays.asList((_white+"Valeur "+_bold+_red+"haute"+_white+": "+_red+"Plus"+_white+" de caves.")));
        iCaveBoost.setItemMeta(iCaveBoostMeta);

        ItemMeta iGoldBoostMeta = iGoldBoost.getItemMeta();
        iGoldBoostMeta.setDisplayName(String.format(settings.GoldBoost.getName(), settings.GoldBoost.getValue()));
        iGoldBoostMeta.setLore(Arrays.asList((_white+"Valeur "+_bold+_red+"haute"+_white+": "+_red+"Plus"+_white+" d'or dans les caves.")));
        iGoldBoost.setItemMeta(iGoldBoostMeta);

        ItemMeta iDiamondBoostMeta = iDiamondBoost.getItemMeta();
        iDiamondBoostMeta.setDisplayName(String.format(settings.DiamondBoost.getName(), settings.DiamondBoost.getValue()));
        iDiamondBoostMeta.setLore(Arrays.asList((_white+"Valeur "+_bold+_red+"haute"+_white+": "+_red+"Plus"+_white+" de diamands dans les caves.")));
        iDiamondBoost.setItemMeta(iDiamondBoostMeta);

        ItemMeta iStretchYMeta = iStretchY.getItemMeta();
        iStretchYMeta.setDisplayName(String.format(settings.StretchY.getName(), settings.StretchY.getValue()));
        iStretchYMeta.setLore(Arrays.asList((_white + "Etirement en hauteur."), (_white+"Valeur "+_bold+_red+"faible"+_white+": Terrain étiré verticalement.")));
        iStretchY.setItemMeta(iStretchYMeta);

        ItemMeta iBaseSizeMeta = iBaseSize.getItemMeta();
        iBaseSizeMeta.setDisplayName(String.format(settings.BaseSize.getName(), settings.BaseSize.getValue()));
        iBaseSizeMeta.setLore(Arrays.asList((_white + "Coefficient d'amplification des reliefs."), (_white+"Valeur "+_bold+_red+"grande"+_white+": Reliefs "+_red+"grand"+_white+".")));
        iBaseSize.setItemMeta(iBaseSizeMeta);

        ItemMeta iBarrierMeta = iBarrierSize.getItemMeta();
        iBarrierMeta.setDisplayName(String.format(settings.WorldBorder.getName(), settings.WorldBorder.getValue()));
        iBarrierMeta.setLore(Arrays.asList((_white+"Valeur "+_bold+_red+"grande"+_white+": Bordure "+_red+"plus"+_white+" loins.")));
        iBarrierSize.setItemMeta(iBarrierMeta);

        ItemMeta iLaunchGenerationMeta = iLaunchGeneration.getItemMeta();
        iLaunchGenerationMeta.setDisplayName(_reset+"Lancer la génération de la map.");
        iLaunchGeneration.setItemMeta(iLaunchGenerationMeta);

        ItemMeta iTpMapMeta = iTpMap.getItemMeta();
        iTpMapMeta.setDisplayName(_reset+"Téléporter à la map.");
        iTpMap.setItemMeta(iTpMapMeta);

        // on place les items
        menu.setItem(3, iCaveBoost);
        menu.setItem(4, iGoldBoost);
        menu.setItem(5, iDiamondBoost);

        menu.setItem(12, iStretchY);
        menu.setItem(13, iBaseSize);
        menu.setItem(14, iBarrierSize);

        menu.setItem(25, iTpMap);
        menu.setItem(26, iLaunchGeneration);


        // on ouvre l'inventaire
        player.openInventory(menu);
    }
    //---- fonctions ----//
}
