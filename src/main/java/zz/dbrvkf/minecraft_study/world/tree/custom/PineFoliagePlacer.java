package zz.dbrvkf.minecraft_study.world.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import zz.dbrvkf.minecraft_study.world.tree.NewFoliagePlacerType;

public class PineFoliagePlacer extends FoliagePlacer {
    private final int height;
    public static final Codec<PineFoliagePlacer> CODEC =
            RecordCodecBuilder.create(
                    instance -> foliagePlacerParts(instance)
                            .and(Codec.intRange(0, 16)
                                    .fieldOf("height")
                                    .forGetter(fp -> fp.height))
                            .apply(instance, PineFoliagePlacer::new));

    public PineFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return NewFoliagePlacerType.PINE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel,
                                 FoliageSetter pBlockSetter,
                                 RandomSource pRandom,
                                 TreeConfiguration pConfig,
                                 int pMaxFreeTreeHeight,
                                 FoliageAttachment pAttachment,
                                 int pFoliageHeight, int pFoliageRadius, int pOffset) {
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig,
                pAttachment.pos().above(0), 2, 0, pAttachment.doubleTrunk());
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig,
                pAttachment.pos().above(1), 2, 0, pAttachment.doubleTrunk());
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig,
                pAttachment.pos().above(2), 2, 0, pAttachment.doubleTrunk());
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ,
                                         int pRange, boolean pLarge) {
        return false;
    }
}
