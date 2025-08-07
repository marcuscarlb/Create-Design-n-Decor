package com.mangomilk.design_decor.registry;

import com.mangomilk.design_decor.DesignDecor;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import com.simibubi.create.foundation.utility.Couple;

@SuppressWarnings({"unused"})
public class CDDSpriteShifts {
    public static final Couple<CTSpriteShiftEntry>
            RED_CONTAINER_TOP = vault("red","top"),
            RED_CONTAINER_FRONT = vault("red","front"),
            RED_CONTAINER_SIDE = vault("red","side"),
            RED_CONTAINER_BOTTOM = vault("red","bottom");
    public static final Couple<CTSpriteShiftEntry>
            BLUE_CONTAINER_TOP = vault("blue","top"),
            BLUE_CONTAINER_FRONT = vault("blue","front"),
            BLUE_CONTAINER_SIDE = vault("blue","side"),
            BLUE_CONTAINER_BOTTOM = vault("blue","bottom");
    public static final Couple<CTSpriteShiftEntry>
            GREEN_CONTAINER_TOP = vault("green","top"),
            GREEN_CONTAINER_FRONT = vault("green","front"),
            GREEN_CONTAINER_SIDE = vault("green","side"),
            GREEN_CONTAINER_BOTTOM = vault("green","bottom");
	public static final Couple<CTSpriteShiftEntry>
			BLACK_CONTAINER_TOP = vault("black","top"),
			BLACK_CONTAINER_FRONT = vault("black","front"),
			BLACK_CONTAINER_SIDE = vault("black","side"),
			BLACK_CONTAINER_BOTTOM = vault("black","bottom");
	public static final Couple<CTSpriteShiftEntry>
			BROWN_CONTAINER_TOP = vault("brown","top"),
			BROWN_CONTAINER_FRONT = vault("brown","front"),
			BROWN_CONTAINER_SIDE = vault("brown","side"),
			BROWN_CONTAINER_BOTTOM = vault("brown","bottom");
	public static final Couple<CTSpriteShiftEntry>
			CYAN_CONTAINER_TOP = vault("cyan","top"),
			CYAN_CONTAINER_FRONT = vault("cyan","front"),
			CYAN_CONTAINER_SIDE = vault("cyan","side"),
			CYAN_CONTAINER_BOTTOM = vault("cyan","bottom");
	public static final Couple<CTSpriteShiftEntry>
			GRAY_CONTAINER_TOP = vault("gray","top"),
			GRAY_CONTAINER_FRONT = vault("gray","front"),
			GRAY_CONTAINER_SIDE = vault("gray","side"),
			GRAY_CONTAINER_BOTTOM = vault("gray","bottom");
	public static final Couple<CTSpriteShiftEntry>
			LIGHT_BLUE_CONTAINER_TOP = vault("light_blue","top"),
			LIGHT_BLUE_CONTAINER_FRONT = vault("light_blue","front"),
			LIGHT_BLUE_CONTAINER_SIDE = vault("light_blue","side"),
			LIGHT_BLUE_CONTAINER_BOTTOM = vault("light_blue","bottom");
	public static final Couple<CTSpriteShiftEntry>
			LIGHT_GRAY_CONTAINER_TOP = vault("light_gray","top"),
			LIGHT_GRAY_CONTAINER_FRONT = vault("light_gray","front"),
			LIGHT_GRAY_CONTAINER_SIDE = vault("light_gray","side"),
			LIGHT_GRAY_CONTAINER_BOTTOM = vault("light_gray","bottom");
	public static final Couple<CTSpriteShiftEntry>
			LIME_CONTAINER_TOP = vault("lime","top"),
			LIME_CONTAINER_FRONT = vault("lime","front"),
			LIME_CONTAINER_SIDE = vault("lime","side"),
			LIME_CONTAINER_BOTTOM = vault("lime","bottom");
	public static final Couple<CTSpriteShiftEntry>
			MAGENTA_CONTAINER_TOP = vault("magenta","top"),
			MAGENTA_CONTAINER_FRONT = vault("magenta","front"),
			MAGENTA_CONTAINER_SIDE = vault("magenta","side"),
			MAGENTA_CONTAINER_BOTTOM = vault("magenta","bottom");
	public static final Couple<CTSpriteShiftEntry>
			ORANGE_CONTAINER_TOP = vault("orange","top"),
			ORANGE_CONTAINER_FRONT = vault("orange","front"),
			ORANGE_CONTAINER_SIDE = vault("orange","side"),
			ORANGE_CONTAINER_BOTTOM = vault("orange","bottom");
	public static final Couple<CTSpriteShiftEntry>
			PINK_CONTAINER_TOP = vault("pink","top"),
			PINK_CONTAINER_FRONT = vault("pink","front"),
			PINK_CONTAINER_SIDE = vault("pink","side"),
			PINK_CONTAINER_BOTTOM = vault("pink","bottom");
	public static final Couple<CTSpriteShiftEntry>
			PURPLE_CONTAINER_TOP = vault("purple","top"),
			PURPLE_CONTAINER_FRONT = vault("purple","front"),
			PURPLE_CONTAINER_SIDE = vault("purple","side"),
			PURPLE_CONTAINER_BOTTOM = vault("purple","bottom");
	public static final Couple<CTSpriteShiftEntry>
			WHITE_CONTAINER_TOP = vault("white","top"),
			WHITE_CONTAINER_FRONT = vault("white","front"),
			WHITE_CONTAINER_SIDE = vault("white","side"),
			WHITE_CONTAINER_BOTTOM = vault("white","bottom");
	public static final Couple<CTSpriteShiftEntry>
			YELLOW_CONTAINER_TOP = vault("yellow","top"),
			YELLOW_CONTAINER_FRONT = vault("yellow","front"),
			YELLOW_CONTAINER_SIDE = vault("yellow","side"),
			YELLOW_CONTAINER_BOTTOM = vault("yellow","bottom");


