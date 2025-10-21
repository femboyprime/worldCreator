package scriptservice.uhc.worldcreator.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import scriptservice.uhc.worldcreator.Main;
import scriptservice.uhc.worldcreator.enums.settings;

import java.io.File;
import java.util.Random;

public class mapUtils {
    private final Main main;
    public mapUtils(Main main) {this.main = main;}

    //---- color strings ----//
    private final String _green = "§a";
    private final String _white = "§f";
    //---- color strings ----//

    public String mapName = "void";

    private String getBase() {
        return "{\"coordinateScale\":684.0" +
                ",\"heightScale\":684.0" +
                ",\"lowerLimitScale\":512.0" +
                ",\"upperLimitScale\":512.0" +
                ",\"depthNoiseScaleX\":" + (600 + settings.CaveBoost.getValue()) +
                ",\"depthNoiseScaleZ\":" + (600 + settings.CaveBoost.getValue()) +

                ",\"depthNoiseScaleExponent\":0.5," +
                "\"mainNoiseScaleX\":5000.0," + // 80
                "\"mainNoiseScaleY\":5000.0," + // 160
                "\"mainNoiseScaleZ\":5000.0," + // 80
                "\"baseSize\":" + (settings.BaseSize.getValue()) + "," +
                "\"stretchY\":" + (settings.StretchY.getValue()) + "," +
                "\"biomeDepthWeight\":0.5," + // 1.0
                "\"biomeDepthOffset\":0.0," +
                "\"biomeScaleWeight\":0.05," + // 1.0
                "\"biomeScaleOffset\":0.0," +

                "\"seaLevel\":-1" +
                ",\"useCaves\":" + true +
                ",\"useDungeons\":" + true +
                ",\"dungeonChance\":" + (10) +
                ",\"useStrongholds\":" + true +
                ",\"useVillages\":" + true +
                ",\"useMineShafts\":" + true +
                ",\"useTemples\":" + false +
                ",\"useMonuments\":" + false +

                ",\"useRavines\":" + true +
                ",\"useWaterLakes\":" + true +
                ",\"waterLakeChance\":8" +
                ",\"useLavaLakes\":" + true +
                ",\"lavaLakeChance\":20" +
                ",\"useLavaOceans\":" + false +
                ",\"fixedBiome\":-1" +
                ",\"biomeSize\":4" +
                ",\"riverSize\":1" +

                ",\"dirtSize\":33," +
                "\"dirtCount\":10," +
                "\"dirtMinHeight\":0," +
                "\"dirtMaxHeight\":256," +
                "\"gravelSize\":33," +
                "\"gravelCount\":8," +
                "\"gravelMinHeight\":0," +
                "\"gravelMaxHeight\":256," +
                "\"graniteSize\":33," +
                "\"graniteCount\":10," +
                "\"graniteMinHeight\":0," +
                "\"graniteMaxHeight\":80," +
                "\"dioriteSize\":33," +
                "\"dioriteCount\":10," +
                "\"dioriteMinHeight\":0," +
                "\"dioriteMaxHeight\":80," +
                "\"andesiteSize\":33," +
                "\"andesiteCount\":10," +
                "\"andesiteMinHeight\":0," +
                "\"andesiteMaxHeight\":80"+

                ",\"coalSize\":"+ 17 +
                ",\"coalCount\":" + 20 +
                ",\"coalMinHeight\":" + 0 +
                ",\"coalMaxHeight\":"+ 128 +
                ",\"ironSize\":" + 9+
                ",\"ironCount\":" + 20 +
                ",\"ironMinHeight\":" + 0 +
                ",\"ironMaxHeight\":" + 64 +
                ",\"goldSize\":" + 10 +
                ",\"goldCount\":" + (3 + settings.GoldBoost.getValue()) +
                ",\"goldMinHeight\":" + 0 +
                ",\"goldMaxHeight\":" + 32 +
                ",\"redstoneSize\":" + 8 +
                ",\"redstoneCount\":" + 20 +
                ",\"redstoneMinHeight\":" + 0 +
                ",\"redstoneMaxHeight\":" + 16+
                ",\"diamondSize\":" + 8 +
                ",\"diamondCount\":" + (1 + settings.DiamondBoost.getValue()) +
                ",\"diamondMinHeight\":" + 0 +
                ",\"diamondMaxHeight\":" + 22 +
                ",\"lapisSize\":" + 7 +
                ",\"lapisCount\":" + 1 +
                ",\"lapisCenterHeight\":" + 16 +
                ",\"lapisSpread\":" + 16 +
                "}";
    }

