package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy;

import com.teamtea.eclipticseasons.common.mixin.condition.ConditionalMixin;
import com.teamtea.eclipticseasons.common.mixin.condition.ModCondition;
import me.cortex.voxy.client.core.model.ModelFactory;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(ModelFactory.class)
@ConditionalMixin(value = "voxy", version = "0.2.14-alpha")
public interface ModelFactoryInvoker {

    @Invoker(remap = false, value = "getColourProvider")
    static BlockColor eclipticseasons$getTintSources(Block block) {
        throw new AssertionError();
    }

    @Invoker(remap = false, value = "captureColourConstant")
    static int eclipticseasons$captureColourConstant(
            BlockColor colorProvider,
            BlockState state,
            Biome biome
    ) {
        throw new AssertionError();
    }
}
