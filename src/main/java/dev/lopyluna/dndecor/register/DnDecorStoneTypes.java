package dev.lopyluna.dndecor.register;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.lopyluna.dndecor.DnDecor;
import dev.lopyluna.dndecor.register.helpers.wood_types.BlockPattern;
import dev.lopyluna.dndecor.register.helpers.wood_types.VariantEntry;
import net.createmod.catnip.lang.Lang;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

import static dev.lopyluna.dndecor.register.helpers.wood_types.BlockPattern.STANDARD_RANGE;
import static dev.lopyluna.dndecor.register.helpers.wood_types.BlockPattern.VANILLA_RANGE;

public enum DnDecorStoneTypes {
    STONE(VANILLA_RANGE, r -> () -> Blocks.STONE),
    PACKED_MUD(VANILLA_RANGE, r -> () -> Blocks.PACKED_MUD),
    AMETHYST_BLOCK(VANILLA_RANGE, r -> () -> Blocks.AMETHYST_BLOCK),
    NETHERRACK(VANILLA_RANGE, r -> () -> Blocks.NETHERRACK),
    BASALT(VANILLA_RANGE, r -> () -> Blocks.BASALT),
    BLACKSTONE(VANILLA_RANGE, r -> () -> Blocks.BLACKSTONE),

    WEATHERED_LIMESTONE(STANDARD_RANGE, r -> r.paletteStoneBlock("weathered_limestone", () -> Blocks.SANDSTONE, true, false)
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY))
            .register()),

    GABBRO(STANDARD_RANGE, r -> r.paletteStoneBlock("gabbro", () -> Blocks.POLISHED_DEEPSLATE, true, false)
            .properties(p -> p.destroyTime(1.25f).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY))
            .recipe((c, p) -> p.smelting(DataIngredient.ingredient(Ingredient.of(Items.GRANITE), Items.GRANITE), RecipeCategory.BUILDING_BLOCKS, c, 0.1f))
            .register()),

    DOLOMITE(STANDARD_RANGE, r -> r.paletteStoneBlock("dolomite", () -> Blocks.TUFF, true, false)
            .properties(p -> p.destroyTime(1.25f).mapColor(MapColor.TERRACOTTA_WHITE))
            .recipe((c, p) -> p.smelting(DataIngredient.ingredient(Ingredient.of(Items.DIORITE), Items.DIORITE), RecipeCategory.BUILDING_BLOCKS, c, 0.1f))
            .register()),

    ;

    private final Function<CreateRegistrate, NonNullSupplier<Block>> factory;
    private VariantEntry variants;

    public NonNullSupplier<Block> baseBlock;
    public final BlockPattern[] variantTypes;
    public TagKey<Item> materialTag;

    DnDecorStoneTypes(BlockPattern[] variantTypes, Function<CreateRegistrate, NonNullSupplier<Block>> factory) {
        this.factory = factory;
        this.variantTypes = variantTypes;
    }
    public VariantEntry getVariants() {
        return variants;
    }

    public static void register(CreateRegistrate registrate) {
        for (DnDecorStoneTypes paletteStoneVariants : values()) {
            paletteStoneVariants.baseBlock = paletteStoneVariants.factory.apply(registrate);
            String id = Lang.asId(paletteStoneVariants.name()).replace("_block", "");
            paletteStoneVariants.materialTag = DnDecorTags.optionalTag(BuiltInRegistries.ITEM, DnDecor.asResource("stone_types/" + id));
            paletteStoneVariants.variants = new VariantEntry(id, paletteStoneVariants);
        }
    }
}
