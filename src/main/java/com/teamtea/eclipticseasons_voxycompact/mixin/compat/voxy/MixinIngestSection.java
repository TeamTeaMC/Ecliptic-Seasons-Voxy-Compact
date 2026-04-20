package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy;

import com.teamtea.eclipticseasons_voxycompact.compat.voxy.helper.IVoxyLevelProvider;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.lang.ref.WeakReference;

@Mixin(targets = {"me.cortex.voxy.common.world.service.VoxelIngestService$IngestSection"})
public abstract class MixinIngestSection implements IVoxyLevelProvider {

    @Unique
    WeakReference<Level> es_voxycompact$level = new WeakReference<>(null);

    @Override
    public void setLevelReference(Level levelReference) {
        this.es_voxycompact$level = new WeakReference<>(levelReference);
    }

    @Override
    public WeakReference<Level> getLevelReference() {
        return es_voxycompact$level;
    }
}
