package dev.lopyluna.dndecor.content.configs;

import net.createmod.catnip.config.ConfigBase;
import org.jetbrains.annotations.NotNull;

public class DClient extends ConfigBase {
    public final ConfigGroup client = group(0,
            "client", "Configs for the Client");

    // custom fluid fog

    @Override
    public @NotNull String getName() {
        return "client";
    }

    private static class Comments {
        static String equipments = "Configure Equipment settings";
        static String invertDeforesterSawFunction = "Invert Deforester Saw activation function";
        static String invertExcavationDrillFunction = "Invert Excavation Drill activation function";
        static String disableBlocksVoidZapperMessage = "Disables the blocks will be voided warning for the Block Zapper";

        static String propagatorDebug = "Debug Rotation Propagator";

        static String ponder = "Ponder settings";
    }
}
