package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy.roxy;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SingleThreadedRandomSource;
import net.neoforged.neoforge.common.util.ConcatenatedListView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin({SoftwareModelTextureBakery.class})
@ConditionalMixin(allOf = {
        @ModCondition(value = "voxy", version = "0.2.14-alpha"),
        @ModCondition(value = "roxy")
})
public abstract class MixinModelTextureBakery_Roxy implements IVoxyModelController {

    @Shadow(remap = false)
    @Final
    private ReuseVertexConsumer vc;

    @ModifyExpressionValue(
            method = "bakeBlockModel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/BakedModel;getQuads(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/util/RandomSource;)Ljava/util/List;")
    )
    private List<BakedQuad> eclipticseasons$collectSeasonalParts(
            List<BakedQuad> original,
            @Local BlockState state,
            @Local Direction[] directions,
            @Local(ordinal = 1) int di
    ) {
        return eclipticseasons$getBakedQuads(original, state, directions[di]);
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
