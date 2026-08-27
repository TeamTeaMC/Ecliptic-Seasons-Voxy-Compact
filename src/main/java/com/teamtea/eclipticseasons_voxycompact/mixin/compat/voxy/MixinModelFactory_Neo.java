package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.teamtea.eclipticseasons.EclipticSeasons;
import com.teamtea.eclipticseasons.common.mixin.condition.ConditionalMixin;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.VoxyTool;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.client.IVoxyModelFactory;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.helper.IVoxyModelController;
import me.cortex.voxy.client.core.gl.GlBuffer;
import me.cortex.voxy.client.core.model.ModelFactory;
import me.cortex.voxy.client.core.model.ModelStore;
import me.cortex.voxy.client.core.model.bakery.SoftwareModelTextureBakery;
import me.cortex.voxy.client.core.rendering.util.UploadStream;
import me.cortex.voxy.common.util.MemoryBuffer;
import me.cortex.voxy.common.util.Pair;
import me.cortex.voxy.common.world.other.Mapper;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Mixin(value = ModelFactory.class, remap = false)
@ConditionalMixin(value = "voxy", version = "0.2.14-alpha", name = "Neo Voxy")
public abstract class MixinModelFactory_Neo {
    @Shadow
    @Final
    public SoftwareModelTextureBakery bakery2;

    @ModifyExpressionValue(
            remap = false,
            method = "processTextureBakeResult",
            at = @At(value = "INVOKE", target = "Lme/cortex/voxy/client/core/model/ModelFactory;getColourProvider(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/client/color/block/BlockColor;")
    )
    private BlockColor eclipticseasons$processTextureBakeResult_fix_snow_leaves(BlockColor original) {
        if (bakery2 instanceof IVoxyModelController modelController && modelController.isSnowyBlock()) {
            original = null;
        }
        return original;
    }
}
