package com.teamtea.eclipticseasons_voxycompact.compat.voxy.client;

import com.teamtea.eclipticseasons.api.event.SolarTermChangeEvent;
import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.VoxyTool;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VoxyEsHandler {

    public static final VoxyEsHandler INSTANCE = new VoxyEsHandler();

    @SubscribeEvent
    public void onSolarTermChangeEvent(SolarTermChangeEvent event) {
        if (event.getLevel() != Minecraft.getInstance().level) return;

        // Auto reload consumes termChange/snowChange together every 15 seconds.
        if (CompatModule.CommonConfig.voxyRefreshSeasonalModels.get())
            VoxyGeometryRefreshManager.refreshAll();
        if (CompatModule.CommonConfig.voxyRefreshOnSolarTermChange.get())
            VoxyTintManager.refreshAll();
    }

    @SubscribeEvent
    public void onLoggingOut(ClientPlayerNetworkEvent.LoggingOut event) {
        VoxyTool.clearBiomeCache();
    }
}
