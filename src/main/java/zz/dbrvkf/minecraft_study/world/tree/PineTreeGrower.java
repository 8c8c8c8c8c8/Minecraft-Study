package zz.dbrvkf.minecraft_study.world.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import zz.dbrvkf.minecraft_study.world.NewConfiguredFeatures;

public class PineTreeGrower extends AbstractTreeGrower {

    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return NewConfiguredFeatures.PINE_KEY;
    }
}
