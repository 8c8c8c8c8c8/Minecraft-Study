package zz.dbrvkf.minecraft_study.world.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import zz.dbrvkf.minecraft_study.world.tree.NewTrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class PineTrunkPlacer extends TrunkPlacer {
    public static final Codec<PineTrunkPlacer> CODEC =
            RecordCodecBuilder.create(instance
                    -> trunkPlacerParts(instance).apply(instance, PineTrunkPlacer::new));
    private static float BRANCH_CHANCE = 0.25f;
    private static int BRANCH_LENGTH = 4;
    private static List<Direction> DIRECTIONS = List.of(Direction.NORTH, Direction.WEST, Direction.EAST, Direction.SOUTH);

    public PineTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return NewTrunkPlacerType.PINE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel,
                                                            BiConsumer<BlockPos, BlockState> pBlockSetter,
                                                            RandomSource pRandom,
                                                            int pFreeTreeHeight,
                                                            BlockPos pPos,
                                                            TreeConfiguration pConfig) {
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        int height = pFreeTreeHeight + pRandom.nextInt(heightRandA, heightRandA + 3) + pRandom.nextInt(heightRandB - 1, heightRandB + 1);
        for (int i = 0; i < height; i++) {
            BlockPos currentPos = pPos.above(i);
            placeLog(pLevel, pBlockSetter, pRandom, currentPos, pConfig);

            if (i % 2 != 0 || pRandom.nextBoolean()) continue;

            DIRECTIONS.forEach(direction ->
                    placeSingleTrunk(pBlockSetter, currentPos, pPos, pConfig, pRandom, direction));
        }
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(height), 0, false));
    }

    private void placeSingleTrunk(BiConsumer<BlockPos, BlockState> pBlockSetter,
                                  BlockPos currentPos, BlockPos beforePos, TreeConfiguration config,
                                  RandomSource random, Direction direction) {
        if (!canPlaceSingleTrunk(random)) return;

        Direction.Axis axis = switch (direction) {
            case EAST, WEST -> Direction.Axis.X;
            default -> Direction.Axis.Z;
        };
        BlockState blockState = config.trunkProvider.getState(random, beforePos)
                .setValue(RotatedPillarBlock.AXIS, axis);
        for (int j = 0; j < BRANCH_LENGTH; j++) {
            pBlockSetter.accept(currentPos.relative(direction, j), blockState);
        }
    }

    private boolean canPlaceSingleTrunk(RandomSource random) {
        return random.nextFloat() < BRANCH_CHANCE;
    }
}
