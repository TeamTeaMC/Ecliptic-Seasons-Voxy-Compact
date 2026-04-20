package com.teamtea.eclipticseasons_voxycompact.config;


import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import net.minecraftforge.common.ForgeConfigSpec;

public class ESVoxyCompactConfig {
    public static final ForgeConfigSpec COMMON_CONFIG = new ForgeConfigSpec.Builder().configure(ESVoxyCompactConfig::new).getRight();

    protected ESVoxyCompactConfig(ForgeConfigSpec.Builder builder) {
        CompatModule.CommonConfig.load(builder);
    }

}

