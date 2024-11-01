package net.lukapix.magmaticexile;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MagmaticExile.MOD_ID)
public class MagmaticExile {
    public static final String MOD_ID = "magmaticexile";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MagmaticExile() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("[MagmaticExile] Loading..");
    }
}
