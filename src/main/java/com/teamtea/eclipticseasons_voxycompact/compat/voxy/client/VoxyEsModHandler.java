package com.teamtea.eclipticseasons_voxycompact.compat.voxy.client;

import com.teamtea.eclipticseasons_voxycompact.compat.voxy.helper.VoxySeasonalModelRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

public class VoxyEsModHandler {

    public static final VoxyEsModHandler INSTANCE = new VoxyEsModHandler();

    @SubscribeEvent
    public void onModelBaked(ModelEvent.ModifyBakingResult event) {
        VoxySeasonalModelRegistry.clear();
    }
}
