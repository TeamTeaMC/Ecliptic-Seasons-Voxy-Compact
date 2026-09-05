package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy.neovoxy;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.teamtea.eclipticseasons.common.mixin.condition.ConditionalMixin;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.helper.IVoxyModelController;
import me.cortex.voxy.client.core.model.ModelFactory;
import me.cortex.voxy.client.core.model.bakery.SoftwareModelTextureBakery;
import net.minecraft.client.color.block.BlockColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ModelFactory.class, remap = false)
@ConditionalMixin(value = "voxy", version = "0.2.14-alpha", name = "neo-voxy")
public abstract class MixinModelFactory_Neo_Patch {
    @Shadow
    @Final
    public SoftwareModelTextureBakery bakery2;

    @ModifyExpressionValue(
            remap = false,
            method = "processTextureBakeResult",
            at = @At(value = "INVOKE", target = "Lme/cortex/voxy/client/core/model/ModelFactory;getColourProvider(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/color/block/BlockColor;")
    )
    private BlockColor eclipticseasons$processTextureBakeResult_fix_snow_leaves(BlockColor original) {
        if (bakery2 instanceof IVoxyModelController modelController && modelController.isSnowyBlock()) {
            original = null;
        }
        return original;
    }
}
