package com.teamtea.eclipticseasons_voxycompact.mixin.compat.voxy.neovoxy;

import com.teamtea.eclipticseasons.common.mixin.condition.ConditionalMixin;
import me.cortex.voxy.client.core.model.ModelFactory;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ModelFactory.class)
@ConditionalMixin(value = "voxy", version = "0.2.14-alpha", name = "neo-voxy")
public interface ModelFactoryInvoker_Neo {

    @Invoker("getColourProvider")
    static BlockColor eclipticseasons$getTintSources(BlockState state) {
        throw new AssertionError();
    }

    @Invoker("captureColourConstant")
    static int eclipticseasons$captureColourConstant(
            BlockColor colorProvider,
            BlockState state,
            Biome biome
    ) {
        throw new AssertionError();
    }
}