    public static final CTSpriteShiftEntry
            ORNATE_GRATE = omni("ornate_grate"),
            INDUSTRIAL_PLATING_BLOCK = omni("industrial_plating_block"),
            INDUSTRIAL_PLATING_BLOCK_SIDE = omni("industrial_plating_block_side"),
            TINTED_FRAMED_GLASS = omni("palettes/tinted_framed_glass"),
            TINTED_HORIZONTAL_FRAMED_GLASS = omni("palettes/horizontal_tinted_framed_glass"),
            TINTED_VERTICAL_FRAMED_GLASS = omni("palettes/vertical_tinted_framed_glass"),
            STONE_TILES = omni("stone_tiles"),
            RED_STONE_TILES = omni("red_stone_tiles"),

            ZINC_CHECKER_TILES = omni("zinc_checker_tiles")

                    ;

    public static final CTSpriteShiftEntry
            ZINC_CATWALK = omni("zinc_catwalk"),
            COPPER_CATWALK = omni("copper_catwalk"),
            BRASS_CATWALK = omni("brass_catwalk"),
            IRON_CATWALK = omni("iron_catwalk")

    ;

    public static final CTSpriteShiftEntry
            WOOD_SUPPORT = vertical("wood_support_side"),

            DIAGONAL_METAL_SUPPORT = omni("diagonal_metal_support_top"),
            METAL_SUPPORT = vertical("metal_support_side");


    public static final Couple<CTSpriteShiftEntry>
            HORIZONTAL_TANK_FRONT = horizontalTank("front"),
            HORIZONTAL_TANK_SIDE = horizontalTank("side");

    private static Couple<CTSpriteShiftEntry> vault(String color,String name) {
        final String prefixed = "block/"+color+"_container/container_" + name;
        return Couple.createWithContext(
                medium -> CTSpriteShifter.getCT(AllCTTypes.RECTANGLE, DesignDecor.asResource(prefixed + "_small"),
                        DesignDecor.asResource(medium ? prefixed + "_medium" : prefixed + "_large")));
    }

    private static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    private static CTSpriteShiftEntry horizontal(String name) {
        return getCT(AllCTTypes.HORIZONTAL, name);
    }

    private static CTSpriteShiftEntry vertical(String name) {
        return getCT(AllCTTypes.VERTICAL, name);
    }


    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, DesignDecor.asResource("block/" + blockTextureName),
                DesignDecor.asResource("block/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }
    private static Couple<CTSpriteShiftEntry> horizontalTank(String name) {
        final String prefixed = "block/horizontal_tank/tank_" + name;
        return Couple.createWithContext(
                medium -> CTSpriteShifter.getCT(AllCTTypes.RECTANGLE, DesignDecor.asResource(prefixed + "_small"),
                        DesignDecor.asResource(medium ? prefixed + "_medium" : prefixed + "_large")));
    }
    public static void init(){}

}
