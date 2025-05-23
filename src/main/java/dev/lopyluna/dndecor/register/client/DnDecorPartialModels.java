package dev.lopyluna.dndecor.register.client;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.lopyluna.dndecor.DnDecor;
import net.minecraft.world.item.DyeColor;

import java.util.EnumMap;
import java.util.Map;

public class DnDecorPartialModels {
    public static final PartialModel
            BELT_START = block("belt/start"), BELT_MIDDLE = block("belt/middle"),
            BELT_END = block("belt/end"), BELT_START_BOTTOM = block("belt/start_bottom"),
            BELT_MIDDLE_BOTTOM = block("belt/middle_bottom"), BELT_END_BOTTOM = block("belt/end_bottom"),
            BELT_DIAGONAL_START = block("belt/diagonal_start"), BELT_DIAGONAL_MIDDLE = block("belt/diagonal_middle"),
            BELT_DIAGONAL_END = block("belt/diagonal_end")
    ;

    public static final Map<DyeColor, PartialModel> DYED_FLYWHEELS = new EnumMap<>(DyeColor.class);
    static {
      for (DyeColor color : DyeColor.values()) DYED_FLYWHEELS.put(color, block(color.getSerializedName() + "_flywheel/block"));
    }

    private static PartialModel block(String path) {
        return PartialModel.of(DnDecor.loc("block/" + path));
    }

    public static void init() {}
}
