package zz.dbrvkf.minecraft_study.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiceBlock extends Block {
    private static final List<Direction> DIRECTIONS = List.of(Direction.UP, Direction.NORTH, Direction.EAST,
            Direction.SOUTH, Direction.WEST, Direction.DOWN);
    public static final DirectionProperty FACING = DirectionProperty.create("number", DIRECTIONS);

    public DiceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, getRandomDirection());
    }

    public BlockState getRandomBlockState() {
        return defaultBlockState().setValue(FACING, getRandomDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    private Direction getRandomDirection() {
        int rand = RandomSource.create().nextIntBetweenInclusive(0, DIRECTIONS.size() - 1);
        return DIRECTIONS.get(rand);
    }
}
