package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
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

@Mixin(value = SoftwareModelTextureBakery.class, remap = false)
@ConditionalMixin(value = "voxy", version = "0.2.14-alpha")
public abstract class MixinModelTextureBakery implements IVoxyModelController {

    @Shadow
    @Final
    private ReuseVertexConsumer translucentVC;

    @Shadow
    @Final
    private ReuseVertexConsumer opaqueVC;

    @ModifyExpressionValue(
            remap = false,
            method = "bakeBlockModel",
            at = @At(value = "INVOKE", target = "Ljava/util/Iterator;hasNext()Z")
    )
    private boolean eclipticseasons$bakeBlockModel_pre(boolean original, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) RenderType layer) {
        if (!original && isSnowyBlock())
            VoxyClientTool.renderToStream(state, layer, translucentVC, opaqueVC);
        return original;
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
