package com.teamtea.eclipticseasons_voxycompact;


import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import com.teamtea.eclipticseasons_voxycompact.config.ESVoxyCompactConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EclipticSeasonsVoxyCompact.MODID)
public class EclipticSeasonsVoxyCompact {
    public static final String MODID = "eclipticseasons_voxycompact";

    public EclipticSeasonsVoxyCompact() {
        CompatModule.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ESVoxyCompactConfig.COMMON_CONFIG);
    }


}
