package scriptservice.uhc.worldcreator.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import scriptservice.uhc.worldcreator.Main;
import scriptservice.uhc.worldcreator.enums.settings;

public class menuListener implements Listener {
    private final Main main;
    public menuListener(Main main) {this.main = main;}

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {return;} // pas d'inventaire ?
        if (!(event.getWhoClicked() instanceof Player)) {return;} // le click ne viens pas d'un player
        if (event.getCurrentItem() == null) {return;} // l'item clické est null

        final ItemStack item = event.getCurrentItem();
        final Player player = (Player) event.getWhoClicked();
        final InventoryAction action = event.getAction();

        if (event.getClickedInventory().getTitle().equalsIgnoreCase(main.worldMenuName)) {
            //   /!\ Math.min en ++, Math.max en --
            settings usedSetting = null;
            boolean cancel = true;
            String actionToDo = "";

            // obtiens les settings
            switch (item.getType()) {
                case BEDROCK:
                    usedSetting = settings.BaseSize;
                    cancel = false;
                    break;
                case GLASS:
                    usedSetting = settings.StretchY;
                    cancel = false;
                    break;
                case BARRIER:
                    usedSetting = settings.WorldBorder;
                    cancel = false;
                    break;
                case STONE:
                    usedSetting = settings.CaveBoost;
                    cancel = false;
                    break;
                case GOLD_ORE:
                    usedSetting = settings.GoldBoost;
                    cancel = false;
                    break;
                case DIAMOND_ORE:
                    usedSetting = settings.DiamondBoost;
                    cancel = false;
                    break;
                case EXP_BOTTLE:
                    actionToDo = "generateMap";
                    cancel = false;
                    break;
                case EYE_OF_ENDER:
                    actionToDo = "tpMap";
                    cancel = false;
                    break;
                case AIR:
                    event.setCancelled(true);
                    break;
            }

            // si cancel == true
            if (cancel) {return;}

            // on fait
            if (usedSetting != null) {
                // une action sur les settings

                if (main.playerUtils.isOwner(player)) {
                    main.playerUtils.sendMessage(player,"action: " + action.toString(), true);
                }


                switch (action) {
                    case PICKUP_ALL: // on ajoute
                        usedSetting.setValue(Math.min(usedSetting.getMaxValue(), usedSetting.getValue() + usedSetting.getStep()));
                        main.playerUtils.sendMessage(player,usedSetting.getDisplayName() + "+ :: " + usedSetting.getValue(), false);
                        break;

                    case PICKUP_HALF: // on retire
                        usedSetting.setValue(Math.max(usedSetting.getMinValue(), usedSetting.getValue() - usedSetting.getStep()));
                        main.playerUtils.sendMessage(player,usedSetting.getDisplayName() + "- :: " + usedSetting.getValue(), false);
                        break;

                    case CLONE_STACK:
                    case DROP_ALL_SLOT:
                    case DROP_ONE_SLOT: // on reset
                        usedSetting.setValue(usedSetting.getDefaultValue());
                        main.playerUtils.sendMessage(player,usedSetting.getDisplayName() + "/ :: " + usedSetting.getValue(), false);
                        break;

                    case MOVE_TO_OTHER_INVENTORY: // on set au max
                        usedSetting.setValue(usedSetting.getMaxValue());
                        main.playerUtils.sendMessage(player,usedSetting.getDisplayName() + "++ :: " + usedSetting.getValue(), false);
                        break;
                }

                // on modifie l'item
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(String.format(usedSetting.getName(), usedSetting.getValue()));
                item.setItemMeta(itemMeta);
            } else {
                // une action sur qqc d'autre
                switch (actionToDo) {
                    case "tpMap":
                        main.mapUtils.teleportWorld(player);
                        break;
                    case "generateMap":
                        main.mapUtils.createWorld(player);
                        break;
                    default:
                        main.playerUtils.sendMessage(player,"hein ??", true);
                        break;
                }
            }

            event.setCancelled(true); // on cancel l'event
        }
    }
}
