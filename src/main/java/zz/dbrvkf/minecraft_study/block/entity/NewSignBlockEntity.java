package zz.dbrvkf.minecraft_study.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NewSignBlockEntity extends SignBlockEntity {

    public NewSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(NewBlockEntities.SIGNS.get(), pPos, pBlockState);
    }
}
