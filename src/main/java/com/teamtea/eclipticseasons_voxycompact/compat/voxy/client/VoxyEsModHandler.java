package com.teamtea.eclipticseasons_voxycompact.compat.voxy.client;

import com.teamtea.eclipticseasons_voxycompact.compat.voxy.helper.VoxySeasonalModelRegistry;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class VoxyEsModHandler {

    public static final VoxyEsModHandler INSTANCE = new VoxyEsModHandler();

    @SubscribeEvent
    public void onModelBaked(ModelEvent.ModifyBakingResult event) {
        VoxySeasonalModelRegistry.clear();
    }
}
