package com.teamtea.eclipticseasons_voxycompact;


import com.teamtea.eclipticseasons_voxycompact.client.VoxyConfigScreenDefinition;
import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import com.teamtea.eclipticseasons_voxycompact.config.ESVoxyCompactConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EclipticSeasonsVoxyCompact.MODID)
public class EclipticSeasonsVoxyCompact {
    public static final String MODID = "eclipticseasons_voxycompact";

    @SuppressWarnings("removal")
    public EclipticSeasonsVoxyCompact() {
        CompatModule.init();
        CompatModule.register(MinecraftForge.EVENT_BUS, FMLJavaModLoadingContext.get().getModEventBus());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ESVoxyCompactConfig.COMMON_CONFIG);

        if (FMLLoader.getDist() == Dist.CLIENT) {
            try {
                Class.forName("com.teamtea.eclipticseasons.compat.eclipticseasons_bundles.client.BundlesScreenDefinition");
                ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(VoxyConfigScreenDefinition.INSTANCE::create));
            } catch (ClassNotFoundException ignored) {
            }
        }
    }


}
