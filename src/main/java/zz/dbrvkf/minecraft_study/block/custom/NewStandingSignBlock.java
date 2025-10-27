package zz.dbrvkf.minecraft_study.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import zz.dbrvkf.minecraft_study.block.entity.NewBlockEntities;

public class NewStandingSignBlock extends StandingSignBlock {
    public NewStandingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return NewBlockEntities.SIGNS.get().create(pPos, pState);
    }
}
