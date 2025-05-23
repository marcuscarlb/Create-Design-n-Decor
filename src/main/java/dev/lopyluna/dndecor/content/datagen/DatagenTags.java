package dev.lopyluna.dndecor.content.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import dev.lopyluna.dndecor.register.DnDecorTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static dev.lopyluna.dndecor.DnDecor.REG;

@SuppressWarnings("deprecation")
public class DatagenTags {
    public static void addGenerators() {
        REG.addDataGenerator(ProviderType.BLOCK_TAGS, DatagenTags::genBlockTags);
        REG.addDataGenerator(ProviderType.ITEM_TAGS, DatagenTags::genItemTags);

    }

    private static void genBlockTags(RegistrateTagsProvider<Block> provIn) {
        TagGen.CreateTagsProvider<Block> prov = new TagGen.CreateTagsProvider<>(provIn, Block::builtInRegistryHolder);


        for (DnDecorTags.BlockTags tag : DnDecorTags.BlockTags.values()) if (tag.alwaysDatagen) prov.getOrCreateRawBuilder(tag.tag);
    }

    private static void genItemTags(RegistrateTagsProvider<Item> provIn) {
        TagGen.CreateTagsProvider<Item> prov = new TagGen.CreateTagsProvider<>(provIn, Item::builtInRegistryHolder);
        prov.tag(DnDecorTags.commonItemTag("create/crushing_wheels")).add(AllBlocks.CRUSHING_WHEEL.asItem());
        prov.tag(DnDecorTags.commonItemTag("create/millstones")).add(AllBlocks.MILLSTONE.asItem());
        prov.tag(DnDecorTags.commonItemTag("create/flywheels")).add(AllBlocks.FLYWHEEL.asItem());
        prov.tag(DnDecorTags.commonItemTag("create/display_boards")).add(AllBlocks.DISPLAY_BOARD.asItem());

        for (DnDecorTags.ItemTags tag : DnDecorTags.ItemTags.values()) if (tag.alwaysDatagen) prov.getOrCreateRawBuilder(tag.tag);
    }
}
