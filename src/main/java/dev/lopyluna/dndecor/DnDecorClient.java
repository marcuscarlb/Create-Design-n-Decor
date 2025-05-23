package dev.lopyluna.dndecor;

import dev.lopyluna.dndecor.register.client.DnDecorPartialModels;
import dev.lopyluna.dndecor.register.client.DnDecorSpriteShifts;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

import static dev.lopyluna.dndecor.DnDecor.MOD_ID;

@Mod(value = MOD_ID, dist = Dist.CLIENT)
public class DnDecorClient {

    public DnDecorClient(IEventBus modEventBus) {
        IEventBus neoEventBus = NeoForge.EVENT_BUS;
        modEventBus.addListener(DnDecorClient::clientInit);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        DnDecorPartialModels.init();
        DnDecorSpriteShifts.init();
    }
}
