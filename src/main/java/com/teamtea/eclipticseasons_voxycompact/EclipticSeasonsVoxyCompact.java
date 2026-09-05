package com.teamtea.eclipticseasons_voxycompact;


import com.teamtea.eclipticseasons.client.gui.screen.config.ConfigCategory;
import com.teamtea.eclipticseasons.client.gui.screen.config.builtin.ESConfigScreenDefinition;
import com.teamtea.eclipticseasons_voxycompact.client.VoxyConfigScreenDefinition;
import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import com.teamtea.eclipticseasons_voxycompact.config.ESVoxyCompactConfig;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfigs;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EclipticSeasonsVoxyCompact.MODID)
public class EclipticSeasonsVoxyCompact {
    public static final String MODID = "eclipticseasons_voxycompact";

    public EclipticSeasonsVoxyCompact(IEventBus modEventBus, ModContainer modContainer) {
        CompatModule.init();
        CompatModule.register(NeoForge.EVENT_BUS, modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, ESVoxyCompactConfig.COMMON_CONFIG);

        if (FMLLoader.getDist() == Dist.CLIENT) {
            // modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
            // ESConfigScreenDefinition.INSTANCE.registry().registerPlugin(
            //         ResourceLocation.fromNamespaceAndPath(MODID, "config_screen"),
            //         context -> {
            //             List<ModConfig> configs = ModConfigs.getModConfigs(MODID);
            //             context.registerConfigs(configs);
            //             final ConfigCategory VOXY = ConfigCategory.create("eclipticseasons_voxycompact", "VOXY_COMPACT", 6);
            //             context.registerCategory(VOXY);
            //             context.put(
            //                     VOXY,
            //                     Component.translatable("eclipticseasons_voxycompact.options.voxy_compact"),
            //                     CompatModule.CommonConfig.voxyCompatibility,
            //                     CompatModule.CommonConfig.voxyAutoRefresh,
            //                     CompatModule.CommonConfig.voxyRefreshOnSolarTermChange
            //             );
            //         }
            // );
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, VoxyConfigScreenDefinition.INSTANCE::create);
        }

    }


}
