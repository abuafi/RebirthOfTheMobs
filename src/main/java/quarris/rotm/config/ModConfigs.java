package quarris.rotm.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import quarris.rotm.ROTM;

@Config(modid = ROTM.MODID)
public class ModConfigs {

    @Config.Name("Entity Configurations")
    @Config.Comment({
            "These configs involve the changes to entities.",
            "Enable debug mode to get the names of potions, entities and potential damage sources",
            "For the player use \"minecraft:player\""
    })
    public static EntityConfig entityConfigs = new EntityConfig();

    @Config.Name("Vehicle Configurations")
    @Config.Comment({
            "These configs related to the use of entities as vehicles, such as minecarts or boats.",
            "For the player use \"minecraft:player\""
    })
    public static VehicleConfig vehicleConfigs = new VehicleConfig();

    @Config.Name("Miscellaneous Configurations")
    @Config.Comment({
            "These are misc configs.",
            "For the player use \"minecraft:player\""
    })
    public static MiscConfig miscConfigs = new MiscConfig();

    @Config.RequiresMcRestart
    @Config.Name("Debug Configurations")
    @Config.Comment({
            "This mod uses a variety of different aspects of the game which require internal names.",
            "These configs allow you to get those names to use in the configs."
    })
    public static DebugConfig debugConfigs = new DebugConfig();

    public static void updateConfigs() {
        ROTM.logger.info("Updating configs for {}", ROTM.NAME);
        entityConfigs.onConfigChanged();
        debugConfigs.onConfigChanged();
        vehicleConfigs.onConfigChanged();
        miscConfigs.onConfigChanged();
    }

    @Mod.EventBusSubscriber(modid = ROTM.MODID)
    public static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(ROTM.MODID)) {
                ConfigManager.sync(ROTM.MODID, Config.Type.INSTANCE);
                updateConfigs();
            }
        }
    }
}
