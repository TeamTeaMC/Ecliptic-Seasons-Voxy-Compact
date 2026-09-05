package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.teamtea.eclipticseasons.api.data.client.model.ModelTester;
import com.teamtea.eclipticseasons.client.core.ExtraModelManager;
import com.teamtea.eclipticseasons.client.lod.SeasonalModelEntry;
import com.teamtea.eclipticseasons.common.mixin.condition.ConditionalMixin;
import com.teamtea.eclipticseasons.common.mixin.condition.ModCondition;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.client.VoxyClientTool;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.helper.IVoxyModelController;
import me.cortex.voxy.client.core.model.bakery.ReuseVertexConsumer;
import me.cortex.voxy.client.core.model.bakery.SoftwareModelTextureBakery;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SingleThreadedRandomSource;
import net.neoforged.neoforge.common.util.ConcatenatedListView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin({SoftwareModelTextureBakery.class})
@ConditionalMixin(value = "voxy", version = "0.2.14-alpha", noneOf = {@ModCondition(value = "roxy")})
public abstract class MixinModelTextureBakery implements IVoxyModelController {

    @Shadow
    @Final
    private ReuseVertexConsumer translucentVC;

    @Shadow
    @Final
    private ReuseVertexConsumer opaqueVC;

    @ModifyExpressionValue(
            require = 0,
            method = "bakeBlockModel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/BakedModel;getQuads(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/util/RandomSource;)Ljava/util/List;")
    )
    private List<BakedQuad> eclipticseasons$collectSeasonalParts(
            List<BakedQuad> original,
            @Local BlockState state,
            @Local Direction direction
    ) {
        return eclipticseasons$getBakedQuads(original, state, direction);
    }

    @ModifyExpressionValue(
            require = 0,
            method = "bakeBlockModel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/BakedModel;getQuads(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/util/RandomSource;Lnet/neoforged/neoforge/client/model/data/ModelData;Lnet/minecraft/client/renderer/RenderType;)Ljava/util/List;")
    )
    private List<BakedQuad> eclipticseasons$collectSeasonalParts_neo(
            List<BakedQuad> original,
            @Local(ordinal = 1) BlockState state,
            @Local Direction direction
    ) {
        return eclipticseasons$getBakedQuads(original, state, direction);
    }

    @Unique
    private List<BakedQuad> eclipticseasons$getBakedQuads(List<BakedQuad> original, BlockState state, Direction direction) {
        SeasonalModelEntry entry = getSeasonalModel();

        if (entry == null) {
            return original;
        }

        ModelTester modelTester = ExtraModelManager.getSeasonalModel(state, entry.modelIdentifier());

        BakedModel seasonalModel = modelTester == null ? null
                : ExtraModelManager.getExtraModel(modelTester.modelResourceLocation());

        // if not found model
        if (seasonalModel == null) {
            return original;
        }
        List<BakedQuad> seasonalModelQuads = seasonalModel.getQuads(state, direction, new SingleThreadedRandomSource(42L));
        return modelTester.replace() ?
                ConcatenatedListView.of(seasonalModelQuads) :
                ConcatenatedListView.of(original, seasonalModelQuads);
    }

    @ModifyExpressionValue(
            remap = false,
            method = "bakeBlockModel",
            at = @At(value = "INVOKE", target = "Ljava/util/Iterator;hasNext()Z")
    )
    private boolean eclipticseasons$bakeBlockModel_pre(boolean original, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) RenderType layer, @Local Direction direction) {
        if (!original && direction == null && isSnowyBlock())
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

    @Unique
    @Nullable
    private SeasonalModelEntry eclipticseasons$seasonalModel;

    @Override
    public void setSeasonalModel(@Nullable SeasonalModelEntry seasonalModel) {
        this.eclipticseasons$seasonalModel = seasonalModel;
    }

    @Override
    public @Nullable SeasonalModelEntry getSeasonalModel() {
        return eclipticseasons$seasonalModel;
    }
}
