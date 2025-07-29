package dev.lopyluna.dndecor.register.helpers;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static dev.lopyluna.dndecor.DnDecor.REGISTRATE;

@SuppressWarnings({"unused"})
public class ItemTransgender {

    public static ItemEntry<Item> itemEntry(String name) {
        return REGISTRATE.item(name, Item::new).register();
    }

    public static ItemEntry<Item> itemEntry(String name, String lang) {
        return REGISTRATE.item(name, Item::new).lang(lang).register();
    }

    @SafeVarargs
    public static ItemEntry<Item> itemEntryTagged(String name, TagKey<Item>... tags) {
        return REGISTRATE.item(name, Item::new).tag(tags).register();
    }

    @SafeVarargs
    public static ItemEntry<Item> itemEntryTagged(String name, String lang, TagKey<Item>... tags) {
        return REGISTRATE.item(name, Item::new).tag(tags).lang(lang).register();
    }
}
