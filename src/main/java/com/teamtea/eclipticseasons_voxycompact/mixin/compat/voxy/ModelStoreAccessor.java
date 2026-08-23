package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy;

import com.teamtea.eclipticseasons.common.mixin.condition.ConditionalMixin;
import me.cortex.voxy.client.core.gl.GlBuffer;
import me.cortex.voxy.client.core.model.ModelStore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ModelStore.class, remap = false)
@ConditionalMixin(value = "voxy", version = "0.2.14-alpha")
public interface ModelStoreAccessor {

    @Accessor("modelColourBuffer")
    GlBuffer eclipticseasons$getModelColourBuffer();
}
