package com.denizenscript.depenizen.bukkit.bridges;

import com.denizenscript.denizen.Denizen;
import com.denizenscript.depenizen.bukkit.commands.bossshop.BossShopCommand;
import com.denizenscript.depenizen.bukkit.properties.bossshop.BossShopInventoryProperties;
import com.denizenscript.depenizen.bukkit.Bridge;
import com.denizenscript.denizen.objects.InventoryTag;
import com.denizenscript.denizencore.objects.properties.PropertyParser;

public class BossShopBridge extends Bridge {

    public static BossShopBridge instance;

    @Override
    public void init() {
        instance = this;
        Denizen.getInstance().getCommandRegistry().registerCommand(BossShopCommand.class);
        PropertyParser.registerProperty(BossShopInventoryProperties.class, InventoryTag.class);
    }
}
