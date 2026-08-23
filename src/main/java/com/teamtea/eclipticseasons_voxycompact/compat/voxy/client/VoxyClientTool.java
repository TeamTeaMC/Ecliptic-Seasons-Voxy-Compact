package com.teamtea.eclipticseasons_voxycompact.compat.voxy.client;

import com.teamtea.eclipticseasons.client.core.ExtraModelManager;
import com.teamtea.eclipticseasons.client.core.ExtraRendererContext;
import com.teamtea.eclipticseasons.client.util.ClientCon;
import com.teamtea.eclipticseasons.common.core.map.MapChecker;
import com.teamtea.eclipticseasons_voxycompact.compat.CompatModule;
import com.teamtea.eclipticseasons_voxycompact.compat.voxy.VoxyTool;
import me.cortex.voxy.client.core.model.bakery.ReuseVertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SingleThreadedRandomSource;

import java.util.ArrayList;
import java.util.List;

public class VoxyClientTool {

    public static void forceReloadAll() {
        if (!VoxyTool.isVoxyTest()
                || !ClientCon.getAgent().isChange()
                || !CompatModule.CommonConfig.voxyLODAutoReload.get()) return;

        ClientLevel level = Minecraft.getInstance().level;
        if (level == null || level.getGameTime() % (20 * 15) != 0) return;

        if( ClientCon.getAgent().isSnowChange()){
            ClientCon.getAgent().setSnowChange(false);
            VoxyGeometryRefreshManager.refreshAll();
        }
    }

    public static void renderToStream(BlockState state, RenderType layer, ReuseVertexConsumer translucentVC, ReuseVertexConsumer opaqueVC) {
        if (!VoxyTool.isVoxyTest()) return;

        if (state.getRenderShape() != RenderShape.INVISIBLE) {
            int defaultBlockTypeFlag = MapChecker.getDefaultBlockTypeFlag(state);
            BakedModel model = ExtraModelManager.getSnowyModel(state, null, defaultBlockTypeFlag, MapChecker.getSnowOffset(state, defaultBlockTypeFlag));
            if (model == null) {
                return;
            }
            ExtraRendererContext context = new ExtraRendererContext();
            context.setReplace(ExtraModelManager.isModelReplaceable(state, ClientCon.getUseLevel(), BlockPos.ZERO, model))
                    .setExtraModel(model)
                    .setOriginalModel(Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(state))
            ;

            RenderType type = state.getBlock() instanceof LeavesBlock ?
                    layer :
                    ExtraModelManager.getRenderType(state);
            for (Direction direction : new Direction[]{Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, null}) {
                SingleThreadedRandomSource randomSource = new SingleThreadedRandomSource(42L);
                for (BakedQuad quad :
                        ExtraModelManager.cancelTop(context, model, ClientCon.getUseLevel(),
                                state, BlockPos.ZERO, direction,
                                randomSource, 42L,
                                model.getQuads(state, direction, randomSource),
                                List.of())) {
                    (type == RenderType.translucent() ? translucentVC : opaqueVC).quad(quad, state.is(BlockTags.LEAVES), layer);
                }
            }
        }
    }


}
