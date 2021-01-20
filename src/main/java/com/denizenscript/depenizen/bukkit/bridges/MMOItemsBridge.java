package com.denizenscript.depenizen.bukkit.bridges;

import com.denizenscript.denizen.objects.ItemTag;
import com.denizenscript.denizencore.tags.TagManager;
import com.denizenscript.depenizen.bukkit.Bridge;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import org.bukkit.inventory.ItemStack;

public class MMOItemsBridge extends Bridge {
    @Override
    public void init() {
        // <--[tag]
        // @attribute <mmoitem[TYPE].id[ID]>
        // @returns ItemTag
        // @plugin Depenizen, MMOItems
        // @description
        // Returns an ItemTag of the named MMOItem.
        // -->
        TagManager.registerTagHandler("mmoitem", (attribute) -> {
            if (!attribute.hasContext(1) || !attribute.hasContext(2)){
                attribute.echoError("The mmoitem tag must have type and id.");
                return null;
            }
            if (attribute.startsWith("id", 2)) {
                String type = attribute.getContext(1).toUpperCase();
                String id = attribute.getContext(2).toUpperCase();
                attribute.fulfill(1);

                if (!MMOItems.plugin.getTypes().has(type)) {
                    attribute.echoError("Mmoitem type " + type + " does not exist.");
                    return null;
                }
                Type mmoitemtype = MMOItems.plugin.getTypes().get(type);
                if (!MMOItems.plugin.getTemplates().hasTemplate(mmoitemtype, id)) {
                    attribute.echoError("Mmoitem " + id + " does not exist in type " + type + ".");
                    return null;
                }

                ItemStack item = MMOItems.plugin.getItem(mmoitemtype, id);
                return new ItemTag(item);
            }
            return null;
        });
    }
}