    private void deleteFile(File worldPath) {
        if (worldPath.exists() && worldPath.isDirectory() && !worldPath.getName().equals("plugins")) {
            return; // bah non mon grand :)
        }

        if (worldPath.exists()) {
            File[] files = worldPath.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFile(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }

        worldPath.delete();
    }

    private void _teleport(boolean loadAttempt, Player player, World world) {
        if (!loadAttempt) {
            // on teleport le player au monde voulu
            player.teleport(new Location(world, 0.5, 125 , 0.5));
            main.playerUtils.sendMessage(player,"Teleportation dans le monde "+_green+"Arena"+_white+".", false);
        } else {
            File worldFolder = new File(Bukkit.getServer().getWorldContainer(), mapName);

            if (worldFolder.exists() && world != null) {
                WorldCreator worldCreator = new WorldCreator(mapName);
                World reworld = worldCreator.createWorld();

                _teleport(false, player, reworld);
            } else {
                main.playerUtils.sendMessage(player,"Le monde n'existe pas.", false);
            }
        }
    }

    public void createWorld(Player player) {
        World oldWorld = Bukkit.getWorld(mapName);

        // on check si il y a déjà un monde qui existe, si oui, on l'unload.
        if (oldWorld != null) {
            for (Player p : oldWorld.getPlayers()) {
                p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            }

            boolean unloadSuccess = Bukkit.unloadWorld(oldWorld, false);

            // si l'unload s'est fait, alors on supprime le folder
            if (unloadSuccess) {
                deleteFile(new File(Bukkit.getServer().getWorldContainer(), mapName));
            } else {
                main.playerUtils.sendMessage(player,"Erreur pendant la suppression du monde.", false);
                return;
            }
        }



        // on crée un nouveau monde
        mapName = "arena " + System.currentTimeMillis();

        WorldCreator worldCreator = new WorldCreator(mapName);
        worldCreator.seed(new Random(System.currentTimeMillis()).nextLong());
        worldCreator.generatorSettings(getBase());
        World newWorld = worldCreator.createWorld();

        if (newWorld == null) {
            main.playerUtils.sendMessage(player,"Erreur pendant la creation du monde.", false);
            return;
        }

        // on fait les gamerules
        newWorld.setTime(6000);
        newWorld.setDifficulty(Difficulty.HARD);
        newWorld.setGameRuleValue("doMobSpawning", "false");
        newWorld.setGameRuleValue("doDaylightCycle", "false");
        newWorld.setGameRuleValue("spectatorsGenerateChunks", "false");
        newWorld.setGameRuleValue("naturalRegeneration", "false");
        newWorld.setGameRuleValue("announceAdvancements", "false");
        newWorld.setGameRuleValue("randomTickSpeed", "3");
        newWorld.setGameRuleValue("doMobSpawning", "false");
        newWorld.setGameRuleValue("doFireTick", "false");

        // le spawn
        newWorld.setSpawnFlags(true, true);
        newWorld.setSpawnLocation(0, newWorld.getHighestBlockYAt(0, 0), 0);

        // la bordure
        WorldBorder worldBorder = newWorld.getWorldBorder();
        worldBorder.setCenter(newWorld.getSpawnLocation()); // le centre de la bordure au (0, 0)
        worldBorder.setSize(settings.WorldBorder.getValue() * 2); // j'dois faire size*2 si j'veux la distance entre 0 et la bordure a size (oui c'est des maths)

        main.playerUtils.sendMessage(player,"Génération du monde "+_green+"Arena"+_white+" terminée avec succes.", false);
    }

    public void teleportWorld(Player player){
        World world = Bukkit.getWorld(mapName);

        // si le monde n'existe pas
        if (world == null) {
            _teleport(true, player, null);
        }

        _teleport(false, player,  world);
    }
}
