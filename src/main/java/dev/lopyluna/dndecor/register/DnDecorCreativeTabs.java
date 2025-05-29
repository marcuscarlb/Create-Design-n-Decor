package dev.lopyluna.dndecor.register;

import com.simibubi.create.AllCreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.lopyluna.dndecor.DnDecor.MOD_ID;

@SuppressWarnings("all")
public class DnDecorCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> REG = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BASE_CREATIVE_TAB = REG.register("base", () -> CreativeModeTab.builder()
            .title(DnDecorLangPartial.TITLE)
            .withTabsBefore(AllCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
            .icon(DnDecorBlocks.BRASS_FRONTLIGHT::asStack).build());

    public static void register(IEventBus modEventBus) {
        REG.register(modEventBus);
    }
}
