package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy.roxy;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.teamtea.eclipticseasons.common.mixin.condition.ConditionalMixin;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.client.VoxyClientTool;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.rasanovum.roxy.bridge.RoxyModelTintBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.Map;


@Mixin(RoxyModelTintBridge.class)
@ConditionalMixin("roxy")
public abstract class MixinRoxyModelTintBridge {

    // @ModifyExpressionValue(
    //         remap = false,
    //         method = "metadata",
    //         at = @At(
    //                 value = "INVOKE",
    //                 target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
    //         )
    // )
    // private static <V> V eclipticseasons$applySeasonalBiomec(V original){
    //     return original;
    // }
    // @Inject(
    //         remap = false,
    //         method = "inspectTint",
    //         at = @At(
    //                 value = "NEW",
    //                 target = "(IZ)Lnet/rasanovum/roxy/bridge/RoxyModelTintBridge$Tint;"
    //         )
    // )
    // private static <V> void eclipticseasons$applySeasonalBiome(
    //         Object state, int index, CallbackInfoReturnable<?> cir,
    //         @Local(name = "dependent") boolean[] bl,
    //         @Local(name = "state") Object state0,
    //         @Local(name = "colours") Object colours) {
    //     if (state0 instanceof BlockState state1) {
    //         try {
    //             Field field = BlockColors.class.getDeclaredField("blockColors");
    //             field.setAccessible(true);
    //
    //             @SuppressWarnings("unchecked")
    //             Map<Block, BlockColor> blockColors = (Map<Block, BlockColor>) field.get(colours);
    //             bl[0] |= VoxyClientTool.isExtendBiomeDependentColour(state1, blockColors.get(state1.getBlock()));
    //
    //         } catch (NoSuchFieldException | IllegalAccessException ignored) {
    //         }
    //
    //     }
    // }
}
