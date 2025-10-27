package zz.dbrvkf.minecraft_study.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NewHangingSignBlockEntity extends SignBlockEntity {

    public NewHangingSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(NewBlockEntities.HANGING_SIGNS.get(), pPos, pBlockState);
    }

}
