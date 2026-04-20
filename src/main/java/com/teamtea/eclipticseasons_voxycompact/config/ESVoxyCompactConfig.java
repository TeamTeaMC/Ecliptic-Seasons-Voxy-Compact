package com.teamtea.eclipticseasons_voxycompact.config;


import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ESVoxyCompactConfig {
    public static final ModConfigSpec COMMON_CONFIG = new ModConfigSpec.Builder().configure(ESVoxyCompactConfig::new).getRight();

    protected ESVoxyCompactConfig(ModConfigSpec.Builder builder) {
        CompatModule.CommonConfig.load(builder);
    }

}

