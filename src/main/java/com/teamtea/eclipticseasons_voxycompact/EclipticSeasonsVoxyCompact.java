package com.teamtea.eclipticseasons_voxycompact;


import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import com.teamtea.eclipticseasons_voxycompact.config.ESVoxyCompactConfig;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EclipticSeasonsVoxyCompact.MODID)
public class EclipticSeasonsVoxyCompact {
    public static final String MODID = "eclipticseasons_voxycompact";

    public EclipticSeasonsVoxyCompact(IEventBus modEventBus, ModContainer modContainer) {
        CompatModule.init();

        modContainer.registerConfig(ModConfig.Type.COMMON, ESVoxyCompactConfig.COMMON_CONFIG);

        if (FMLLoader.getDist() == Dist.CLIENT)
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

    }


}
