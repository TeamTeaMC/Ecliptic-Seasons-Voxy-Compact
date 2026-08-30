package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy;

import com.teamtea.eclipticseasons.common.mixin.condition.ConditionalMixin;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.client.VoxyClientTool;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.helper.IVoxyModelController;
import me.cortex.voxy.client.core.model.bakery.ReuseVertexConsumer;
import me.cortex.voxy.client.core.model.bakery.SoftwareModelTextureBakery;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SoftwareModelTextureBakery.class})
@ConditionalMixin(value = "voxy", version = "[0.2.16-beta]")
public abstract class MixinModelTextureBakery_Roxy implements IVoxyModelController {

    @Shadow(remap = false)
    @Final
    private ReuseVertexConsumer vc;

    @Inject(
            remap = false,
            method = "bakeBlockModel",
            at = @At(value = "TAIL")
    )
    private void eclipticseasons$bakeBlockModel_pre(BlockState state, RenderType layer, CallbackInfo ci) {
        if (isSnowyBlock())
            VoxyClientTool.renderToStream(state, layer, vc, vc);
    }


    @Unique
    boolean eclipticseasons$snowyBlock = false;

    @Override
    public void setSnowyBlock(boolean snowyBlock) {
        this.eclipticseasons$snowyBlock = snowyBlock;
    }

    @Override
    public boolean isSnowyBlock() {
        return eclipticseasons$snowyBlock;
    }
}
