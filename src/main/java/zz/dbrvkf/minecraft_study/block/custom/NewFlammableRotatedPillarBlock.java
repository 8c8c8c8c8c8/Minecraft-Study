package zz.dbrvkf.minecraft_study.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;
import zz.dbrvkf.minecraft_study.block.NewBlocks;

public class NewFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public NewFlammableRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context,
                                                     ToolAction toolAction, boolean simulate) {
        if (!(context.getItemInHand().getItem() instanceof AxeItem))
            return super.getToolModifiedState(state, context, toolAction, simulate);

        if (state.is(NewBlocks.PINE_LOG.get()))
            return NewBlocks.STRIPPED_PINE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

        if (state.is(NewBlocks.PINE_WOOD.get()))
            return NewBlocks.STRIPPED_PINE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
