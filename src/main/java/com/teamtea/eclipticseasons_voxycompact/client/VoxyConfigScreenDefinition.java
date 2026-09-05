package com.teamtea.eclipticseasons_voxycompact.client;

import com.teamtea.eclipticseasons.client.gui.screen.ConfigScreenContext;
import com.teamtea.eclipticseasons.client.gui.screen.config.ConfigCategory;
import com.teamtea.eclipticseasons.client.gui.screen.config.ConfigScreenDefinition;
import com.teamtea.eclipticseasons.client.gui.screen.config.ConfigScreenText;
import com.teamtea.eclipticseasons.client.gui.screen.config.session.ConfigScreenSession;
import com.teamtea.eclipticseasons.client.gui.screen.config.session.ESConfigScreenSession;
import com.teamtea.eclipticseasons_voxycompact.EclipticSeasonsVoxyCompact;
import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import net.minecraft.network.chat.Component;

public class VoxyConfigScreenDefinition implements ConfigScreenDefinition {
    public static final VoxyConfigScreenDefinition INSTANCE =
            new VoxyConfigScreenDefinition();

    protected ConfigCategory voxy = ConfigCategory.create(
            EclipticSeasonsVoxyCompact.MODID,
            "VOXY",
            0
    );

    protected ConfigCategory all = ConfigCategory.create(
            EclipticSeasonsVoxyCompact.MODID,
            "ALL",
            1
    );

    @Override
    public String modId() {
        return EclipticSeasonsVoxyCompact.MODID;
    }

    @Override
    public ConfigScreenText text() {
        return new ConfigScreenText(
                Component.translatable(
                        "eclipticseasons_voxycompact.options.title"
                ),
                Component.translatable(
                        "eclipticseasons_voxycompact.options.search"
                ),
                Component.translatable(
                        "eclipticseasons_voxycompact.options.search.no_result"
                ),
                Component.translatable(
                        "eclipticseasons_voxycompact.options.classic_screen"
                )
        );
    }

    @Override
    public void initialize(ConfigScreenContext context) {
        context.registerCategory(voxy);
        // context.registerCategory(all);

        // List<ModConfig> configs = ModConfigs.getModConfigs(modId());
        context.registerConfigs(modId());

        Component general = Component.translatable(
                "eclipticseasons_voxycompact.options.general"
        );

        context.put(
                voxy,
                general,
                CompatModule.CommonConfig.voxyCompatibility,
                CompatModule.CommonConfig.voxyAutoRefresh,
                CompatModule.CommonConfig.voxyRefreshOnSolarTermChange
        );

        // context.addSource(new ModConfigEntrySource(
        //         all,
        //         Component.translatable(
        //                 "eclipticseasons_voxycompact.options.all"
        //         ),
        //         configs
        // ));
    }

    @Override
    public ConfigScreenSession createSession(
            ConfigScreenContext context
    ) {
        return new ESConfigScreenSession(context.configs());
    }
}